package com.ifpb.projeto.view;

import com.ifpb.projeto.model.Pedido;
import com.ifpb.projeto.model.Comanda;

public class App {
    public static void main(String[] args) {
        Pedido pedido1 = new Pedido("Pão",4,5);
        System.out.println(pedido1.getValorTotal());


    }

}
