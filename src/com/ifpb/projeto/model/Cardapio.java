package com.ifpb.projeto.model;
import java.io.Serializable;
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

public class Cardapio implements Serializable {

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
     * Função geral que adiciona um produto
     * @param produto : O produto que deseja adicionar
     */

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    /**
     * Função geral que remove um produto
     * @param index : índice do produto desejado
     */
    public void removeProduto(int index) {
        produtos.remove(index);
    }

    /**
     * Função geral que atualiza um produto
     * @param index : o índice do produto desejado
     * @param novo : o produto com valores atualizados
     */

    public void atualizar(int index, Produto novo){
        produtos.set(index, novo);
    }

    /**
     * Função que verifica se a lista de produtos está vazia
     * @return true se estiver vazia, false  se não
     */
    public boolean isEmpty(){
        return produtos.isEmpty();
    }

    /**
     * Função que verifica o tamamho da lista de produtos
     * @return inteiro com o tamanho da lista
     */
    public int size(){
        return produtos.size();
    }
}

