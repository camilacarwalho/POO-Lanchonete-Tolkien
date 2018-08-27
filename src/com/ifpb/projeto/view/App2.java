package com.ifpb.projeto.view;
import java.time.LocalDate;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;

import com.ifpb.projeto.model.*;
import com.ifpb.projeto.control.*;


public class App2 {
    public static void main(String[] args) {
        CadastroUsuario teste = new CadastroUsuario();
        teste.cadastrar();
    }

}
