/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_ftp_factory;


import br_com_kantar_model.ConfiguracoesSFTPModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Eduardo.Fernando
 */
public class URLConnection extends FTPService {

    public URLConnection(ConfiguracoesSFTPModel Model) {
        super(Model);
    }

    @Override
    public void downloadArquivo(String Remoto, String Local) throws Exception {

        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";

        String host = getModel().getHost();
        String user = getModel().getUser();
        String pass = getModel().getSenha();

        ftpUrl = String.format(ftpUrl, user, pass, host, Remoto);
        System.out.println("URL: " + ftpUrl);

        URL url = new URL(ftpUrl);
        java.net.URLConnection conn = url.openConnection();
        InputStream inputStream = conn.getInputStream();

        FileOutputStream outputStream = new FileOutputStream(Local);

        byte[] buffer = new byte[2048];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        inputStream.close();

        System.out.println("File downloaded");

    }

    @Override
    public void uploadArquivo(String Local, String Remoto) throws Exception {

        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";

        String host = getModel().getHost();
        String user = getModel().getUser();
        String pass = getModel().getSenha();

        ftpUrl = String.format(ftpUrl, user, pass, host, Remoto);

        System.out.println("Upload URL: " + ftpUrl);

        URL url = new URL(ftpUrl);
        java.net.URLConnection conn = url.openConnection();
        try ( OutputStream outputStream = conn.getOutputStream();  FileInputStream inputStream = new FileInputStream(Local)) {

            byte[] buffer = new byte[2048];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

        }

        System.out.println("File uploaded");

    }

    @Override
    public boolean validaDestinoRemoto(String Remoto) {

        try {

            File DiretorioRemoto = new File(Remoto); 
            String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
            String host = getModel().getHost();
            String user = getModel().getUser();
            String pass = getModel().getSenha();

            ftpUrl = String.format(ftpUrl, user, pass, host, DiretorioRemoto.getParent());
            System.out.println("URL: " + ftpUrl);

            URL url = new URL(ftpUrl);
            java.net.URLConnection conn = url.openConnection();
            Object Valor = conn.getInputStream();

        } catch (MalformedURLException ex) {

            throw new IllegalArgumentException("FTP URL Error: "+ex);
            
        } catch (IOException ex) {
            throw new IllegalArgumentException("FTP URL Error: Diretorio invalido ou arquivo inexistente! "+ex);
        }

        return true;
    }



}
