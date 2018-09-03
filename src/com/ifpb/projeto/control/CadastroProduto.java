package com.ifpb.projeto.control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;

import com.ifpb.projeto.model.*;
/**
 * A classe CasastroProduto representa o CRUD de objetoc tipo Produtp.
 * Consulta, Cadastro, Edição e Exclusão.
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */

public class CadastroProduto {

    private Cardapio cardapio;

    public CadastroProduto() {
        cardapio = new Cardapio();
    }

    /**
     * Leitura de todos os produtos cadastrados
     * @return : Lista de produtos, caso não há nenhum, retorna mensagem de aviso.
     */
    public Cardapio getCardapio() {
        if (cardapio.isEmpty()){
            System.out.println("Não há produtos cadastrados!!!");
        }
        return cardapio;
    }

    /**
     * Função facilitadora para leitura de dados de um Produto, criada para evitar repetições de linha de código.
     * @return O produto lido.
     */
    public Produto readData(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Código:");
        int codigo = scan.nextInt();
        System.out.println("Nome:");
        String nome = scan.next();
        System.out.println("Descrição");
        String descricao = scan.next();
        System.out.println("Preço:");
        float preco = scan.nextFloat();
        Produto novoproduto = new Produto(codigo, nome, descricao, preco);
        return novoproduto;
    }

    /**
     * Função que cadastra um produto novo e adiciona no cardápio.
     * @return true
     */

    public boolean cadastrar(Produto novo) {
        cardapio.addProduto(novo);
        System.out.println("Produto cadastrado com sucesso!!!");
        return true;
    }

    /**
     * Função que consulta os dados de um produto.
     * @param codigo : O código do produto desejado.
     * @return String com dados do produto escolhido.
     */

    public String consulta(String codigo) {
        List<Produto> produtos = cardapio.getProdutos();
        for (Produto produto: produtos) {
            if(Objects.equals(codigo,produto.getCodigo())){
                return produto.toString();
            }
        }
        return "Este produto não existe!";
    }

    /**
     * Função que edita um produto.
     * @param index : A posição do produto da lista de cadastrados.
     * @return true caso o produto seja editado com sucesso
     * @return false caso a posição passada como parâmetro nao exista.
     */

    public boolean update(int index,Produto novo){
        if(index>cardapio.size()-1){
            return false;
        }
        cardapio.atualizar(index,novo);
        return true;
    }

    /**
     * Função que deleta um produto.
     * @param index : A posição do produto na lista de cadastrados.
     * @return true caso o produto seja deletado com sucesso.
     * @return  false caso a posição passada como parâmetro não exista.
     */

    public boolean delete(int index){
        if(index>cardapio.size()-1){
            return false;
        }
        cardapio.removeProduto(index);
        return true;
    }

    @Override
    public String toString() {
        if(cardapio.isEmpty()){
            return "Cardápio vazio!";
        }
        String str = ":.:.:.:.:.:PRODUTOS::.:.:.:.:.:\n";
        List<Produto> produtos = cardapio.getProdutos();
        int cont = 0;
        for (Produto produto:produtos) {
            cont++;
            str += cont+":    \n"+produto.toString();
        }
        return str;
    }
}


