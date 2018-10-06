package com.ifpb.projeto.control;

import com.ifpb.projeto.model.Comanda;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe GerenciaComandasFechadas representa um Controle sobre todas as comandas ja encerradas.
 *   Responsável por auxiliar na busca de comandas em um período determinado
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */

public class GerenciaComandasFechadas {

    private static File comandasFechadas = new File("Fechadas");

    public static List<Comanda> getComandas() throws IOException, ClassNotFoundException {
        List<Comanda> comandas = new ArrayList<>();
        if (!comandasFechadas.exists()) {
            comandasFechadas.createNewFile();
        } else {
            if (comandasFechadas.length() > 0) {
                try (ObjectInputStream in = new ObjectInputStream(
                        new FileInputStream(comandasFechadas))) {
                    comandas = (List<Comanda>) in.readObject();
                }
            }
        }
        return comandas;
    }

    public static void atualizarArquivo(List<Comanda> comandas) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(comandasFechadas))){
            out.writeObject(comandas);
        }
    }

    /**
     * Esta função será invocada sempre que uma comanda por encerrada, para manter comandas no histórico da aplicação
     * @param comanda : O objeto comanda desejado
     */
    public static void addComanda(Comanda comanda) throws IOException, ClassNotFoundException {
        List<Comanda> comandas = getComandas();
        comandas.add(comanda);
        atualizarArquivo(comandas);
    }


    /**
     * Esta função retorna todas as comandas cuja data de inicialização se encontra entre o intervalo de tempo informado.
     * @param inicio : A primeira data do intervalo
     * @param fim : A data final do intervalo
     * @return Lista com as comandas encontradas
     */

    public static List<Comanda> between(LocalDate inicio, LocalDate fim) throws IOException, ClassNotFoundException {
        //Caso as as datas venham invertidas, elas são postas na ordem correta
        if(inicio.compareTo(fim)>0){
            LocalDate aux = fim;
            fim = inicio;
            inicio = aux;
        }
        //É criada uma lista vazia para caso não haja nenhuma comanda no intervalo;
        List<Comanda> resultado = new ArrayList<>();
        List<Comanda> comandas = getComandas();
        //para cada comanda na lista "comandas":
        for (Comanda comanda: comandas){
            //Testa se a comanda se econtra no intervalo referente;
            if((comanda.getData().compareTo(inicio)>=0) && (comanda.getData().compareTo(fim)<=0)){ //REVER
                resultado.add(comanda);
            }
        }
        return resultado;
    }
}
