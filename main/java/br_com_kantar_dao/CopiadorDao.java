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
import br_com_kantar_services.CadastroRotasServices;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Eduardo.Fernando
 */
public class CopiadorDao {
    
    private List<CadastroRotasModel> ConfigsTemp = null;
    private ServicoDataExtensoes PadraoGerador;
    private JTable Tabela;
    private LocalDate Data;
    private FTPService ServicoFTP;
    
    public CopiadorDao() throws IOException {
        
        ConfigsTemp = new CadastroRotasServices().getConfiguracoes();
        
    }
    
    public CopiadorDao(LocalDate Data, JTable Tabela) throws IOException {
        
        this.Data = Data;
        this.Tabela = Tabela;
        ConfigsTemp = new CadastroRotasDao().obterConfiguracoes();
        
    }
    
    public String definirComportamentoOrigemArquivo(CadastroRotasModel ObjetoRotas) throws Exception {
        
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
    
    public String definirComportamentoDestinoArquivo(CadastroRotasModel ObjetoRotas) throws Exception {
        
        PadraoGerador = new FactoryPadrao().getPadrao(this.Data.toString().replaceAll("-", ""), ObjetoRotas.getComportamentoDataDestino());
        
        return PadraoGerador
                .obterPadrao(
                        this.Data.toString().replaceAll("-", ""),
                        ObjetoRotas.getPadraoDestino(),
                        ObjetoRotas.getComportamentoDataDestino(),
                        ObjetoRotas.getCalculoDataDestino()
                );
        
    }
    
    public void copiaArquivo(String Origem, String Destino, int Linha) throws IOException {
        
        IOConstraints.validaSeArquivoExiste(Origem);
        IOConstraints.validaSeDiretorioDestinoExiste(Destino);
        UtilOperacoesIO.copiarArquivo(new File(Origem), new File(Destino));
        this.Tabela.setValueAt("OK", Linha, 0);
        
    }
    
    public void addArquivoZip(String Origem, String Destino, String InternalFolder, int Linha) throws IOException, Exception {
        
        IOConstraints.validaSeArquivoExiste(Origem);
        IOConstraints.validaSeDiretorioDestinoExiste(Destino);
        UtilOperacoesIO.ziparArquivo(Origem, Destino, InternalFolder);
        this.Tabela.setValueAt("OK", Linha, 0);
        
    }
    
    public void copiaArquivoFTP(String Origem, String Destino, String Owner, int Tipo, int Linha, boolean Extracao) throws IOException, Exception {
        
        this.ServicoFTP = new FactoryFTPConnection().getFTPService(Owner, Tipo);
        this.ServicoFTP.validaDestinoRemoto(Origem);
        this.ServicoFTP.downloadArquivo(Origem, Destino);
        
        if (Extracao == true) {
            
            UtilOperacoesIO.extrairArquivo(Destino, new File(Destino).getParent());
            
        }
        
        this.Tabela.setValueAt("OK", Linha, 0);
    }
    
    public void uploadArquivoFTP(String Origem, String Destino, String Owner, int Tipo, int Linha) throws IOException, Exception {
        
        this.ServicoFTP = new FactoryFTPConnection().getFTPService(Owner, Tipo);
        this.ServicoFTP.validaDestinoRemoto(Destino);
        ServicoFTP.uploadArquivo(Origem, Destino);
        this.Tabela.setValueAt("OK", Linha, 0);
        
    }
    
    public void distribuirAcoes(String Modo, String Origem, String Destino, String Owner, String internalFolder, int Tipo, int Linha) {
        
        DefaultTableModel Modelo = (DefaultTableModel) this.Tabela.getModel();
        
        switch (Modo) {
            
            case "copy" -> {
                
                try {
                    
                    copiaArquivo(Origem, Destino, Linha);
                    
                } catch (Exception e) {
                    
                    Modelo.setValueAt("Falha ao copiar : " + e.getMessage(), Linha, 0);
                    UtilTable.autoResizeTable(this.Tabela, true, 200);
                    
                }
                
            }
            case "FTP_COPY" -> {
                
                try {
                    
                    copiaArquivoFTP(Origem, Destino, Owner, Tipo, Linha, false);
                    
                } catch (Exception e) {
                    
                    Modelo.setValueAt("Falha ao copiar : " + e.getMessage(), Linha, 0);
                    UtilTable.autoResizeTable(this.Tabela, true, 200);
                    
                }
                
            }
            case "FTP_COPY_EXTRACT" -> {
                
                try {
                    
                    copiaArquivoFTP(Origem, Destino, Owner, Tipo, Linha, true);
                    
                } catch (Exception e) {
                    
                    Modelo.setValueAt("Falha ao copiar : " + e.getMessage(), Linha, 0);
                    UtilTable.autoResizeTable(this.Tabela, true, 200);
                    
                }
                
            }
            
            case "FTP_UPLOAD" -> {
                
                try {
                    
                    uploadArquivoFTP(Origem, Destino, Owner, Tipo, Linha);
                    
                } catch (Exception e) {
                    
                    Modelo.setValueAt("Falha ao carregar : " + e.getMessage(), Linha, 0);
                    UtilTable.autoResizeTable(this.Tabela, true, 200);
                    
                }
                
            }
            
            case "ADD_ZIP" -> {
                
                try {
                    
                    addArquivoZip(Origem, Destino, internalFolder, Linha);
                    
                } catch (Exception e) {
                    
                    Modelo.setValueAt("Falha ao zipar : " + e.getMessage(), Linha, 0);
                    UtilTable.autoResizeTable(this.Tabela, true, 200);
                }
                
            }
            default -> {
                UtilTable.autoResizeTable(this.Tabela, true, 200);
            }
        }
        
    }
    
    public void executor() throws Exception {
        
        String getMode = "";
        String getOrigem = "";
        String getDestino = "";
        String getOwner = "";
        String getInternalFolder = "";
        
        int getTipo = 0;
        
        for (int i = 0; i < this.Tabela.getRowCount(); i++) {
            
            getMode = this.Tabela.getValueAt(i, 7).toString();
            getOrigem = this.Tabela.getValueAt(i, 5).toString();
            getDestino = this.Tabela.getValueAt(i, 6).toString();
            getOwner = this.Tabela.getValueAt(i, 8).toString();
            getInternalFolder = this.Tabela.getValueAt(i, 9).toString();
            getTipo = Integer.parseInt(this.Tabela.getValueAt(i, 4).toString());
            
            distribuirAcoes(getMode, getOrigem, getDestino, getOwner, getInternalFolder, getTipo, i);
            
        }
        
    }

    //// CONTEXTO DE PREENCHIMENTO DA TABELA COPIADOR
    public void preencherTabela(CadastroRotasModel ObjetoRotas) throws Exception {
        
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
        
        Cadastros.forEach((CadastroRotasModel x) -> {
            
            try {
                
                preencherTabela(x);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Falha ao filtrar a tabela Copiador");
                
            }
            
        });
        
    }
    
    public void filtrarTabelaCopiador(List<String> Regioes, String Instancia) throws IOException {
        
        List<CadastroRotasModel> ConfiguracoesFiltradas = this.ConfigsTemp.stream()
                .filter(x -> x.getInstancia().equals(Instancia))
                .filter(e -> Regioes.contains(e.getPraca()))
                .collect(Collectors.toList());
        
        distribuirDadosFiltrados(ConfiguracoesFiltradas);
        
    }

    //// FORNECIMENTO DE LISTAS ESPECIAIS 
    public Set<String> obterInstancias() throws IOException {
        
        Set<String> Instancias = this.ConfigsTemp.stream()
                .map(x -> x.getInstancia())
                .collect(Collectors.toSet());
        
        List<String> RetList = new ArrayList<>(Instancias);
        Collections.sort(RetList);
        Instancias = new LinkedHashSet(RetList);
        
        return Instancias;
    }
    
    public Set<String> obterProcessos() throws IOException {
        
        Set<String> Instancias = this.ConfigsTemp.stream()
                .map(x -> x.getProcesso())
                .collect(Collectors.toSet());
        
        List<String> RetList = new ArrayList<>(Instancias);
        Collections.sort(RetList);
        Instancias = new LinkedHashSet(RetList);
        return Instancias;
    }
    
    public List<String> obterListaRegioes() throws IOException {
        
        String[] Regioes = FileUtils.readFileToString(new File("Cf/Regioes.cf")).split("\r\n");
        
        return Arrays.asList(Regioes);
        
    }
    
    public static void main(String[] args) throws IOException {
        
        new CopiadorDao().obterListaRegioes();
        
    }
    
}
