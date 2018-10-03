package com.ifpb.projeto.view;

import javax.swing.*;

public class VerPedidos extends JFrame {
    private JPanel panel1;
    private JList list1;
    private JButton OKButton;
    private JButton editarButton;

    public VerPedidos(){
        setContentPane(panel1);
        setTitle("Ver Pedidos");
    }

    public static void main(String[] args) {
        VerPedidos dialog = new VerPedidos();
        dialog.pack();
        dialog.setVisible(true);
    }
}
