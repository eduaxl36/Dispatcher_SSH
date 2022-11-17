/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_util;

import br_com_kantar_WINZIP.Descompactar;
import br_com_kantar_WINZIP.compactador;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JOptionPane;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Eduardo.Fernando
 */
public class UtilOperacoesIO {

    public static void ziparArquivo(String OrigemArquivo, String ZipDestino, String InternalFolder) throws Exception {

        if (InternalFolder.equals("-")) {

            InternalFolder = "";

        }
        compactador.compact(ZipDestino, OrigemArquivo, InternalFolder);

    }

    public static boolean copiarArquivo(File Origem, File DestinoDir) throws IOException {

        FileUtils.copyFile(
                Origem,
                DestinoDir,
                true);

        return true;
    }

    public static void extrairArquivo(String ArquivoZip, String Pasta) throws ZipException {

        Descompactar.extractToFolder(new File(ArquivoZip), Pasta);

    }

}
