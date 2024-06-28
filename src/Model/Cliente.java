// src/model/Cliente.java
package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Cliente implements Serializable {
    private String nome;
    private int numPessoas;
    private Mesa mesa;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    public Cliente(String nome, int numPessoas) {
        this.nome = nome;
        this.numPessoas = numPessoas;
        this.horaEntrada = LocalDateTime.now();
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public int getNumPessoas() {
        return numPessoas;
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

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " com " + numPessoas + " pessoas.";
    }
}
