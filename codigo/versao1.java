import java.util.Date;
import java.util.List;

public class Restaurante {
    private int totalMesas;
    private List<Mesa> mesas;
    private FilaDeEspera fila;

    public void alocarMesa(Cliente cliente) {

    }
}

public class Mesa {
    private int capacidade;
    private boolean ocupada;

    public void alocar() {

    }

    public void desalocar() {

    }
}

public class Cliente {
    private String nome;
    private int numPessoas;
    private Date dataEntrada;
    private Date dataSaida;

    public void checkIn(Date hora) {

    }

    public void checkOut(Date hora) {

    }
}

public class Requisicao {
    private Date horaEntrada;
    private Date horaSaida;
    private boolean atendida;

    public void registrarEntrada(Date hora) {

    }

    public void registrarSaida(Date hora) {

    }
}

public class FilaDeEspera {
    private List<Cliente> clientes;

    public void adicionarCliente(Cliente cliente) {

    }

    public void removerCliente(Cliente cliente) {

    }
}