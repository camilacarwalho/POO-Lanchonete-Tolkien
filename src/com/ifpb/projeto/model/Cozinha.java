package com.ifpb.projeto.model;

import java.util.ArrayList;
import java.util.List;

public class Cozinha {
    private List<Pedido> pedidos;

    //Construtor
    public Cozinha(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    //Getters and Setters

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    //Esta função remove um pedido da Lista da cozinha;
    public void atendePedido(Pedido pedido){
        //Testa se o pedido ainda não foi atendido;
        if (!pedido.isAtendido()) pedidos.remove(pedido);
    }
}
