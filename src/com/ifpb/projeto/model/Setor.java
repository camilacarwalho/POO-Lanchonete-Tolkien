package com.ifpb.projeto.model;
/**
 * A classe Setor do tipo Enum representa os setores onde usuários podem atuar.
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */
/*
Cria uma lista de valores constantes para simplificar a seleção do setor para cada usuário;
É criada um a classe "enum" com quatro valores de setor e seu respectivo código;
Para a inicialização desta classe não se deve usar "New";
Ex: Setor setor = Setor.COZINHA;
Neste exemplo o valor de setor é igual a COZINHA;
E o valor de setor.getID() é igual a 2;
 */
public enum Setor {
    ATENDIMENTO(1), COZINHA(2), CAIXA(3), GERENCIA(4);
    //O valor do ID é criado como uma constante para evitar alterações durante a execução do programa;
    public final int ID;
    //O construtor é automaticamente Private, sendo acessado somente pela classe;
    //Fazendo assim que não seja necessária a utilização do "New";
    Setor(int ID) {
        this.ID = ID;
    }
    //O valor da classe vai ser o nome de uma das constantes declaradas no início;
    //Para se ter o valor interno daquela constante é necessário a utilizaçãoi desta função;
    public int getID() {
        return ID;
    }
}
