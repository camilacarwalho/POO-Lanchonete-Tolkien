package com.ifpb.projeto.control;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import java.time.format.DateTimeFormatter;

import com.ifpb.projeto.Exceptions.CpfExistenteException;
import com.ifpb.projeto.model.*;
/**
 * A classe CasastroUsuario representa o CRUD de objetoc tipo Usuario.
 * Autenticação, Consulta, TelaDeCadastro, Edição e Exclusão.
 *   @author Camila Carvalho
 *   @author Mailson Dennis
 *   @since 26-07-2018
 *   @version 1.0
 */

public class CadastroUsuario {

    private File fileCadastrados;
    private Set<Usuario> cadastrados;


    public CadastroUsuario() throws IOException, ClassNotFoundException {
        fileCadastrados = new File("Cadastrados");
        if(!fileCadastrados.exists()){
            fileCadastrados.createNewFile();
            cadastrados = new HashSet<>();
        }else{
            if(fileCadastrados.length()>0){
                try(ObjectInputStream in = new ObjectInputStream(
                        new FileInputStream(fileCadastrados))){
                    cadastrados = (Set<Usuario>) in.readObject();
                }
            }else cadastrados = new HashSet<>();
        }
    }


    public void atualizarArquivo() throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(fileCadastrados))){
            out.writeObject(cadastrados);
        }
    }

/**
* Leitura de todos os usuários cadastrados
* @return : Lista de usuários, caso não há nenhum, retorna mensagem de aviso.
*/

    public Set<Usuario> getCadastrados() {
        return cadastrados;
    }

 /**
 * Função que cadastra um usuário novo e adiciona na lista de cadastrados.
 *@return true
 */

    public boolean cadastrar(Usuario novo) throws IOException, CpfExistenteException {
        for (Usuario usuario:cadastrados){
            if(novo.getCpf().equals(usuario.getCpf())){
                throw new CpfExistenteException("Já existe um usuário com este cpf!");
            }
        }
        cadastrados.add(novo);
        atualizarArquivo();
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
     * @return String com dados do usuário logado.
     */


    public Usuario consulta(String email) {
        for (Usuario usuario: cadastrados) {
            if(usuario.getEmail().equals(email)){
                return usuario;
            }
        }
        return null;
    }

    /**
     * Função que edita um usuário.
     * @param antigo,novo : O usuário que vai ser atualizado, e o usuário já atualizado.
     * @return true caso o produto seja editado com sucesso.
     * @return false caso a posição passada como parâmetro nao exista.
     */
    public boolean update(Usuario antigo,Usuario novo) throws IOException {
        if(!cadastrados.remove(antigo)){
            return false;
        }
        cadastrados.add(novo);
        atualizarArquivo();
        return true;
    }

//    /**
//     * Função que garante que o usuário a editar seja o logado, por questões de ética e segurança.
//     * @param email : O email do usuário.
//     * @param senha : A senha do usuário.
//     * @return true, caso a atualização seja realizada com sucesso.
//     */
//    public boolean updateThis(String email, String senha, Usuario novo){
//        Usuario antigo = consulta(email,senha);
//        return update(cadastrados.indexOf(antigo),novo);
//    }
    /**
     * Função que deleta um usuário.
     * @param usuario : O objeto do usuário que será deletado.
     * @return true caso o usuário seja deletado com sucesso.
     * @return  false caso a posição passada como parâmetro não exista.
     */

    public boolean delete(Usuario usuario) throws IOException {
        if(cadastrados.remove(usuario)){
            atualizarArquivo();
            return true;
        }return false;
    }

//    /**
//     * Função que garante que o usuário a deletar seja o logado, por questões de ética e segurança.
//     * @param email : O email do usuário.
//     * @param senha : A senha do usuário.
//     * @return true, caso a exclusão seja realizada com sucesso.
//     */
//
////        minha nossa senhora o cara morreu
////    bjos do dudu
////    énoix
//
//    public boolean deleteThis(String email, String senha){
//        Usuario falecido = consulta(email,senha);
//        return delete(cadastrados.indexOf(falecido));
//    }

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


