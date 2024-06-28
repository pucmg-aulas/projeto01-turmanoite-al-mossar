package Model;

import java.io.Serializable;

public class ItemPedido implements Serializable {
    private ItemMenu item;
    private int quantidade;

    public ItemPedido(ItemMenu item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public ItemMenu getItem() {
        return item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoTotal() {
        return item.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return quantidade + "x " + item.getNome() + " - R$" + Utils.formatarValor(item.getPreco() * quantidade);
    }
}
