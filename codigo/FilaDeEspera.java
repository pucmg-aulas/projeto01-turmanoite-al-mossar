import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

public class FilaDeEspera {
    private Queue<Cliente> clientes;


    public FilaDeEspera() {
        this.clientes = new LinkedList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
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


  public void removerCliente(Cliente cliente) {
      Iterator<Cliente> iterator = clientes.iterator();
      while (iterator.hasNext()) {
          if (iterator.next().equals(cliente)) {
              iterator.remove();
              break;
          }
      }
  }


}import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

public class FilaDeEspera {
    private Queue<Cliente> clientes;


    public FilaDeEspera() {
        this.clientes = new LinkedList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
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


  public void removerCliente(Cliente cliente) {
      Iterator<Cliente> iterator = clientes.iterator();
      while (iterator.hasNext()) {
          if (iterator.next().equals(cliente)) {
              iterator.remove();
              break;
          }
      }
  }


}
