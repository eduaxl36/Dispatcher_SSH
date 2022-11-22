/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_dao;

import br_com_kantar_model.ConfiguracoesSFTPModel;
import br_com_util.UtilTable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Eduardo.Fernando
 */
public final class CadastroSFTPDao {

    private final String ROOT = "Cf/FTP/";
    private final String CAMINHO_ARQUIVO_CF_FTP_CONNECTION = "Cf/FTP/FTPConnection.csv";
    private final String NOME_LITERAL_ARQUIVO_CHAVE = "Key.txt";
    private ConfiguracoesSFTPModel ObjCf;
    private JTable Tabela;
    private List<ConfiguracoesSFTPModel> instanciasCadastroSFTP = null;
    private int Id;
    private String Owner;
    private String User;
    private String Pass;
    private String Porta;
    private int Tipo;
    private String Key;
    private String Host;

    public CadastroSFTPDao() throws IOException {
        
        instanciasCadastroSFTP = obterInstanciasCadastrosSFTP();

    }

    public CadastroSFTPDao(JTable Tabela, int Id, String Owner, String User, String Pass, String Host, String Porta, int Tipo, String Key) throws IOException  {

        instanciasCadastroSFTP = obterInstanciasCadastrosSFTP();
        
        this.Tabela = Tabela;
        this.Id = Id;
        this.Owner = Owner;
        this.User = User;
        this.Pass = Pass;
        this.Porta = Porta;
        this.Tipo = Tipo;
        this.Key = Key;
        this.Host = Host;

    }

    public CadastroSFTPDao(JTable Tabela, int Id, String Owner, String User, String Pass, String Host, String Porta, int Tipo) throws IOException {

        instanciasCadastroSFTP = obterInstanciasCadastrosSFTP();

        this.Tabela = Tabela;
        this.Id = Id;
        this.Owner = Owner;
        this.User = User;
        this.Pass = Pass;
        this.Porta = Porta;
        this.Tipo = Tipo;
        this.Host = Host;

    }

    public List<ConfiguracoesSFTPModel> getInstanciasCadastroSFTP() {
        
        return instanciasCadastroSFTP;
        
    }

    
    public ConfiguracoesSFTPModel retornaInstanciaCadastroSFTP(String[] Linha) {

        ObjCf = new ConfiguracoesSFTPModel(
                Linha[0],
                Integer.parseInt(
                        Linha[1]),
                Linha[3],
                Linha[4],
                Linha[2],
                Integer.parseInt(Linha[5]));

        return ObjCf;

    }

    public List<ConfiguracoesSFTPModel> obterInstanciasCadastrosSFTP() throws FileNotFoundException, IOException  {

        List<ConfiguracoesSFTPModel> Configuracoes = new ArrayList();

        BufferedReader Leitor = new BufferedReader(new FileReader(new File(CAMINHO_ARQUIVO_CF_FTP_CONNECTION)));

        String Linha = Leitor.readLine();

        while (Linha != null) {

            String[] DadosFracionados = Linha.split(";");

            Configuracoes.add(retornaInstanciaCadastroSFTP(DadosFracionados));

            Linha = Leitor.readLine();

        }

        return Configuracoes;

    }

    public ConfiguracoesSFTPModel retornaInstanciaCadastroSFTP(String Owner, int Tipo) throws IOException {

        List<ConfiguracoesSFTPModel> Configuracoes = this.instanciasCadastroSFTP
                .stream()
                .filter(x -> x.getOwnerFTP().equals(Owner))
                .filter(x -> x.getTipoFtp() == Tipo)
                .collect(Collectors.toList());

        return Configuracoes.get(0);

    }

    public Set<String> obterListaNomes() throws IOException {

        Set<String> Instancias = new LinkedHashSet();

        this.instanciasCadastroSFTP.forEach(x -> {

            Instancias.add(x.getOwnerFTP());

        });

        return Instancias;
    }

    public void preecherTabela() {

        DefaultTableModel Modelo = (DefaultTableModel) this.Tabela.getModel();

        this.instanciasCadastroSFTP.forEach(x -> {

            Modelo.addRow(new Object[]{
                x.getOwnerFTP(),
                x.getHost(),
                x.getUser(),
                x.getSenha(),
                x.getPorta(),
                x.getTipoFtp()

            });

        });

    }

    public String obterChavePublica(String Owner) throws IOException {

        return FileUtils.readFileToString(new File(ROOT + Owner + "/Key.txt"));

    }

    public void removerRegistro() throws IOException {

        DefaultTableModel model = (DefaultTableModel) Tabela.getModel();
        model.removeRow(this.Id);

    }

    public void addRegistro() throws IOException {

        DefaultTableModel model = (DefaultTableModel) this.Tabela.getModel();

        model.addRow(new Object[]{
            this.Owner,
            this.Host,
            this.User,
            this.Pass,
            this.Porta,
            this.Tipo

        });

    }

    public void alterarRegistro() {

        DefaultTableModel model = (DefaultTableModel) this.Tabela.getModel();

        model.setValueAt(this.Owner, this.Id, 0);
        model.setValueAt(this.Host, this.Id, 1);
        model.setValueAt(this.User, this.Id, 2);
        model.setValueAt(this.Pass, this.Id, 3);
        model.setValueAt(this.Porta, this.Id, 4);
        model.setValueAt(this.Tipo, this.Id, 5);

    }

    public void acionaAcoesChavePublica(String Modo) throws IOException {

        if (Modo.equals("Listo para borrar registro")) {

            if (this.Tipo == 2) {
                FileUtils.deleteDirectory(new File(ROOT + Owner));
            }

        } else {
            if (this.Tipo == 2) {

                criaEstruturaChavePublica();
                gravarChavePublica();

            }

        }
    }

    public void gravarArquivoFTPConnection(String Modo) throws FileNotFoundException, IOException {

        try ( PrintWriter Pw = new PrintWriter(new File(CAMINHO_ARQUIVO_CF_FTP_CONNECTION))) {

            for (int i = 0; i < this.Tabela.getRowCount(); i++) {

                Pw.println(
                        this.Tabela.getValueAt(i, 0) + ";"
                        + this.Tabela.getValueAt(i, 5) + ";"
                        + this.Tabela.getValueAt(i, 1) + ";"
                        + this.Tabela.getValueAt(i, 2) + ";"
                        + this.Tabela.getValueAt(i, 3) + ";"
                        + this.Tabela.getValueAt(i, 4)
                );

            }
            acionaAcoesChavePublica(Modo);
        }

    }

    public void criaChave(File Chave) throws IOException {

        Chave = new File(this.ROOT + this.Owner + this.NOME_LITERAL_ARQUIVO_CHAVE);

        if (!Chave.exists()) {

            Chave.createNewFile();

        }

    }

    public void criaEstruturaChavePublica() throws IOException {

        File SourceChave = new File(this.ROOT + this.Owner);

        if (!SourceChave.exists()) {

            SourceChave.mkdir();

        }
        criaChave(SourceChave);

    }

    public void gravarChavePublica() throws IOException {

        File SourceChave = new File(this.ROOT + this.Owner + "/"+this.NOME_LITERAL_ARQUIVO_CHAVE);
        try ( PrintWriter Pw = new PrintWriter(new File(SourceChave.getAbsolutePath()))) {

            Pw.println(this.Key);

        }

    }

}
