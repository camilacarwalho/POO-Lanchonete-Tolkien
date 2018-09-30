package com.ifpb.projeto.model;

import com.ifpb.projeto.Exceptions.NumeroMesaPositivoException;
import com.ifpb.projeto.control.GerenciaComandasFechadas;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe GerenciarMesa modela a entidade responsável pelo gerenciamento de mesas do domínio da aplicação.
 *   Composto por uma lista de Comandas, que irá gerenciar a comanda presente em cada mesa
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */

public class GerenciarMesa {

    private List<Comanda> mesas;

    /**
     * Construtor da classe.
     */
    public GerenciarMesa() {
        mesas = new ArrayList<Comanda>();
    }

    /**
     * Getters e Setters
     */

    public List<Comanda> getMesas() {
        return mesas;
    }

    public void setMesas(List<Comanda> mesas) {
        this.mesas = mesas;
    }

    public Comanda getComanda(int numMesa){
        for (Comanda comanda:mesas ) {
            if(comanda.getNumMesa()==numMesa){
                return comanda;
            }
        }
        return null;
    }


    /**
     * Esta função irá criar uma nova comanda para a mesa que for selecionada. (sujeito a testes)
     * A variável existeComanda irá informar se a mesa desejada já possui comanda.
     * A função irá rodar a lista até que a comanda na mesa seja encontrada.
     * Se a comanda for encontrada, a variável passa a ser TRUE.
     * Se a comanda não for encontrada, irá gerar uma comanda na mesa informada.
     * @param numeroMesa : O número da mesa desejada
     * @return true, caso a comanda possa ser criada.
     * @return false, caso a comanda não possa ser gerada nessa mesa.
     */
    public boolean gerarComanda(int numeroMesa) throws NumeroMesaPositivoException {
        boolean existeComanda = false;
        for (Comanda comanda : mesas) {
            if (comanda.getNumMesa() == numeroMesa) {
                existeComanda = true;
                break;
            }
        }
        if (!existeComanda) {
            Comanda comanda = new Comanda(numeroMesa);
            mesas.add(comanda);
            return true;

        } else return false;
    }

    /**
     * Esta função irá mostrar ao usuário todos os pedidos de uma mesa.
     * É feito o teste de onde se encontra a mesa, quando for encontrada, seus pedidos são impressos.(testar)
     * @param numeroMesa :  O número da mesa em que deseja fazer a ação
     * @return String com os pedidos da comanda.
     */
    public String verPedidos(int numeroMesa) {
        for (Comanda comanda : mesas) {
            if (comanda.getNumMesa() == numeroMesa) {
                return comanda.toString();
            }
        }
        return "";
    }

    //Para testar também asudhausdjinqwuenbqujdba
    public boolean fazerPedido(int numeroMesa, Pedido pedido,Cozinha cozinha) {
        for (Comanda comanda : mesas) {
            if (comanda.getNumMesa() == numeroMesa) {
                comanda.adicionarPedido(pedido);
                cozinha.adicionarPedido(pedido);
                return true;
            }
        }
        return false;
    }

    /**
     * Esta função tem como objetivo encerrar a comanda de uma mesa.
     *É incializado um indice impossivel, para o caso de não existir comanda na mesa informada.
     *É feita uma busca pela comanda da mesa informada.
     * Caso exista comanda naquela mesa, testa se todos os pedidos foram atendidos.
     * Caso todos os pedidos tenham sido atendidos, é salvo o índice de onde esta comanda se encontra na lista "mesas".
     * Testa se a variável do índice mudou, caso tenha mudado, então a comanda pode ser encerrada.
     * @param numeroMesa : informando a mesa desejada.
     * @return true, caso a comanda tenha sido encerrada
     * @return false, caso o encerramento não seja permitido
     */
    public boolean encerrarComanda(int numeroMesa, GerenciaComandasFechadas gerencia) {
        int indice = -1;
        for (Comanda comanda : mesas) {
            if (comanda.getNumMesa() == numeroMesa) {
                System.out.println("entrou aqui");
                if(comanda.allIsAtendido()){
                    System.out.println("Entrou nesse if");
                    gerencia.adicionaComanda(comanda);
                    indice = mesas.indexOf(comanda);
                    System.out.println("Valor final:"+comanda.getValorFinal());
                    break;
                }
                else break;

            }
        }
        if (indice != -1) {
            mesas.remove(indice);
            return true;
        } else return false;

    }

    /**
     * Esta função auxilia na edição de um pedido.
     * @param idPedidoAntigo : O pedido original que será editado
     * @param numMesa : O número da mesa
     * @param novo : O pedido novo, resultado da edição
     * @return true caso o pedido seja editado
     */

    public boolean editarPedido(int idPedidoAntigo, int numMesa, Pedido novo) {
        for (Comanda comanda : mesas) {
            if (numMesa == comanda.getNumMesa()) {
                List<Pedido> mesa = comanda.getComanda();
                for (Pedido pedido : mesa) {
                    if (idPedidoAntigo == pedido.getIdPedido()) {
                        int indice = mesa.indexOf(pedido);
                        mesa.add(indice, novo);
                        return true;
                    }
                }
            }
        }return false;
    }
}