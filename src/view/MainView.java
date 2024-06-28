// src/view/MainView.java
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JButton addClienteButton;
    private JButton addPedidoButton;
    private JButton listMesasButton;
    private JButton listComandasButton;
    private JButton sairRestauranteButton;
    private JButton listHistoricoButton;
    private JButton buscarPorDataButton;
    private JButton encerrarContaButton;
    private JButton mostrarVendasButton;
    private JButton pedidoDeliveryButton;
    private JButton listarPedidosDeliveryButton;
    private JButton mostrarFilaDeEsperaButton;
    private JTextArea filaDeEsperaTextArea;

    public MainView() {
        setTitle("Restaurante");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(13, 1));

        addClienteButton = new JButton("Adicionar Cliente");
        addPedidoButton = new JButton("Adicionar Pedido");
        listMesasButton = new JButton("Listar Mesas");
        listComandasButton = new JButton("Listar Comandas");
        sairRestauranteButton = new JButton("Sair do Restaurante");
        listHistoricoButton = new JButton("Listar Historico");
        buscarPorDataButton = new JButton("Buscar Por Data");
        encerrarContaButton = new JButton("Encerrar Conta");
        mostrarVendasButton = new JButton("Mostrar Vendas");
        pedidoDeliveryButton = new JButton("Pedido Delivery");
        listarPedidosDeliveryButton = new JButton("Listar Pedidos Delivery");
        mostrarFilaDeEsperaButton = new JButton("Mostrar Fila de Espera");

        add(addClienteButton);
        add(addPedidoButton);
        add(listMesasButton);
        add(listComandasButton);
        add(sairRestauranteButton);
        add(listHistoricoButton);
        add(buscarPorDataButton);
        add(encerrarContaButton);
        add(mostrarVendasButton);
        add(pedidoDeliveryButton);
        add(listarPedidosDeliveryButton);
        add(mostrarFilaDeEsperaButton);

        filaDeEsperaTextArea = new JTextArea();
        filaDeEsperaTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(filaDeEsperaTextArea);
        scrollPane.setPreferredSize(new Dimension(350, 200));
        add(scrollPane);
    }

    public void addClienteButtonListener(ActionListener listener) {
        addClienteButton.addActionListener(listener);
    }

    public void addPedidoButtonListener(ActionListener listener) {
        addPedidoButton.addActionListener(listener);
    }

    public void listMesasButtonListener(ActionListener listener) {
        listMesasButton.addActionListener(listener);
    }

    public void listComandasButtonListener(ActionListener listener) {
        listComandasButton.addActionListener(listener);
    }

    public void sairRestauranteButtonListener(ActionListener listener) {
        sairRestauranteButton.addActionListener(listener);
    }

    public void listHistoricoButtonListener(ActionListener listener) {
        listHistoricoButton.addActionListener(listener);
    }

    public void buscarPorDataButtonListener(ActionListener listener) {
        buscarPorDataButton.addActionListener(listener);
    }

    public void encerrarContaButtonListener(ActionListener listener) {
        encerrarContaButton.addActionListener(listener);
    }

    public void mostrarVendasButtonListener(ActionListener listener) {
        mostrarVendasButton.addActionListener(listener);
    }

    public void pedidoDeliveryButtonListener(ActionListener listener) {
        pedidoDeliveryButton.addActionListener(listener);
    }

    public void listarPedidosDeliveryButtonListener(ActionListener listener) {
        listarPedidosDeliveryButton.addActionListener(listener);
    }

    public void mostrarFilaDeEsperaButtonListener(ActionListener listener) {
        mostrarFilaDeEsperaButton.addActionListener(listener);
    }

    public void atualizarFilaDeEspera(String filaDeEspera) {
        filaDeEsperaTextArea.setText(filaDeEspera);
    }
}
