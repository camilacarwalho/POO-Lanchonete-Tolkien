package com.ifpb.projeto.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Pedido{
    private Produto produto;
    private int quantidade;
    private LocalDate data;
    private LocalTime hora;
    private static int idPedido;
    private int numeroPedido;
    //Verificar se foi atendido ou não
    private boolean atendido;

    //Construtor
    public Pedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;

        //Seta os valores de data e de hora do pedido como data e hora em que o pedido foi realizado;
        data = LocalDate.now();
        hora = LocalTime.now();
        atendido = false;
        numeroPedido = ++idPedido;
    }


    //Getters and Setters

    public int getIdPedido() {
        return idPedido;
    }

    public float getValorTotal() {
        float valorTotal;
        valorTotal = quantidade* (produto.getPreco());
        return valorTotal;

    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
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

    public Produto getProduto() {return produto;}

    public void setProduto(Produto produto) {this.produto = produto;}

    public int getNumeroPedido() {return numeroPedido;}

    public void setNumeroPedido(int numeroPedido) {this.numeroPedido = numeroPedido;}

    public void editarAtendido(){
        if(!isAtendido()){
            setAtendido(true);
        }
    }

    @Override
    public String toString() {
        return "Pedido{" + "produto=" + produto.getNome()+", quantidade=" + quantidade + ", Numero do pedido="+numeroPedido+", valorTotal=" + getValorTotal() +"}\n";
    }
}
