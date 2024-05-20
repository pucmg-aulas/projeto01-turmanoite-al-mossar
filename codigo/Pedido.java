import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<ItemPedido> itens;
    private double total;
    private boolean finalizado;

    public Pedido() {
        this.itens = new ArrayList<>();
        this.total = 0.0;
        this.finalizado = false;
    }

    public void adicionarItem(ItemMenu item, int quantidade) {
        if (!finalizado) {
            itens.add(new ItemPedido(item, quantidade));
            total += item.getPreco() * quantidade;
        }
    }

    public void finalizarPedido() {
        finalizado = true;
        total += total * 0.1;  // Adiciona 10% de taxa de serviço
    }

    public double getTotal() {
        return total;
    }

    public double getDivisaoConta(int numPessoas) {
        return total / numPessoas;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public static class ItemPedido {
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
    }
}
