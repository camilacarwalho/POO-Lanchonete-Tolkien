package com.ifpb.projeto.model;

import com.ifpb.projeto.Exceptions.PedidoNaoExisteException;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe Cozinha modela a entidade cozinha do domínio da aplicação.
 *   Composto por uma lista de pedidos
 *   Adicionar e atender pedidos
 *   Essa classe foi simplificada para <b>facilitar o desenvolvimento</b>
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */


public class Cozinha {
    private List<Pedido> pedidos;


    /**
     * Construtor da classe.
     */
    public Cozinha() {
        pedidos = new ArrayList<Pedido>();
    }



    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }


    /**
     * Retornar um pedido pelo seu id
     * @param idPedido : número do id do pedido desejado
     * @return o pedido encontrado
     * @return nulo caso o pedido não seja encontrado
     */

    public Pedido getPedido(int idPedido){
        for (Pedido pedido:pedidos) {
            if(pedido.getNumeroPedido()==idPedido){
                return pedido;
            }
        }
        return null;
    }
    /**
     * Adicionar um objeto do tipo Pedido
     * @param pedido : objeto
     */
    public void adicionarPedido(Pedido pedido){
        pedidos.add(pedido);

    }
    /**
     * Remover um pedido da Lista da cozinha
     * Atende o pedido
     * @param idPedido : número do id do pedido desejado
     * @return true caso o pedido seja removido
     * @return false caso o pedido já tenha sido atendido
     */
    //Esta função remove um pedido da Lista da cozinha;
    public boolean atendePedido(int idPedido) throws PedidoNaoExisteException {
        if(getPedido(idPedido)==null){
            throw new PedidoNaoExisteException("Não existe nenhum pedido com este código!");
        }
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
