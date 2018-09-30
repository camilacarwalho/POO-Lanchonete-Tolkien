package com.ifpb.projeto.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe Gerencia modela a entidade gerencia do domínio da aplicação.
 *   Responsável por auxiliar na busca de comandas em um período determinado
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */


public class Gerencia {

    private static List<Comanda> comandas = new ArrayList<>();

    /**
     * Esta função será invocada sempre que uma comanda por encerrada, para manter comandas no histórico da aplicação
     * @param comanda : O objeto comanda desejado
     */

    public static void adicionaComanda(Comanda comanda){
        comandas.add(comanda);
    }

    /**
     * Esta função retorna todas as comandas cuja data de inicialização se encontra entre o intervalo de tempo informado.
     * @param inicio : A primeira data do intervalo
     * @param fim : A data final do intervalo
     * @return String com as comandas encontradas
     */

    public static String between(LocalDate inicio, LocalDate fim){
        if(inicio.compareTo(fim)>0){
            LocalDate aux = fim;
            fim = inicio;
            inicio = aux;
        }
        //É criada uma lista vazia para caso não haja nenhuma comanda no intervalo;
        String resultado = "";
        //para cada comanda na lista "comandas":
        for (Comanda comanda: comandas){
            //Testa se a comanda se econtra no intervalo referente;
            if((comanda.getData().compareTo(inicio)>=0) && (comanda.getData().compareTo(fim)<=0)){ //REVER
                resultado+=comanda;
            }
        }
        return resultado;
    }

}
