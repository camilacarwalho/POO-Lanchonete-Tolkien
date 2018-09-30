package com.ifpb.projeto.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
/**
 * A classe Usuário representa os dados de um usuário do dompinio da aplicação.
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */
public class Usuario {



    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate nascimento;
    private Setor setor;
    private String senha;

    /**
     * Construtor da classe
     * @param cpf : O CPF de um usuário.
     * @param nome : O nome de um usuário.
     * @param email :  O e-mail de um usuário.
     * @param telefone : O número de telefone de um usuário para contato.
     * @param nascimento : A data de nascimento do usuário.
     * @param setor : O setor em que o usuário atua.
     * @param senha : A senha de um usuário para autenticação.
     */
    public Usuario(String cpf, String nome, String email, String telefone, LocalDate nascimento, Setor setor, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.setor = setor;
        this.senha = senha;
    }

    /**
     * Getters e Setters
     */

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


    /**
     * Função criada para caso seja necessário saber a idade do usuário
     * @return Idade do usuário
     */
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


    /**
     * Função interna que realiza a autenticação de um usuário
     * @param email : O email escolhido pelo usuário no cadastro,
     * @param senha : A senha escolhida pelo usuário no cadastro.
     * @return true, caso a autenticação seja feita.
     * @return false, caso o email e senha não formem o usuário.
     */
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

        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return ".-----------------------------------------.\n"
                +"Nome: " + nome +"\n"
                +"CPF: "+cpf+"\n"
                +"E-mail: "+email+"\n"
                +"Telefone"+telefone+"\n"
                +"Setor: "+setor+"\n"
                +".-----------------------------------------.\n";
    }
}

