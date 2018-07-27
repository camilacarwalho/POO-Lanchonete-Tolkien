package com.ifpb.projeto.model;

public enum Setor {
    ATENDIMENTO("Atendimento"), COZINHA("Cozinha"), CAIXA("Caixa"), GERENCIA("Gerencia");

    private final int ID;

    private Setor(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }}
