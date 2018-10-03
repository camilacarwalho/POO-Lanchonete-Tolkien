package com.ifpb.projeto.view;

import com.ifpb.projeto.model.Gerencia;

import javax.swing.*;

public class GerenciarMenu extends JFrame {
    private JPanel panel1;
    private JButton salvarButton;
    private JButton excluirButton;
    private JButton editarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton buscarButton;

    public GerenciarMenu(){
        setContentPane(panel1);
        setTitle("Gerenciar Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    public static void main(String[] args) {
        GerenciarMenu dialog = new GerenciarMenu();
        dialog.pack();
        dialog.setVisible(true);

    }
}
