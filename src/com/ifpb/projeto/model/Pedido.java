package com.ifpb.projeto.model;

import com.ifpb.projeto.Exceptions.QuantidadePorPedidoPositivaException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * A classe Pedido modela a entidade que representa um modelo no domínio da aplicação.
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */

public class Pedido implements Serializable {
    private Produto produto;
    private int quantidade;
    private LocalDate data;
    private LocalTime hora;
    private static int idPedido;
    private int numeroPedido;
    //Verificar se foi atendido ou não
    private boolean atendido;

    /**
     * Construtor da classe
     * Atributos como data e hora serão setados como o momento em que o pedido for realizado.
     * Atributo atendido é usado como facilitador no tratamento de atendimento de pedidos.
     * @param produto Recebe o produto que deve ser adicionado como Pedido.
     * @param quantidade Recebe um número inteiro representando a quantidade de produtos
     */
    public Pedido(Produto produto, int quantidade) throws QuantidadePorPedidoPositivaException {
        if(quantidade<=0){
            throw new QuantidadePorPedidoPositivaException("A quantidade de produtos por pedido tem que ser positiva!");
        }
        this.produto = produto;
        this.quantidade = quantidade;
        data = LocalDate.now();
        hora = LocalTime.now();
        atendido = false;
        numeroPedido = ++idPedido;
    }

    /**
     * Função que retorna a ID de um pedido
     * @return : ID do pedido
     */

    public static int getIdPedido() {
        return idPedido;
    }

    /**
     * Função que seta a ID de um pedido
     * @param ID : inteiro com a ID que deseja setar
     */

    public static void setIdPedido(int ID){
        idPedido = ID;
    }

    /**
     * Função que retorna o valor total de um pedido
     * @return float com o valor total
     */


    public float getValorTotal() {
        float valorTotal;
        valorTotal = quantidade* (produto.getPreco());
        return valorTotal;

    }

    /**
     * Função que retorna a quantidade de produtos por pedido
     * @return inteiro com a quantidade econtrada
     */

    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Função que seta a quantidade de produtos por pedido
     * @param quantidade : inteiro com a quantidade desejada
     * @throws QuantidadePorPedidoPositivaException
     */

    public void setQuantidade(int quantidade) throws QuantidadePorPedidoPositivaException {
        if(quantidade<=0){
            throw new QuantidadePorPedidoPositivaException("A quantidade de produtos por pedido tem que ser positiva!");
        }
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() { return hora; }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto){
        this.produto = produto;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public void editarAtendido(){
        if(!isAtendido()){
            setAtendido(true);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return quantidade == pedido.quantidade &&
                Objects.equals(produto, pedido.produto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(produto, quantidade);
    }

    @Override
    public String toString() {
        return "Pedido{" + "produto=" + produto.getNome()+", quantidade=" + quantidade + ", Numero do pedido="+numeroPedido+", valorTotal=" + getValorTotal() +"}\n";
    }
}
