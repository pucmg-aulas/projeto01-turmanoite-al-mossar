

package model;

public class Mesa {
    private int id;
    private int capacidade;
    private boolean ocupada;
    private Cliente cliente;
    private Pedido pedido;

    public Mesa(int id, int capacidade) {
        this.id = id;
        this.capacidade = capacidade;
        this.ocupada = false;
        this.cliente = null;
        this.pedido = null;
    }

    public void iniciarPedido() {
            this.pedido = new Pedido();
        }

        public Pedido getPedido() {
            return pedido;
        }
    

    public int getId() {
        return id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void ocupar(Cliente cliente) {
        this.ocupada = true;
        this.cliente = cliente;
        this.pedido = new Pedido();  // Inicia um novo pedido assim que a mesa é ocupada
    }

    public void desocupar() {
        ocupada = false;
        cliente = null;  // Limpar referência ao cliente ao desocupar
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
