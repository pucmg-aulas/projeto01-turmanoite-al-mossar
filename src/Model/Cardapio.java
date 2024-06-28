// src/model/Cardapio.java
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cardapio implements Serializable {
    private List<ItemMenu> itens;

    public Cardapio() {
        this.itens = new ArrayList<>();
        inicializarCardapio();
    }

    private void inicializarCardapio() {
        itens.add(new ItemMenu("Moqueca de Tilapia", 25.00));
        itens.add(new ItemMenu("Falafel Assado", 20.00));
        itens.add(new ItemMenu("Salada Primavera com Macarrao Konjac", 18.00));
        itens.add(new ItemMenu("Escondidinho de Frango", 22.00));
        itens.add(new ItemMenu("Strogonoff", 24.00));
        itens.add(new ItemMenu("Cacarola de carne com legumes", 28.00));
        itens.add(new ItemMenu("Agua", 3.00));
        itens.add(new ItemMenu("Suco", 5.00));
        itens.add(new ItemMenu("Refrigerante", 4.00));
        itens.add(new ItemMenu("Cerveja", 8.00));
        itens.add(new ItemMenu("Taca de vinho", 12.00));
    }

    public List<ItemMenu> getItens() {
        return itens;
    }

    public String listarItens() {
        StringBuilder sb = new StringBuilder();
        itens.forEach(item -> sb.append(item.toString()).append("\n"));
        return sb.toString();
    }
}
