package com.ifpb.projeto.view;

import com.ifpb.projeto.control.CadastroUsuario;

import javax.swing.*;
import java.io.IOException;

public class TelaDeCadastro extends JDialog {

    private CadastroUsuario crudUsuario;

    private JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JComboBox comboBox1;
    private JButton salvarButton;
    private JButton cancelarButton;

    public TelaDeCadastro(){
        try{
            crudUsuario = new CadastroUsuario();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Erro na conexão com o arquivo!","Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Não foi possivel encontrar a classe!","Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        setContentPane(contentPane);
        setTitle("Cadastro de Usuário");
        setModal(true);
    }
}
