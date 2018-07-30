package com.ifpb.projeto.model;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.lang.Object;
import com.ifpb.projeto.model.Produto;

public class Cardapio {

    //O cardapio vai ser composto apenas por uma lista de produtos;
    //A lista poderá receber novos produtos;
    //Modificar produtos atuais;
    //Excluir produtos já existentes;
    private List<Produto> produtos;

    //Construtor
    public Cardapio() {
        produtos = new ArrayList<Produto>();
    }

    //Getters and Setters

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }


    //FUNÇÕES COMUNS PARA CADA CARDÁPIO;
    //TODAS SUJEITAS A MUDANÇA;
    //POR FAVOR FALAR COM MAILSU NO WHATS E NÃO DAR VÁCUO NELE <3;
    //EU N TE DOU VACUO DESGRAÇA

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removeProduto(Produto produto) {
        produtos.remove(produto);}

    public void editaProduto(Produto produto, int indice){
        produtos.set(indice,produto);
    }
    //Sujeito a mudanças na segunda fase;

    //Salvar,Excluir e Editar um produto
}

