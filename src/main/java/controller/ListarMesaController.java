package controller;

import dao.Mesas;
import model.Mesa;
import view.TelaExibicaoMesas;

public class ListarMesaController {

    private  TelaExibicaoMesas view;
    private  Mesas mesas;

    public ListarMesaController() {

        this.mesas = Mesas.getInstance();
        this.view = new TelaExibicaoMesas();

        this.view.getBtnSalvarMesa().addActionListener((e)->{
            cadastrarMesa();
        });
                
        this.view.setVisible(true);
    }

    public void cadastrarMesa() {

        int id = Integer.parseInt(view.getTxtID().getText().trim());
        int capacidade = Integer.parseInt(view.getTxtCap().getText().trim());


        Mesa m = new Mesa(id, capacidade);

        mesas.addMesa(m);

    }

}
