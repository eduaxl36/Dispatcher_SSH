/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_dao;

import br_com_kantar_model.FTPConnectionModel;
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
public class FTPConnectionDao {

    private final String ROOT = "Cf/FTP/";
    private final String CAMINHO_ARQUIVO_CF_FTP_CONNECTION = "Cf/FTP/FTPConnection.csv";
    private FTPConnectionModel ObjCf;
    private JTable Tabela;
    private List<FTPConnectionModel> Configuracoes = null;
    private int Id;
    private String Owner;
    private String User;
    private String Pass;
    private String Porta;
    private int Tipo;
    private String Key;
    private String Host;

    public FTPConnectionDao() throws IOException {
        Configuracoes = ObterConfiguracoes();
    }

    public FTPConnectionDao(JTable Tabela, int Id, String Owner, String User, String Pass, String Host, String Porta, int Tipo, String Key) throws IOException {

        Configuracoes = ObterConfiguracoes();

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

    public FTPConnectionDao(JTable Tabela, int Id, String Owner, String User, String Pass, String Host, String Porta, int Tipo) throws IOException {

        Configuracoes = ObterConfiguracoes();

        this.Tabela = Tabela;
        this.Id = Id;
        this.Owner = Owner;
        this.User = User;
        this.Pass = Pass;
        this.Porta = Porta;
        this.Tipo = Tipo;
        this.Host = Host;

    }

    public List<FTPConnectionModel> getConfiguracoes() {
        return Configuracoes;
    }

    public FTPConnectionModel retornaObjetoCf(String[] Linha) {

        ObjCf = new FTPConnectionModel(
                Linha[0],
                Integer.parseInt(
                        Linha[1]),
                Linha[3],
                Linha[4],
                Linha[2],
                Integer.parseInt(Linha[5]));

        return ObjCf;

    }

    public List<FTPConnectionModel> ObterConfiguracoes() throws FileNotFoundException, IOException {

        List<FTPConnectionModel> Configuracoes = new ArrayList();

        BufferedReader Leitor = new BufferedReader(new FileReader(new File(CAMINHO_ARQUIVO_CF_FTP_CONNECTION)));

        String Linha = Leitor.readLine();

        while (Linha != null) {

            String[] DadosFracionados = Linha.split(";");

            Configuracoes.add(retornaObjetoCf(DadosFracionados));

            Linha = Leitor.readLine();

        }

        return Configuracoes;

    }

    public FTPConnectionModel obterDadosFTP(String Owner, int Tipo) throws IOException {

        List<FTPConnectionModel> Configuracoes = ObterConfiguracoes()
                .stream()
                .filter(x -> x.getOwnerFTP().equals(Owner))
                .filter(x -> x.getTipoFtp() == Tipo)
                .collect(Collectors.toList());

        return Configuracoes.get(0);

    }

    public Set<String> obterListaNomesFTP() throws IOException {

        Set<String> Instancias = new LinkedHashSet();

        ObterConfiguracoes().forEach(x -> {

            Instancias.add(x.getOwnerFTP());

        });

        return Instancias;
    }

    public void preecherTabelaFTP() {

        DefaultTableModel Modelo = (DefaultTableModel) this.Tabela.getModel();

        this.Configuracoes.forEach(x -> {

            Modelo.addRow(new Object[]{
                x.getOwnerFTP(),
                x.getHost(),
                x.getUser(),
                x.getSenha(),
                x.getPorta(),
                x.getTipoFtp()

            });

        });

        UtilTable.ajustarTabela(this.Tabela);
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

                criaPastaChavePublica();
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

    public void criaPastaChavePublica() throws IOException {

        File SourceChave = new File(ROOT + this.Owner);

        if (!SourceChave.exists()) {

            SourceChave.mkdir();

        }

        SourceChave = new File(ROOT + this.Owner + "/Key.txt");

        if (!SourceChave.exists()) {

            SourceChave.createNewFile();

        }

    }

    public void gravarChavePublica() throws IOException {

        File SourceChave = new File(ROOT + this.Owner + "/Key.txt");
        try ( PrintWriter Pw = new PrintWriter(new File(SourceChave.getAbsolutePath()))) {

            Pw.println(this.Key);

        }

    }

    public static void main(String[] args) throws IOException {

//        System.out.println(new FTPConnectionDao().obterDadosFTP("Realtime", 3));
    }

}
