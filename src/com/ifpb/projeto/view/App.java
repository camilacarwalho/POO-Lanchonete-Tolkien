package com.ifpb.projeto.view;

import com.ifpb.projeto.model.Comanda;
import com.ifpb.projeto.model.GerenciarMesa;
import com.ifpb.projeto.model.Pedido;
import com.ifpb.projeto.model.Setor;

public class App {
    public static void main(String[] args) {
        Setor setor = Setor.ATENDIMENTO;
        System.out.println(setor);

        GerenciarMesa gerenciar = new GerenciarMesa();
        gerenciar.gerarComanda(1);
        Pedido pedido = new Pedido("Pao",0.5,2);
        gerenciar.fazerPedido(1,pedido);
        gerenciar.verPedidos(1);
    }

}
