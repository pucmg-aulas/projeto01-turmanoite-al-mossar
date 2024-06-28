package main;

import Controller.MainController;
import dao.RestauranteDAO;
import Model.Restaurante;
import view.MainView;

public class Main {
    public static void main(String[] args) {
        RestauranteDAO restauranteDAO = RestauranteDAO.getInstance();
        Restaurante restaurante = restauranteDAO.carregar();

        MainView mainView = new MainView();
        MainController mainController = new MainController(restaurante, mainView);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            restauranteDAO.salvar(restaurante);
        }));

        mainView.setVisible(true);
    }
}
