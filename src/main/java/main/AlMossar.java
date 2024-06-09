/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package main;
import controller.MenuController;
import model.Restaurante;
import model.Cliente;
import model.ItemMenu;
import model.Mesa;
import java.util.Scanner;



public class AlMossar {

    public static void main(String[] args) {
        MenuController menucontroller = MenuController.getInstancia();
        
        try (Scanner scanner = new Scanner(System.in)) {
            Restaurante restaurante = new Restaurante();
            int opcao;
            
            do {
                System.out.println("\nMenu:");
                System.out.println("1 - Entrar no restaurante");
                System.out.println("2 - Sair do restaurante");
                System.out.println("3 - Mostrar todas as mesas");
                System.out.println("4 - Mostrar histórico de clientes");
                System.out.println("5 - Anotar pedido para uma mesa");
                System.out.println("6 - Mostrar comandas");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                
                switch (opcao) {
                    case 2 -> sairRestaurante(scanner, restaurante);
                    case 3 -> mostrarMesas(restaurante);
                    
                    case 5 -> anotarPedido(scanner, restaurante);
                    case 6 -> restaurante.mostrarComandas();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }
            } while (opcao != 0);
        }
    }


    private static void sairRestaurante(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o ID da mesa para sair: ");
        int idMesa = scanner.nextInt();
        Mesa mesa = restaurante.getMesa(idMesa);
        if (mesa != null && mesa.isOcupada()) {
            mesa.getPedido().finalizarPedido();
            System.out.println("Total da conta (com 10% de serviço): R$" + mesa.getPedido().getTotal());
            restaurante.desocuparMesa(idMesa);
        } else {
            System.out.println("Model.Mesa não encontrada ou já está desocupada.");
        }
    }

    private static void mostrarMesas(Restaurante restaurante) {
        restaurante.mostrarMesas();
    }

    private static void anotarPedido(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o ID da mesa para anotar o pedido: ");
        int idMesa = scanner.nextInt();
        Mesa mesa = restaurante.getMesa(idMesa);
        if (mesa != null && mesa.isOcupada()) {
            System.out.println("Cardápio disponível:");
            for (ItemMenu item : restaurante.getCardapio().getItens()) {
                System.out.println(item.getNome() + " - R$ " + item.getPreco());
            }
            System.out.print("Digite o nome do prato ou bebida desejada: ");
            scanner.nextLine(); // Limpar buffer
            String nomeItem = scanner.nextLine();
            System.out.print("Digite a quantidade desejada: ");
            int quantidade = scanner.nextInt();
            ItemMenu item = restaurante.getCardapio().getItemPorNome(nomeItem);
            if (item != null) {
                mesa.getCliente().fazerPedido(mesa, nomeItem, quantidade, restaurante.getCardapio());
                System.out.println("Item adicionado ao pedido.");
            } else {
                System.out.println("Item não encontrado no cardápio.");
            }
        } else {
            System.out.println("Model.Mesa não encontrada ou não está ocupada.");
        }
    }
}
