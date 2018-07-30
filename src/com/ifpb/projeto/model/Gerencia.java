package com.ifpb.projeto.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Gerencia {

    private static List<Comanda> comandas;

    //Esta função deve ser invocada sempre que uma comanda for encerrada;
    public static void adicionaComanda(Comanda comanda){
        comandas.add(comanda);
    }

    //Esta função pega todas as comandas cuja data de inicialização se encontra entre o intervalo de tempo informado;
    public List<Comanda> between(LocalDate inicio, LocalDate fim){
        //É criada uma lista vazia para caso não haja nenhuma comanda no intervalo;
        List<Comanda> resultado = null;
        //para cada comanda na lista "comandas":
        for (Comanda comanda: comandas){
            //Testa se a comanda se econtra no intervalo referente;
            if((comanda.getData().compareTo(inicio)>=0) && (comanda.getData().compareTo(fim)<=0)){
                resultado.add(comanda);
                //Adiciona a comanda na lista resultante;
            }
        }
        return resultado;
    }

}
