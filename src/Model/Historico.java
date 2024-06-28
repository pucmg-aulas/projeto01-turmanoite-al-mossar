package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Historico implements Serializable {
    private String nomeCliente;
    private int numPessoas;
    private int numeroMesa;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private String comanda;

    public Historico(String nomeCliente, int numPessoas, int numeroMesa, LocalDateTime horaEntrada, LocalDateTime horaSaida, String comanda) {
        this.nomeCliente = nomeCliente;
        this.numPessoas = numPessoas;
        this.numeroMesa = numeroMesa;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.comanda = comanda;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    @Override
    public String toString() {
        return "Nome: " + nomeCliente + ", Pessoas: " + numPessoas + ", Mesa: " + numeroMesa + 
               ", Entrada: " + horaEntrada + ", Saida: " + horaSaida + "\nComanda:\n" + comanda;
    }
}
