package com.ifpb.projeto.model;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.lang.Object;
import com.ifpb.projeto.model.Produto;

public class Cardapio {
    private List<Produto> produtos;

    public Cardapio(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    }

