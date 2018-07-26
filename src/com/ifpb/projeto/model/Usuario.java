package com.ifpb.projeto.model;

import java.time.LocalDate;
import java.time.Period;

public class Usuario {
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate nascimento;
    private String setor;

    public Usuario(String cpf, String nome, String email, String telefone, LocalDate nascimento, String setor) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.setor = setor;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public int getIdade(){
        Period period = Period.between(nascimento,
                LocalDate.now());

        return period.getYears();
    }

}

