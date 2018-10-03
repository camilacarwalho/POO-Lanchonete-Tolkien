package com.ifpb.projeto.view;

import javax.swing.*;

public class EditarUsuario extends JFrame {
    private JPanel panel1;
    private JButton editarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JFormattedTextField formattedTextField1;
    private JComboBox comboBox1;

    public EditarUsuario(){
        setContentPane(panel1);
        setTitle("Editar Usuário");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EditarUsuario dialog = new EditarUsuario();
        dialog.pack();
        dialog.setVisible(true);
    }
}
