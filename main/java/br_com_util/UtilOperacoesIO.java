/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_util;

import br_com_kantar_WINZIP.Descompactar;
import br_com_kantar_WINZIP.compactador;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Eduardo.Fernando
 */
public class UtilOperacoesIO {

    public static UtilOperacoesIO getInstance() {

        return new UtilOperacoesIO();
    }

    public void compactarArquivo(String OrigemArquivo, String ZipDestino, String InternalFolder) throws Exception {

        if (InternalFolder.equals("-")) {

            InternalFolder = "";

        }
        compactador.compact(ZipDestino, OrigemArquivo, InternalFolder);

    }

    public boolean copiarTodosArquivosParaPasta(File Origem, File DestinoDir) throws Exception {

  
        File[] ArquivosArray = Origem.listFiles();

        List<File> Arquivos = Arrays.asList(ArquivosArray);

        Arquivos.forEach(x -> {

            
            try {
                FileUtils.copyFileToDirectory(x, DestinoDir);
            } catch (IOException ex) {
               
            }
            
        });

        return true;
    }

    public boolean copiarArquivo(File Origem, File DestinoDir) throws IOException {

        FileUtils.copyFile(
                Origem,
                DestinoDir,
                true);

        return true;
    }

    public void extrairArquivo(String ArquivoZip, String Pasta) throws ZipException {

        Descompactar.extractToFolder(new File(ArquivoZip), Pasta);

    }

}
