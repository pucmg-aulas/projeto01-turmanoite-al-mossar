package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Serializable {
    private List<ItemPedido> itens;

    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemMenu item, int quantidade) {
        itens.add(new ItemPedido(item, quantidade));
    }

    public List<ItemPedido> getItensPedido() {
        return itens;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ItemPedido item : itens) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}
