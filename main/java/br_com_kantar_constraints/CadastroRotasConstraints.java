/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_constraints;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo.Fernando
 */
public class CadastroRotasConstraints {

    private static CadastroRotasConstraints Instancia;

    private CadastroRotasConstraints() {
    }

    public static CadastroRotasConstraints getInstance() {

        return Instancia = new CadastroRotasConstraints();

    }

    public void validaInstancias(String CampoInstancia) {

        if (CampoInstancia.startsWith(" ") || CampoInstancia.length() == 0) {

            throw new IllegalArgumentException("Campo Instancias : O campo contem um argumento invalido!");

        }

    }

    public void validaProcesso(String CampoProcesso) {

        if (CampoProcesso.startsWith(" ") || CampoProcesso.length() == 0) {

            throw new IllegalArgumentException("Campo Instancias : O campo contem um argumento invalido!");

        }

    }

    public void validaSeDiretorioOrigemExiste(String Origem) {

        File DiretorioOrigem = new File(Origem);


        if (!DiretorioOrigem.exists()) {

            throw new IllegalArgumentException("Campo Origem :Caminho origem invalido!");

        }   
        

    }
    public void validaSeDiretorioDestinoExiste(String Destino) {

        File DiretorioDestino = new File(Destino);

        if (!DiretorioDestino.exists()) {

            throw new IllegalArgumentException("Campo Destino :Caminho Destino invalido!");

        }

    }
    public void validaPreenchimentoOrigem(String Origem) {

        validaSeDiretorioOrigemExiste(Origem);

        StringBuilder ValidarUltimoCaractere = new StringBuilder(Origem);

        String UltimoCarac = ValidarUltimoCaractere.reverse().toString().substring(0, 1);

        if (!(UltimoCarac.equals("\\") || UltimoCarac.equals("/"))) {

            throw new IllegalArgumentException("Campo Origem : O campo deve finalizar com barras /exemplo/");

        }

    }
    public void validaPreenchimentoDestino(String Destino) {

        validaSeDiretorioDestinoExiste(Destino);

        StringBuilder ValidarUltimoCaractere = new StringBuilder(Destino);

        String UltimoCarac = ValidarUltimoCaractere.reverse().toString().substring(0, 1);

        if (!(UltimoCarac.equals("\\") || UltimoCarac.equals("/"))) {

            throw new IllegalArgumentException("Campo Destino : O campo deve finalizar com barras /exemplo/");

        }

    }
//    public  boolean validaHost(String Host) {
//
//        if (Host.length() == 0 || Host.startsWith(" ")) {
//
//            throw new IllegalArgumentException("Campo Host : O campo contem um argumento invalido!");
//
//        }
//
//        return true;
//
//    }
//
//    public  boolean validaUser(String User) {
//
//        if (User.length() == 0 || User.startsWith(" ")) {
//
//            throw new IllegalArgumentException("Campo User : O campo contem um argumento invalido!");
//
//        }
//
//        return true;
//
//    }
//
//    public  boolean validaSenha(String Senha) {
//
//        if (Senha.length() == 0 || Senha.startsWith(" ")) {
//
//            throw new IllegalArgumentException("Campo Senha : O campo contem um argumento invalido!");
//
//        }
//
//        return true;
//
//    }
}
