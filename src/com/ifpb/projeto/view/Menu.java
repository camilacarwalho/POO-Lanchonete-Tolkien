package com.ifpb.projeto.view;

import javax.swing.*;

public class Menu extends JDialog{
    private JPanel contentPane;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton cadastrarButton;
    private JButton logarButton;


    public Menu(){
        setContentPane(contentPane);
        setTitle("Login");
        setModal(true);

    }
    public static void main(String[] args) {
        Menu dialog = new Menu();
        dialog.pack();
        dialog.setVisible(true);
    }
}
