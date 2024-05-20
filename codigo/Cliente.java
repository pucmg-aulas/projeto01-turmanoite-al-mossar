import java.util.Date;

public class Cliente {
    private String nome;
    private int numPessoas;
    private Requisicao requisicao;

    public Cliente(String nome, int numPessoas) {
        this.nome = nome;
        this.numPessoas = numPessoas;
        this.requisicao = new Requisicao(); // Inicializa uma nova requisição ao criar o cliente
    }

    public String getNome() {
        return nome;
    }

    public int getNumPessoas() {
        return numPessoas;
    }

    public Requisicao getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }

    public void fazerPedido(Mesa mesa, String nomeItem, int quantidade, Cardapio cardapio) {
        ItemMenu item = cardapio.getItemPorNome(nomeItem);
        if (item != null && mesa.getPedido() != null) {
            mesa.getPedido().adicionarItem(item, quantidade);
        } else {
            System.out.println("Item não encontrado ou pedido não iniciado.");
        }
    }

    public void fecharConta(Mesa mesa) {
        if (mesa.getPedido() != null) {
            mesa.getPedido().finalizarPedido();
            System.out.println("Total da conta com serviço: " + mesa.getPedido().getTotal());
            System.out.println("Valor por pessoa: " + mesa.getPedido().getDivisaoConta(numPessoas));
        }
    }

    public void sairDoRestaurante() {
        if (this.requisicao != null && this.requisicao.getMesa() != null) {
            this.requisicao.getMesa().desocupar();
            this.requisicao.registrarSaida(new Date());
            System.out.println("Cliente " + nome + " concluiu sua refeição e desocupou a mesa " + this.requisicao.getMesa().getId() + ".");
        } else {
            System.out.println("Cliente " + nome + " não está em uma mesa.");
        }
    }
}
