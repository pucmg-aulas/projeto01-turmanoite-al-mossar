/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import view.TelaCadastrarCliente;


public class CadastrarClienteController {
   
    TelaCadastrarCliente view;
    
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
    
}
