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

    private static File fileCadastrados = new File("Cadastrados");

/**
 * Leitura de todos os usuários cadastrados
 * @return : Set de usuários, caso não há nenhum, retorna mensagem de aviso.
 * @throws IOException
 * @throws ClassNotFoundException
*/
    public static Set<Usuario> getCadastrados() throws IOException, ClassNotFoundException {
        Set<Usuario> cadastrados = new HashSet<>();
        if (!fileCadastrados.exists()) {
            fileCadastrados.createNewFile();
        } else {
            if (fileCadastrados.length() > 0) {
                try (ObjectInputStream in = new ObjectInputStream(
                        new FileInputStream(fileCadastrados))) {
                    cadastrados = (Set<Usuario>) in.readObject();
                }
            }
        }
        return cadastrados;
    }

/**
 * Função que cadastra um usuário novo e adiciona no Set de cadastrados.
 * @param novo: o novo usuário a ser cadastrado.
 * @throws CpfExistenteException
 * @throws IOException
 * @return true
*/
    public static boolean add(Usuario novo) throws CpfExistenteException, IOException {
        Set<Usuario> cadastrados = new HashSet<>();
        for(Usuario user:cadastrados){
            if(user.getCpf().equals(novo.getCpf())){
                throw new CpfExistenteException("Já existe um usuário com este CPF");
            }
        }
        if(cadastrados.add(novo)){
            atualizarArquivo(cadastrados);
            return true;
        }
        return false;
    }


/**
 * Função que atualiza o arquivo que contem os usuários cadastrados.
 * @param cadastrados: o Set de usuários cadastrados que vai ser cobrescrito no arquivo
 * @throws IOException
 * @return void
*/
    public static void atualizarArquivo(Set<Usuario> cadastrados) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(fileCadastrados))){
            out.writeObject(cadastrados);
        }
    }

/**
 * Função para buscar um usuário pelo seu email.
 * @param email: o email do usuário a ser buscado.
 * @return Usuario, caso existea um usuário com este email no Set de cadastrados.
 * @return null, caso não exista um usuário com este email.
 * @throws IOException
 * @throws ClassNotFoundException
*/
    public static Usuario buscarPorEmail(String email) throws IOException, ClassNotFoundException {
        Set<Usuario> cadastrados = getCadastrados();
        for (Usuario user:cadastrados) {
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
/**
 * Função que atualiza o os dados de um devido usuário.
 * @param antigo: O usuário que será atualizado.
 * @param novo: O usuário já com as atualizações feitas.
 * @throws IOException
 * @return true caso consiga atualizar o usuario.
 * @reutrn false caso não consiga atualziar.
*/
    public static boolean update(Usuario antigo, Usuario novo) throws IOException {
        Set<Usuario> cadastrados = new HashSet<>();
        if(antigo!=null){
            if(cadastrados.remove(antigo)){
                if(cadastrados.add(novo)){
                    atualizarArquivo(cadastrados);
                    return true;
                }
            }
        }
        return false;
    }

/**
 * Função que remove um usuário do arquivo.
 * @param user: O usuário que será removido do arquivo
 * @throws IOException
 * @return true caso seja possivel remnover o usuário
 * @return false caso não seja possivel remover o usuário
*/
    public static boolean remove(Usuario user) throws IOException {
        Set<Usuario> cadastrados = new HashSet<>();
        if(cadastrados.remove(user)){
            atualizarArquivo(cadastrados);
            return true;
        }
        return false;
    }

/**
 * Função que autententica o usuário.
 * @param email: O email do usuário que deseja logar no sistema.
 * @param senha: A senha do usuário que deseja logar no sistema.
 * @throws IOException
 * @throws ClassNotFoundException
 * @return true caso o email e a senha sejam os mesmos de algum usuário cadastroado no sistema.
 * @return false caso a combinação de email e senha não sejam referentes a nenhum usuário cadastrado.
*/
    public static boolean autentication(String email, String senha) throws IOException, ClassNotFoundException {
        Set<Usuario> cadastrados = getCadastrados();
        for (Usuario usuario: cadastrados) {
            if(usuario.autentication(email,senha)){
                    return true;
            }
        }
        return false;
    }






//    private Set<Usuario> cadastrados;
//
//
//    public CadastroUsuario() throws IOException, ClassNotFoundException {
//        fileCadastrados = new File("Cadastrados");
//        if(!fileCadastrados.exists()){
//            fileCadastrados.createNewFile();
//            cadastrados = new HashSet<>();
//        }else{
//            if(fileCadastrados.length()>0){
//                try(ObjectInputStream in = new ObjectInputStream(
//                        new FileInputStream(fileCadastrados))){
//                    cadastrados = (Set<Usuario>) in.readObject();
//                }
//            }else cadastrados = new HashSet<>();
//        }
//    }
//
//
//    public void atualizarArquivo() throws IOException {
//        try(ObjectOutputStream out = new ObjectOutputStream(
//                new FileOutputStream(fileCadastrados))){
//            out.writeObject(cadastrados);
//        }
//    }
//
///**
//* Leitura de todos os usuários cadastrados
//* @return : Lista de usuários, caso não há nenhum, retorna mensagem de aviso.
//*/
//
//    public Set<Usuario> getCadastrados() {
//        return cadastrados;
//    }
//
// /**
// * Função que cadastra um usuário novo e adiciona na lista de cadastrados.
// *@return true
// */
//
//    public boolean cadastrar(Usuario novo) throws IOException, CpfExistenteException {
//        for (Usuario usuario:cadastrados){
//            if(novo.getCpf().equals(usuario.getCpf())){
//                throw new CpfExistenteException("Já existe um usuário com este cpf!");
//            }
//        }
//        if(cadastrados.add(novo)){
//            atualizarArquivo();
//            return true;
//        }
//        return false;
//    }
//    /**
//     * Função que realiza a autenticação de um usuário
//     * @param email : O email escolhido pelo usuário no cadastro,
//     * @param senha : A senha escolhida pelo usuário no cadastro.
//     * @return true, caso a autenticação seja feita.
//     * @return false, caso o email e senha não formem o usuário.
//     */
//    public boolean autentication(String email, String senha) {
//        for (Usuario usuario: cadastrados) {
//            if(usuario.autentication(email,senha)){
//                    return true;
//            }
//        }
//        return false;
//    }
//    /**
//     * Função que consulta os dados de um usuário..
//     * @param email: O email do usuário.
//     * @return String com dados do usuário logado.
//     */
//
//
//    public Usuario consulta(String email) throws IOException, ClassNotFoundException {
//        try(ObjectInputStream in = new ObjectInputStream(
//                new FileInputStream(fileCadastrados))){
//            cadastrados = (Set<Usuario>) in.readObject();
//        }
//        for (Usuario usuario: cadastrados) {
//            if(usuario.getEmail().equals(email)){
//                return usuario;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Função que edita um usuário.
//     * @param antigo,novo : O usuário que vai ser atualizado, e o usuário já atualizado.
//     * @return true caso o produto seja editado com sucesso.
//     * @return false caso a posição passada como parâmetro nao exista.
//     */
//    public boolean update(Usuario antigo,Usuario novo) throws IOException {
//        if(!cadastrados.remove(antigo)){
//            return false;
//        }
//        cadastrados.add(novo);
//        atualizarArquivo();
//        return true;
//    }
//
//    /**
//     * Função que deleta um usuário.
//     * @param usuario : O objeto do usuário que será deletado.
//     * @return true caso o usuário seja deletado com sucesso.
//     * @return  false caso a posição passada como parâmetro não exista.
//     */
//
//    public boolean delete(Usuario usuario) throws IOException {
//        if(cadastrados.remove(usuario)){
//            atualizarArquivo();
//            return true;
//        }return false;
//    }


    @Override
    public String toString() {
        Set<Usuario> cadastrados = null;
        try {
            cadastrados = getCadastrados();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(cadastrados==null || cadastrados.isEmpty()){
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


