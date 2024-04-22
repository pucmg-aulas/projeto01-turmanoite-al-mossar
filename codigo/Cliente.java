import java.util.Date;

public class Cliente {
  private String nome;
  private int numPessoas;
  private Requisicao requisicao;

  public Cliente(String nome, int numPessoas) {
    this.nome = nome;
    this.numPessoas = numPessoas;
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

  public void sairDoRestaurante() {
    if (this.requisicao != null && this.requisicao.getMesa() != null) {
      this.requisicao.getMesa().desocupar();
      this.requisicao.registrarSaida(new Date());
      System.out.println(
          "Cliente " + nome + " concluiu sua refeição e desocupou a mesa " + this.requisicao.getMesa().getId() + ".");
    } else {
      System.out.println("Cliente " + nome + " não está em uma mesa.");
    }
  }
}
