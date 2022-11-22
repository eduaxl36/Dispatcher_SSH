/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_services;

import br_com_kantar_dao.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Eduardo.Fernando
 */
public class CadastroSFTPServices {

    private CadastroSFTPDao Dao = null;

    public CadastroSFTPServices() {
    }

    public CadastroSFTPServices(JTable Tabela, int Id, String Owner, String User, String Pass, String Host, String Porta, int Tipo, String Key)  {

        try {
            this.Dao = new CadastroSFTPDao(Tabela, Id, Owner, User, Pass, Host, Porta, Tipo, Key);
        } catch (IOException ex) {
            Logger.getLogger(CadastroSFTPServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public CadastroSFTPServices(JTable Tabela, int Id, String Owner, String User, String Pass, String Host, String Porta, int Tipo)  {

        try {
            this.Dao = new CadastroSFTPDao(Tabela, Id, Owner, User, Pass, Host, Porta, Tipo);
        } catch (IOException ex) {
            Logger.getLogger(CadastroSFTPServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preecherTabela() {

        try {

            this.Dao.preecherTabela();
            

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Falha ao tentar preencher a tabela +\n" + e.getMessage());

        }

    }

    public String obterChavePublica(String Owner)  {

        try {

            return this.Dao.obterChavePublica(Owner);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Falha ao tentar recuperar a chave publica +\n" + e.getMessage());

        }

        return "";
    }

    public void addRegistro()  {

        try {

            this.Dao.addRegistro();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Falha ao tentar adicionar registro +\n" + e.getMessage());

        }

    }

    public void removerRegistro()  {

        try {

            this.Dao.removerRegistro();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Falha ao tentar remover o registro +\n" + e.getMessage());

        }

    }

    public void alterarRegistro() {

        try {

            this.Dao.alterarRegistro();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Falha ao tentar alterar o registro +\n" + e.getMessage());

        }

    }

    public void gravarArquivoFTPConnection(String Modo)  {
        try {
            
            this.Dao.gravarArquivoFTPConnection(Modo);
            
        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Falha ao tentar gravar o arquivo SFTP +\n" + e.getMessage());

        }

    }

}
