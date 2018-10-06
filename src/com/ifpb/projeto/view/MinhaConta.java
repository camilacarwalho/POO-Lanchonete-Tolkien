package com.ifpb.projeto.view;

import com.ifpb.projeto.control.CadastroUsuario;
import com.ifpb.projeto.model.Usuario;
import javafx.print.PaperSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MinhaConta extends JFrame {
    private JPanel panel1;
    private JButton alterarDadosButton;
    private JButton editarSenhaButton;
    private JButton excluirContaButton;
    private JButton voltarButton;
    private JLabel LabelNome;
    private JLabel LabelEmail;
    private JLabel LabelNascimento;
    private JLabel LabemSetor;
    private JLabel LabelCpf;
    private Usuario logado;

    public MinhaConta(){
        logado = TelaLogin.getLogado();

        setContentPane(panel1);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        if(logado==null){
            LabelNome.setText("Mellon");
            LabelEmail.setText("gandalfDoidão@gmail.com");
            LabelCpf.setText("111.111.111-11");
            LabelNascimento.setText("-25/27/-2500");
            LabemSetor.setText("Sociedade do anel");
        }else{
            atualizarTela();
        }

        alterarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(logado==null){
                    JOptionPane.showMessageDialog(null,
                            "Não é possivel alterar qualquer dado da conta master","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    EditarUsuario editarUser = new EditarUsuario();
                    editarUser.pack();
                    editarUser.setVisible(true);
                    logado = TelaLogin.getLogado();
                    atualizarTela();
                }
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuPrincipal menu = new MenuPrincipal();
                menu.pack();
                menu.setVisible(true);
            }
        });
        editarSenhaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(logado==null){
                    JOptionPane.showMessageDialog(null,
                            "Não é possivel alterar qualquer dado da conta master","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    JPasswordField password = new JPasswordField(10);
                    password.setEchoChar('*');
                    // Cria um rótulo para o campo
                    JLabel texto1 = new JLabel("Digite a sua senha:");
                    // Coloca o rótulo e a caixa de entrada numa JPanel:
                    JPanel entUsuario = new JPanel();
                    entUsuario.add(texto1);
                    entUsuario.add(password);

                    JOptionPane.showMessageDialog(null,entUsuario,"Alteração de senha",JOptionPane.INFORMATION_MESSAGE);

                    String senha = new String(password.getPassword());
                    try {
                        if(CadastroUsuario.autentication(logado.getEmail(),senha)){
                            JLabel texto2 = new JLabel("Insira a nova senha:");
                            JLabel texto3 = new JLabel("Insira Novamente:");
                            JPanel entNovaSenha = new JPanel();
                            JPasswordField password2 = new JPasswordField(10);
                            password.setEchoChar('*');
                            JPasswordField password3 = new JPasswordField(10);
                            password.setEchoChar('*');
                            entNovaSenha.add(texto2);
                            entNovaSenha.add(password2);
                            entNovaSenha.add(texto3);
                            entNovaSenha.add(password3);
                            entNovaSenha.setLayout(null);
                            entNovaSenha.setPreferredSize(new Dimension(220,50));
                            texto2.setBounds(5, 5, 140, 20);
                            password2.setBounds(120, 7, 140, 20);
                            texto3.setBounds(5,30,140,20);
                            password3.setBounds(120,35,140,20);
                            JOptionPane.showMessageDialog(null,entNovaSenha,"Alteração de senha",
                                    JOptionPane.INFORMATION_MESSAGE);
                            senha = new String(password2.getPassword());
                            String confirmacao = new String(password3.getPassword());
                            if(senha.equals(confirmacao)){
                                if(senha.equals(confirmacao)){
                                    Usuario novo = TelaLogin.getLogado();
                                    novo.setSenha(senha);
                                    if(CadastroUsuario.update(TelaLogin.getLogado(),novo)){
                                        TelaLogin.setLogado(novo);
                                        logado = novo;
                                        JOptionPane.showMessageDialog(null,
                                                "Senha alterada com sucesso!","Mensagem de Confirmação",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    }else{
                                        JOptionPane.showMessageDialog(null,
                                                "Não foi possivel alterar a senha","Mensagem de Erro",
                                                JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "Senhas Diferentes. Tente novamente!","Mensagem de Erro",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "Senha invalida!","Mensagem de Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null,
                                "Falha ao se conectar com o arquivo!","Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }catch(ClassNotFoundException ex){
                        JOptionPane.showMessageDialog(null,
                                "Problema com a classe Usuário","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        excluirContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(logado==null){
                    JOptionPane.showMessageDialog(null,
                            "Não é possivel excluir a conta master","Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    if(JOptionPane.showConfirmDialog(null,"Deseja continuar?","WARNING",JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION){
                        try {
                            if(CadastroUsuario.remove(logado)){
                                JOptionPane.showMessageDialog(null,
                                        "Conta deletada com sucesso!","Mensagem de Erro",
                                        JOptionPane.INFORMATION_MESSAGE);
                                TelaLogin.setLogado(null);
                                TelaLogin login = new TelaLogin();
                                login.pack();
                                login.setVisible(true);
                                dispose();
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "Não foi possivel deletar esta conta!","Mensagem de Erro",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(null,
                                    "Falha ao se conectar com o arquivo!","Mensagem de Erro",
                                    JOptionPane.ERROR_MESSAGE);
                        }catch(ClassNotFoundException ex){
                            JOptionPane.showMessageDialog(null,
                                    "Problema com a classe Usuário","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }
            }
        });
    }

    public void atualizarTela(){
        LabelNome.setText(logado.getNome());
        LabelEmail.setText(logado.getEmail());
        LabelCpf.setText(logado.getCpf());
        String[] nascimento = logado.getNascimento().toString().split("-");
        LabelNascimento.setText(nascimento[2]+"/"+nascimento[1]+"/"+nascimento[0]);
        LabemSetor.setText(String.valueOf(logado.getSetor()));
    }

}
