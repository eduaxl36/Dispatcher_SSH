/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_dao;

import br_com_kantar_model.CadastroRotasModel;

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

/**
 *
 * @author Eduardo.Fernando
 */
public class CadastroRotasDao {

    private CadastroRotasModel Modelo;
    private JTable Tabela;
    private List<CadastroRotasModel> Configuracoes = null;
    private final String ROTA_ARQUIVO_SOURCES = "Cf/CaminhosParaCopia.csv";
    private CadastroSFTPDao CadastroSFTPDao;
    
    

    public CadastroRotasDao() throws IOException  {

        Configuracoes = obterConfiguracoes();
        CadastroSFTPDao = new CadastroSFTPDao();

    }

    public CadastroRotasDao(int Id, JTable Tabela, String Instancia, String Praça, String Processo, int Tipo, String Origem, String PadraoOrigem, String ComportamentoOrigem, String Destino, String PadraoDestino, String ComportamentoDestino, String Modo, String Owner, String ZipInterno, int CalOrigem, int CalcDestino) throws IOException  {

        Modelo = new CadastroRotasModel(Id, Instancia, Praça, Processo, Tipo, Origem, PadraoOrigem, ComportamentoOrigem, CalOrigem, Destino, PadraoDestino, ComportamentoDestino, CalcDestino, Modo, Owner, ZipInterno);
        this.Tabela = Tabela;
        Configuracoes = obterConfiguracoes();
        CadastroSFTPDao = new CadastroSFTPDao();
    }

    public void addRegistro() {

        DefaultTableModel model = (DefaultTableModel) Tabela.getModel();

        model.addRow(new Object[]{
            Modelo.getInstancia(),
            Modelo.getPraca(),
            Modelo.getProcesso(),
            Modelo.getTipo(),
            Modelo.getOrigem(),
            Modelo.getPadraoOrigem(),
            Modelo.getComportamentoDataOrigem(),
            Modelo.getCalculoDataOrigem(),
            Modelo.getDestino(),
            Modelo.getPadraoDestino(),
            Modelo.getComportamentoDataDestino(),
            Modelo.getCalculoDataDestino(),
            Modelo.getMode(),
            Modelo.getDataOwner(),
            Modelo.getInternalFolder()

        });

    }

    public void excluirRegistro() {

        DefaultTableModel model = (DefaultTableModel) this.Tabela.getModel();
        model.removeRow(this.Modelo.getId());

    }

    public void alterarRegistro() {

        DefaultTableModel model = (DefaultTableModel) this.Tabela.getModel();

        model.setValueAt(Modelo.getInstancia(), this.Modelo.getId(), 0);
        model.setValueAt(Modelo.getPraca(), this.Modelo.getId(), 1);
        model.setValueAt(Modelo.getProcesso(), this.Modelo.getId(), 2);
        model.setValueAt(Modelo.getTipo(), this.Modelo.getId(), 3);
        model.setValueAt(Modelo.getOrigem(), this.Modelo.getId(), 4);
        model.setValueAt(Modelo.getPadraoOrigem(), this.Modelo.getId(), 5);
        model.setValueAt(Modelo.getComportamentoDataOrigem(), this.Modelo.getId(), 6);
        model.setValueAt(Modelo.getCalculoDataOrigem(), this.Modelo.getId(), 7);
        model.setValueAt(Modelo.getDestino(), this.Modelo.getId(), 8);
        model.setValueAt(Modelo.getPadraoDestino(), this.Modelo.getId(), 9);
        model.setValueAt(Modelo.getComportamentoDataDestino(), this.Modelo.getId(), 10);
        model.setValueAt(Modelo.getCalculoDataDestino(), this.Modelo.getId(), 11);
        model.setValueAt(Modelo.getMode(), this.Modelo.getId(), 12);
        model.setValueAt(Modelo.getDataOwner(), this.Modelo.getId(), 13);
        model.setValueAt(Modelo.getInternalFolder(), this.Modelo.getId(), 14);

    }

    public void gravarTxt() throws FileNotFoundException  {

        try ( PrintWriter Pw = new PrintWriter(new File(ROTA_ARQUIVO_SOURCES))) {
            for (int i = 0; i < Tabela.getRowCount(); i++) {

                Pw.println(
                        Tabela.getValueAt(i, 0) + ";"
                        + Tabela.getValueAt(i, 1) + ";"
                        + Tabela.getValueAt(i, 2) + ";"
                        + Tabela.getValueAt(i, 3) + ";"
                        + Tabela.getValueAt(i, 4) + ";"
                        + Tabela.getValueAt(i, 5) + ";"
                        + Tabela.getValueAt(i, 6) + ";"
                        + Tabela.getValueAt(i, 7) + ";"
                        + Tabela.getValueAt(i, 8) + ";"
                        + Tabela.getValueAt(i, 9) + ";"
                        + Tabela.getValueAt(i, 10) + ";"
                        + Tabela.getValueAt(i, 11) + ";"
                        + Tabela.getValueAt(i, 12) + ";"
                        + Tabela.getValueAt(i, 13) + ";"
                        + Tabela.getValueAt(i, 14)
                );

            }
        }

    }

    public CadastroRotasModel obterObjetoRotas(String Linha) {

        CadastroRotasModel Objeto = new CadastroRotasModel();

        String[] Info = Linha.split(";");

        Objeto.setInstancia(Info[0]);
        Objeto.setPraca(Info[1]);
        Objeto.setProcesso(Info[2]);
        Objeto.setTipo(Integer.parseInt(Info[3]));
        Objeto.setOrigem(Info[4]);
        Objeto.setPadraoOrigem(Info[5]);
        Objeto.setComportamentoDataOrigem(Info[6]);
        Objeto.setCalculoDataOrigem(Integer.parseInt(Info[7]));
        Objeto.setDestino(Info[8]);
        Objeto.setPadraoDestino(Info[9]);
        Objeto.setComportamentoDataDestino(Info[10]);
        Objeto.setCalculoDataDestino(Integer.parseInt(Info[11]));
        Objeto.setMode(Info[12]);
        Objeto.setDataOwner(Info[13]);
        Objeto.setInternalFolder(Info[14]);

        return Objeto;
    }

    public final List<CadastroRotasModel> obterConfiguracoes() throws FileNotFoundException, IOException {

        BufferedReader Leitor = new BufferedReader(new FileReader(new File(ROTA_ARQUIVO_SOURCES)));
        String Linha = Leitor.readLine();
        List<CadastroRotasModel> Sources = new ArrayList();

        while (Linha != null) {

            Sources.add(obterObjetoRotas(Linha));

            Linha = Leitor.readLine();

        }

        return Sources;

    }

    public void carregarTabelaRotas()  {

        DefaultTableModel Modelo = (DefaultTableModel) this.Tabela.getModel();

        List<CadastroRotasModel> ConfigsTemp = this.Configuracoes;

        ConfigsTemp.forEach(x -> {

            Modelo.addRow(new Object[]{
                x.getInstancia(),
                x.getPraca(),
                x.getProcesso(),
                x.getTipo(),
                x.getOrigem(),
                x.getPadraoOrigem(),
                x.getComportamentoDataOrigem(),
                x.getCalculoDataOrigem(),
                x.getDestino(),
                x.getPadraoDestino(),
                x.getComportamentoDataDestino(),
                x.getCalculoDataDestino(),
                x.getMode(),
                x.getDataOwner(),
                x.getInternalFolder()
            }
            );

        });

    }


    public Set<String> obterInstancias() throws IOException {

        Set<String> Instancias = this.Configuracoes.stream()
                .map(x -> x.getInstancia())
                .collect(Collectors.toSet());

        return Instancias;
    }

    
    
    public Set<String> obterListaNomesSFTP() throws IOException {

        Set<String> Instancias = new LinkedHashSet();

        this.CadastroSFTPDao.getInstanciasCadastroSFTP().forEach(x -> {

            Instancias.add(x.getOwnerFTP());

        });

        return Instancias;
    }

    public List<CadastroRotasModel> getConfiguracoes() {
        return Configuracoes;
    }

    
    public static void main(String[] args) {
        System.out.println("fsdfsd");
    }
    
}
