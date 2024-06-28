// src/model/Requisicao.java
package Model;

import java.time.LocalDateTime;

public class Requisicao {
    private Cliente cliente;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    public Requisicao(Cliente cliente) {
        this.cliente = cliente;
        this.horaEntrada = LocalDateTime.now();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraSaida() {
        this.horaSaida = LocalDateTime.now();
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }
}
