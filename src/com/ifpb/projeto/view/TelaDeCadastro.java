package com.ifpb.projeto.view;

import com.ifpb.projeto.Exceptions.CpfExistenteException;
import com.ifpb.projeto.control.CadastroUsuario;
import com.ifpb.projeto.model.Setor;
import com.ifpb.projeto.model.Usuario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TelaDeCadastro extends JDialog {

    private CadastroUsuario crudUsuario;

    private JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JFormattedTextField CPF;
    private JFormattedTextField Nascimento;
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
        getRootPane().setDefaultButton(salvarButton);
        setModal(true);
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Cpf = CPF.getText();
                String nome = textField1.getText();
                String email = textField2.getText();
                String senha = new String(passwordField1.getPassword());
                String confirmacao = new String(passwordField2.getPassword());
                String telefone = textField3.getText();

                DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern("dd/MM/yyyy");

                LocalDate nascimento = null;
                String data = Nascimento.getText();
                Setor setor = (Setor) comboBox1.getSelectedItem();

                if(Cpf.equals("")||nome.equals("")||email.equals("")||senha.equals("")||confirmacao.equals("")||telefone.equals("")
                        ||data.length()<10){
                    JOptionPane.showMessageDialog(null,
                            "Por favor preencha todos os campos corretamente!","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    try{
                        nascimento= LocalDate.parse(data,formatter);
                    }catch(DateTimeParseException ex){
                        JOptionPane.showMessageDialog(null,
                                "Erro ao na ao converter a data para o formato dd/MM/yyyy","Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    if(!senha.equals(confirmacao)){
                        JOptionPane.showMessageDialog(null,
                                "Senhas diferentes!","Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }else{
                        Usuario novo = new Usuario(Cpf,nome,email,telefone,nascimento,setor,senha);
                        try{
                            if(crudUsuario.cadastrar(novo)){
                                JOptionPane.showMessageDialog(null,
                                        "Usuário cadastrado com sucesso!","Mensagem de confirmação",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (CpfExistenteException e1) {
                            JOptionPane.showMessageDialog(null,
                                    "Já existe um usuário com este CPF!","Mensagem de Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(null,
                                    "Falha ao se conectar com o arquivo!","Mensagem de Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
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
            formatter2 = new MaskFormatter("###.###.###-##");
        }catch(ParseException e){
            e.printStackTrace();
        }

        CPF = new JFormattedTextField();
        Nascimento = new JFormattedTextField();

        if(formatter1!=null){
            formatter1.install(Nascimento);
        }
        if(formatter2!=null){
            formatter2.install(CPF);
        }

        comboBox1 = new JComboBox(Setor.values());
    }
}
