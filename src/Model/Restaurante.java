package Model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Restaurante implements Serializable {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private List<Mesa> mesas;
    private Cardapio cardapio;
    private FilaDeEspera filaDeEspera;
    private List<Historico> historico;
    private List<PedidoDelivery> pedidosDelivery;
    private double vendasDiarias;
    private Map<LocalDate, List<ValorReceber>> valoresAReceberPorData;

    public Restaurante() {
        this.mesas = new ArrayList<>();
        this.cardapio = new Cardapio();
        this.filaDeEspera = new FilaDeEspera();
        this.historico = new ArrayList<>();
        this.pedidosDelivery = new ArrayList<>();
        this.vendasDiarias = 0;
        this.valoresAReceberPorData = new HashMap<>();
        inicializarMesas();
    }

    private void inicializarMesas() {
        for (int i = 1; i <= 4; i++) {
            mesas.add(new Mesa(i, 4));
        }
        for (int i = 5; i <= 8; i++) {
            mesas.add(new Mesa(i, 6));
        }
        for (int i = 9; i <= 10; i++) {
            mesas.add(new Mesa(i, 8));
        }
    }

    public boolean adicionarCliente(Cliente cliente) {
        Optional<Mesa> mesaOpt = mesas.stream()
                .filter(mesa -> mesa.getCapacidade() >= cliente.getNumPessoas() && mesa.isDisponivel())
                .findFirst();

        if (mesaOpt.isPresent()) {
            Mesa mesa = mesaOpt.get();
            mesa.adicionarCliente(cliente);
            return true; // Mesa disponível e cliente adicionado
        } else {
            filaDeEspera.adicionarCliente(cliente);
            return false; // Cliente adicionado à fila de espera
        }
    }

    public void listarMesas() {
        for (Mesa mesa : mesas) {
            System.out.println(mesa);
        }
    }

    public void listarComandas() {
        for (Mesa mesa : mesas) {
            System.out.println(mesa.listarComandas());
        }
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void atenderFilaDeEspera() {
        while (!filaDeEspera.isEmpty()) {
            Cliente cliente = filaDeEspera.proximoCliente();
            Optional<Mesa> mesaOpt = mesas.stream()
                .filter(mesa -> mesa.getCapacidade() >= cliente.getNumPessoas() && mesa.isDisponivel())
                .findFirst();
            if (mesaOpt.isPresent()) {
                Mesa mesa = mesaOpt.get();
                mesa.adicionarCliente(cliente);
            } else {
                filaDeEspera.adicionarCliente(cliente);
                break;
            }
        }
    }

    public void liberarMesa(int numeroMesa) {
        Mesa mesa = getMesa(numeroMesa);
        if (mesa != null) {
            if (!mesa.getPedidos().isEmpty()) {
                throw new RuntimeException("A mesa possui pedidos nao encerrados. Encerrar a conta antes de liberar a mesa.");
            }
            Historico historico = mesa.liberarMesa();
            if (historico != null) {
                this.historico.add(historico);
            }
            atenderFilaDeEspera();
        } else {
            throw new RuntimeException("Mesa nao encontrada.");
        }
    }

    private Mesa getMesa(int numeroMesa) {
        return mesas.stream().filter(m -> m.getNumero() == numeroMesa).findFirst().orElse(null);
    }

    public List<Historico> getHistorico() {
        return historico;
    }

    public void listarHistorico() {
        historico.forEach(System.out::println);
    }

    public List<Historico> buscarPorData(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return historico.stream()
                .filter(h -> !h.getHoraEntrada().isBefore(dataInicial) && !h.getHoraEntrada().isAfter(dataFinal))
                .collect(Collectors.toList());
    }

    public String encerrarConta(int numeroMesa, String metodoPagamento) {
        Mesa mesa = getMesa(numeroMesa);
        if (mesa != null && !mesa.isDisponivel()) {
            double totalComServico = mesa.calcularTotalComServico();
            double desconto = 0;
            int prazoRecebimento = 0;

            switch (metodoPagamento.toLowerCase()) {
                case "dinheiro":
                    desconto = 0;
                    prazoRecebimento = 0;
                    break;
                case "pix":
                    desconto = Math.min(totalComServico * 0.0145, 10);
                    prazoRecebimento = 0;
                    break;
                case "debito":
                    desconto = totalComServico * 0.014;
                    prazoRecebimento = 14;
                    break;
                case "credito":
                    desconto = totalComServico * 0.031;
                    prazoRecebimento = 30;
                    break;
                default:
                    throw new IllegalArgumentException("Método de pagamento inválido");
            }

            double totalAReceber = totalComServico - desconto;
            vendasDiarias += totalAReceber;

            LocalDate dataRecebimento = LocalDate.now().plusDays(prazoRecebimento);
            ValorReceber valorReceber = new ValorReceber(totalAReceber, metodoPagamento);

            valoresAReceberPorData.putIfAbsent(dataRecebimento, new ArrayList<>());
            valoresAReceberPorData.get(dataRecebimento).add(valorReceber);

            String contaInfo = "Conta encerrada para a mesa " + numeroMesa + ".\n" +
                               "Total com serviço: R$" + Utils.formatarValor(totalComServico) + "\n" +
                               "Desconto: R$" + Utils.formatarValor(desconto) + "\n" +
                               "Total a receber: R$" + Utils.formatarValor(totalAReceber) + "\n" +
                               "Prazo para recebimento: " + prazoRecebimento + " dias\n" +
                               "Data para recebimento: " + dataRecebimento;

            Historico historico = mesa.liberarMesa();
            if (historico != null) {
                this.historico.add(historico);
            }

            return contaInfo;
        } else {
            throw new RuntimeException("Mesa nao encontrada ou já está disponível.");
        }
    }

    public void adicionarPedidoDelivery(PedidoDelivery pedido) {
        pedidosDelivery.add(pedido);
    }

    public List<PedidoDelivery> getPedidosDelivery() {
        return pedidosDelivery;
    }

    public double calcularTaxaEntrega(double distancia) {
        return distancia * 1; // Taxa de entrega de R$1 por km
    }

    public double getVendasDiarias() {
        return vendasDiarias;
    }

    public Map<LocalDate, List<ValorReceber>> getValoresAReceberPorData() {
        return valoresAReceberPorData;
    }

    public FilaDeEspera getFilaDeEspera() {
        return filaDeEspera;
    }

    public static class ValorReceber implements Serializable {
        private double valor;
        private String metodoPagamento;

        public ValorReceber(double valor, String metodoPagamento) {
            this.valor = valor;
            this.metodoPagamento = metodoPagamento;
        }

        public double getValor() {
            return valor;
        }

        public String getMetodoPagamento() {
            return metodoPagamento;
        }

        @Override
        public String toString() {
            return "R$" + Utils.formatarValor(valor) + " (" + metodoPagamento + ")";
        }
    }
}
