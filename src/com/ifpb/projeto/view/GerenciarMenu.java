package com.ifpb.projeto.view;

import com.ifpb.projeto.Exceptions.CodigoInvalidoException;
import com.ifpb.projeto.Exceptions.PrecoInvalidoException;
import com.ifpb.projeto.Exceptions.ProdutoInexistenteException;
import com.ifpb.projeto.control.CadastroProduto;
import com.ifpb.projeto.model.Produto;
import com.ifpb.projeto.view.auxilio.FloatField;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
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
                                        Float.parseFloat(textFieldPreco.getText().replace(',','.')));

                                try {
                                    if(CadastroProduto.add(novo)){
                                        JOptionPane.showMessageDialog(null,
                                                "Produto cadastrado com sucesso!","Mensagem de confirmação",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        JOptionPane.showMessageDialog(null,
                                                novo);
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
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Produto p = CadastroProduto.buscarPorCodigo((int)spinner1.getValue());
                    if(p==null){
                        JOptionPane.showMessageDialog(null,
                                "O produto não existe!","Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                        limparForm();
                    }else{
                        textFieldNome.setText(p.getNome());
                        textFieldDescricao.setText(p.getDescricao());
                        textFieldPreco.setText(new Float(p.getPreco()).toString().replace('.',','));
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
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto p = null;
                try {
                    p = CadastroProduto.buscarPorCodigo((int)spinner1.getValue());
                    if(p==null) {
                        JOptionPane.showMessageDialog(null,
                                "O produto não existe!", "Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                        limparForm();
                    }else{
                        if(CadastroProduto.remove((int)spinner1.getValue())){
                            JOptionPane.showMessageDialog(null,
                                    "Produto Excluido com Sucesso!", "Mensagem de confirmação",
                                    JOptionPane.INFORMATION_MESSAGE);
                            limparForm();
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "Não foi possivel excluir o produto", "Mensagem de Erro",
                                    JOptionPane.ERROR_MESSAGE);
                            limparForm();
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
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto old = null;
                try {
                    old = CadastroProduto.buscarPorCodigo((int) spinner1.getValue());
                    if (old == null) {
                        JOptionPane.showMessageDialog(null,
                                "O produto não existe, por favor ponha um código válido!", "Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }else{
                        try {
                            Produto novo = new Produto((int)spinner1.getValue(),textFieldNome.getText(),textFieldDescricao.getText(),
                                    Float.parseFloat(textFieldPreco.getText().replace(',','.')));
                            if(novo.equals(old)){
                                JOptionPane.showMessageDialog(null,
                                        "Não houve nenhuma alteração!","Mensagem de Erro",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                try {
                                    if(CadastroProduto.update(old.getCodigo(),novo)){
                                        JOptionPane.showMessageDialog(null,
                                                "O produto foi alterado com sucesso!","Mensagem de Erro",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        limparForm();
                                    }else{
                                        JOptionPane.showMessageDialog(null,
                                                "O produto não pode ser alterado","Mensagem de Erro",
                                                JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (ProdutoInexistenteException e1) {
                                    JOptionPane.showMessageDialog(null,
                                            "O produto solicitado não existe!","Mensagem de Erro",
                                            JOptionPane.ERROR_MESSAGE);
                                }
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
        });
    }
    private void createUIComponents() {
        textFieldPreco = new FloatField(10,1000,2);
        textFieldPreco.setText("0,00");
        spinner1 = new JSpinner();
        spinner1.setModel(new SpinnerNumberModel(1, 1, null, 1));
        JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)spinner1.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);

    }

    private void limparForm(){
        textFieldNome.setText("");
        textFieldDescricao.setText("");
        textFieldPreco.setText("0,00");
    }
    

}
