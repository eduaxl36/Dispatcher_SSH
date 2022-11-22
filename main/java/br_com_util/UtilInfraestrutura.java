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

    private static UtilInfraestrutura Instancia;
    private final String LOCAL_TEMP_FILE = "temp/";

    private UtilInfraestrutura() {
    }

    public static UtilInfraestrutura getInstance() {

        Instancia = new UtilInfraestrutura();
        return Instancia;
    }


    public void abrirPasta(File Pasta) throws IOException {

        Desktop instancia = Desktop.getDesktop();
        instancia.open(Pasta);

    }

    public  void limpaTemp() throws IOException {

        for (File x : new File(LOCAL_TEMP_FILE).listFiles()) {

            if (x.isDirectory()) {
                FileUtils.deleteDirectory(x);

            }

            x.delete();

        }

    }
}
