/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br_com_kantar_controller;

import br_com_kantar_services.CadastroSFTPServices;
import br_com_util.UtilTable;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Eduardo.Fernando
 */
public class CadastroSFTPController extends Cadastro {

    /// DEFINE VARIAVEIS DE EVENTOS
    private javax.swing.JTextField txtFTPNome;
    private javax.swing.JTextField txtHost;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtPorta;
    private javax.swing.JTextField txtUser;
    private javax.swing.JComboBox<String> cbtipo;
    private javax.swing.JTextArea area;

    /// DEFINE VARIAVEIS DE SERVICO
    private CadastroSFTPServices ServicoCadastroSFTP;
    private UtilTable UtilitarioTabela;

    public CadastroSFTPController(JTextField txtFTPNome, JTextField txtHost, JTextField txtPass, JTextField txtPorta, JTextField txtUser, JComboBox<String> cbtipo, JTextArea area, JTable datos, JLabel Id, JButton btnAdd, JButton btnAlterar, JButton btnCancelar, JButton btnConfirmar, JButton btnDown, JButton btnExcluir, JButton btnUp, JLabel txt_tool_tip) throws IOException {

        super(datos, Id, btnAdd, btnAlterar, btnCancelar, btnConfirmar, btnDown, btnExcluir, btnUp, txt_tool_tip);
        this.txtFTPNome = txtFTPNome;
        this.txtHost = txtHost;
        this.txtPass = txtPass;
        this.txtPorta = txtPorta;
        this.txtUser = txtUser;
        this.cbtipo = cbtipo;
        this.area = area;

        iniciaEventosDeServico();
        carregaTabela();

    }

    public final void iniciaEventosDeServico()  {

        UtilitarioTabela = UtilTable.getInstance();
        this.ServicoCadastroSFTP = new CadastroSFTPServices(
                this.getDatos(),
                Integer.parseInt(this.getId().getText()),
                txtFTPNome.getText(),
                txtUser.getText(),
                txtPass.getText(),
                txtHost.getText(),
                txtPorta.getText(),
                cbtipo.getSelectedIndex(),
                area.getText()
        );

    }

    @Override
    public final void carregaTabela() {

        this.ServicoCadastroSFTP.preecherTabela();
        this.UtilitarioTabela.realizaAjuste(this.getDatos());

    }

    @Override
    public void alterarDados() {
      
            this.ServicoCadastroSFTP.alterarRegistro();

    }

    @Override
    public void adicionarDados()  {

        this.ServicoCadastroSFTP.addRegistro();

    }

    @Override
    public void removerDados() {

        this.ServicoCadastroSFTP.removerRegistro();

    }

    @Override
    public void desativaCampos() {

        txtFTPNome.setEnabled(false);
        txtHost.setEnabled(false);
        txtUser.setEnabled(false);
        txtPass.setEnabled(false);
        txtPorta.setEnabled(false);
        cbtipo.setEnabled(false);
        area.setEnabled(false);

    }

    @Override
    public void ativarCampos() {

        txtFTPNome.setEnabled(true);
        txtHost.setEnabled(true);
        txtUser.setEnabled(true);
        txtPass.setEnabled(true);
        txtPorta.setEnabled(true);
        cbtipo.setEnabled(true);
        area.setEnabled(true);

    }

    @Override
    public void limparCampos() {

        txtFTPNome.setText("");
        txtHost.setText("");
        txtUser.setText("");
        txtPass.setText("");
        txtPorta.setText("");
        area.setText("");

    }

    @Override
    public void transfereDadosTabelaParaCaixa()  {

        int LinhaSelecionada = this.getDatos().getSelectedRow();
        this.getId().setText("" + this.getDatos().getSelectedRow());
        txtFTPNome.setText((String) this.getDatos().getValueAt(LinhaSelecionada, 0));
        txtHost.setText((String) this.getDatos().getValueAt(LinhaSelecionada, 1));
        txtUser.setText((String) this.getDatos().getValueAt(LinhaSelecionada, 2));
        txtPass.setText((String) this.getDatos().getValueAt(LinhaSelecionada, 3));
        txtPorta.setText("" + this.getDatos().getValueAt(LinhaSelecionada, 4));
        cbtipo.setSelectedIndex(Integer.parseInt("" + this.getDatos().getValueAt(LinhaSelecionada, 5)));

        if (Integer.parseInt("" + this.getDatos().getValueAt(LinhaSelecionada, 5)) == 2) {

            area.setText(this.ServicoCadastroSFTP.obterChavePublica(this.txtFTPNome.getText()));

        } else {

            area.setText("");

        }

    }

    @Override
    public void gravarArquivoComAlteracoes()  {

        this.ServicoCadastroSFTP.gravarArquivoFTPConnection(this.getModo());

    }

    @Override
    public void acaoConfirma()  {

        iniciaEventosDeServico();

        switch (this.getModo()) {
            case "Modo de adição" ->
                adicionarDados();
            case "Modo de deleção" ->
                removerDados();
            default ->
                alterarDados();
        }

        comportamentoPosCofirmacao();

    }

    @Override
    public void acaoCancela() {

        definirComportamentoPreliminaresBotoes();
        desativaCampos();
        this.getTxt_tool_tip().setText("Modo de espera");

    }

    @Override
    public void comportamentoPosCofirmacao() {

        definirComportamentoPreliminaresBotoes();
        desativaCampos();
        gravarArquivoComAlteracoes();
        limparCampos();

    }

}
