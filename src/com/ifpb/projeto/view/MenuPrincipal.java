package com.ifpb.projeto.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private JPanel panel1;
    private JButton cardápioButton;
    private JButton cozinhaButton;
    private JButton mesasButton;
    private JButton gerênciaButton;
    private JButton minhaContaButton;
    private JButton sairButton;
    private JLabel createLabelImagem;

    public MenuPrincipal() {

        setContentPane(panel1);
        setTitle("Menu Principal");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cardápioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciarMenu cardapio = new GerenciarMenu();
                cardapio.pack();
                cardapio.setVisible(true);
            }
        });
        mesasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciarMesas mesas = new GerenciarMesas();
                mesas.pack();
                mesas.setVisible(true);
            }
        });
        minhaContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarUsuario minhaconta = new EditarUsuario();
                minhaconta.pack();
                minhaconta.setVisible(true);
            }
        });
        cozinhaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cozinha cozinha = new Cozinha();
                cozinha.pack();
                cozinha.setVisible(true);
            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TelaLogin dialog = new TelaLogin();
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ImageIcon icone = new ImageIcon("img/logo.png");
        createLabelImagem = new JLabel(icone);
    }
}
