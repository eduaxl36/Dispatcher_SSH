/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br_com_kantar_ftp_factory;

import br_com_kantar_model.ConfiguracoesSFTPModel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo.Fernando
 */
public class SFTPConnection extends FTPService {

    private final String CRIPTOGRAFICA = "StrictHostKeyChecking";
    private final String PROTOCOLO = "sftp";
    private final String REPLY = "no";
    private Session Sessao;
    private ChannelSftp Canal;

    public SFTPConnection(ConfiguracoesSFTPModel Model) {
        super(Model);
    }

    public void iniciarConexao() throws JSchException, InterruptedException {

        obterSessao();
        Conexao();
    }

    public void finalizarConexao() throws JSchException, InterruptedException {

        while (this.Sessao.isConnected()) {

            this.Sessao.disconnect();
            System.out.println("Aguardando Sessao desconectar...");

        }
        System.out.println("Sessao desconectada com sucesso!");
    }

    public Session obterSessao() throws JSchException, InterruptedException {

        for (int i = 0; i < 10; i++) {

            try {

                String KeyLocate = getModel().getOwnerFTP() + "/Key.txt";

                JSch ConexaoInterna = new JSch();

                ConexaoInterna.setKnownHosts(KeyLocate);

                this.Sessao = ConexaoInterna.getSession(getModel().getUser(), getModel().getHost(), getModel().getPorta());

                this.Sessao.setPassword(getModel().getSenha());

                java.util.Properties config = new java.util.Properties();

                config.put(CRIPTOGRAFICA, REPLY);

                this.Sessao.setConfig(config);

                this.Sessao.connect();

                if (this.Sessao.isConnected()) {

                    break;

                }

            } catch (JSchException e) {

                if (i == 9) {

                    throw new JSchException("""
                                            Erro conexao com o SFTP: Houve uma falha de conex\u00e3o, favor verificar credencias ou se ha alguma instabilidade com o servidor 
                                            """ + getModel().getOwnerFTP() + " --> " + e.getMessage());

                }

            }
            System.out.println("Tentativa de conexao com o SFTP " + getModel().getOwnerFTP() + " Aguardando 15 segundos....");
            Thread.sleep(9000);

        }

        return this.Sessao;

    }

    public ChannelSftp Conexao() throws JSchException, InterruptedException {

        this.Canal = (ChannelSftp) this.Sessao.openChannel(PROTOCOLO);

        this.Canal.connect();

        return this.Canal;

    }

    @Override
    public void downloadArquivo(String Remoto, String Local) throws SftpException, JSchException, InterruptedException {

        iniciarConexao();
        this.Canal.get(Remoto, Local);
        finalizarConexao();

    }

    @Override
    public void uploadArquivo(String Local, String Remoto) throws SftpException, JSchException, InterruptedException {

        iniciarConexao();
        this.Canal.put(Local, Remoto);
        finalizarConexao();

    }

    @Override
    public boolean validaDestinoRemoto(String DestinoRemoto) {

        try {

            iniciarConexao();

            File Pasta = new File(DestinoRemoto);
                  
            this.Canal.stat(Pasta.getParent().replace("\\", "/"));
            
            finalizarConexao();

        } catch (SftpException e) {

            if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
                throw new IllegalArgumentException("Error : Diretorio invalido ou Arquivo inexistente no SFTP!");

            }
        } catch (JSchException ex) {
            throw new IllegalArgumentException("Error : Falha de conexao com o SFTP!" + ex);
        } catch (InterruptedException ex) {
            throw new IllegalArgumentException("Error : Falha de conexao com o SFTP!" + ex);
        }

        return true;

    }

}
