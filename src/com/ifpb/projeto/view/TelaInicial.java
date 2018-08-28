package com.ifpb.projeto.view;
import java.time.LocalDate;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;

import com.ifpb.projeto.model.*;
import com.ifpb.projeto.control.*;


public class TelaInicial {
    public static void main(String[] args) {

        CadastroUsuario usuario = new CadastroUsuario();
        CadastroProduto produtos = new CadastroProduto();
        boolean continua = true;
        int seleciona;
        int selecionaMenu;
        Scanner scan = new Scanner(System.in);


        while(continua){

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
                        System.out.println("Email: ");
                        String email = scan.next();
                        System.out.println("Senha: ");
                        String senha = scan.next();
                        if(usuario.autentication(email, senha)) {
                            System.out.println("Login feito com sucesso!!!");

                            boolean continuaMenu = true;
                            while(continuaMenu){

                                System.out.println("1:Cardápio\n2:Mesas\n3:Minha Conta\n4:Cozinha\n5:Gerencia\n6:Sair");
                                selecionaMenu = scan.nextInt();

                                if(selecionaMenu<0||selecionaMenu>7){
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
                                        case 6: break;

                                        case 0: continuaMenu = false;
                                    }
                                }
                                limpaMinhaTelaUltraAdvancedMaisMais();
                            }
                            System.out.println("::.:.:.:.:.:FIM::.:.:.:.:.:");

                        }else System.out.println("Usuário inexistente!");

                        break;
                    case 2:
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

}
