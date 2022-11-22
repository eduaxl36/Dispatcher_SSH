/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_dao;

import br_com_util.UtilOperacoesIO;
import br_com_kantar_ftp_factory.FTPService;
import br_com_kantar_ftp_factory.FactoryFTPConnection;
import br_com_kantar_constraints.IOConstraints;
import br_com_kantar_datas.FactoryPadrao;
import br_com_kantar_datas.ServicoDataExtensoes;
import br_com_kantar_model.CadastroRotasModel;
import br_com_util.UtilInfraestrutura;
import br_com_util.UtilTable;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
public class CopiadorDao {

    private List<CadastroRotasModel> CadastrosRotas = null;
    private CadastroRotasDao CadastroRota;
    private ServicoDataExtensoes PadraoGerador;
    private JTable Tabela;
    private LocalDate Data;
    private FTPService ServicoFTP;
    private UtilOperacoesIO OperacoesIO;
    private UtilTable UtilitarioTabela;
    private final String ARQUIVO_REGIOES = "Cf/Regioes.cf";
    private UtilInfraestrutura UtilInfra;

    public CopiadorDao() throws IOException  {

        inciarServicos();

    }

    public CopiadorDao(LocalDate Data, JTable Tabela) throws IOException {

        this.Data = Data;
        this.Tabela = Tabela;
        inciarServicos();

    }

    public final void inciarServicos() throws IOException  {

        CadastroRota = new CadastroRotasDao();
        CadastrosRotas = CadastroRota.getConfiguracoes();
        OperacoesIO = UtilOperacoesIO.getInstance();
        UtilitarioTabela = UtilTable.getInstance();
        UtilInfra = UtilInfraestrutura.getInstance();

    }

    public String definirComportamentoOrigemArquivo(CadastroRotasModel ObjetoRotas) {

        PadraoGerador = new FactoryPadrao().getPadrao(this.Data.toString().replaceAll("-", ""), ObjetoRotas.getComportamentoDataOrigem());

        String Arquivo = PadraoGerador
                .obterPadrao(
                        this.Data.toString().replaceAll("-", ""),
                        ObjetoRotas.getPadraoOrigem(),
                        ObjetoRotas.getComportamentoDataOrigem(),
                        ObjetoRotas.getCalculoDataOrigem()
                );

        return Arquivo;

    }

    public String definirComportamentoDestinoArquivo(CadastroRotasModel ObjetoRotas) {

        PadraoGerador = new FactoryPadrao().getPadrao(this.Data.toString().replaceAll("-", ""), ObjetoRotas.getComportamentoDataDestino());

        return PadraoGerador
                .obterPadrao(
                        this.Data.toString().replaceAll("-", ""),
                        ObjetoRotas.getPadraoDestino(),
                        ObjetoRotas.getComportamentoDataDestino(),
                        ObjetoRotas.getCalculoDataDestino()
                );

    }

    public void copiaTodoConteudoParaPasta(String PastaOrigem, String PastaDestino, int Linha, DefaultTableModel Modelo)  {

        try {

            IOConstraints.validaSeArquivoExiste(PastaOrigem);
            IOConstraints.validaSeDiretorioDestinoExiste(PastaDestino);

            OperacoesIO.copiarTodosArquivosParaPasta(new File(PastaOrigem), new File(PastaDestino));
            this.Tabela.setValueAt("OK", Linha, 0);

            UtilInfra.limpaTemp();

        } catch (Exception e) {

            Modelo.setValueAt("Falha ao copiar : " + e.getMessage(), Linha, 0);
            UtilitarioTabela.realizaAjuste(this.Tabela);

        }

    }

    public void copiaArquivo(String Origem, String Destino, int Linha, DefaultTableModel Modelo)  {

        try {

            IOConstraints.validaSeArquivoExiste(Origem);
            IOConstraints.validaSeDiretorioDestinoExiste(Destino);

            OperacoesIO.copiarArquivo(new File(Origem), new File(Destino));
            this.Tabela.setValueAt("OK", Linha, 0);

        } catch (Exception e) {

            Modelo.setValueAt("Falha ao copiar : " + e.getMessage(), Linha, 0);
            UtilitarioTabela.realizaAjuste(this.Tabela);

        }

    }

    public void addArquivoZip(String Origem, String Destino, String InternalFolder, int Linha, DefaultTableModel Modelo)  {

        try {

            IOConstraints.validaSeArquivoExiste(Origem);
            IOConstraints.validaSeDiretorioDestinoExiste(Destino);

            this.OperacoesIO.compactarArquivo(Origem, Destino, InternalFolder);
            this.Tabela.setValueAt("OK", Linha, 0);

        } catch (Exception e) {

            Modelo.setValueAt("Falha ao zipar : " + e.getMessage(), Linha, 0);
            UtilitarioTabela.realizaAjuste(this.Tabela);
        }

    }

    public void extrairArquivoZip(String Origem, String Destino, int Linha, DefaultTableModel Modelo) {

        try {

            IOConstraints.validaSeArquivoExiste(Origem);
            IOConstraints.validaSeDiretorioDestinoExiste(Destino);

            this.OperacoesIO.extrairArquivo(Origem, new File(Destino).getParent());

            this.Tabela.setValueAt("OK", Linha, 0);

        } catch (Exception e) {

            Modelo.setValueAt("Falha ao extrair : " + e.getMessage(), Linha, 0);
            UtilitarioTabela.realizaAjuste(this.Tabela);
        }

    }

    public void copiaArquivoFTP(String Origem, String Destino, String Owner, int Tipo, int Linha, boolean Extracao, DefaultTableModel Modelo)  {

        try {

            this.ServicoFTP = new FactoryFTPConnection().getFTPService(Owner, Tipo);
            this.ServicoFTP.validaDestinoRemoto(Origem);

            this.ServicoFTP.downloadArquivo(Origem, Destino);

            if (Extracao == true) {
                this.OperacoesIO.extrairArquivo(Destino, new File(Destino).getParent());
            }

            this.Tabela.setValueAt("OK", Linha, 0);

        } catch (Exception e) {

            Modelo.setValueAt("Falha ao copiar : " + e.getMessage(), Linha, 0);
            UtilitarioTabela.realizaAjuste(this.Tabela);

        }

    }

    public void uploadArquivoFTP(String Origem, String Destino, String Owner, int Tipo, int Linha, DefaultTableModel Modelo)  {

        try {

            this.ServicoFTP = new FactoryFTPConnection().getFTPService(Owner, Tipo);
            this.ServicoFTP.validaDestinoRemoto(Destino);

            this.ServicoFTP.uploadArquivo(Origem, Destino);
            this.Tabela.setValueAt("OK", Linha, 0);

        } catch (Exception e) {

            Modelo.setValueAt("Falha ao carregar : " + e.getMessage(), Linha, 0);
            UtilitarioTabela.realizaAjuste(this.Tabela);

        }

    }

    public void distribuirAcoes(String Modo, String Origem, String Destino, String Owner, String internalFolder, int Tipo, int Linha)  {

        DefaultTableModel Modelo = (DefaultTableModel) this.Tabela.getModel();

        switch (Modo) {

            case "copy" -> {

                copiaArquivo(Origem, Destino, Linha, Modelo);

            }
            case "FTP_COPY" -> {

                copiaArquivoFTP(Origem, Destino, Owner, Tipo, Linha, false, Modelo);

            }
            case "FTP_COPY_EXTRACT" -> {

                copiaArquivoFTP(Origem, Destino, Owner, Tipo, Linha, true, Modelo);

            }

            case "FTP_UPLOAD" -> {

                uploadArquivoFTP(Origem, Destino, Owner, Tipo, Linha, Modelo);

            }

            case "ADD_ZIP" -> {

                addArquivoZip(Origem, Destino, internalFolder, Linha, Modelo);

            }
            case "EXTRACT_ZIP" -> {

                extrairArquivoZip(Origem, Destino, Linha, Modelo);

            }

            case "COPY_ALL_TO_PASTA" -> {

                copiaTodoConteudoParaPasta(Origem, Destino, Linha, Modelo);

            }

        }

    }

    public void executor()  {

        for (int i = 0; i < this.Tabela.getRowCount(); i++) {

            String getMode = this.Tabela.getValueAt(i, 7).toString();
            String getOrigem = this.Tabela.getValueAt(i, 5).toString();
            String getDestino = this.Tabela.getValueAt(i, 6).toString();
            String getOwner = this.Tabela.getValueAt(i, 8).toString();
            String getInternalFolder = this.Tabela.getValueAt(i, 9).toString();
            int getTipo = Integer.parseInt(this.Tabela.getValueAt(i, 4).toString());

            distribuirAcoes(getMode, getOrigem, getDestino, getOwner, getInternalFolder, getTipo, i);

        }

    }

    //// CONTEXTO DE PREENCHIMENTO DA TABELA COPIADOR
    
    public void preencherTabela(CadastroRotasModel ObjetoRotas) {

        String ArquivoOrigem = definirComportamentoOrigemArquivo(ObjetoRotas);
        String ArquivoDestino = definirComportamentoDestinoArquivo(ObjetoRotas);
        DefaultTableModel Modelo = (DefaultTableModel) this.Tabela.getModel();

        Modelo.addRow(new Object[]{
            "Pendente",
            ObjetoRotas.getInstancia(),
            ObjetoRotas.getPraca(),
            ObjetoRotas.getProcesso(),
            ObjetoRotas.getTipo(),
            ObjetoRotas.getOrigem() + ArquivoOrigem,
            ObjetoRotas.getDestino() + ArquivoDestino,
            ObjetoRotas.getMode(),
            ObjetoRotas.getDataOwner(),
            ObjetoRotas.getInternalFolder()

        });

    }

    public void distribuirDadosFiltrados(List<CadastroRotasModel> Cadastros) {

        Cadastros.forEach((var x) -> {

            preencherTabela(x);

        });

    }

    public void filtrarTabelaCopiador(List<String> Regioes, String Instancia) throws IOException {

        List<CadastroRotasModel> ConfiguracoesFiltradas = this.CadastrosRotas.stream()
                .filter(x -> x.getInstancia().equals(Instancia))
                .filter(e -> Regioes.contains(e.getPraca()))
                .collect(Collectors.toList());

        distribuirDadosFiltrados(ConfiguracoesFiltradas);
        UtilitarioTabela.realizaAjuste(this.Tabela);

    }

    public void zerarTabela() {

        DefaultTableModel Modelo = (DefaultTableModel) this.Tabela.getModel();
        Modelo = (DefaultTableModel) this.Tabela.getModel();
        Modelo.setNumRows(0);

    }

    ////  IMPORTAÇÃO DAS LISTAS EXTERNAS
    
    public Set<String> obterInstancias() throws IOException {

        Set<String> Instancias = this.CadastrosRotas.stream()
                .map(x -> x.getInstancia())
                .collect(Collectors.toSet());

        List<String> RetList = new ArrayList<>(Instancias);
        Collections.sort(RetList);
        Instancias = new LinkedHashSet(RetList);

        return Instancias;
    }

    public Set<String> obterProcessos() throws IOException {

        Set<String> Instancias = this.CadastrosRotas.stream()
                .map(x -> x.getProcesso())
                .collect(Collectors.toSet());

        List<String> RetList = new ArrayList<>(Instancias);
        Collections.sort(RetList);
        Instancias = new LinkedHashSet(RetList);
        return Instancias;
    }

    public List<String> obterListaRegioes() throws IOException {

        String[] Regioes = FileUtils.readFileToString(new File(ARQUIVO_REGIOES)).split("\r\n");

        return Arrays.asList(Regioes);

    }

}
