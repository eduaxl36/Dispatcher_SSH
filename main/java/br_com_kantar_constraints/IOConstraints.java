/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_constraints;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

/**
 *
 * @author Eduardo.Fernando
 */
public class IOConstraints {

   

    public static boolean validaSeDiretorioOrigemExiste(String Origem) {

        File DiretorioOrigem = new File(Origem);

        if (!DiretorioOrigem.getParentFile().exists()) {

            throw new IllegalArgumentException("Error : Caminho origem invalido!");

        }

        return true;

    }

    public static boolean validaSeDiretorioDestinoExiste(String Destino) {

        File DiretorioDestino = new File(Destino);

        if (!DiretorioDestino.getParentFile().exists()) {

            throw new IllegalArgumentException("Error : Caminho destino invalido!");

        }

        return true;

    }

    public static boolean validaSeArquivoExiste(String Origem) {



        File ArquivoOrigem = new File(Origem);

        if (!ArquivoOrigem.exists()) {

            throw new IllegalArgumentException("Error : O arquivo origem nao existe!");

        }

        return true;

    }

    public static boolean validaPreenchimentoInstancia(String Instancia) {

        if (Instancia.length() == 0 || Instancia.startsWith(" ")) {

            throw new IllegalArgumentException("Campo Instancia : Campo possui um argumento invalido!");

        }

        return true;

    }

    public boolean validaPreenchimentoProcesso(String Processo) {

        if (Processo.length() == 0 || Processo.startsWith(" ")) {

            throw new IllegalArgumentException("Campo Processo : Campo possui um argumento invalido!");

        }

        return true;

    }

    public static boolean validaPreenchimentoOrigem(String Origem) {

        if (Origem.length() == 0 || Origem.startsWith(" ")) {

            throw new IllegalArgumentException("Campo Origem : Campo possui um argumento invalido!");

        }

        StringBuilder ValidarUltimoCaractere = new StringBuilder(Origem);

        String UltimoCarac = ValidarUltimoCaractere.reverse().toString().substring(0, 1);

        if (!(UltimoCarac.equals("\\") || UltimoCarac.equals("/"))) {

            throw new IllegalArgumentException("Campo Origem : O campo deve finalizar com barras /exemplo/");

        }

        return true;

    }

    public static boolean validaPreenchimentoDestino(String Destino) {

        if (Destino.length() == 0 || Destino.startsWith(" ")) {

            throw new IllegalArgumentException("Campo Destino : Campo possui um argumento invalido!");

        }

        StringBuilder ValidarUltimoCaractere = new StringBuilder(Destino);

        String UltimoCarac = ValidarUltimoCaractere.reverse().toString().substring(0, 1);

        if (!(UltimoCarac.equals("\\") || UltimoCarac.equals("/"))) {

            throw new IllegalArgumentException("Campo Destino : O campo deve finalizar com barras /exemplo/");

        }

        return true;

    }

    public static boolean validaPreenchimentoPadrao(String Padrao) {

        if (Padrao.length() == 0 || Padrao.startsWith(" ")) {

            throw new IllegalArgumentException("Campo Padrao : Campo possui um argumento invalido!");

        }

        return true;

    }

    public static void main(String[] args) throws IOException, JSchException, SftpException, ParseException {

        System.out.println(validaPreenchimentoOrigem("\\dsdasdsadas\\"));

//        ConnectionSFTPAR ConexaoAR = new ConnectionSFTPAR(new ConfiguracoesServices().obterConfiguracaoPorChave("user_sftpar"), new ConfiguracoesServices().obterConfiguracaoPorChave("senha_sftpar"), new ConfiguracoesServices().obterConfiguracaoPorChave("host_sftpar"), Integer.parseInt(new ConfiguracoesServices().obterConfiguracaoPorChave("port")));
//        ChannelSftp ConARObj = ConexaoAR.Conexao();
////        System.out.println(validaSeArquivoExiste("c:/teste/AR_TSV_YYYYMMDD.zip",LocalDate.parse("2022-10-29")));
//
////        System.out.println(validaExistenciaArquivoSFTP(LocalDate.parse("2022-10-30"), "/Produccion/IMI/Produccion/Paralelo/OUTPUT_TELEPANEL/AR_TSV_YYYYMMDD.zip", ConARObj));
//        System.out.println(validaExistenciaDiretorioSFTP("/Produccion/IMI/Produccion/catastros/Gba/", ConARObj));
    }

}
