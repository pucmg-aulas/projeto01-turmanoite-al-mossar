/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.Cliente;
import model.Restaurante;
import java.util.Scanner;
import view.TelaCadastrarCliente;


public class CadastrarClienteController {
   
    TelaCadastrarCliente view;
    RequisicaoController requiscaoController = new RequisicaoController ();
    
    public CadastrarClienteController() {
        view = new TelaCadastrarCliente();
        view.setVisible(true);
    }

    public TelaCadastrarCliente getView() {
        return view;
    }

    public void setView(TelaCadastrarCliente view) {
        this.view = view;
    }
    
    public void Entrar (){
        String nome = view.getTxtNomeCliente().getText();
        int quantidade = Integer.parseInt(view.getTxtQuantidadePessoas().getText());
        
        requiscaoController.processarRequisicaoCliente(new Cliente(nome, quantidade));
    } 
    
}
