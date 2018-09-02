package com.ifpb.projeto.control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;

import com.ifpb.projeto.model.*;
/**
 * A classe CasastroUsuario representa o CRUD de objetoc tipo Usuario.
 * Autenticação, Consulta, Cadastro, Edição e Exclusão.
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */

public class CadastroUsuario {

    private List<Usuario> cadastrados;

    public CadastroUsuario() {
        cadastrados = new ArrayList<>();
    }
/**
* Leitura de todos os usuários cadastrados
* @return : Lista de usuários, caso não há nenhum, retorna mensagem de aviso.
*/

    public List<Usuario> getCadastrados() {
        return cadastrados;
    }
 /**
 * Função facilitadora para leitura de dados de um Usuário, criada para evitar repetições de linha de código.
 * @return O usuário lido.
 */

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
 /**
 * Função que cadastra um usuário novo e adiciona na lista de cadastrados.
 *@return true
 */

    public boolean cadastrar() {
        Usuario novo =  readData();
        cadastrados.add(novo);
        return true;
    }
    /**
     * Função que realiza a autenticação de um usuário
     * @param email : O email escolhido pelo usuário no cadastro,
     * @param senha : A senha escolhida pelo usuário no cadastro.
     * @return true, caso a autenticação seja feita.
     * @return false, caso o email e senha não formem o usuário.
     */
    public boolean autentication(String email, String senha) {
        for (Usuario usuario: cadastrados) {
            if(usuario.autentication(email,senha)){
                    return true;
            }
        }
        return false;
    }
    /**
     * Função que consulta os dados de um usuário..
     * @param email: O email do usuário.
     * @param senha : A senha do usuário.
     * @return String com dados do usuário logado.
     */


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

    /**
     * Função que edita um usuário.
     * @param index : A posição do usuário da lista de cadastrados.
     * @return true caso o produto seja editado com sucesso
     * @return false caso a posição passada como parâmetro nao exista.
     */
    public boolean update(int index){
        if(index>cadastrados.size()-1){
            return false;
        }
        Usuario novo = readData();
        cadastrados.add(index,novo);
        return true;
    }

    /**
     * Função que garante que o usuário a editar seja o logado, por questões de ética e segurança.
     * @param email : O email do usuário.
     * @param senha : A senha do usuário.
     * @return true, caso a atualização seja realizada com sucesso.
     */
    public boolean updateThis(String email, String senha){
        Usuario antigo = consulta(email,senha);
        return update(cadastrados.indexOf(antigo));
    }
    /**
     * Função que deleta um usuário.
     * @param index : A posição do usuário na lista de cadastrados.
     * @return true caso o usuário seja deletado com sucesso.
     * @return  false caso a posição passada como parâmetro não exista.
     */

    public boolean delete(int index){
        if(index>cadastrados.size()-1){
            return false;
        }
        cadastrados.remove(index);
        return true;
    }

    /**
     * Função que garante que o usuário a deletar seja o logado, por questões de ética e segurança.
     * @param email : O email do usuário.
     * @param senha : A senha do usuário.
     * @return true, caso a exclusão seja realizada com sucesso.
     */

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


