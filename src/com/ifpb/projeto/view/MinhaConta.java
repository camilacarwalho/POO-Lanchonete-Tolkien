package com.ifpb.projeto.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinhaConta extends JFrame {
    private JPanel panel1;
    private JButton alterarDadosButton;
    private JButton editarSenhaButton;
    private JButton excluirContaButton;
    private JButton voltarButton;

    public MinhaConta(){
        setContentPane(panel1);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        alterarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarUsuario minhaconta = new EditarUsuario();
                minhaconta.pack();
                minhaconta.setVisible(true);

            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


}
