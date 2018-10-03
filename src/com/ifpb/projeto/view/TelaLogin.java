package com.ifpb.projeto.view;

import com.ifpb.projeto.control.CadastroUsuario;
import com.ifpb.projeto.model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TelaLogin extends JFrame{

    private CadastroUsuario crudUsuario;

    private JPanel contentPane;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton cadastrarButton;
    private JButton logarButton;
    private JLabel test;


    public TelaLogin(){

        try {
            crudUsuario = new CadastroUsuario();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Erro na conexão com o arquivo!","Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Não foi possivel encontrar a classe!","Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
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
                            "Usuário autenticado com sucesso!","Mensagem de confirmação",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    if(email.equals("")||senha.equals("")){
                        JOptionPane.showMessageDialog(null,
                                "Por favor preencha todos os campos corretamente!","Mensagem de Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }else{
                        Usuario user = null;
                        try{
                            user = crudUsuario.consulta(email);
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
                            if(crudUsuario.autentication(email,senha)){
                                JOptionPane.showMessageDialog(null,
                                        "Usuário autenticado com sucesso!");
                                    MenuPrincipal menu = new MenuPrincipal();
                                    menu.pack();
                                    menu.setVisible(true);
                            }else{
                                JOptionPane.showMessageDialog(null,
                                        "Senha inválida!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
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
    public static void main(String[] args) {
        TelaLogin dialog = new TelaLogin();
        dialog.pack();
        dialog.setVisible(true);
    }

}
