/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_controller;

import br_com_kantar_services.CopiadorServices;
import com.toedter.calendar.JDateChooser;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JTable;

/**
 *
 * @author Eduardo.Fernando
 */
public class CopiadorController {

    private CopiadorServices ServicoCopiador;

    private javax.swing.JProgressBar PB;
    private javax.swing.JComboBox<String> cb_itens;
    private javax.swing.JTable datos;
    private com.toedter.calendar.JDateChooser dp_data;
    private javax.swing.JList<String> lstRegioes;

    public CopiadorController(JProgressBar PB, JComboBox<String> cb_itens, JTable datos, JDateChooser dp_data, JList<String> lstRegioes) throws IOException {

        this.PB = PB;
        this.cb_itens = cb_itens;
        this.datos = datos;
        this.dp_data = dp_data;
        this.lstRegioes = lstRegioes;

        iniciarServicos();
       

    }

    public final void iniciarServicos() throws IOException {

        this.ServicoCopiador = new CopiadorServices();

    }

    public void selecionarTodasRegioes() {

        int Inicio = 0;
        int Fim = lstRegioes.getModel().getSize() - 1;
        if (Fim >= 0) {
            lstRegioes.setSelectionInterval(Inicio, Fim);
        }

    }

    public void carregarCombos() throws IOException {

        preencherInstancias();
        preencherRegioes();

    }

    public void preencherInstancias() throws IOException {

        Calendar Calendario = Calendar.getInstance();

        dp_data.setDate(Calendario.getTime());

        this.ServicoCopiador.obterInstancias().forEach(x -> {

            cb_itens.addItem(x);

        });

    }

    public void preencherRegioes() throws IOException {

        DefaultListModel ModeloLista = new DefaultListModel();
        ModeloLista.clear();

        this.ServicoCopiador.obterListaRegioes().forEach(x -> {

            ModeloLista.addElement(x);

        });

        lstRegioes.setModel(ModeloLista);

    }

    public List<String> retornaSelecionados() throws IOException {

        List<String> SelectedRegions = lstRegioes.getSelectedValuesList();

        return SelectedRegions;
    }

    public void aplicarFiltroTabela() throws IOException {

        LocalDate DataInput = dp_data.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        this.ServicoCopiador = new CopiadorServices(DataInput, datos);
        this.ServicoCopiador.zerarTabela();
        this.ServicoCopiador.filtrarTabelaCopiador(retornaSelecionados(), cb_itens.getSelectedItem().toString());

    }

    public void executarInstrucoes() {

        new Thread() {

            @Override
            public void run() {
                PB.setIndeterminate(true);
                try {

                    LocalDate DataInput = dp_data.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    ServicoCopiador = new CopiadorServices(DataInput, datos);
                    ServicoCopiador.executor();

                } catch (Exception ex) {

                    PB.setIndeterminate(false);
                }
                PB.setIndeterminate(false);
            }

        }.start();

    }

}
