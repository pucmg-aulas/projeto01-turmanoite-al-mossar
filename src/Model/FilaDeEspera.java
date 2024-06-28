// src/model/FilaDeEspera.java
package Model;

import java.util.LinkedList;
import java.util.Queue;

public class FilaDeEspera {
    private Queue<Cliente> fila;

    public FilaDeEspera() {
        this.fila = new LinkedList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        fila.add(cliente);
    }

    public Cliente proximoCliente() {
        return fila.poll();
    }

    public boolean isEmpty() {
        return fila.isEmpty();
    }

    public Queue<Cliente> getFila() {
        return fila;
    }
}


// 2 ctrl z nescessarios