package com.ifpb.projeto.view;

import com.ifpb.projeto.control.CadastroUsuario;
import com.ifpb.projeto.model.Setor;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class EditarUsuario extends JFrame {

    private CadastroUsuario crudUsuario;

    private JPanel panel1;
    private JTextField textFieldNome;
    private JTextField textFieldEmail;
    private JComboBox comboBoxSetor;
    private JFormattedTextField telefone;
    private JButton salvarButton;
    private JButton cancelarButton;
    private JComboBox comboBoxEmail;
    private JFormattedTextField nascimento;

    public EditarUsuario(){

        try{
            crudUsuario = new CadastroUsuario();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Erro na conexão com o arquivo!","Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Não foi possivel encontrar a classe!","Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

        setContentPane(panel1);
        setTitle("Editar Usuário");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(salvarButton);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldNome.getText().equals("") || textFieldEmail.getText().equals("") || telefone.getText().length()<14 ||
                        nascimento.getText().length()<10){
                    JOptionPane.showMessageDialog(null,
                            "Por favor preencha todos os campos corretamente!","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{

                }
            }
        });
    }

    public static void main(String[] args) {
        EditarUsuario dialog = new EditarUsuario();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void createUIComponents() {
        MaskFormatter formatter1 = null;
        MaskFormatter formatter2 = null;
        try {
            formatter1 = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try{
            formatter2 = new MaskFormatter("(##)#####-####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        nascimento = new JFormattedTextField();
        telefone = new JFormattedTextField();

        if(formatter1!=null){
            formatter1.install(nascimento);
        }
        if(formatter2!=null){
            formatter2.install(telefone);
        }

        String[] array = {"@gmail.com","@hotmail.com","@yahoo.com"};

        comboBoxEmail = new JComboBox(array);
        comboBoxSetor = new JComboBox(Setor.values());
    }
}
