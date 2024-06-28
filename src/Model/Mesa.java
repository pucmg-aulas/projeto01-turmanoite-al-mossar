package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mesa implements Serializable {
    private int numero;
    private int capacidade;
    private List<Pedido> pedidos;
    private Cliente cliente;

    public Mesa(int numero, int capacidade) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.pedidos = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void adicionarCliente(Cliente cliente) {
        if (this.cliente == null) {
            this.cliente = cliente;
            cliente.setMesa(this);
        } else {
            throw new RuntimeException("Mesa ja esta ocupada");
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean isDisponivel() {
        return cliente == null;
    }

    public Historico liberarMesa() {
        if (this.cliente != null) {
            this.cliente.setHoraSaida();
            Historico historico = new Historico(
                cliente.getNome(),
                cliente.getNumPessoas(),
                numero,
                cliente.getHoraEntrada(),
                cliente.getHoraSaida(),
                listarComandas() // Incluindo a comanda no histórico
            );
            this.cliente = null;
            this.pedidos.clear();
            return historico;
        }
        return null;
    }

    @Override
    public String toString() {
        String status = isDisponivel() ? "disponivel" : "ocupada";
        String clienteInfo = isDisponivel() ? "" : ", " + cliente.toString();
        return "Mesa " + numero + " com capacidade para " + capacidade + " pessoas: " + status + clienteInfo;
    }

    public String listarComandas() {
        if (isDisponivel()) {
            return "Mesa " + numero + " esta disponivel.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Mesa ").append(numero).append(" - Comandas:\n");
        for (Pedido pedido : pedidos) {
            sb.append(pedido.toString()).append("\n");
        }
        sb.append("Total: R$").append(calcularTotal()).append("\n");
        return sb.toString();
    }

    public double calcularTotal() {
        return pedidos.stream()
                .flatMap(p -> p.getItensPedido().stream())
                .mapToDouble(ItemPedido::getPrecoTotal)
                .sum();
    }

    public double calcularTotalComServico() {
        double total = calcularTotal();
        double taxaServico = total * 0.10;
        return total + taxaServico;
    }

    public double calcularDivisaoPorPessoa() {
        return calcularTotalComServico() / cliente.getNumPessoas();
    }
}
