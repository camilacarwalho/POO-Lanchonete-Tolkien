package com.ifpb.projeto.view;

import javax.swing.*;

public class EditarUsuario extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JFormattedTextField formattedTextField2;
    private JButton salvarButton;
    private JButton cancelarButton;
    private JComboBox comboBox2;

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
