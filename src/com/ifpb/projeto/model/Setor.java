package com.ifpb.projeto.model;
/**
 * A classe Setor do tipo Enum representa os setores onde usuários podem atuar.
 * Lista de valores constantes.
 * O valor do ID é criado como uma constante para evitar alterações durante a execução do programa.
 * Não é necessário um New.
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */
public enum Setor {
    ATENDIMENTO(1), COZINHA(2), CAIXA(3), GERENCIA(4);

    public final int ID;
    Setor(int ID) {
        this.ID = ID;
    }

    /**
     * Função usada para se ter o valor interno da constante.
     * @return ID do setor escolhido.
     */
    public int getID() {
        return ID;
    }
}
