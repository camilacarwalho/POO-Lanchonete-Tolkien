package com.ifpb.projeto.view;

import com.ifpb.projeto.Exceptions.CodigoInvalidoException;
import com.ifpb.projeto.Exceptions.PrecoInvalidoException;
import com.ifpb.projeto.control.CadastroProduto;
import com.ifpb.projeto.model.Produto;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
                if(textFieldNome.getText().equals("") || textFieldDescricao.getText().equals("")){
                    JOptionPane.showMessageDialog(null,
                            "Por favor preencha todos os campos corretamente!","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    try {
                        if(CadastroProduto.buscarPorCodigo((int)spinner1.getValue())!=null){
                            JOptionPane.showMessageDialog(null,
                                    "Já existe um produto cadastrado com esse código!","Mensagem de Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }else{
                            try {
                                Produto novo = new Produto((int)spinner1.getValue(),textFieldNome.getText(),textFieldDescricao.getText(),
                                        Float.parseFloat(textFieldPreco.getText()));
                                try {
                                    if(CadastroProduto.add(novo)){
                                        JOptionPane.showMessageDialog(null,
                                                "Produto cadastrado com sucesso!","Mensagem de confirmação",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    }
                                } catch (IOException e1) {
                                    JOptionPane.showMessageDialog(null,
                                            "Erro na conexão com o arquivo!","Mensagem de Erro",
                                            JOptionPane.ERROR_MESSAGE);
                                } catch (ClassNotFoundException e1) {
                                    JOptionPane.showMessageDialog(null,
                                            "Ocorreu um erro com a classe Produto!","Mensagem de Erro",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (PrecoInvalidoException e1) {
                                JOptionPane.showMessageDialog(null,
                                        "O preço do produto deve ser um valor positivo!","Mensagem de Erro",
                                        JOptionPane.ERROR_MESSAGE);
                            } catch (CodigoInvalidoException e1) {
                                JOptionPane.showMessageDialog(null,
                                        "O código digitado é inválido!","Mensagem de Erro",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null,
                                "Erro na conexão com o arquivo!","Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException e1) {
                        JOptionPane.showMessageDialog(null,
                                "Ocorreu um erro com a classe Produto!", "Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }


            }
        });
    }
    private void createUIComponents() {
        spinner1 = new JSpinner();
        spinner1.setModel(new SpinnerNumberModel(1, 1, null, 1));
        JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)spinner1.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);

    }

    public static void main(String[] args) {
        GerenciarMenu dialog = new GerenciarMenu();
        dialog.pack();
        dialog.setVisible(true);

    }


}
