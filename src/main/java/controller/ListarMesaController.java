package controller;

import dao.Mesas;
import javax.swing.JOptionPane;
import model.Mesa;
import view.TelaAddMesa;
import view.TelaExibicaoMesas;

public class ListarMesaController {

    private TelaExibicaoMesas view;
    private Mesas mesas;

    public ListarMesaController() {

        this.mesas = Mesas.getInstance();
        this.view = new TelaExibicaoMesas();

        this.view.getjToggleButton1().addActionListener((e) -> {
            addMesa();
        });
    }

    public void addMesa() {

        int id = Integer.parseInt(view.getJtxtId().getText());
        int capacidade = Integer.parseInt(view.getJtxtCapt().getText());

        Mesa m = new Mesa(id, capacidade);

        mesas.addMesa(m);

        JOptionPane.showMessageDialog(view, "Mesa salva com sucesso!");

        //this.view.dispose();
        limparTela();

    }

    private void limparTela() {

        this.view.getJtxtId().setText("");
        this.view.getJtxtCapt().setText("");
    }

}
