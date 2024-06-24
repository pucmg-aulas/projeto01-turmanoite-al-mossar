/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ItensMenu;
import model.ItemMenu;
import view.TelaExibirCardapio;

/**
 *
 * @author Guilh
 */
public class ListarCardapioController {
    
    private ItensMenu itensmenu;
    private TelaExibirCardapio view;
    

    public ListarCardapioController() {
        this.view = new TelaExibirCardapio();
        this.itensmenu = ItensMenu.getInstance();
        System.out.println("ENTROU");
        this.view.getjButtonAdicionar().addActionListener((e)->{
            AdicionarItem();
            System.out.println("ADD ITEM");
        });
    }

       public void AdicionarItem() {

        String nome = view.getTxtItem().getText().trim();
        float preco = Integer.parseInt(view.getTxtPreco().getText().trim());

        ItemMenu i = new ItemMenu(nome, preco);

        itensmenu.addItens(i);

    }

}
