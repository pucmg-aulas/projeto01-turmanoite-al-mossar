package Controller;

import Model.*;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class MainController {
    private Restaurante restaurante;
    private MainView mainView;

    public MainController(Restaurante restaurante, MainView mainView) {
        this.restaurante = restaurante;
        this.mainView = mainView;

        this.mainView.addClienteButtonListener(new AddClienteListener());
        this.mainView.addPedidoButtonListener(new AddPedidoListener());
        this.mainView.listMesasButtonListener(new ListMesasListener());
        this.mainView.listComandasButtonListener(new ListComandasListener());
        this.mainView.sairRestauranteButtonListener(new SairRestauranteListener());
        this.mainView.listHistoricoButtonListener(new ListHistoricoListener());
        this.mainView.buscarPorDataButtonListener(new BuscarPorDataListener());
        this.mainView.encerrarContaButtonListener(new EncerrarContaListener());
        this.mainView.mostrarVendasButtonListener(new MostrarVendasListener());
        this.mainView.pedidoDeliveryButtonListener(new PedidoDeliveryListener());
        this.mainView.listarPedidosDeliveryButtonListener(new ListarPedidosDeliveryListener());
        this.mainView.mostrarFilaDeEsperaButtonListener(new MostrarFilaDeEsperaListener());
    }

    class AddClienteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:");
            String numPessoasStr = JOptionPane.showInputDialog("Quantas pessoas vao comer?");
            int numPessoas = Integer.parseInt(numPessoasStr);
            Cliente cliente = new Cliente(nomeCliente, numPessoas);

            boolean mesaDisponivel = restaurante.adicionarCliente(cliente);

            if (mesaDisponivel) {
                JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nao ha mesas disponíveis para " + numPessoas + " pessoas. Cliente adicionado a fila de espera.");
            }
        }
    }

    class AddPedidoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String numeroMesaPedidoStr = JOptionPane.showInputDialog("Digite o numero da mesa:");
            int numeroMesaPedido = Integer.parseInt(numeroMesaPedidoStr);
            Mesa mesaPedido = restaurante.getMesas().stream()
                .filter(mesa -> mesa.getNumero() == numeroMesaPedido)
                .findFirst()
                .orElse(null);
            if (mesaPedido != null && !mesaPedido.isDisponivel()) {
                StringBuilder itensCardapio = new StringBuilder("Itens disponíveis no cardapio:\n");
                itensCardapio.append(restaurante.getCardapio().listarItens());
                JOptionPane.showMessageDialog(null, itensCardapio.toString(), "Cardápio", JOptionPane.INFORMATION_MESSAGE);

                String itemNome = JOptionPane.showInputDialog("Digite o nome do item do pedido:");
                ItemMenu item = restaurante.getCardapio().getItens().stream()
                    .filter(i -> i.getNome().equalsIgnoreCase(itemNome))
                    .findFirst()
                    .orElse(null);
                if (item != null) {
                    String quantidadeStr = JOptionPane.showInputDialog("Digite a quantidade do item:");
                    int quantidade = Integer.parseInt(quantidadeStr);
                    Pedido pedido = new Pedido();
                    pedido.adicionarItem(item, quantidade);
                    mesaPedido.adicionarPedido(pedido);
                    JOptionPane.showMessageDialog(null, "Pedido adicionado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Item nao encontrado no cardapio.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mesa nao encontrada ou está disponível.");
            }
        }
    }

    class ListMesasListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StringBuilder mesasStr = new StringBuilder();
            for (Mesa mesa : restaurante.getMesas()) {
                mesasStr.append(mesa.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, mesasStr.toString(), "Mesas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class ListComandasListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StringBuilder comandasStr = new StringBuilder();
            for (Mesa mesa : restaurante.getMesas()) {
                comandasStr.append(mesa.listarComandas()).append("\n");
            }
            JOptionPane.showMessageDialog(null, comandasStr.toString(), "Comandas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class SairRestauranteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String numeroMesaStr = JOptionPane.showInputDialog("Digite o número da mesa que deseja liberar:");
            int numeroMesa = Integer.parseInt(numeroMesaStr);
            try {
                restaurante.liberarMesa(numeroMesa);
                JOptionPane.showMessageDialog(null, "Mesa liberada com sucesso!");
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    class ListHistoricoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StringBuilder historicoStr = new StringBuilder();
            for (Historico historico : restaurante.getHistorico()) {
                historicoStr.append(historico.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, historicoStr.toString(), "Histórico", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class BuscarPorDataListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String dataInicialStr = JOptionPane.showInputDialog("Digite a data inicial (dd/MM/yyyy HH:mm):");
            LocalDateTime dataInicial = LocalDateTime.parse(dataInicialStr, formatter);

            String dataFinalStr = JOptionPane.showInputDialog("Digite a data final (dd/MM/yyyy HH:mm):");
            LocalDateTime dataFinal = LocalDateTime.parse(dataFinalStr, formatter);

            List<Historico> resultados = restaurante.buscarPorData(dataInicial, dataFinal);
            StringBuilder resultadosStr = new StringBuilder();
            resultados.forEach(h -> resultadosStr.append(h.toString()).append("\n"));

            JOptionPane.showMessageDialog(null, resultadosStr.toString(), "Resultados da Busca", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class EncerrarContaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String numeroMesaStr = JOptionPane.showInputDialog("Digite o número da mesa:");
            int numeroMesa = Integer.parseInt(numeroMesaStr);
            String metodoPagamento = JOptionPane.showInputDialog("Digite o método de pagamento (dinheiro, pix, debito, credito):");

            try {
                String contaInfo = restaurante.encerrarConta(numeroMesa, metodoPagamento);
                JOptionPane.showMessageDialog(null, contaInfo, "Conta Encerrada", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Metodo de pagamento inválido.");
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    class PedidoDeliveryListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:");
            String distanciaStr = JOptionPane.showInputDialog("Digite a distancia em km para entrega:");
            double distancia = Double.parseDouble(distanciaStr);

            PedidoDelivery pedidoDelivery = new PedidoDelivery(distancia, nomeCliente);

            boolean adicionarMaisItens;
            do {
                StringBuilder itensCardapio = new StringBuilder("Itens disponíveis no cardápio:\n");
                itensCardapio.append(restaurante.getCardapio().listarItens());
                JOptionPane.showMessageDialog(null, itensCardapio.toString(), "Cardapio", JOptionPane.INFORMATION_MESSAGE);

                String itemNome = JOptionPane.showInputDialog("Digite o nome do item do pedido:");
                ItemMenu item = restaurante.getCardapio().getItens().stream()
                    .filter(i -> i.getNome().equalsIgnoreCase(itemNome))
                    .findFirst()
                    .orElse(null);
                if (item != null) {
                    String quantidadeStr = JOptionPane.showInputDialog("Digite a quantidade do item:");
                    int quantidade = Integer.parseInt(quantidadeStr);
                    pedidoDelivery.adicionarItem(item, quantidade);
                } else {
                    JOptionPane.showMessageDialog(null, "Item nao encontrado no cardapio.");
                }

                int continuar = JOptionPane.showConfirmDialog(null, "Deseja adicionar mais itens?", "Adicionar Itens", JOptionPane.YES_NO_OPTION);
                adicionarMaisItens = (continuar == JOptionPane.YES_OPTION);
            } while (adicionarMaisItens);

            restaurante.adicionarPedidoDelivery(pedidoDelivery);
            JOptionPane.showMessageDialog(null, "Pedido de delivery adicionado com sucesso! Taxa de entrega: R$" + Utils.formatarValor(pedidoDelivery.getTaxaEntrega()));
        }
    }

    class ListarPedidosDeliveryListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Map<String, List<PedidoDelivery>> pedidosPorData = restaurante.getPedidosDelivery().stream()
                .collect(Collectors.groupingBy(PedidoDelivery::getDataFormatada));

            StringBuilder pedidosDeliveryStr = new StringBuilder();
            int pedidoNumero = 1;
            for (Map.Entry<String, List<PedidoDelivery>> entry : pedidosPorData.entrySet()) {
                pedidosDeliveryStr.append(entry.getKey()).append(":\n");
                for (PedidoDelivery pedido : entry.getValue()) {
                    pedidosDeliveryStr.append("#").append(String.format("%03d", pedidoNumero)).append("\n");
                    pedidosDeliveryStr.append(pedido.toString()).append("\n");
                    pedidoNumero++;
                }
            }
            JOptionPane.showMessageDialog(null, pedidosDeliveryStr.toString(), "Pedidos Delivery", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class MostrarVendasListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String vendasDiarias = "Vendas diárias: R$" + Utils.formatarValor(restaurante.getVendasDiarias());
            StringBuilder valoresAReceberStr = new StringBuilder("Valores a receber:\n");

            for (Map.Entry<LocalDate, List<Restaurante.ValorReceber>> entry : restaurante.getValoresAReceberPorData().entrySet()) {
                valoresAReceberStr.append("Data: ").append(entry.getKey()).append(" - ");
                for (Restaurante.ValorReceber valor : entry.getValue()) {
                    valoresAReceberStr.append("R$").append(Utils.formatarValor(valor.getValor()))
                                      .append(" (").append(valor.getMetodoPagamento()).append("), ");
                }
                valoresAReceberStr.setLength(valoresAReceberStr.length() - 2); // remove the last comma and space
                valoresAReceberStr.append("\n");
            }

            JOptionPane.showMessageDialog(null, vendasDiarias + "\n" + valoresAReceberStr.toString(), "Vendas e Valores a Receber", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class MostrarFilaDeEsperaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StringBuilder filaDeEsperaStr = new StringBuilder();
            for (Cliente cliente : restaurante.getFilaDeEspera().getFila()) {
                filaDeEsperaStr.append(cliente.toString()).append("\n");
            }
            mainView.atualizarFilaDeEspera(filaDeEsperaStr.toString());
        }
    }
}
