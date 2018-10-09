package com.ifpb.projeto.control;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import java.time.format.DateTimeFormatter;

import com.ifpb.projeto.Exceptions.CpfExistenteException;
import com.ifpb.projeto.Exceptions.DataInvalidaException;
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
    public static boolean add(Usuario novo) throws CpfExistenteException, IOException, ClassNotFoundException, DataInvalidaException {
        Set<Usuario> cadastrados = getCadastrados();
        for(Usuario user:cadastrados){
            if(user.getCpf().equals(novo.getCpf())){
                throw new CpfExistenteException("Já existe um usuário com este CPF");
            }
            if(novo.getNascimento().isAfter(LocalDate.now())){
                throw new DataInvalidaException("Data inválida");
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
    public static boolean update(Usuario antigo, Usuario novo) throws IOException, ClassNotFoundException, DataInvalidaException {
        Set<Usuario> cadastrados = getCadastrados();
        if(antigo!=null){
            if(novo.getNascimento().isAfter(LocalDate.now())){
                throw new DataInvalidaException("Data inválida");
            }

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
    public static boolean remove(Usuario user) throws IOException, ClassNotFoundException {
        Set<Usuario> cadastrados = getCadastrados();
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


