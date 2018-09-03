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
                                        case 2: System.out.println("\nAtualizar este usuario:\n\n");
                                                System.out.println("Digite novamente o seu email:");
                                                email = scan.next();
                                                System.out.println("Digite a sua senha\n");
                                                senha = scan.next();
                                                if(usuario.autentication(email,senha)){
                                                    usuario.updateThis(email,senha,readData());
                                                    System.out.println("Usuario atualizado com sucesso!");
                                                }else System.out.println("ERROR!");
                                                break;
                                        case 3: System.out.println("Deseja continuar?");
                                                System.out.println("\n1 - Sim\n2 - Não\n");
                                                int escolha = scan.nextInt();
                                                if(escolha<1||escolha>2){
                                                    System.out.println("ERROR: COOMO VOCÊ CONSEGUE?");
                                                }
                                                else{
                                                    if(escolha == 1){
                                                        usuario.deleteThis(email,senha);
                                                        System.out.println("Usuario deletado com sucesso!\n");
                                                        continuaMenu=false;
                                                    }
                                                }
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
                        usuario.cadastrar(readData());
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
        int index;
        while(continuaMenu){
            System.out.println(":.:.:.:.GERÊNCIA.:.:.:.:\n\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("1:Cardápio\n2:Cadastrar produtos\n3:Atualizar Produtos\n4:Deletar produtos\n5:Voltar ao Menu Principal");
            int selecionaMenu = scan.nextInt();

            if(selecionaMenu<1||selecionaMenu>6){
                System.out.println("ERROR:Press ENTER para continar!");
                scan.nextLine();
            }
            else{
                switch(selecionaMenu){
                    case 1:
                        System.out.println(produtos);
                        break;
                    case 2: produtos.cadastrar(readDataP());
                        break;
                    case 3:
                            System.out.println("Selecione o produto que deseja atualizar:\n\n");
                            System.out.println(produtos);
                            index = scan.nextInt();
                            if (produtos.update(index-1,readDataP())){
                                System.out.println("Atualizado com sucesso!!!");
                            } else System.out.println("ERROR!!!");
                        break;
                    case 4:
                          System.out.println("Selecione o produto que deseja deletar:\n\n");
                          System.out.println(produtos);
                          index = scan.nextInt();
                          if(produtos.delete(index-1)){
                              System.out.println("Produto deletado com sucesso!!!");
                          }else System.out.println("ERROR!!!");
                        break;
                    case 5: continuaMenu = false;
                            break;
                }
            }
        }
    }

    public static Usuario readData(){
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("CPF:");
        String cpf = scan.next();
        System.out.println("Nome:");
        String nome = scan.next();
        System.out.println("E-mail:");
        String email = scan.next();
        System.out.println("Telefone:");
        String telefone = scan.next();
        System.out.println("Data:");
        String data = scan.next();
        LocalDate nascimento = LocalDate.parse(data, formatter);
        System.out.println("Setor:\n 1-Atendimento\n2-Cozinha\n3-Caixa\n4-Gerencia");
        int set = scan.nextInt();
        Setor setor;
        switch (set) {
            case 1:
                setor = Setor.ATENDIMENTO;
                break;
            case 2:
                setor = Setor.COZINHA;
                break;
            case 3:
                setor = Setor.CAIXA;
                break;
            case 4:
                setor = Setor.GERENCIA;
                break;
            default:
                setor = Setor.ATENDIMENTO;
        }
        System.out.println("Senha");
        String senha = scan.next();
        Usuario novousuario = new Usuario(cpf, nome, email, telefone, nascimento, setor, senha);
        return novousuario;
    }
    public static Produto readDataP(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Código:");
        int codigo = scan.nextInt();
        System.out.println("Nome:");
        String nome = scan.next();
        System.out.println("Descrição");
        String descricao = scan.next();
        System.out.println("Preço:");
        float preco = scan.nextFloat();
        Produto novoproduto = new Produto(codigo, nome, descricao, preco);
        return novoproduto;
    }
}
