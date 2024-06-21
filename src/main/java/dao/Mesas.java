package dao;

import model.Mesa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mesas extends AbstractDAO  {

    private List<Mesa> mesas;

    private static Mesas instance;

    // Endereço do arquivo serializado que contém a coleção de mesas
    private final String localArquivo = "./src/main/java/data/Mesas.dat";

    // Singleton
    private Mesas() {
        this.mesas = new ArrayList<>();
        carregaMesas();
    }

    // Método para recuperar a única instância de mesas
    public static Mesas getInstance() {
        if (instance == null) {
            instance = new Mesas();
        }
        return instance;
    }

    public void addMesa(Mesa mesa) {
        this.mesas.add(mesa);
        grava();
    }

    
    private void carregaMesas() {

        this.mesas = super.leitura(localArquivo);
    }

    private void grava() {
        super.grava(localArquivo, mesas);
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void excluirMesa(Mesa mesa) {

        mesas.remove(mesa);
        grava();
    }

    public Mesa buscarMesaPorId(int id) {

        for (Mesa mesa : mesas) {
            if (mesa.getId() == id) {
                return mesa;
            }
        }
        return null;
    }

}
