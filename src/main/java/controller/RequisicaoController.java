/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Cliente;
import model.Mesa;
import java.util.Date;
import model.FilaDeEspera;

/**
 *
 * @author Guilh
 */
public class RequisicaoController {
    
      FilaDeEspera filaDeEspera = new FilaDeEspera();
      MesaController mesaController = new MesaController();
      ClienteController clienteController = new ClienteController();
 
    
        public void processarRequisicaoCliente(Cliente cliente) {
        Mesa mesaDisponivel = mesaController.encontrarMesaDisponivel(cliente.getNumPessoas());
         if (mesaDisponivel != null) {
            mesaDisponivel.ocupar(cliente);
             cliente.getRequisicao().setMesa(mesaDisponivel);
             cliente.getRequisicao().registrarEntrada(new Date());
             System.out.println("Model.Mesa " + mesaDisponivel.getId() + " alocada para " + cliente.getNome());
             clienteController.getHistoricoClientes().add(cliente); // Adiciona o cliente ao histórico quando é alocado a uma mesa
        } else {
             System.out.println("Nenhuma mesa disponível para " + cliente.getNome() + ". Adicionado à fila de espera.");
             filaDeEspera.adicionarCliente(cliente);
         }
     }
}
