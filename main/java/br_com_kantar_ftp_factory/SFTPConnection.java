/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br_com_kantar_ftp_factory;

import br_com_kantar_model.FTPConnectionModel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.IOException;

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

    public SFTPConnection(FTPConnectionModel Model) {
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
            Conexao().stat(DestinoRemoto);
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

    public static void main(String[] args) throws JSchException, SftpException, IOException {

//          FTPConnectionModel fs = new FTPConnectionDao().obterDadosFTP("Argentina", 4);
//          FTPService obj = new SFTP1(fs);
//          
//          
//          obj.downloadArquivo("/Produccion/IMI/Produccion/catastros/Int/20221110.txt", "c:/teste/20221110.txt");
//        JSch jsch = new JSch();
//        jsch.setKnownHosts("key.txt");
//        Session session = jsch.getSession("regional", "200.123.138.140", 22);
//        session.setPassword("K4nt4r.2021!");
//
//        session.connect();
//
//        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
//        channel.connect();
//
//        SftpATTRS attrs = channel.lstat("/Produccion/IMI/Produccion/Paralelo/OUTPUT_TELEPANEL");
//
//        boolean result
//                = attrs != null
//                && ((attrs.getPermissions() & 00200) != 0)
//                && attrs.getUId() != 0;
//
//        System.out.println(result);
////        channel.put("\\\\kimbrspp464\\ProducaoLatamParalelo\\AR\\paralelo_sistv\\SISTVARTSV-20220105.zip", "/Produccion/IMI/Produccion/Paralelo/OUTPUT_TELEPANEL/SISTVARTSV-20220105.zip");
//
////        Vector filelist = channel.ls("/Produccion");
////        for (int i = 0; i < filelist.size(); i++) {
////            LsEntry entry = (LsEntry) filelist.get(i);
////
//////                JOptionPane.showMessageDialog(null, entry.getFilename());
////            System.out.println(entry.getFilename());
////        }
//        channel.disconnect();
//        session.disconnect();
    }

}