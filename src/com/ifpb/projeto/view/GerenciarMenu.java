package com.ifpb.projeto.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerenciarMenu extends JFrame {
    private JPanel panel1;
    private JButton salvarButton;
    private JButton excluirButton;
    private JButton editarButton;
    private JTextField textFieldNome;
    private JTextField textFieldDescricao;
    private JTextField textFieldPreco;
    private JButton buscarButton;
    private JSpinner spinner1;

    public GerenciarMenu(){
        setContentPane(panel1);
        setTitle("Gerenciar Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(salvarButton);


        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                int codigo = (int) textFieldNome
            }
        });
    }
    private void createUIComponents() {
        spinner1 = new JSpinner();
    }

    public static void main(String[] args) {
        GerenciarMenu dialog = new GerenciarMenu();
        dialog.pack();
        dialog.setVisible(true);

    }


}
