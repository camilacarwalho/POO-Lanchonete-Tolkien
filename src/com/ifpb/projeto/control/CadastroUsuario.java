package com.ifpb.projeto.control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;

import com.ifpb.projeto.model.*;

public class CadastroUsuario {

    private List<Usuario> cadastrados;

    public CadastroUsuario() {
        cadastrados = new ArrayList<>();
    }

    public List<Usuario> getCadastrados() {
        return cadastrados;
    }

    public Usuario readData(){
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("CPF:");
        String cpf = scan.next();
        System.out.println("Nome:");
        String nome = scan.next();
        System.out.println("E-mail:");
        String email = scan.next();
        System.out.println("Telefone:");
        String telefone = scan.next();
        System.out.println("Data:");
        String data = scan.next();
        LocalDate nascimento = LocalDate.parse(data, formatter);
        System.out.println("Setor:\n 1-Atendimento\n2-Cozinha\n3-Caixa\n4-Gerencia");
        int set = scan.nextInt();
        Setor setor;
        switch (set) {
            case 1:
                setor = Setor.ATENDIMENTO;
                break;
            case 2:
                setor = Setor.COZINHA;
                break;
            case 3:
                setor = Setor.CAIXA;
                break;
            case 4:
                setor = Setor.GERENCIA;
                break;
            default:
                setor = Setor.ATENDIMENTO;
        }
        System.out.println("Senha");
        String senha = scan.next();
        Usuario novousuario = new Usuario(cpf, nome, email, telefone, nascimento, setor, senha);
        return novousuario;
    }

    public boolean cadastrar() {
        Usuario novo =  readData();
        cadastrados.add(novo);
        return true;
    }

    public boolean autentication(String email, String senha) {
        for (Usuario usuario: cadastrados) {
            if(usuario.autentication(email,senha)){
                    return true;
            }
        }
        return false;
    }


    public Usuario consulta(String email, String senha) {
        for (Usuario usuario: cadastrados) {
            if(usuario.autentication(email,senha)){
                System.out.println(usuario);
                return usuario;
            }
        }
        System.out.println("O usuário não existe!");
        return null;
    }

    public boolean update(int index){
        if(index>cadastrados.size()-1){
            return false;
        }
        Usuario novo = readData();
        cadastrados.add(index,novo);
        return true;
    }

    public boolean updateThis(String email, String senha){
        Usuario antigo = consulta(email,senha);
        return update(cadastrados.indexOf(antigo));
    }

    public boolean delete(int index){
        if(index>cadastrados.size()-1){
            return false;
        }
        cadastrados.remove(index);
        return true;
    }

    public boolean deleteThis(String email, String senha){
        Usuario falecido = consulta(email,senha);
        return delete(cadastrados.indexOf(falecido));
    }

    @Override
    public String toString() {
        if(cadastrados.isEmpty()){
            return "Não existem usuários cadastrados!";
        }
        String str = ":.:.:.:.:.:USUÁRIOS::.:.:.:.:.:\n";
        int cont = 0;
        for (Usuario usuario:cadastrados) {
            cont++;
            str += cont+":    \n"+ usuario.toString();
        }
        return str;
    }
}


