package com.ifpb.projeto.model;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.lang.Object;
import com.ifpb.projeto.model.Produto;

/**
 * A classe Cardapio modela a entidade cardápio do domínio da aplicação.
 *   Composto por uma lista de produtos.
 *   Receber produtos, modificar e excluir existentes.
 *   Essa classe foi simplificada para <b>facilitar o desenvolvimento</b>
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */

public class Cardapio {

    private List<Produto> produtos;
    /**
     * Construtor da classe.
     */
    public Cardapio() {
        produtos = new ArrayList<Produto>();
    }

   /**
     * Retorna os produtos como objeto inteiro
     * @return produtos
     */

    public List<Produto> getProdutos() {
        return produtos;
    }


    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }


    /**
     * Funções gerais para cardápio
     * @param produto
     */

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removeProduto(Produto produto) {
        produtos.remove(produto);}

    public void editaProduto(Produto produto, int indice){
        produtos.set(indice,produto);
    }

}

