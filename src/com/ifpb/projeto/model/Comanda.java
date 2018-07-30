package com.ifpb.projeto.model;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import com.ifpb.projeto.model.Pedido;

public class Comanda {
   private List<Pedido> comanda;
   private double valorFinal;
   private int numMesa;
   private static LocalDate data;
   private static int numComanda;

    //Construtor
    public Comanda(int numMesa) {
        numComanda++;
        comanda = new ArrayList<>();
        this.valorFinal = 0;
        this.numMesa = numMesa;
        data = LocalDate.now();
    }

    //Getters and Setters

    public double getValorFinal() { //Retorna valor total da comanda, junção de todos os pedidos
        for(int i=0;i<comanda.size();i++){
            this.valorFinal += comanda.get(i).getValorTotal();
        }
        return valorFinal;
    }

    public List<Pedido> getComanda() {
        return comanda;
    }

    public void setComanda(List<Pedido> comanda) {
        this.comanda = comanda;
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

    public LocalDate getData() {
        return data;
    }

    public static int getNumComanda() {
        return numComanda;
    }

    public static void setNumComanda(int numComanda) {
        Comanda.numComanda = numComanda;
    }

    public void adicionarPedido(Pedido pedido){
        comanda.add(pedido);
        this.getValorFinal();
    }
    
    public boolean editarAtendido(int idPedido){
        for (Pedido pedido:comanda) {
            if (pedido.getIdPedido()==idPedido) {
                pedido.setAtendido(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "comanda=" + numComanda +
                ", valorFinal=" + valorFinal +
                ", numMesa=" + numMesa +
                '}';
    }
}
