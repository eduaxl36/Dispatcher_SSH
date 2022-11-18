/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_services;

import br_com_kantar_dao.*;
import br_com_kantar_model.FTPConnectionModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo.Fernando
 */
public class FTPConnectionServices {

    private FTPConnectionDao Servico = null;

    public FTPConnectionServices() {
    }

    public FTPConnectionServices(JTable Tabela, int Id, String Owner, String User, String Pass, String Host, String Porta, int Tipo, String Key) throws IOException {

        this.Servico = new FTPConnectionDao(Tabela, Id, Owner, User, Pass, Host, Porta, Tipo, Key);

    }

    public FTPConnectionServices(JTable Tabela, int Id, String Owner, String User, String Pass, String Host, String Porta, int Tipo) throws IOException {

        this.Servico = new FTPConnectionDao(Tabela, Id, Owner, User, Pass, Host, Porta, Tipo);

    }

    public void preecherTabelaFTP() {

        this.Servico.preecherTabelaFTP();

    }

    public String obterChavePublica(String Owner) throws IOException {

        return this.Servico.obterChavePublica(Owner);
    }

    public void addRegistro() throws IOException {

        this.Servico.addRegistro();

    }

    public void removerRegistro() throws IOException {

        this.Servico.removerRegistro();

    }

    public void alterarRegistro() {

        this.Servico.alterarRegistro();

    }

    public void gravarArquivoFTPConnection(String Modo) throws FileNotFoundException, IOException {

        this.Servico.gravarArquivoFTPConnection(Modo);

    }

}
