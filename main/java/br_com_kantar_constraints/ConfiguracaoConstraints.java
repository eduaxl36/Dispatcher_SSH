/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_constraints;

/**
 *
 * @author Eduardo.Fernando
 */
public class ConfiguracaoConstraints {

    public static boolean validaHost(String Host) {

        if (Host.length() == 0 || Host.startsWith(" ")) {

            throw new IllegalArgumentException("Campo Host : O campo contem um argumento invalido!");

        }

        return true;

    }

    public static boolean validaUser(String User) {

        if (User.length() == 0 || User.startsWith(" ")) {

            throw new IllegalArgumentException("Campo User : O campo contem um argumento invalido!");

        }

        return true;

    }

    public static  boolean validaSenha(String Senha) {

        if (Senha.length() == 0 || Senha.startsWith(" ")) {

            throw new IllegalArgumentException("Campo Senha : O campo contem um argumento invalido!");

        }

        return true;

    }
}
