package com.ifpb.projeto.control;

import com.ifpb.projeto.model.Comanda;

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

    private List<Comanda> comandas;

    public GerenciaComandasFechadas(){
        comandas = new ArrayList<>();
    }

    public List<Comanda> getComandas(){
        return comandas;
    }

    public void setComandas(List<Comanda> comandas){
        this.comandas = comandas;
    }

    /**
     * Esta função será invocada sempre que uma comanda por encerrada, para manter comandas no histórico da aplicação
     * @param comanda : O objeto comanda desejado
     */

    public void adicionaComanda(Comanda comanda){
        comandas.add(comanda);
    }

    /**
     * Esta função retorna todas as comandas cuja data de inicialização se encontra entre o intervalo de tempo informado.
     * @param inicio : A primeira data do intervalo
     * @param fim : A data final do intervalo
     * @return String com as comandas encontradas
     */

    public String between(LocalDate inicio, LocalDate fim){
        //Caso as as datas venham invertidas, elas são postas na ordem correta
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
