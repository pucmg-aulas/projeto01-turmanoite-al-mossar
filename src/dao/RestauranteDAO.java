package dao;

import Model.Restaurante;
import java.io.Serializable;
import java.util.List;

public class RestauranteDAO extends AbstractDAO implements Serializable {
    private static RestauranteDAO instance;
    private final String localArquivo = "./src/main/java/data/restaurante.dat";

    private RestauranteDAO() {}

    public static RestauranteDAO getInstance() {
        if (instance == null) {
            instance = new RestauranteDAO();
        }
        return instance;
    }

    public void salvar(Restaurante restaurante) {
        grava(localArquivo, List.of(restaurante));
    }

    public Restaurante carregar() {
        List<Restaurante> restaurantes = leitura(localArquivo);
        if (!restaurantes.isEmpty()) {
            return restaurantes.get(0);
        } else {
            return new Restaurante();
        }
    }
}
