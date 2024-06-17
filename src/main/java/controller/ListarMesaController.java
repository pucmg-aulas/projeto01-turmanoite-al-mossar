package controller;

import dao.Mesas;
import model.Mesa;
import javax.swing.table.DefaultTableModel;
import view.TelaExibicaoMesas;
import javax.swing.JOptionPane;

public class ListarMesaController {

    private TelaExibicaoMesas view;
    private Mesas mesas;

    public ListarMesaController() {

        this.mesas = Mesas.getInstance();
        this.view = new TelaExibicaoMesas();
        

        this.view.setVisible(true);

    }
    


    public void cadastrarMesa() {
        try {
            int id = Integer.parseInt(view.getTxtID().getText().trim());
            boolean ocupada = false;
            int capacidade = Integer.parseInt(view.getTxtCap().getText().trim());
            Mesa mesa = new Mesa(id, ocupada ,capacidade);
            mesas.addMesa(mesa);
            JOptionPane.showMessageDialog(view, "Mesa cadastrado com sucesso");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "ID e Capacidade devem ser números.", "Erro de Cadastro",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void LoadTableMesas() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ID", "Capacidade", "Ocupada"}, 0);

        for (Mesa mesa : mesas.getMesas()) {
            Object[] linha = {mesa.getId(), mesa.getCapacidade(), mesa.isOcupada()};
            modelo.addRow(linha);
            System.out.println("Mesa carregada: " + mesa);
        }

        view.getTableMesas().setModel(modelo);
    }


    public void limpaCampo() {

        view.getTxtCap().setText("");
        view.getTxtID().setText("");
    }
}
