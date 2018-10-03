package com.ifpb.projeto.view;

import javax.swing.*;

public class Cozinha extends JFrame {
    private JPanel panel1;
    private JButton atenderButton;
    private JComboBox comboBox1;

    public Cozinha(){
        setContentPane(panel1);
        setTitle("Cozinha");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Cozinha dialog = new Cozinha();
        dialog.pack();
        dialog.setVisible(true);
    }
}
