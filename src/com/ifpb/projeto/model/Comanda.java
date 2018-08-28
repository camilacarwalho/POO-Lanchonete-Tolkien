package com.ifpb.projeto.model;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import com.ifpb.projeto.model.Pedido;

/**
 * A classe Comanda modela a entidade comanda do domínio da aplicação.
 *   Adiciona pedidos e verifica se foram atendidos ou não.
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */

public class Comanda {
   private List<Pedido> comanda;
   private int numMesa;
   private LocalDate data;
   private static int codigo;
   private int numComanda;


    /**
     * Construtor da classe.
     * @param numMesa : número da mesa
     */

    public Comanda(int numMesa) {
        numComanda = ++codigo;
        comanda = new ArrayList<>();
        this.numMesa = numMesa;
        data = LocalDate.now();
    }


    /**
     * Calcula o valor final da comanda
     * @return o valor final
     */

    public float getValorFinal() {

        float valorFinal = 0;
        for(int i=0;i<comanda.size();i++){
           valorFinal += comanda.get(i).getValorTotal();
        }
        return valorFinal;
    }

    /**
     * Retornar a comanda desejada
     * @return comanda
     */

    public List<Pedido> getComanda() {
        return comanda;
    }

    public void setComanda(List<Pedido> comanda) {
        this.comanda = comanda;
    }

    /**
     * Retornar o número da mesa
     * @return número da mesa
     */

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

    /**
     * Procurar um pedido pelo seu ID
     * @param idPedido : O número que representa o ID do pedido desejado
     * @return pedido
     */

    public Pedido getPedido(int idPedido){
        for (Pedido pedido: comanda) {
            if(pedido.getNumeroPedido()==idPedido){
                return pedido;
            }
        }
        return null;
    }
    /**
     * Adiciona um novo pedido a comanda
     * @param pedido : O pedido que deseja adicionar
     */
    public void adicionarPedido(Pedido pedido){
        comanda.add(pedido);
    }
    /**
     * Verifica se todos os pedidos foram atendidos
     * @return true, se foram atendidos
     * @return false, se não foram atendidos
     */

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
    /**
     * Procurar pelos pedidos não atendidos
     * @return string de todos os pedidos
     */

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
