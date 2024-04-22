public class Mesa {
    private final int id;
    private final int capacidade;
    private boolean ocupada;

    public Mesa(int id, int capacidade) {
        this.id = id;
        this.capacidade = capacidade;
    }

    public int getId() {
        return id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void ocupar() {
        ocupada = true;
    }

    public void desocupar() {
        ocupada = false;
    }

    @Override
    public String toString() {
        return "Mesa{" +
               "id=" + id +
               ", capacidade=" + capacidade +
               ", ocupada=" + (ocupada ? "Sim" : "Não") +
               '}';
    }
}
