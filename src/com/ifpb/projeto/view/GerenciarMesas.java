package com.ifpb.projeto.view;

import com.ifpb.projeto.model.Gerencia;
import com.ifpb.projeto.model.GerenciarMesa;

import javax.swing.*;

public class GerenciarMesas extends JFrame{
    private JPanel panel1;
    private JButton novaComandaButton;
    private JButton verPedidosButton;
    private JButton fazerPedidoButton;
    private JComboBox comboBox1;
    private JButton encerrarComandaButton;

    public GerenciarMesas(){
        setContentPane(panel1);
        setTitle("Gerenciar Mesas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GerenciarMesas dialog = new GerenciarMesas();
        dialog.pack();
        dialog.setVisible(true);
    }
}
