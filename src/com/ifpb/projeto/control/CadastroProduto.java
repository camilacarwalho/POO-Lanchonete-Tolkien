package com.ifpb.projeto.control;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.projeto.Exceptions.ProdutoInexistenteException;
import com.ifpb.projeto.model.*;
/**
 * A classe CasastroProduto representa o CRUD de objetoc tipo Produto.
 * Consulta, TelaDeCadastro, Edição e Exclusão.
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */

public class CadastroProduto {

    private static File fileCardapio = new File("Produtos");

    public static List<Produto> getProdutos() throws IOException, ClassNotFoundException {
        List<Produto> produtos = new ArrayList<>();
        if (!fileCardapio.exists()) {
            fileCardapio.createNewFile();
        } else {
            if (fileCardapio.length() > 0) {
                try (ObjectInputStream in = new ObjectInputStream(
                        new FileInputStream(fileCardapio))) {
                    produtos = (List<Produto>) in.readObject();
                }
            }
        }
        return produtos;
    }

    public static boolean add(Produto novo) throws IOException, ClassNotFoundException {
        List<Produto> produtos = getProdutos();
        if(buscarPorCodigo(novo.getCodigo())==null){
            produtos.add(novo);
            atualizarArquivo(produtos);
            return true;
        }
        return false;
    }

    public static void atualizarArquivo(List<Produto> produtos) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(fileCardapio))){
            out.writeObject(produtos);
        }
    }

    public static Produto buscarPorCodigo(int codigo) throws IOException, ClassNotFoundException {
        List<Produto> produtos = getProdutos();
        if(produtos.size()==0){
            return null;
        }else{
            for(Produto p: produtos){
                if(p.getCodigo()==codigo) return p;
            }
            return null;
        }
    }

    public static boolean remove(int codigo) throws IOException, ClassNotFoundException{
        List<Produto> produtos = getProdutos();
        if(produtos.size()==0){
            return false;
        }else{
            Produto p = buscarPorCodigo(codigo);
            if(p!=null){
                produtos.remove(p);
                atualizarArquivo(produtos);
                return true;
            }
        }
        return false;
    }

    public static boolean update(int codigo, Produto novo) throws IOException, ClassNotFoundException, ProdutoInexistenteException {
        List<Produto> produtos = getProdutos();
        Produto antigo = buscarPorCodigo(codigo);
        if(antigo!=null){
            produtos.add(produtos.indexOf(antigo),novo);
            atualizarArquivo(produtos);
            return true;
        }
        throw new ProdutoInexistenteException("O produto selecionado não existe");
    }


//    private File fileCardapio;
//    private Cardapio cardapio;
//
//    public CadastroProduto() throws IOException, ClassNotFoundException {
//        fileCardapio = new File("Cardapio");
//        if(!fileCardapio.exists()){
//            fileCardapio.createNewFile();
//            cardapio = new Cardapio();
//        }else{
//            if(fileCardapio.length()>0){
//                try(ObjectInputStream in = new ObjectInputStream(
//                        new FileInputStream(fileCardapio))){
//                    cardapio = (Cardapio) in.readObject();
//                }
//
//            }else cardapio = new Cardapio();
//        }
//    }
//
//    public void atualizarArquivo() throws IOException {
//        try(ObjectOutputStream out = new ObjectOutputStream(
//                new FileOutputStream(fileCardapio))){
//            out.writeObject(cardapio);
//        }
//    }
//
//    /**
//     * Leitura de todos os produtos cadastrados
//     * @return : Lista de produtos, caso não há nenhum, retorna mensagem de aviso.
//     */
//    public Cardapio getCardapio() {
//        if (cardapio.isEmpty()){
//            System.out.println("Não há produtos cadastrados!!!");
//        }
//        return cardapio;
//    }
//
//    /**
//     * Função que cadastra um produto novo e adiciona no cardápio.
//     * @return true
//     */
//
//    public boolean cadastrar(Produto novo) throws IOException {
//        cardapio.addProduto(novo);
//        atualizarArquivo();
//        return true;
//    }
//
//    /**
//     * Função que consulta os dados de um produto.
//     * @param codigo : O código do produto desejado.
//     * @return String com dados do produto escolhido.
//     */
//
//    public Produto consulta(String codigo) {
//        List<Produto> produtos = cardapio.getProdutos();
//        for (Produto produto: produtos) {
//            if(Objects.equals(codigo,produto.getCodigo())){
//                return produto;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Função que edita um produto.
//     * @param index : A posição do produto da lista de cadastrados.
//     * @return true caso o produto seja editado com sucesso
//     * @return false caso a posição passada como parâmetro nao exista.
//     */
//
//    public boolean update(int index,Produto novo) throws IOException {
//        if(index>cardapio.size()-1){
//            return false;
//        }
//        cardapio.atualizar(index,novo);
//        atualizarArquivo();
//        return true;
//    }
//
//    /**
//     * Função que deleta um produto.
//     * @param index : A posição do produto na lista de cadastrados.
//     * @return true caso o produto seja deletado com sucesso.
//     * @return  false caso a posição passada como parâmetro não exista.
//     */
//
//    public boolean delete(int index) throws IOException {
//        if(index>cardapio.size()-1){
//            return false;
//        }
//        cardapio.removeProduto(index);
//        atualizarArquivo();
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        if(cardapio.isEmpty()){
//            return "Cardápio vazio!";
//        }
//        String str = ":.:.:.:.:.:PRODUTOS::.:.:.:.:.:\n";
//        List<Produto> produtos = cardapio.getProdutos();
//        int cont = 0;
//        for (Produto produto:produtos) {
//            cont++;
//            str += cont+":    \n"+produto.toString();
//        }
//        return str;
//    }
}


