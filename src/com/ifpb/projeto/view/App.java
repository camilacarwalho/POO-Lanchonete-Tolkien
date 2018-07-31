package com.ifpb.projeto.view;
import java.time.LocalDate;
import java.util.Scanner;

import com.ifpb.projeto.model.*;


public class App {
    public static void main(String[] args) {
        System.out.println("=^..^=   =^..^=   =^..^=    Testes    =^..^=    =^..^=    =^..^=");
        Scanner scanner = new Scanner(System.in);
        System.out.println("---Testando Comanda e GerenciarMesa---");
        GerenciarMesa gerenciar = new GerenciarMesa();
        System.out.println("Numero da mesa:");
        int numeroMesa = scanner.nextInt();
        gerenciar.gerarComanda(numeroMesa);


        System.out.println("---Testando Produto---");
       System.out.println("Código:");
        int codigo = scanner.nextInt();
        System.out.println("Nome:");
        String nomeP = scanner.nextLine();
        System.out.println("Descrição");//REVER pq essa parte pula???
        String descricao = scanner.nextLine();
        System.out.println("Preço");
        double preco = scanner.nextDouble();
        Produto produto = new Produto(codigo,nomeP,descricao,preco);


        System.out.println("---Testando Pedido---");
        System.out.println("Quantidade:");
        int quantidade = scanner.nextInt();
        Pedido pedido = new Pedido(produto,quantidade);
        gerenciar.fazerPedido(numeroMesa,pedido);
        System.out.println(pedido.toString());

        System.out.println("---Testando Comanda---");
        Comanda comanda = new Comanda(numeroMesa);
        comanda.adicionarPedido(pedido);
        gerenciar.verPedidos(numeroMesa);

        System.out.println("---Testando Usuario---");
        System.out.println("CPF:");//Aqui tb ta pulando
        String cpf = scanner.nextLine();
        System.out.println("Nome");
        String nomeU = scanner.nextLine();
        System.out.println("Email");
        String email = scanner.nextLine();
        System.out.println("Telefone");
        String telefone = scanner.nextLine();
        System.out.println("Senha:");
        String senha = scanner.nextLine();
        Setor setor = Setor.GERENCIA; //Ver como faz para pegar um enum
        Usuario usuario = new Usuario(cpf,nomeP,email,telefone,LocalDate.now(),setor,senha);
        System.out.println(usuario.toString());




    }

}
