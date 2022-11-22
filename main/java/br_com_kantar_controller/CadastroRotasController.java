/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_controller;

import br_com_kantar_services.CadastroRotasServices;
import br_com_kantar_services.CopiadorServices;
import br_com_util.UtilTable;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Eduardo.Fernando
 */
public class CadastroRotasController extends Cadastro {

    ///INICIALIZACAO EVENTOS
    private javax.swing.JTextField txtCalcDataDestino;
    private javax.swing.JTextField txtCalcDataOrigem;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtInstancia;
    private javax.swing.JTextField txtInternalFolder;
    private javax.swing.JTextField txtOrigem;
    private javax.swing.JTextField txtPatronDestino;
    private javax.swing.JTextField txtPatronOrigem;
    private javax.swing.JTextField txtProcesso;
    private javax.swing.JComboBox<String> cbMode;
    private javax.swing.JComboBox<String> cbOwner;
    private javax.swing.JComboBox<String> cbPlaza;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JComboBox<String> cb_acao_destino;
    private javax.swing.JComboBox<String> cb_acao_origem;

    /// DEFINE VARIAVEIS DE SERVICOS
    private CadastroRotasServices CadastroDeRotasServico;
    private CopiadorServices ServicoCopiador;
    private UtilTable UtilitarioTabela;

    public CadastroRotasController(JTextField txtCalcDataDestino, JTextField txtCalcDataOrigem, JTextField txtDestino, JTextField txtInstancia, JTextField txtInternalFolder, JTextField txtOrigem, JTextField txtPatronDestino, JTextField txtPatronOrigem, JTextField txtProcesso, JComboBox<String> cbMode, JComboBox<String> cbOwner, JComboBox<String> cbPlaza, JComboBox<String> cbTipo, JComboBox<String> cb_acao_destino, JComboBox<String> cb_acao_origem, JTable datos, JLabel Id, JButton btnAdd, JButton btnAlterar, JButton btnCancelar, JButton btnConfirmar, JButton btnDown, JButton btnExcluir, JButton btnUp, JLabel txt_tool_tip) throws IOException {
        super(datos, Id, btnAdd, btnAlterar, btnCancelar, btnConfirmar, btnDown, btnExcluir, btnUp, txt_tool_tip);
        this.txtCalcDataDestino = txtCalcDataDestino;
        this.txtCalcDataOrigem = txtCalcDataOrigem;
        this.txtDestino = txtDestino;
        this.txtInstancia = txtInstancia;
        this.txtInternalFolder = txtInternalFolder;
        this.txtOrigem = txtOrigem;
        this.txtPatronDestino = txtPatronDestino;
        this.txtPatronOrigem = txtPatronOrigem;
        this.txtProcesso = txtProcesso;
        this.cbMode = cbMode;
        this.cbOwner = cbOwner;
        this.cbPlaza = cbPlaza;
        this.cbTipo = cbTipo;
        this.cb_acao_destino = cb_acao_destino;
        this.cb_acao_origem = cb_acao_origem;

        iniciarPacoteBasicosObjetos();
    }

    public final void iniciarPacoteBasicosObjetos() throws IOException {

        this.setModo("Modo de espera");

        inciaConstrutorServicos();
        carregaTabela();
        carregaCombos();

    }

    public void inciarServicoConstrutorDefault() {

        this.ServicoCopiador = new CopiadorServices();

    }

    public void inciaConstrutorServicos() {

        ServicoCopiador = new CopiadorServices();
        UtilitarioTabela = UtilTable.getInstance();
        CadastroDeRotasServico = new CadastroRotasServices(
                Integer.parseInt(this.getId().getText()),
                this.getDatos(),
                this.txtInstancia.getText(),
                this.cbPlaza.getSelectedItem().toString(),
                this.txtProcesso.getText(),
                this.cbTipo.getSelectedIndex(),
                this.txtOrigem.getText(),
                this.txtPatronOrigem.getText(),
                this.cb_acao_origem.getSelectedItem().toString(),
                this.txtDestino.getText(),
                this.txtPatronDestino.getText(),
                this.cb_acao_destino.getSelectedItem().toString(),
                this.cbMode.getSelectedItem().toString(),
                this.cbOwner.getSelectedItem().toString(),
                this.txtInternalFolder.getText(),
                Integer.parseInt(this.txtCalcDataOrigem.getText()),
                Integer.parseInt(this.txtCalcDataDestino.getText())
        );

    }

    public void carregaListaFTPS() throws IOException {

        this.cbPlaza.removeAllItems();

        this.CadastroDeRotasServico.obterListaFTPS().forEach(x -> {

            this.cbOwner.addItem(x);

        });

    }

    public void carregaListaProcessos() throws IOException {

        this.ServicoCopiador.obterListaRegioes().forEach(x -> {

            this.cbPlaza.addItem(x);

        });

    }

    public final void carregaCombos() throws IOException {

        carregaListaFTPS();
        carregaListaProcessos();

    }

    @Override
    public void carregaTabela() {

        this.CadastroDeRotasServico.carregarTabela();
        this.UtilitarioTabela.realizaAjuste(this.getDatos());

    }

    @Override
    public void adicionarDados() {

        inciarServicoConstrutorDefault();
        this.CadastroDeRotasServico.addRegistro();
    }

    @Override
    public void removerDados() {

        inciarServicoConstrutorDefault();
        this.CadastroDeRotasServico.excluirRegistro();

    }

    @Override
    public void alterarDados() {

        inciarServicoConstrutorDefault();
        this.CadastroDeRotasServico.alterarRegistro();

    }

    @Override
    public void gravarArquivoComAlteracoes() {
        inciarServicoConstrutorDefault();
        CadastroDeRotasServico.gravarTxt();

    }

    @Override
    public void comportamentoPosCofirmacao() {

        definirComportamentoPreliminaresBotoes();
        desativaCampos();
        gravarArquivoComAlteracoes();
        limparCampos();

    }

    @Override
    public void limparCampos() {

        txtDestino.setText("");
        txtOrigem.setText("");
        txtPatronOrigem.setText("");
        txtInstancia.setText("");
        txtProcesso.setText("");
        this.getId().setText("-1");
        txtCalcDataOrigem.setText("0");
        txtCalcDataDestino.setText("0");
        txtPatronOrigem.setText("");
        txtPatronDestino.setText("");
        txtInternalFolder.setText("-");

    }

    @Override
    public void ativarCampos() {

        txtDestino.setEnabled(true);
        txtOrigem.setEnabled(true);
        txtPatronOrigem.setEnabled(true);
        txtInstancia.setEnabled(true);
        txtProcesso.setEnabled(true);
        txtInternalFolder.setEnabled(true);
        txtPatronDestino.setEnabled(true);
        cb_acao_destino.setEnabled(true);
        cb_acao_origem.setEnabled(true);
        txtCalcDataDestino.setEnabled(true);
        txtCalcDataOrigem.setEnabled(true);
        cbMode.setEnabled(true);
        cbOwner.setEnabled(true);
        cbPlaza.setEnabled(true);
        cbTipo.setEnabled(true);

    }

    @Override
    public void desativaCampos() {

        txtDestino.setEnabled(false);
        txtOrigem.setEnabled(false);
        txtPatronOrigem.setEnabled(false);
        txtInstancia.setEnabled(false);
        txtProcesso.setEnabled(false);
        txtInternalFolder.setEnabled(false);
        txtPatronDestino.setEnabled(false);
        txtPatronOrigem.setEnabled(false);
        txtCalcDataDestino.setEnabled(false);
        txtCalcDataOrigem.setEnabled(false);
        cb_acao_destino.setEnabled(false);
        cb_acao_origem.setEnabled(false);
        cbMode.setEnabled(false);
        cbOwner.setEnabled(false);
        cbPlaza.setEnabled(false);
        cbTipo.setEnabled(false);

    }

    @Override
    public void transfereDadosTabelaParaCaixa() {

        int LinhaSelecionada = this.getDatos().getSelectedRow();

        if (LinhaSelecionada >= 0) {

            this.getId().setText("" + this.getDatos().getSelectedRow());
            txtInstancia.setText((String) this.getDatos().getValueAt(LinhaSelecionada, 0));
            cbPlaza.setSelectedItem((String) this.getDatos().getValueAt(LinhaSelecionada, 1));
            txtProcesso.setText((String) this.getDatos().getValueAt(LinhaSelecionada, 2));
            cbTipo.setSelectedIndex(Integer.parseInt("" + this.getDatos().getValueAt(LinhaSelecionada, 3)));
            txtOrigem.setText((String) this.getDatos().getValueAt(LinhaSelecionada, 4));
            txtPatronOrigem.setText((String) this.getDatos().getValueAt(LinhaSelecionada, 5));
            cb_acao_origem.setSelectedItem(this.getDatos().getValueAt(LinhaSelecionada, 6).toString());
            txtCalcDataOrigem.setText("" + this.getDatos().getValueAt(LinhaSelecionada, 7));
            txtDestino.setText((String) this.getDatos().getValueAt(LinhaSelecionada, 8));
            txtPatronDestino.setText((String) this.getDatos().getValueAt(LinhaSelecionada, 9));
            cb_acao_destino.setSelectedItem(this.getDatos().getValueAt(LinhaSelecionada, 10).toString());
            txtCalcDataDestino.setText("" + this.getDatos().getValueAt(LinhaSelecionada, 11));
            cbMode.setSelectedItem(this.getDatos().getValueAt(LinhaSelecionada, 12).toString());
            cbOwner.setSelectedItem(this.getDatos().getValueAt(LinhaSelecionada, 13).toString());
            txtInternalFolder.setText("" + this.getDatos().getValueAt(LinhaSelecionada, 14));

        }

    }

    @Override
    public void acaoCancela() {

        definirComportamentoPreliminaresBotoes();
        desativaCampos();
        this.getTxt_tool_tip().setText("Modo de espera");

    }

    @Override
    public void acaoConfirma() {

        inciaConstrutorServicos();

        switch (this.getModo()) {

            case "Modo de adição" -> {

                adicionarDados();
            }
            case "Modo de deleção" -> {
                inciaConstrutorServicos();
                removerDados();
            }
            case "Modo de edição" -> {

                alterarDados();
            }
        }

        comportamentoPosCofirmacao();

    }

}
