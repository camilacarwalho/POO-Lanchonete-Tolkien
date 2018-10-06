package com.ifpb.projeto.view;

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

public class EditarUsuario extends JDialog {

    private JPanel panel1;
    private JTextField textFieldNome;
    private JTextField textFieldEmail;
    private JComboBox comboBoxSetor;
    private JFormattedTextField telefone;
    private JButton salvarButton;
    private JButton cancelarButton;
    private JComboBox comboBoxEmail;
    private JFormattedTextField nascimento;
    private Usuario logado;

    public EditarUsuario(){

        logado = TelaLogin.getLogado();

        setContentPane(panel1);
        setTitle("Editar Usuário");
        getRootPane().setDefaultButton(salvarButton);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText();
                String email = textFieldEmail.getText() + comboBoxEmail.getSelectedItem();
                String phone = telefone.getText();
                String data = nascimento.getText();

                if(nome.equals("") || textFieldEmail.getText().equals("") || phone.length()<14 ||
                        data.length()<10){
                    JOptionPane.showMessageDialog(null,
                            "Por favor preencha todos os campos corretamente!","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    LocalDate Nasc = null;
                    Setor setor = (Setor) comboBoxSetor.getSelectedItem();

                    DateTimeFormatter formatter = DateTimeFormatter
                            .ofPattern("dd/MM/yyyy");

                    try{
                        Nasc = LocalDate.parse(data,formatter);
                        Usuario novo = new Usuario(logado.getCpf(),nome,email,phone,Nasc,setor,logado.getSenha());
                        Usuario teste = CadastroUsuario.buscarPorEmail(email);
                        if(teste!=null&&teste.getEmail().equals(logado.getEmail()) || teste==null){
                            String modificacoes = "\tForam feitas as seguintes modificações:\n";
                            if(!nome.equals(logado.getNome())){
                                modificacoes += "O nome foi alterado de "+logado.getNome()+" Para "+nome+";\n";
                            }
                            if(!email.equals(logado.getEmail())){
                                modificacoes += "O email foi alterado de "+logado.getEmail()+" Para "+email+";\n";
                            }
                            if(!phone.equals(logado.getTelefone())){
                                modificacoes += "O Telefone foi alterado de "+logado.getTelefone()+" Para "+phone+";\n";
                            }
                            if(!Nasc.equals(logado.getNascimento())){
                                modificacoes += "A data de nascimento foi alterado de "+logado.getNome()+" Para "+Nasc+";\n";
                            }
                            if(modificacoes.equals("\tForam feitas as seguintes modificações:\n")){
                                modificacoes = "Não foi feita nenhuma alteração!";
                            }

                            try {
                                if(CadastroUsuario.update(logado,novo)){
                                    TelaLogin.setLogado(novo);
                                    logado=novo;
                                    JOptionPane.showMessageDialog(null,modificacoes,"Mensagem de Confirmação",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    dispose();
                                }
                            } catch (IOException e1) {
                                JOptionPane.showMessageDialog(null,"Erro na conexão com o arquivo!","Mensagem de Erro",
                                        JOptionPane.ERROR_MESSAGE);
                            }catch(ClassNotFoundException ex){
                                JOptionPane.showMessageDialog(null,
                                        "Problema com a classe Usuário","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "Já existe um usuário com este email!","Mensagem de Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                    }catch(DateTimeParseException ex){
                        JOptionPane.showMessageDialog(null,
                                "Erro ao na ao converter a data para o formato dd/MM/yyyy","Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }catch(IOException ex){
                        JOptionPane.showMessageDialog(null,
                                "Falha na conexão com o arquivo!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                    }catch(ClassNotFoundException ex){
                        JOptionPane.showMessageDialog(null,
                                "Problema com a classe Usuário","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
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
