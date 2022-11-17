/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_ftp_factory;

import br_com_kantar_dao.FTPConnectionDao;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo.Fernando
 */
public class FactoryFTPConnection {
    
FTPConnectionDao ServicoFTPConnection;

    public FactoryFTPConnection() throws IOException {
        this.ServicoFTPConnection = new FTPConnectionDao();
    }

    public FTPService getFTPService(String Owner, int Tipo) throws IOException {
        
        FTPService Servico = null;
         
        switch (Tipo) {
            case 0 -> Servico = new FTPConnection(ServicoFTPConnection.obterDadosFTP(Owner, Tipo));
            case 1 -> Servico = new URLConnection(ServicoFTPConnection.obterDadosFTP(Owner, Tipo));
            case 2 -> Servico = new SFTPConnection(ServicoFTPConnection.obterDadosFTP(Owner, Tipo));
            default -> {
            }
        }

        return Servico;
    }

    
    public static void main(String[] args) throws IOException, Exception {
        
    }
    
}
