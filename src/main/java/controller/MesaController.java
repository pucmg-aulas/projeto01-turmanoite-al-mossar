/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Cliente;
import model.FilaDeEspera;
import model.Mesa;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Guilh
 */
public class MesaController {
        
    private List<Mesa> mesas;
    private FilaDeEspera filaDeEspera;
    ClienteController clienteController = new ClienteController();
    
    public Mesa encontrarMesaDisponivel(int numPessoas) {
        for (Mesa mesa : mesas) {
            if (!mesa.isOcupada() && mesa.getCapacidade() >= numPessoas) {
                return mesa;
            }
        }
        return null;
    }
    
    public void desocuparMesa(int idMesa) {
        Mesa mesa = null;
        for (Mesa m : mesas) {
            if (m.getId() == idMesa && m.isOcupada()) {
                mesa = m;
                Cliente cliente = m.getCliente();
                cliente.getRequisicao().registrarSaida(new Date());
                m.desocupar();
                System.out.println("Model.Mesa " + idMesa + " desocupada.");
                break;
            }
        }
        if (mesa == null) {
            System.out.println("Model.Mesa " + idMesa + " não encontrada ou já está desocupada.");
            return;
        }

        // Tentar realocar um cliente da fila de espera para a mesa que acabou de ser liberada
        realocarClienteParaMesa(mesa);
    }
    
    
    private void realocarClienteParaMesa(Mesa mesa) {
        Iterator<Cliente> iterator = filaDeEspera.getFila().iterator();
        while (iterator.hasNext()) {
            Cliente cliente = iterator.next();
            if (cliente.getNumPessoas() <= mesa.getCapacidade() && !mesa.isOcupada()) {
                iterator.remove(); // Remove o cliente da fila de espera
                mesa.setCliente(cliente);
                mesa.ocupar(cliente);
                cliente.getRequisicao().setMesa(mesa);
                cliente.getRequisicao().registrarEntrada(new Date());
                clienteController.getHistoricoClientes().add(cliente); // Adiciona o cliente ao histórico quando é realocado para uma mesa
                System.out.println("Model.Cliente " + cliente.getNome() + " foi realocado da fila de espera para a mesa " + mesa.getId());
                return;
            }
        }
    }

}
