package controller;

import dao.Mesas;
import model.Mesa;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import view.TelaExibicaoMesas;



public class ListarMesaController {

    private TelaExibicaoMesas view;
    private Mesas mesas;

    public ListarMesaController() {

        this.mesas = Mesas.getInstance();
        this.view = new TelaExibicaoMesas();

        carregaTabela();

        this.view.setVisible(true);
    }

    private void carregaTabela() {
        Object colunas[] = { "Id", "Capacidade", "Ocupada" };
        DefaultTableModel tablemodel = new DefaultTableModel(colunas, 0);

        tablemodel.setNumRows(0);
        Iterator<Mesa> it = mesas.getMesas().iterator();
        while (it.hasNext()) {
            Mesa m = it.next();
            String mesa = m.toString();
            String linha[] = mesa.split("%");
            tablemodel.addRow(new Object[] { linha[0], linha[1] });
        }
        view.getjTable1().setModel(tablemodel);
    }

}
