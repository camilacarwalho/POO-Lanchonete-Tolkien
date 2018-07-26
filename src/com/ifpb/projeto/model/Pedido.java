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

    public Pedido(String nome, double valorUnitario, int quantidade, LocalDate data, LocalTime hora) {
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.valorTotal = quantidade*valorUnitario;
        this.data = data;
        this.hora = hora;
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
}
