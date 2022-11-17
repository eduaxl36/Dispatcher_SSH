/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_services;

import br_com_kantar_constraints.IOConstraints;
import br_com_kantar_dao.CadastroRotasDao;
import br_com_kantar_dao.FTPConnectionDao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo.Fernando
 */
public class CadastroRotasServices {

    private CadastroRotasDao Servico;

    public CadastroRotasServices() {
        Servico = new CadastroRotasDao();
    }

    public CadastroRotasServices(int Id, JTable Tabela, String Instancia, String Praça, String Processo, int Tipo, String Origem, String PadraoOrigem, String ComportamentoOrigem, String Destino, String PadraoDestino, String ComportamentoDestino, String Modo, String Owner, String ZipInterno, int CalcOrigem, int CalcDestino) {

        Servico = new CadastroRotasDao(Id, Tabela, Instancia, Praça, Processo, Tipo, Origem, PadraoOrigem, ComportamentoOrigem, Destino, PadraoDestino, ComportamentoDestino, Modo, Owner, ZipInterno, CalcOrigem, CalcDestino);

    }

    public Set<String> obterInstancias() throws IOException {

        return this.Servico.obterInstancias();

    }

    public void carregarTabela() throws IOException {

        this.Servico.carregarTabelaRotas();
        this.Servico.ajustarTabela();

    }

    public void gravarTxt() throws FileNotFoundException {

        this.Servico.gravarTxt();

    }

    public void alterarRegistro() {

        this.Servico.alterarRegistro();

    }

    public void excluirRegistro() {

        this.Servico.excluirRegistro();

    }

    public String addRegistro() {

        this.Servico.addRegistro();

        return "";

    }

    public Set<String> obterListaFTPS() throws IOException {

        return this.Servico.obterListaFTPS();

    }

}