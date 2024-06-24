/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.ItemMenu;

/**
 *
 * @author Guilh
 */
public class ItensMenu extends AbstractDAO {

    private List<ItemMenu> itensMenu;
    private static ItensMenu instance;

    // Endereço do arquivo serializado que contém a coleção de itens
    private final String localArquivo = "./src/main/java/data/itens.dat";

    private ItensMenu() {
        this.itensMenu = new ArrayList<>();
        carregaItens();
    }

    // Método para recuperar a única instância de itens
    public static ItensMenu getInstance() {
        if (instance == null) {
            instance = new ItensMenu();
        }
        return instance;

    }

    public void addItens(ItemMenu itemMenu) {
        this.itensMenu.add(itemMenu);
        grava();
    }

     public void excluirItem(ItemMenu itemMenu) {

        itensMenu.remove(itemMenu);
        grava();
    }   
    
    private void grava() {
        super.grava(localArquivo, itensMenu);
    }

    private void carregaItens() {
        this.itensMenu = super.leitura(localArquivo);
    }
}
