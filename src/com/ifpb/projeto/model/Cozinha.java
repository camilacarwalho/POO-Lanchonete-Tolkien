package com.ifpb.projeto.model;

import java.util.ArrayList;
import java.util.List;

public class Cozinha {
    private List<Pedido> pedidos;

    public Cozinha(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void atendePedido(Pedido pedido){
        if (pedido.isAtendido()) pedido.setAtendido(false);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
