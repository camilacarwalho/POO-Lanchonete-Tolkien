package com.ifpb.projeto.control;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;

import com.ifpb.projeto.model.*;

public class CadastroUsuario {
    Scanner scan = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.
            ofPattern("dd/MM/yyyy");

    public boolean cadastrar(){
        System.out.println("CPF:");
        String cpf = scan.next();
        System.out.println("Nome:");
        String nome = scan.next();
        System.out.println("E-mail:");
        String email = scan.next();
        System.out.println("Telefone:");
        String telefone = scan.next();
        String data = scan.nextLine();
        LocalDate nascimento = LocalDate.parse(data, formatter);
        System.out.println("Setor:");
        System.out.println("Senha");
        String senha = scan.next();
        Usuario novousuario = new Usuario(cpf,nome,email,telefone,nascimento,Setor.GERENCIA,senha);
        return true;
    };

     public boolean autentication(String email, String senha){
            return Objects.equals(email, email) && Objects.equals(senha, senha);
        }

    };

