// src/model/Conta.java
package Model;

import java.util.List;

public class Conta {
    private List<ItemMenu> itens;
    private double taxaServico = 0.10;
    private double valorTotal;

    public Conta(List<ItemMenu> itens) {
        this.itens = itens;
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        valorTotal = itens.stream().mapToDouble(ItemMenu::getPreco).sum();
        valorTotal += valorTotal * taxaServico;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double calcularValorPorPessoa(int numPessoas) {
        return valorTotal / numPessoas;
    }

    public double calcularDesconto(double desconto) {
        return valorTotal - (valorTotal * desconto / 100);
    }

    public double calcularValorFinal(String metodoPagamento) {
        double desconto;
        switch (metodoPagamento.toLowerCase()) {
            case "dinheiro":
                desconto = 0;
                break;
            case "pix":
                desconto = Math.min(10, valorTotal * 0.0145);
                break;
            case "débito":
                desconto = valorTotal * 0.014;
                break;
            case "crédito":
                desconto = valorTotal * 0.031;
                break;
            default:
                throw new IllegalArgumentException("Método de pagamento inválido");
        }
        return valorTotal - desconto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        itens.forEach(item -> sb.append(item.toString()).append("\n"));
        sb.append("Valor total (com taxa de serviço): R$").append(valorTotal);
        return sb.toString();
    }
}
