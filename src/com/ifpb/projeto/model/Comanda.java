package com.ifpb.projeto.model;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import com.ifpb.projeto.model.Pedido;

public class Comanda {
   private List<Pedido> comanda;
   private float valorFinal;
   private int numMesa;
   private LocalDate data;
   private static int codigo;
   private int numComanda;

    //Construtor
    public Comanda(int numMesa) {
        numComanda = ++codigo;
        comanda = new ArrayList<>();
        this.valorFinal = 0f;
        this.numMesa = numMesa;
        data = LocalDate.now();
    }

    //Getters and Setters

    public float getValorFinal() {
        return valorFinal;
    }
    public float calcularValorFinal(){
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

    public void setValorFinal(float valorFinal) {
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

    public int getNumComanda() { return numComanda; }

    public void setNumComanda(int numComanda) { this.numComanda = numComanda; }

    public static int getCodigo() { return codigo; }

    public Pedido getPedido(int idPedido){
        for (Pedido pedido: comanda) {
            if(pedido.getNumeroPedido()==idPedido){
                return pedido;
            }
        }
        return null;
    }

    public void adicionarPedido(Pedido pedido){
        comanda.add(pedido);
        this.calcularValorFinal();
    }



    @Override
    public String toString() {
        String resultado = "|Mesa:"+numMesa+"|| Numero da comanda"+numComanda+"||Data: "+ data+"|\n";
        for (Pedido pedido:comanda) {
            resultado+=pedido.toString();
        }
        resultado+= "\nValor Final:"+valorFinal;
        return resultado;
    }
}
