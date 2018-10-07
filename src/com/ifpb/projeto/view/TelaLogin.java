package com.ifpb.projeto.view;

import com.ifpb.projeto.control.CadastroUsuario;
import com.ifpb.projeto.control.GerenciaComandasFechadas;
import com.ifpb.projeto.model.Comanda;
import com.ifpb.projeto.model.Usuario;
import com.ifpb.projeto.view.auxilio.AplicaNimbusLookAndFeel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TelaLogin extends JFrame{

    private JPanel contentPane;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton cadastrarButton;
    private JButton logarButton;

    private static Usuario logado;

    public TelaLogin(){
        setContentPane(contentPane);
        setTitle("Tela de Login");
        getRootPane().setDefaultButton(logarButton);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        logarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textField1.getText();
                String senha = new String(passwordField1.getPassword());

                if(email.equals("0")&&senha.equals("0")){
                    JOptionPane.showMessageDialog(null,
                            "Diga amigo e entre!","Mensagem de confirmação",JOptionPane.INFORMATION_MESSAGE);
                    logado = null;
                    dispose();
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.pack();
                    menu.setVisible(true);
                }else{
                    if(email.equals("")||senha.equals("")){
                        JOptionPane.showMessageDialog(null,
                                "Por favor preencha todos os campos corretamente!","Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }else{
                        Usuario user = null;
                        try{
                            user = CadastroUsuario.buscarPorEmail(email);
                        }catch(IOException ex){
                            JOptionPane.showMessageDialog(null,
                                    "Falha na conexão com o arquivo!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                        }catch(ClassNotFoundException ex){
                            JOptionPane.showMessageDialog(null,
                                    "Problema com a classe Usuário","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                        }
                        if(user==null){
                            JOptionPane.showMessageDialog(null,"Este usuário não existe!You Shall Not Pass!!!",
                                    "Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                        }else{
                            try {
                                if(CadastroUsuario.autentication(email,senha)){
                                    JOptionPane.showMessageDialog(null,
                                            "Usuário autenticado com sucesso!");
                                        logado = user;
                                        dispose();
                                        MenuPrincipal menu = new MenuPrincipal();
                                        menu.pack();
                                        menu.setVisible(true);
                                }else{
                                    JOptionPane.showMessageDialog(null,
                                            "Senha inválida!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                                }
                            } catch(IOException ex){
                                JOptionPane.showMessageDialog(null,
                                        "Falha na conexão com o arquivo!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                            }catch(ClassNotFoundException ex){
                                JOptionPane.showMessageDialog(null,
                                        "Problema com a classe Usuário","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                            }
                        }

                    }
                }

            }
        });


        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaDeCadastro cadastro = new TelaDeCadastro();
                cadastro.pack();
                cadastro.setVisible(true);
            }
        });
    }

    public static Usuario getLogado(){
        return logado;
    }

    public static void setLogado(Usuario usuario){
        logado = usuario;
    }
    public static void main(String[] args) {
        AplicaNimbusLookAndFeel.pegaNimbus();
        try {
            Comanda.setCodigo(GerenciaComandasFechadas.getComandas().size());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Falha ao se conectar com o arquivo!","Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,
                    "Problema com a classe Comanda","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
        }
        TelaLogin dialog = new TelaLogin();
        dialog.pack();
        dialog.setVisible(true);
    }

}
