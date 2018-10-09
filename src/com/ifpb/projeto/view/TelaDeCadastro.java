package com.ifpb.projeto.view;

import com.ifpb.projeto.Exceptions.CpfExistenteException;
import com.ifpb.projeto.control.CadastroProduto;
import com.ifpb.projeto.control.CadastroUsuario;
import com.ifpb.projeto.model.Setor;
import com.ifpb.projeto.model.Usuario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class TelaDeCadastro extends JDialog {

    private JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JFormattedTextField phone;
    private JFormattedTextField CPF;
    private JFormattedTextField Nascimento;
    private JComboBox comboBox1;
    private JButton salvarButton;
    private JButton cancelarButton;
    private JComboBox comboBox2;


    public TelaDeCadastro(){
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
                String email = textField2.getText()+ comboBox2.getSelectedItem();
                String senha = new String(passwordField1.getPassword());
                String confirmacao = new String(passwordField2.getPassword());
                String telefone = phone.getText();

                DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern("dd/MM/yyyy");

                LocalDate nascimento = null;


                String data = Nascimento.getText();
                Setor setor = (Setor) comboBox1.getSelectedItem();
                //oii
                LocalDate date6 = LocalDate.parse(data, formatter);



                if(Cpf.equals("")||nome.equals("")||email.equals("")||senha.equals("")||confirmacao.equals("")||telefone.equals("")
                        ||data.length()<10){
                    JOptionPane.showMessageDialog(null,
                            "Por favor preencha todos os campos corretamente!","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);


                }else{

                    try{
                        nascimento = LocalDate.parse(data,formatter);
                        if(!senha.equals(confirmacao)){
                            JOptionPane.showMessageDialog(null,
                                    "Senhas diferentes!","Mensagem de Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }else{
                            try {
                                Usuario teste = CadastroUsuario.buscarPorEmail(email);
                                if(teste!=null){
                                    JOptionPane.showMessageDialog(null,
                                            "Já existe um usuário cadastrado com este email","Mensagem de Erro",
                                            JOptionPane.ERROR_MESSAGE);

                                }else{

                                    Usuario novo = new Usuario(Cpf,nome,email,telefone,nascimento,setor,senha);
                                    JOptionPane.showMessageDialog(null,novo);

                                    try{
                                        if (date6.isAfter(LocalDate.now())){
                                        JOptionPane.showMessageDialog(null,"Data Inválida", "Mensagem de erro",JOptionPane.ERROR_MESSAGE);
                                        dispose();
                                        }//Arrumar num local que n permita o cadastro
                                        if(CadastroUsuario.add(novo)){
                                            JOptionPane.showMessageDialog(null,
                                                    "Usuário cadastrado com sucesso!","Mensagem de confirmação",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            dispose();
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
                            }catch (IOException e1) {
                                JOptionPane.showMessageDialog(null,
                                        "Falha ao se conectar com o arquivo!","Mensagem de Erro",
                                        JOptionPane.ERROR_MESSAGE);
                            }catch(ClassNotFoundException ex){
                                JOptionPane.showMessageDialog(null,
                                        "Problema com a classe Usuário","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }catch(DateTimeParseException ex){
                        JOptionPane.showMessageDialog(null,
                                "Erro ao na ao converter a data para o formato dd/MM/yyyy","Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void createUIComponents() {
        MaskFormatter formatter1 = null;
        MaskFormatter formatter2 = null;
        MaskFormatter formatter3 = null;
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
        try{
            formatter3 = new MaskFormatter("(##)#####-####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        phone = new JFormattedTextField();
        CPF = new JFormattedTextField();
        Nascimento = new JFormattedTextField();

        if(formatter1!=null){
            formatter1.install(Nascimento);
        }
        if(formatter2!=null){
            formatter2.install(CPF);
        }
        if(formatter3!=null){
            formatter3.install(phone);
        }

        String[] array = {"@gmail.com","@hotmail.com","@yahoo.com"};

        comboBox1 = new JComboBox(Setor.values());
        comboBox2 = new JComboBox(array);
    }
}
