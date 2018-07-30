package com.ifpb.projeto.view;
import java.util.Scanner;

import com.ifpb.projeto.model.*;


public class App {
    public static void main(String[] args) {
        Setor setor = Setor.ATENDIMENTO;
        System.out.println(setor);

        GerenciarMesa gerenciar = new GerenciarMesa();
        gerenciar.gerarComanda(1);
        Produto produto = new Produto(234,"Pao","eh mt bom",0.50);
        Pedido pedido = new Pedido(produto,3);
        gerenciar.fazerPedido(1,pedido);
        gerenciar.verPedidos(1);
    }

}
