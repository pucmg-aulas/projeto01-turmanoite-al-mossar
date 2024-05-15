import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<ItemMenu> itens;
    private double total;
    private boolean finalizado;

    public Pedido() {
        this.itens = new ArrayList<>();
        this.total = 0.0;
        this.finalizado = false;
    }

    public void adicionarItem(ItemMenu item) {
        if (!finalizado) {
            itens.add(item);
            total += item.getPreco();
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
}
