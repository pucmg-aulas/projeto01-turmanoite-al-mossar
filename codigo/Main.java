import java.util.Date;

public class Main {
  public static void main(String[] args) {
    // Cria o restaurante com mesas de diferentes capacidades
    Restaurante restaurante = new Restaurante();

    // Cria alguns clientes
    Cliente cliente1 = new Cliente("Danilo", 8);
    Cliente cliente2 = new Cliente("Elenice", 8);
    Cliente cliente3 = new Cliente("Michelle", 6);
    Cliente cliente4 = new Cliente("Sandro", 6);
    Cliente cliente5 = new Cliente("Beiloçudo", 4);
    Cliente cliente6 = new Cliente("Mc Kevin", 4);
    Cliente cliente7 = new Cliente("Tio Paulo",4);
    Cliente cliente8 = new Cliente("Jorge Souza",6);
    Cliente cliente9 = new Cliente("LuCosta",6);
    Cliente cliente10 = new Cliente("Hulk",6);
  
    //Clientes que irao para o restaurante
    System.out.println("Clientes chegando ao restaurante.");
    restaurante.processarRequisicaoCliente(cliente1);
    restaurante.processarRequisicaoCliente(cliente2);
    restaurante.processarRequisicaoCliente(cliente3);
    restaurante.processarRequisicaoCliente(cliente4);
    restaurante.processarRequisicaoCliente(cliente5);
    restaurante.processarRequisicaoCliente(cliente6);
    restaurante.processarRequisicaoCliente(cliente7);
    restaurante.processarRequisicaoCliente(cliente8);
    restaurante.processarRequisicaoCliente(cliente9);
    
    //Clientes que fazem a requisição
    System.out.println("Outro cliente chega.");
    restaurante.processarRequisicaoCliente(cliente10);
    
    //Clientes que terminaram a refeição
    System.out.println("Um cliente está saindo.");
    cliente4.sairDoRestaurante();
    

    // Após a saída de um cliente, tentamos alocar mesa para quem estava esperando
    if (!restaurante.getFilaDeEspera().estaVazia()) {
      Cliente clienteEspera = restaurante.getFilaDeEspera().removerCliente();
      restaurante.processarRequisicaoCliente(clienteEspera);
    }

    
    System.out.println("Estado final das mesas:");
    restaurante.getMesas().forEach(mesa -> {
      System.out.println(mesa);
    });

    
    System.out.println("Clientes restantes na fila de espera:");
    while (!restaurante.getFilaDeEspera().estaVazia()) {
      Cliente cliente = restaurante.getFilaDeEspera().removerCliente();
      System.out.println(cliente.getNome() + " ainda está esperando.");
    }
    if (!restaurante.getFilaDeEspera().estaVazia()) {
      Cliente clienteEspera = restaurante.getFilaDeEspera().removerCliente();
      restaurante.processarRequisicaoCliente(clienteEspera);
    }
  }
}