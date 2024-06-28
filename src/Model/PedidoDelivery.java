package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PedidoDelivery implements Serializable {
    private List<ItemPedido> itens;
    private double distancia;
    private double taxaEntrega;
    private LocalDateTime dataHora;
    private String nomeCliente;

    public PedidoDelivery(double distancia, String nomeCliente) {
        this.itens = new ArrayList<>();
        this.distancia = distancia;
        this.taxaEntrega = distancia * 1; // Taxa de entrega de R$1 por km
        this.dataHora = LocalDateTime.now();
        this.nomeCliente = nomeCliente;
    }

    public void adicionarItem(ItemMenu item, int quantidade) {
        itens.add(new ItemPedido(item, quantidade));
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public double getDistancia() {
        return distancia;
    }

    public double getTaxaEntrega() {
        return taxaEntrega;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataHora.format(formatter);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido Delivery:\n");
        sb.append("Nome do Cliente: ").append(nomeCliente).append("\n");
        sb.append("Data e Hora: ").append(dataHora).append("\n");
        sb.append("Distancia: ").append(distancia).append(" km\n");
        sb.append("Taxa de Entrega: R$").append(Utils.formatarValor(taxaEntrega)).append("\n");
        sb.append("Itens:\n");
        for (ItemPedido item : itens) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
}
