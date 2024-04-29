    import java.util.List;
    import java.util.ArrayList;
    import java.util.Set;
    import java.util.HashSet;
    import java.util.Iterator;

  
    public class Restaurante {
        private List<Mesa> mesas;
        private FilaDeEspera filaDeEspera;
        private Set<Cliente> historicoClientes;  // Para armazenar o histórico de todos os clientes

        public Restaurante() {
            this.mesas = new ArrayList<>();
            this.filaDeEspera = new FilaDeEspera();
            this.historicoClientes = new HashSet<>();  // Inicializa a lista do histórico
            // Inicializando mesas
            for (int i = 0; i < 4; i++) this.mesas.add(new Mesa(i + 1, 4));
            for (int i = 4; i < 8; i++) this.mesas.add(new Mesa(i + 1, 6));
            for (int i = 8; i < 10; i++) this.mesas.add(new Mesa(i + 1, 8));
        }

        public void processarRequisicaoCliente(Cliente cliente) {
            Mesa mesaDisponivel = encontrarMesaDisponivel(cliente.getNumPessoas());
            if (mesaDisponivel != null) {
                mesaDisponivel.ocupar();
                mesaDisponivel.setCliente(cliente);
                historicoClientes.add(cliente);  // Adiciona o cliente ao histórico
                System.out.println("Mesa " + mesaDisponivel.getId() + " alocada para " + cliente.getNome());
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
                    System.out.println("Cliente: " + cliente.getNome() + ", Pessoas: " + cliente.getNumPessoas());
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
                  mesa.ocupar();
                  historicoClientes.add(cliente);  // Adiciona o cliente ao histórico
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
  }
