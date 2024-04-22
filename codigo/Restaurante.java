import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Restaurante {
    private List<Mesa> mesas;
    private FilaDeEspera filaDeEspera;

    public Restaurante() {
        this.mesas = new ArrayList<>();
        for (int i = 0; i < 4; i++) this.mesas.add(new Mesa(i + 1, 4));
        for (int i = 4; i < 8; i++) this.mesas.add(new Mesa(i + 1, 6));
        for (int i = 8; i < 10; i++) this.mesas.add(new Mesa(i + 1, 8));
        this.filaDeEspera = new FilaDeEspera();
    }

    public void processarRequisicaoCliente(Cliente cliente) {
        Mesa mesaDisponivel = encontrarMesaDisponivel(cliente.getNumPessoas());
        if (mesaDisponivel != null) {
            mesaDisponivel.ocupar();
            Requisicao requisicao = new Requisicao();
            requisicao.setMesa(mesaDisponivel);
            requisicao.registrarEntrada(new Date());
            cliente.setRequisicao(requisicao);
            System.out.println("Mesa " + mesaDisponivel.getId() + " (capacidade " + mesaDisponivel.getCapacidade() + ") alocada ao cliente " + cliente.getNome());
        } else {
            System.out.println("Todas as mesas adequadas estão ocupadas. Cliente " + cliente.getNome() + " adicionado à fila de espera.");
            filaDeEspera.adicionarCliente(cliente);
        }
    }

    private Mesa encontrarMesaDisponivel(int numPessoas) {
        for (Mesa mesa : mesas) {
            if (!mesa.isOcupada() && mesa.getCapacidade() >= numPessoas) {
                return mesa;
            }
        }
        return null;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public FilaDeEspera getFilaDeEspera() {
        return filaDeEspera;
    }

}
