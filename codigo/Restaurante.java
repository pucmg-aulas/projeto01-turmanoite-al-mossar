import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Date;

public class Restaurante {
    private List<Mesa> mesas;
    private FilaDeEspera filaDeEspera;
    private Set<Cliente> historicoClientes; 
    private Cardapio cardapio;

    public Restaurante() {
        this.mesas = new ArrayList<>();
        this.cardapio = new Cardapio();
        this.filaDeEspera = new FilaDeEspera();
        this.historicoClientes = new HashSet<>();
        inicializarCardapio();
        inicializarMesas();
    }

    private void inicializarMesas() {
        for (int i = 0; i < 4; i++) this.mesas.add(new Mesa(i + 1, 4));
        for (int i = 4; i < 8; i++) this.mesas.add(new Mesa(i + 1, 6));
        for (int i = 8; i < 10; i++) this.mesas.add(new Mesa(i + 1, 8));
    }

    private void inicializarCardapio() {
        // Pratos
        cardapio.adicionarItem(new ItemMenu("Moqueca de Tilápia", 30.00));
        cardapio.adicionarItem(new ItemMenu("Falafel Assado", 25.00));
        cardapio.adicionarItem(new ItemMenu("Salada Primavera com Macarrão Konjac", 20.00));
        cardapio.adicionarItem(new ItemMenu("Escondidinho de Frango", 28.00));
        cardapio.adicionarItem(new ItemMenu("Strogonoff", 27.00));
        cardapio.adicionarItem(new ItemMenu("Caçarola de carne com legumes", 32.00));

        // Bebidas
        cardapio.adicionarItem(new ItemMenu("Água", 3.00));
        cardapio.adicionarItem(new ItemMenu("Suco", 6.00));
        cardapio.adicionarItem(new ItemMenu("Refrigerante", 4.50));
        cardapio.adicionarItem(new ItemMenu("Cerveja", 8.00));
        cardapio.adicionarItem(new ItemMenu("Taça de vinho", 10.00));
    }

    public void processarRequisicaoCliente(Cliente cliente) {
        Mesa mesaDisponivel = encontrarMesaDisponivel(cliente.getNumPessoas());
        if (mesaDisponivel != null) {
            mesaDisponivel.ocupar(cliente);
            cliente.getRequisicao().setMesa(mesaDisponivel);
            cliente.getRequisicao().registrarEntrada(new Date());
            System.out.println("Mesa " + mesaDisponivel.getId() + " alocada para " + cliente.getNome());
            historicoClientes.add(cliente); // Adiciona o cliente ao histórico quando é alocado a uma mesa
        } else {
            System.out.println("Nenhuma mesa disponível para " + cliente.getNome() + ". Adicionado à fila de espera.");
            filaDeEspera.adicionarCliente(cliente);
        }
    }

    public void mostrarHistoricoClientes() {
        if (historicoClientes.isEmpty()) {
            System.out.println("Não há histórico de clientes no momento.");
        } else {
            System.out.println("Histórico de todos os clientes:");
            for (Cliente cliente : historicoClientes) {
                Requisicao requisicao = cliente.getRequisicao();
                System.out.println("Cliente: " + cliente.getNome() + ", Pessoas: " + cliente.getNumPessoas() + 
                                   ", Entrada: " + requisicao.getHoraEntrada() + ", Saída: " + requisicao.getHoraSaida() + "\n");
            }
        }
    }

    private Mesa encontrarMesaDisponivel(int numPessoas) {
        for (Mesa mesa : mesas) {
            if (!mesa.isOcupada() && mesa.getCapacidade() >= numPessoas) {
                return mesa;
            }
        }
        return null;
    }

    public void desocuparMesa(int idMesa) {
        Mesa mesa = null;
        for (Mesa m : mesas) {
            if (m.getId() == idMesa && m.isOcupada()) {
                mesa = m;
                Cliente cliente = m.getCliente();
                cliente.getRequisicao().registrarSaida(new Date());
                m.desocupar();
                System.out.println("Mesa " + idMesa + " desocupada.");
                break;
            }
        }
        if (mesa == null) {
            System.out.println("Mesa " + idMesa + " não encontrada ou já está desocupada.");
            return;
        }

        // Tentar realocar um cliente da fila de espera para a mesa que acabou de ser liberada
        realocarClienteParaMesa(mesa);
    }

    private void realocarClienteParaMesa(Mesa mesa) {
        Iterator<Cliente> iterator = filaDeEspera.getFila().iterator();
        while (iterator.hasNext()) {
            Cliente cliente = iterator.next();
            if (cliente.getNumPessoas() <= mesa.getCapacidade() && !mesa.isOcupada()) {
                iterator.remove(); // Remove o cliente da fila de espera
                mesa.setCliente(cliente);
                mesa.ocupar(cliente);
                cliente.getRequisicao().setMesa(mesa);
                cliente.getRequisicao().registrarEntrada(new Date());
                historicoClientes.add(cliente); // Adiciona o cliente ao histórico quando é realocado para uma mesa
                System.out.println("Cliente " + cliente.getNome() + " foi realocado da fila de espera para a mesa " + mesa.getId());
                return;
            }
        }
    }

    public void mostrarMesas() {
        for (Mesa mesa : mesas) {
            String status = mesa.isOcupada() ? "Ocupada" : "Livre";
            String clienteInfo = mesa.isOcupada() ? ", Cliente: " + mesa.getCliente().getNome() + ", Pessoas: " + mesa.getCliente().getNumPessoas() : "";
            System.out.println("Mesa ID: " + mesa.getId() + ", Capacidade: " + mesa.getCapacidade() + ", Status: " + status + clienteInfo);
        }
    }

    public Mesa getMesa(int idMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getId() == idMesa) {
                return mesa;
            }
        }
        return null;
    }

    public Cardapio getCardapio() {
        return this.cardapio;
    }

    public void mostrarComandas() {
        for (Mesa mesa : mesas) {
            if (mesa.isOcupada()) {
                System.out.println("Mesa ID: " + mesa.getId() + " - Pedido:");
                Pedido pedido = mesa.getPedido();
                if (pedido != null && !pedido.getItens().isEmpty()) {
                    for (Pedido.ItemPedido itemPedido : pedido.getItens()) {
                        System.out.println("  - " + itemPedido.getItem().getNome() + " (x" + itemPedido.getQuantidade() + "): R$" + itemPedido.getItem().getPreco() + " cada");
                    }
                } else {
                    System.out.println("  Nenhum item pedido.");
                }
            } else {
                System.out.println("Mesa ID: " + mesa.getId() + " - Nenhum cliente.");
            }
        }
    }
}
