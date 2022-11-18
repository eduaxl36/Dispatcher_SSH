/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_ftp_factory;

import br_com_kantar_exception.FtpFileNotFoundException;
import br_com_kantar_model.FTPConnectionModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Eduardo.Fernando
 */
public class FTPConnection extends FTPService {

    private FTPClient ftpClient;

    public FTPConnection(FTPConnectionModel Model) {
        super(Model);
    }

    public FTPClient obterSessao() throws IOException {

        this.ftpClient = new FTPClient();

        int port = getModel().getPorta();

        String username = getModel().getUser();

        String password = getModel().getSenha();

        String host = getModel().getHost();

        this.ftpClient.connect(host, port);

        this.ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        this.ftpClient.enterLocalPassiveMode();

        this.ftpClient.login(username, password);

        return this.ftpClient;

    }

    @Override
    public void downloadArquivo(String Remoto, String Local) throws FileNotFoundException, IOException {

        try ( FileOutputStream Out = new FileOutputStream(new File(Local))) {

            this.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            this.ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);

            this.ftpClient.retrieveFile(Remoto, Out);

            Out.flush();
        }

    }

    @Override
    public void uploadArquivo(String Local, String Remoto) throws FileNotFoundException, IOException {

        this.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        this.ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
        FileInputStream In = new FileInputStream(new File(Local));

        this.ftpClient.storeFile(Remoto, In);

    }

    @Override
    public boolean validaDestinoRemoto(String Remoto) throws FtpFileNotFoundException {

        File PastaRemota = new File(Remoto);
        String PastaRemotaRotaParente = PastaRemota.getParent();
        try {
            FTPFile[] remoteFiles = obterSessao().listFiles(PastaRemotaRotaParente);
            if (!(remoteFiles.length > 0)) {

                throw new FtpFileNotFoundException("Erro FTP: Diretorio invalido ou arquivo nao encontrado " + Remoto);

            }

        } catch (IOException ioe) {

            throw new FtpFileNotFoundException("FTP: " + ioe);

        } catch (FtpFileNotFoundException fnn) {

            throw new FtpFileNotFoundException("FTP: Diretorio invalido ou arquivo nao encontrado " + fnn);

        }

        return true;
    }

}
