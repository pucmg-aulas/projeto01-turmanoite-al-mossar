package main;

import java.util.List;

import dao.Mesas;
import view.TelaMenu;
import model.Mesa;

public class AlMossar {
    public static void main(String[] args) {

        TelaMenu view = new TelaMenu();
        view.setVisible(true);

        Mesas mesasDAO = Mesas.getInstance();

        for (Mesa mesa : mesasDAO.getMesas()) {
            System.out.println(mesa);
        }

    
        List<Mesa> mesas = mesasDAO.getMesas();

        if (mesas.isEmpty()) {
            System.out.println("Nenhuma mesa foi carregada.");
        } else {
            for (Mesa mesa : mesas) {
                System.out.println("Mesa carregada: " + mesa);
            }
        }
    }
}