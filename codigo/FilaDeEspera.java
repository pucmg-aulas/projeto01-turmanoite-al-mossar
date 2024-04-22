import java.util.LinkedList;
import java.util.Queue;

public class FilaDeEspera {
    private Queue<Cliente> clientes;


    public FilaDeEspera() {
        this.clientes = new LinkedList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void removerCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public Queue<Cliente> getFila() {
        return clientes;
    }

    public boolean estaVazia() {
        return clientes.isEmpty();
    }

    public Cliente proximoCliente() {
        return clientes.poll();
    }

    public Cliente removerCliente() {
        return clientes.poll();
    }


}
