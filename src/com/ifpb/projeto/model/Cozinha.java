package com.ifpb.projeto.model;

import java.util.ArrayList;
import java.util.List;

public class Cozinha {
    private List<Pedido> pedidos;

    //Construtor
    public Cozinha(List<Pedido> pedidos) {
        pedidos = new ArrayList<Pedido>();
    }

    //Getters and Setters

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    //Esta função remove um pedido da Lista da cozinha;
    public boolean atendePedido(Comanda comanda, int idPedido){

        //Testa se o pedido ainda não foi atendido;
        if(comanda.editarAtendido(idPedido)){
            for (Pedido pedido: pedidos) {
                if(pedido.getIdPedido()==idPedido){
                    pedidos.remove(pedido);
                    return true;
                }
            }
        }
        return false;
    }
}
