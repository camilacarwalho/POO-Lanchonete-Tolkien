package com.ifpb.projeto.view;
import java.time.LocalDate;
import java.util.Scanner;

import com.ifpb.projeto.model.*;

import javax.xml.bind.SchemaOutputResolver;


public class App {
    public static void main(String[] args) {
        Cozinha c = new Cozinha();
        GerenciarMesa m = new GerenciarMesa();

        Produto pao = new Produto(1, "pao","É um pao hasudhas", 0.30f);
        Produto cafe = new Produto(2,"café","É bom",0.50f);
        Produto pastel = new Produto(3,"pastel","Dá gastrite", 1.50f);
        Produto bolo = new Produto(4, "bolo","de milho",1.00f);

        Usuario teste = new Usuario("111.111.111-01","Lucas", "teste@gmail","11",LocalDate.now(),Setor.GERENCIA, "1234");
        boolean continua = true;
        int seleciona;
        Scanner scan = new Scanner(System.in);
        int mesa;

        while(continua){

            System.out.println("1: Fazer um pedido!");
            System.out.println("2: Editar um pedido!");
            System.out.println("3: Adicionar comanda!");
            System.out.println("4: Encerrar comanda!");
            System.out.println("5: Atender pedido!");
            System.out.println("6: Ver pedidos!");
            System.out.println("7: Testar autenticação:");
            System.out.println("8: Ver comandas em intervalo de tempo");
            System.out.println("9: Ver as informações de uma comanda");
            System.out.println("0: Encerrar programa");

            seleciona = scan.nextInt();

            if(seleciona<0||seleciona>9){
                System.out.println("ERROR:Press ENTER para continar!");
                scan.nextLine();
            }
            else{
                switch(seleciona){
                    case 1:System.out.println("selecione um pedido:");
                           System.out.println("Digite 1:"+pao);
                           System.out.println("Digite 2:"+cafe);
                           System.out.println("Digite 3:"+pastel);
                           System.out.println("Digite 4:"+bolo);
                           int pedido = scan.nextInt();
                           if(pedido<0||pedido>9){
                               System.out.println("ERROR:Press ENTER para continar!");
                               scan.nextLine();
                           }
                           else{
                               System.out.println("Informe a quantidade:");
                               int quatidade = scan.nextInt();
                               System.out.println("Informe o numero da mesa:");
                               mesa = scan.nextInt();
                               switch (pedido){
                                   case 1: System.out.println(m.fazerPedido(mesa,new Pedido(pao,quatidade)));
                                       c.adicionarPedido(new Pedido(pao,quatidade));
                                       break;
                                   case 2: System.out.println(m.fazerPedido(mesa,new Pedido(cafe,quatidade)));
                                       c.adicionarPedido(new Pedido(cafe,quatidade));
                                       break;
                                   case 3: System.out.println(m.fazerPedido(mesa,new Pedido(pastel,quatidade)));
                                       c.adicionarPedido(new Pedido(pastel,quatidade));
                                       break;
                                   case 4: System.out.println(m.fazerPedido(mesa,new Pedido(bolo,quatidade)));
                                       c.adicionarPedido(new Pedido(bolo,quatidade));
                                       break;
                               }
                           }
                           break;
                    case 2: System.out.println("Não implementado ainda");
                            break;
                    case 3: System.out.println("Informe a mesa");
                            mesa = scan.nextInt();
                            System.out.println(m.gerarComanda(mesa));
                            break;
                    case 4: System.out.println("Informe a mesa");
                            mesa = scan.nextInt();
                            System.out.println(m.encerrarComanda(mesa));
                            break;
                    case 5: System.out.println("Informe o ID do pedido");
                            int id = scan.nextInt();
                            System.out.println(c.atendePedido(id));
                            break;
                    case 6: System.out.println("Informe a mesa");
                            mesa = scan.nextInt();
                            System.out.println(m.verPedidos(mesa));
                            break;
                    case 7: System.out.println("Informe o email:");
                            String email = scan.next();
                            System.out.println("Informe a senha");
                            String senha = scan.next();
                            System.out.println(teste.autentication(email,senha));
                            break;
                    case 8: System.out.println("Informe o ano:");
                            int ano = scan.nextInt();
                            System.out.println("Informe o mes:");
                            int mes = scan.nextInt();
                            System.out.println("Informe o dia:");
                            int dia = scan.nextInt();
                            LocalDate inicio = LocalDate.of(ano,mes,dia);
                            System.out.println("inicio:"+inicio);
                            System.out.println("Informe o ano:");
                            ano = scan.nextInt();
                            System.out.println("Informe o mes:");
                            mes = scan.nextInt();
                            System.out.println("Informe o dia:");
                            dia = scan.nextInt();
                            LocalDate fim = LocalDate.of(ano,mes,dia);
                            System.out.println("Fim:"+ fim);
                            System.out.println(Gerencia.between(inicio,fim));
                            break;
                    case 9: System.out.println("Informe a mesa");
                            mesa = scan.nextInt();
                            System.out.println(m.getComanda(mesa));
                            break;
                    case 0: continua = false;
                }
            }
            limpaMinhaTelaUltraAdvancedMaisMais();
        }
        System.out.println("---------FIM---------");

    }
    public static void limpaMinhaTelaUltraAdvancedMaisMais(){
        for (int i=0; i<40;i++){
            System.out.println("\n");
        }
    }
}
