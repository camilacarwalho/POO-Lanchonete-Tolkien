package com.ifpb.projeto.model;
import java.util.List;

import com.ifpb.projeto.model.Pedido;

public class Comanda {
   private List<Pedido> pedidos;
   private double valorFinal;
   private int numMesa;
   private static int numComanda;

    public Comanda(List<Pedido> pedidos, int numMesa) {
        this.pedidos = pedidos;
        this.valorFinal = 0;
        this.numMesa = numMesa;
    }

    public static int getNumComanda() {
        return numComanda; //Perguntar a Yan
    }

    public static void setNumComanda(int numComanda) {
        Comanda.numComanda = numComanda;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public double getValorFinal() {
        for(int i=0;i<pedidos.size();i++) this.valorFinal += pedidos.get(i).getValorTotal();
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    //atender, editar um pedido
}
