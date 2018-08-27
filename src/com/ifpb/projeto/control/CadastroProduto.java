package com.ifpb.projeto.control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;

import com.ifpb.projeto.model.*;

public class CadastroProduto {

    private List<Produto> cardapio;

    public CadastroProduto() {
        cardapio = new ArrayList<>();
    }

    public List<Produto> getCardapio() {
        if (cardapio.isEmpty()){
            System.out.println("Não há produtos cadastrados!!!");
        }
        return cardapio;
    }

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

    public boolean cadastrar() {
        Produto novo =  readData();
        cardapio.add(novo);
        return true;
    }



    public String consulta(String nome) {

        return "";
    }

    public boolean update(int index){
        if(index>cardapio.size()-1){
            return false;
        }
        Produto novo = readData();
        cardapio.add(index,novo);
        return true;
    }

    public boolean delete(int index){
        if(index>cardapio.size()-1){
            return false;
        }
        cardapio.remove(index);
        return true;
    }

    @Override
    public String toString() {
        if(cardapio.isEmpty()){
            return "Cardápio vazio!";
        }
        String str = ":.:.:.:.:.:PRODUTOS::.:.:.:.:.:\n";
        int cont = 0;
        for (Produto produto:cardapio) {
            cont++;
            str += cont+":    \n"+produto.toString();
        }
        return str;
    }
}


