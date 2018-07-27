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
    //Verificar se foi atendido ou não
    private boolean atendido;

    public Pedido(String nome, double valorUnitario, int quantidade, LocalDate data, LocalTime hora) {
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.valorTotal = quantidade*valorUnitario; //Calcular valor total de um pedido
        this.data = data;
        this.hora = hora;
        this.atendido = false;
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

    public LocalDate getData() {return data;}

    public void setData(LocalDate data) {this.data = data; }

    public LocalTime getHora() {return hora;}

    public void setHora(LocalTime hora) {this.hora = hora;}

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }
}
