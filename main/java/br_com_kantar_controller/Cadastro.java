/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_controller;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo.Fernando
 */
public abstract class Cadastro {

    ///INICIALIZACAO EVENTOS
    private javax.swing.JTable datos;
    private javax.swing.JLabel Id;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnDown;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnUp;
    private JLabel txt_tool_tip;

    /// DEFINE VARIAVEIS DE CONTROLE JTABLE
    private boolean LinhaSelecionada = false;
    private int index;
    private DefaultTableModel Modelo;
    private String Modo;

    public Cadastro(JTable datos, JLabel Id, JButton btnAdd, JButton btnAlterar, JButton btnCancelar, JButton btnConfirmar, JButton btnDown, JButton btnExcluir, JButton btnUp, JLabel txt_tool_tip) {
        this.datos = datos;
        this.Id = Id;
        this.btnAdd = btnAdd;
        this.btnAlterar = btnAlterar;
        this.btnCancelar = btnCancelar;
        this.btnConfirmar = btnConfirmar;
        this.btnDown = btnDown;
        this.btnExcluir = btnExcluir;
        this.btnUp = btnUp;
        this.txt_tool_tip = txt_tool_tip;

    }

    public abstract void alterarDados();

    public abstract void adicionarDados();

    public abstract void removerDados();

    public abstract void desativaCampos();

    public abstract void ativarCampos();

    public abstract void limparCampos();

    public abstract void transfereDadosTabelaParaCaixa();

    public abstract void acaoConfirma();

    public abstract void acaoCancela();

    public abstract void comportamentoPosCofirmacao();

    public abstract void gravarArquivoComAlteracoes();
    
    public abstract void carregaTabela();
    
    public boolean checarSelecaoDaLinha() {

        if (LinhaSelecionada == false) {

            Modelo = (DefaultTableModel) datos.getModel();
            LinhaSelecionada = true;

        }

        return LinhaSelecionada;
    }

    public void moverLinhaParaCima() {

        checarSelecaoDaLinha();

        index = datos.getSelectedRow();
        if (index > 0) {

            Modelo.moveRow(index, index, index - 1);
            datos.setRowSelectionInterval(index - 1, index - 1);
            datos.setSelectionBackground(Color.BLACK);
            int UpdateId = Integer.parseInt(Id.getText()) - 1;
            Id.setText("" + UpdateId);
        }

    }

    public void moverLinhaParaBaixo() {

        checarSelecaoDaLinha();

        index = datos.getSelectedRow();
        if (index < Modelo.getRowCount() - 1) {

            Modelo.moveRow(index, index, index + 1);
            datos.setRowSelectionInterval(index + 1, index + 1);
            datos.setSelectionBackground(Color.BLACK);
            int UpdateId = Integer.parseInt(Id.getText()) + 1;
            Id.setText("" + UpdateId);

        }

    }

    public void AtivaExclusao() {

        Modo = "Modo de deleção";
        comportamentoPosClickBotaoExclusao();
        txt_tool_tip.setText(Modo);

    }

    public void AtivaAdicao()  {

        Modo = "Modo de adição";
        limparCampos();
        ativarCampos();
        comportamentoPosClickBotaoAdicao();

    }

    public void AtivaEdicao()  {

        Modo = "Modo de edição";
        ativarCampos();
        comportamentoPosClickBotaoEdicao();
        txt_tool_tip.setText(Modo);

    }

    public void definirComportamentoPreliminaresBotoes() {

        btnCancelar.setEnabled(false);
        btnConfirmar.setEnabled(false);
        btnAdd.setEnabled(true);
        btnExcluir.setEnabled(true);
        btnAlterar.setEnabled(true);
        btnUp.setEnabled(false);
        btnDown.setEnabled(false);

    }

    public void comportamentoPosClickBotaoEdicao() {

        datos.setEnabled(true);
        btnAdd.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnConfirmar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnUp.setEnabled(true);
        btnDown.setEnabled(true);

    }

    public void comportamentoPosClickBotaoExclusao() {

        btnAdd.setEnabled(false);
        btnAlterar.setEnabled(false);
        btnConfirmar.setEnabled(true);
        btnCancelar.setEnabled(true);

    }

    public void comportamentoPosClickBotaoAdicao() {

        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnConfirmar.setEnabled(true);
        btnCancelar.setEnabled(true);

    }

    public JTable getDatos() {
        return datos;
    }

    public JLabel getId() {
        return Id;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnAlterar() {
        return btnAlterar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }

    public JButton getBtnDown() {
        return btnDown;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    public JButton getBtnUp() {
        return btnUp;
    }

    public JLabel getTxt_tool_tip() {
        return txt_tool_tip;
    }

    public boolean isLinhaSelecionada() {
        return LinhaSelecionada;
    }

    public int getIndex() {
        return index;
    }

    public DefaultTableModel getModelo() {
        return Modelo;
    }

    public String getModo() {
        return Modo;
    }

    public void setDatos(JTable datos) {
        this.datos = datos;
    }

    public void setId(JLabel Id) {
        this.Id = Id;
    }

    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
    }

    public void setBtnAlterar(JButton btnAlterar) {
        this.btnAlterar = btnAlterar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public void setBtnConfirmar(JButton btnConfirmar) {
        this.btnConfirmar = btnConfirmar;
    }

    public void setBtnDown(JButton btnDown) {
        this.btnDown = btnDown;
    }

    public void setBtnExcluir(JButton btnExcluir) {
        this.btnExcluir = btnExcluir;
    }

    public void setBtnUp(JButton btnUp) {
        this.btnUp = btnUp;
    }

    public void setTxt_tool_tip(JLabel txt_tool_tip) {
        this.txt_tool_tip = txt_tool_tip;
    }

    public void setLinhaSelecionada(boolean LinhaSelecionada) {
        this.LinhaSelecionada = LinhaSelecionada;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setModelo(DefaultTableModel Modelo) {
        this.Modelo = Modelo;
    }

    public void setModo(String Modo) {
        this.Modo = Modo;
    }

}
