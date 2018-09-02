package com.ifpb.projeto.model;

import java.util.Objects;
/**
 * A classe Produto modela a entidade produto do domínio da aplicação.
 *   Composto por atributos gerais de produtos.
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */

public class Produto {
    private int codigo;
    private String nome;
    private String descricao;
    private float preco;

    /**
     * Construtor da classe
     * @param codigo : Representa o código do produto
     * @param nome : Representa o nome do produto
     * @param descricao : Representa a descrição do produto
     * @param preco : Representa o preço do produto em reais
     */
    public Produto(int codigo, String nome, String descricao, float preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    /**
     * Getters e Setters
     */

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return codigo == produto.codigo &&
                Double.compare(produto.preco, preco) == 0 &&
                Objects.equals(nome, produto.nome) &&
                Objects.equals(descricao, produto.descricao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(codigo, nome, descricao, preco);
    }

    @Override
    public String toString() {
        return ".-----------------------------------------.\n"
                +"Nome: " + nome +"\n"
                +"Código: "+codigo+"\n"
                +"Preço: "+preco+"\n"
                +"Descriçãp: "+descricao+"\n"
                +".-----------------------------------------.\n";
    }
}

