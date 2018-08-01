package com.ifpb.projeto.model;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import com.ifpb.projeto.model.Pedido;

public class Comanda {
   private List<Pedido> comanda;
   private int numMesa;
   private LocalDate data;
   private static int codigo;
   private int numComanda;

    //Construtor
    public Comanda(int numMesa) {
        numComanda = ++codigo;
        comanda = new ArrayList<>();
        this.numMesa = numMesa;
        data = LocalDate.now();
    }

    //Getters and Setters

    public float getValorFinal() {

        float valorFinal = 0;
        for(int i=0;i<comanda.size();i++){
           valorFinal += comanda.get(i).getValorTotal();
        }
        return valorFinal;
    }

    public List<Pedido> getComanda() {
        return comanda;
    }

    public void setComanda(List<Pedido> comanda) {
        this.comanda = comanda;
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
    }

    public boolean allIsAtendido(){
        boolean verifica = true;
        for (Pedido pedido:comanda) {
            if(!pedido.isAtendido()){
                verifica = false;
                break;
            }
        }
        return verifica;
    }

    public String pedidosNaoAtendidos(){
        String s = "";
        for (Pedido pedido:comanda) {
            if(!pedido.isAtendido()){
                s+=pedido.toString();
            }
        }
        if(s.equals("")){
            return "Todos os pedidos desta mesa ja foram atendidos";
        }
        return s;
    }

    @Override
    public String toString() {
        String resultado = "|Mesa:"+numMesa+"|| Numero da comanda"+numComanda+"||Data: "+ data+"|\n";
        for (Pedido pedido:comanda) {
            resultado+=pedido.toString();
        }
        resultado+= "\nValor Final:"+getValorFinal()+"\n";
        return resultado;
    }
}
