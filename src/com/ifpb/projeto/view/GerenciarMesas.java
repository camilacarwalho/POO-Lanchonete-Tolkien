package com.ifpb.projeto.view;

import com.ifpb.projeto.model.Gerencia;
import com.ifpb.projeto.model.GerenciarMesa;
import com.ifpb.projeto.model.Setor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        verPedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerPedidos verpedidos = new VerPedidos();
                verpedidos.pack();
                verpedidos.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        GerenciarMesas dialog = new GerenciarMesas();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void createUIComponents() {
        GerenciarMesa mesas = new GerenciarMesa();
        //Aprender sobre essa parte aqui
        comboBox1 = new JComboBox(mesas.getMesas().toArray());
    }
}
