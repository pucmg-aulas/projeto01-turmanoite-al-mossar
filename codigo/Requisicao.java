import java.util.Date;

public class Requisicao {
    private Date horaEntrada;
    private Date horaSaida;
    private Mesa mesa;

    public void registrarEntrada(Date hora) {
        this.horaEntrada = hora;
    }

    public void registrarSaida(Date hora) {
        this.horaSaida = hora;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public Date getHoraSaida() {
        return horaSaida;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}
