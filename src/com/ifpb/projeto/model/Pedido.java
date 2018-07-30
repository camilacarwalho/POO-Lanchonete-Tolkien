package com.ifpb.projeto.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Pedido{
    private String nome;
    private double valorUnitario;
    private int quantidade;
    private double valorTotal;
    private LocalDate data;
    private LocalTime hora;
    private static int idPedido;
    //Verificar se foi atendido ou não
    private boolean atendido;

    //Construtor
    public Pedido(String nome, double valorUnitario, int quantidade) {
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        valorTotal = quantidade*valorUnitario; //Calcular valor total de um pedido
        //Seta os valores de data e de hora do pedido como data e hora em que o pedido foi realizado;
        data = LocalDate.now();
        hora = LocalTime.now();
        atendido = false;
        idPedido++;
    }


    //Getters and Setters

    public int getIdPedido() {
        return idPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
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

    public LocalTime getHora() {
        return hora;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }
}
