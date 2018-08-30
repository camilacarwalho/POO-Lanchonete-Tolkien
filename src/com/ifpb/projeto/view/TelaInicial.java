package com.ifpb.projeto.view;
import java.time.LocalDate;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;

import com.ifpb.projeto.model.*;
import com.ifpb.projeto.control.*;
import com.sun.org.apache.xpath.internal.SourceTree;


public class TelaInicial {
    public static void main(String[] args) {

        CadastroUsuario usuario = new CadastroUsuario();
        CadastroProduto produtos = new CadastroProduto();
        boolean continua = true;
        int seleciona;
        int selecionaMenu;
        Scanner scan = new Scanner(System.in);


        while(continua){
            System.out.println(":.:.:.:.TELA INICIAL.:.:.:.:\n\n");
            System.out.println("1: Autenticar:");
            System.out.println("2: Criar nova conta:");
            seleciona = scan.nextInt();

            if(seleciona<0||seleciona>2){
                System.out.println("ERROR:Press ENTER para continar!");
                scan.nextLine();
            }
            else{
                switch(seleciona){
                    case 1:
                        System.out.println(":.:.:.:.TELA DE LOGIN.:.:.:.:");
                        System.out.println("Email: ");
                        String email = scan.next();
                        System.out.println("Senha: ");
                        String senha = scan.next();
                        if(usuario.autentication(email, senha)) {
                            System.out.println("Login feito com sucesso!!!");
                            boolean continuaMenu = true;
                            while(continuaMenu){
                                System.out.println(":.:.:.:.USUARIO LOGADO.:.:.:.:\n");
                                System.out.println("1:Entrar na Gerencia\n2:Atualizar conta" +
                                        "\n3:Deletar conta\n4:Sair da conta\n");
                                selecionaMenu = scan.nextInt();
                                if(selecionaMenu<1||selecionaMenu>4){
                                    System.out.println("ERROR:Press ENTER para continar!");
                                    scan.nextLine();
                                }
                                else{
                                    int selecionaUsuario;
                                    switch(selecionaMenu){
                                        case 1: gerencia(produtos);
                                                break;
                                        case 2: System.out.println("\nSelecione o usuario que deseja atualizar:\n");
                                                selecionaUsuario = scan.nextInt();
                                                usuario.update(selecionaUsuario-1);
                                                break;
                                        case 3: System.out.println("\nSelecione o usuario que deseja deletar:\n");
                                                selecionaUsuario = scan.nextInt();
                                                usuario.delete(selecionaUsuario-1);
                                                break;
                                        case 4: continuaMenu = false;
                                                break;

                                    }
                                }
                                limpaMinhaTelaUltraAdvancedMaisMais();
                            }
                            System.out.println("::.:.:.:.:.:FIM::.:.:.:.:.:");

                        }else System.out.println("Usuário inexistente!");

                        break;
                    case 2:
                        System.out.println(":.:.:.:.CADASTRANDO USUÁRIO.:.:.:.:\n\n");
                        usuario.cadastrar();
                        break;

                    case 0: continua = false;
                }
            }
            limpaMinhaTelaUltraAdvancedMaisMais();
        }
        System.out.println("::.:.:.:.:.:FIM::.:.:.:.:.:");


    }
    public static void limpaMinhaTelaUltraAdvancedMaisMais(){
        for (int i=0; i<40;i++){
            System.out.println("\n");
        }
    }
    public static void gerencia(CadastroProduto produtos){
        boolean continuaMenu = true;
        while(continuaMenu){
            System.out.println(":.:.:.:.GERÊNCIA.:.:.:.:\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("1:Cardápio\n2:Mesas\n3:Minha Conta\n4:Cozinha\n5:Gerencia\n6:Voltar ao Menu Principal");
            int selecionaMenu = scan.nextInt();

            if(selecionaMenu<1||selecionaMenu>6){
                System.out.println("ERROR:Press ENTER para continar!");
                scan.nextLine();
            }
            else{
                switch(selecionaMenu){
                    case 1: produtos.getCardapio();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6: continuaMenu = false;
                            break;
                }
            }
        }
    }
}
