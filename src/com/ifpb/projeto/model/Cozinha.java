package com.ifpb.projeto.model;

import java.util.ArrayList;
import java.util.List;

public class Cozinha {
    private List<Pedido> pedidos;

    //Construtor
    public Cozinha() {
        pedidos = new ArrayList<Pedido>();
    }

    //Getters and Setters

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedido getPedido(int idPedido){
        for (Pedido pedido:pedidos) {
            if(pedido.getNumeroPedido()==idPedido){
                return pedido;
            }
        }
     return null;
    }

    public void adicionarPedido(Pedido pedido){
        pedidos.add(pedido);

    }

    //Esta função remove um pedido da Lista da cozinha;
    public boolean atendePedido(int idPedido){
        if(getPedido(idPedido)==null) return false;
        //Testa se o pedido ainda não foi atendido;
        if(!getPedido(idPedido).isAtendido()){
            for (Pedido pedido: pedidos) {
                if(pedido.getNumeroPedido()==idPedido){
                    pedido.editarAtendido();
                    pedidos.remove(pedido);
                    return true;
                }
            }
        }
        return false;
    }
}
