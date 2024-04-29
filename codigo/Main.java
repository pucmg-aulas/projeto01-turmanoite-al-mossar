import java.util.Scanner;
public class Main {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Restaurante restaurante = new Restaurante();
    int opcao;

    do {
        System.out.println("\nMenu:");
        System.out.println("1 - Entrar no restaurante");
        System.out.println("2 - Sair do restaurante");
        System.out.println("3 - Mostrar todas as mesas");
        System.out.println("4 - Mostrar histórico de clientes");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
        opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                entrarRestaurante(scanner, restaurante);
                break;
            case 2:
                sairRestaurante(scanner, restaurante);
                break;
            case 3:
                mostrarMesas(restaurante);
                break;
            case 4:
                restaurante.mostrarHistoricoClientes();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    } while (opcao != 0);

    scanner.close();
}

    private static void entrarRestaurante(Scanner scanner, Restaurante restaurante) {
        System.out.print("Nome do cliente: ");
        String nome = scanner.next();
        System.out.print("Número de pessoas: ");
        int numPessoas = scanner.nextInt();
        restaurante.processarRequisicaoCliente(new Cliente(nome, numPessoas));
    }

    private static void sairRestaurante(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o ID da mesa para desocupar: ");
        int idMesa = scanner.nextInt();
        restaurante.desocuparMesa(idMesa);
    }

    private static void mostrarMesas(Restaurante restaurante) {
        restaurante.mostrarMesas();
    }
}
