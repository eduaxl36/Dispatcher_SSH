/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_services;

import br_com_kantar_dao.CopiadorDao;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Eduardo.Fernando
 */
public class CopiadorServices {

    private CopiadorDao Servico;

    public CopiadorServices(LocalDate Data, JTable Tabela) throws IOException {

        this.Servico = new CopiadorDao(Data, Tabela);

    }

    public CopiadorServices()  {

        this.Servico = new CopiadorDao();
    }

    public void zerarTabela() {

        this.Servico.zerarTabela();

    }

    public void executor()   {

        this.Servico.executor();

    }

    public void filtrarTabelaCopiador(List<String> Regioes, String Instancia) throws IOException {

        try {
            this.Servico.filtrarTabelaCopiador(Regioes, Instancia);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Falha ao tentar filtrar a tabela " + e.getMessage());

        }

    }

    public Set<String> obterInstancias() throws IOException {

        try {

            return this.Servico.obterInstancias();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Falha ao tentar recuperar a lista de instancias " + e.getMessage());

        }
        return null;
    }

    public Set<String> obterProcessos() throws IOException {

        try {

            return this.Servico.obterProcessos();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Falha ao tentar recuperar a lista de processos " + e.getMessage());

        }

        return null;

    }

    public List<String> obterListaRegioes() throws IOException {

        try {
            return this.Servico.obterListaRegioes();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Falha ao tentar recuperar a lista de regioes " + e.getMessage());

        }

        return null;

    }

}
