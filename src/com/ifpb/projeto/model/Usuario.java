package com.ifpb.projeto.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

//Modelagem de Usuario
public class Usuario {

    //Abaixo se econtram os dados base do cadastro de um usuário;

    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate nascimento;
    private Setor setor;
    private String senha;

    //Construtor
    public Usuario(String cpf, String nome, String email, String telefone, LocalDate nascimento, Setor setor, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.setor = setor;
        this.senha = senha;
    }

    //Getters and Setters

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


    //Caso precise de idade
    public int getIdade(){
        Period period = Period.between(nascimento,
                LocalDate.now());

        return period.getYears();
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setSetor(int setor) {
        if (setor == Setor.ATENDIMENTO.getID()) {
            setSetor(Setor.ATENDIMENTO);
        } else if (setor == Setor.COZINHA.getID()) {
            setSetor(Setor.COZINHA);
        } else if (setor == Setor.CAIXA.getID()) {
            setSetor(Setor.CAIXA);
        } else if (setor == Setor.GERENCIA.getID()){
            setSetor(Setor.GERENCIA);
        }
    }

    //FUNÇÕES COMUNS PARA CADA USUÁRIO;

    //Verifica a autenticação do usuário;
    //SUJEITO A MUDANÇAS, POR FAVOR FALAR COM MAILSU NO WHATS E NÃO DAR VÁCUO NELE <3;
    public boolean autentication(String email, String senha){
        return Objects.equals(this.email, email) && Objects.equals(this.senha, senha);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(cpf, usuario.cpf) &&
                Objects.equals(nome, usuario.nome) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(telefone, usuario.telefone) &&
                Objects.equals(nascimento, usuario.nascimento) &&
                setor == usuario.setor;
    }

    @Override
    public int hashCode() {

        return Objects.hash(cpf, nome, email, telefone, nascimento, setor);
    }
}

