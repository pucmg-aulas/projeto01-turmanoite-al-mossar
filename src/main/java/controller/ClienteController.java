/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Cliente;
import model.Requisicao;
import java.util.Set;

/**
 *
 * @author Guilh
 */
public class ClienteController {
    
    private Set<Cliente> historicoClientes; 

    public Set<Cliente> getHistoricoClientes() {
        return historicoClientes;
    }

    public void setHistoricoClientes(Set<Cliente> historicoClientes) {
        this.historicoClientes = historicoClientes;
    }
    
    
    
    public void mostrarHistoricoClientes() {
        if (historicoClientes.isEmpty()) {
            System.out.println("Não há histórico de clientes no momento.");
        } else {
            System.out.println("Histórico de todos os clientes:");
            for (Cliente cliente : historicoClientes) {
                Requisicao requisicao = cliente.getRequisicao();
                System.out.println("Model.Cliente: " + cliente.getNome() + ", Pessoas: " + cliente.getNumPessoas() +
                                   ", Entrada: " + requisicao.getHoraEntrada() + ", Saída: " + requisicao.getHoraSaida() + "\n");
            }
        }
    }
}
