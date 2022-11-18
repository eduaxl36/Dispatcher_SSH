/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_services;

import br_com_kantar_dao.CopiadorDao;
import br_com_kantar_dao.FTPConnectionDao;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo.Fernando
 */
public class CopiadorServices {

    
    private CopiadorDao Servico;

    public CopiadorServices(LocalDate Data, JTable Tabela) throws IOException {

        this.Servico = new CopiadorDao(Data, Tabela);

    }

    public CopiadorServices() throws IOException {
        
        this.Servico = new CopiadorDao();
    }

    public void executor() throws Exception {

        try {

            this.Servico.executor();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void filtrarTabelaCopiador(List<String> Regioes, String Instancia) throws IOException {

        this.Servico.filtrarTabelaCopiador(Regioes, Instancia);

    }

    public Set<String> obterInstancias() throws IOException {

        return this.Servico.obterInstancias();

    }

    public Set<String> obterProcessos() throws IOException {

        return this.Servico.obterProcessos();

    }

    public List<String> obterListaRegioes() throws IOException {

        return this.Servico.obterListaRegioes();

    }

}
