// src/model/ItemMenu.java
package Model;

import java.io.Serializable;

public class ItemMenu implements Serializable {
    private String nome;
    private double preco;

    public ItemMenu(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return nome + " - R$" + preco;
    }
}
