/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Eduardo.Fernando
 */
public class UtilInfraestrutura {

    public static void CopiaImagensIcones() throws IOException {

        File PastaDestino = new File("c:/temp/img");

        if (!PastaDestino.exists()) {

            PastaDestino.mkdir();

        }

        for (File Arquivo : new File("img/").listFiles()) {

            FileUtils.copyFileToDirectory(Arquivo, PastaDestino, true);

        }

    }

    public static void abrirPasta(File Pasta) throws IOException {

        Desktop instancia = Desktop.getDesktop();
        instancia.open(Pasta);

    }

    public static void limpaTemp() throws IOException {

        for (File x : new File("temp/").listFiles()) {

            x.delete();

        }

    }
}
