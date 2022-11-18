/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br_com_kantar_interfaces;

import br_com_kantar_constraints.IOConstraints;
import br_com_kantar_services.CadastroRotasServices;
import br_com_kantar_services.CopiadorServices;
import br_com_util.UtilTable;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo.Fernando
 */
public final class CadastroRotas extends javax.swing.JInternalFrame {

    private CadastroRotasServices Servico;
    private CopiadorServices ServicoCopiador;
    private String Modo = "";
    private boolean LinhaSelecionada = false;
    private int index;
    private DefaultTableModel Model;

    public CadastroRotas() throws IOException {
        
        
        initComponents();
        inciaServicoEventos();
        this.Servico.carregarTabela();
        UtilTable.ajustarTabela(datos);
        carregaCombos();
        
        Modo = "Modo de espera";

    }

    public void inciaServicoEventos() throws IOException {

        ServicoCopiador = new CopiadorServices();
        Servico = new CadastroRotasServices(
                Integer.parseInt(Id.getText()),
                datos,
                txtInstancia.getText(),
                cbPlaza.getSelectedItem().toString(),
                txtProcesso.getText(),
                cbTipo.getSelectedIndex(),
                txtOrigem.getText(),
                txtPatronOrigem.getText(),
                cb_acao_origem.getSelectedItem().toString(),
                txtDestino.getText(),
                txtPatronDestino.getText(),
                cb_acao_destino.getSelectedItem().toString(),
                cbMode.getSelectedItem().toString(),
                cbOwner.getSelectedItem().toString(),
                txtInternalFolder.getText(),
                Integer.parseInt(txtCalcDataOrigem.getText()),
                Integer.parseInt(txtCalcDataDestino.getText())
        );
    

    }

    public final void carregaCombos() throws IOException {

        cbPlaza.removeAllItems();

        this.Servico.obterListaFTPS().forEach(x -> {

            cbOwner.addItem(x);

        });

        this.ServicoCopiador.obterListaRegioes().forEach(x -> {

            cbPlaza.addItem(x);

        });

    }

    public void AtivaEdicao() {

        Modo = "Modo de edição";

        ativarCampos();
        comportamentoBotoesAtivacaoEdicao();

    }

    public void AtivaExclusao() {

        Modo = "Modo de deleção";
        comportamentoBotoesAtivacaoExclusao();

    }

    public void AtivaAdicao() {

        Modo = "Modo de adição";
        limparCampos();
        ativarCampos();
        comportamentoBotoesAtivacaoAdicao();

    }

    public void cargaInicialBotoes() {

        btnAdd.setEnabled(true);
        btnAlterar.setEnabled(true);
        btnExcluir.setEnabled(true);
        btnConfirmar.setEnabled(true);
        btnUp.setEnabled(false);
        btnDown.setEnabled(false);

    }

    public void moverLinhaParaCima() {

        if (LinhaSelecionada == false) {

            Model = (DefaultTableModel) datos.getModel();
            LinhaSelecionada = true;

        }

        index = datos.getSelectedRow();
        if (index > 0) {

            Model.moveRow(index, index, index - 1);
            datos.setRowSelectionInterval(index - 1, index - 1);
            datos.setSelectionBackground(Color.BLACK);
            int UpdateId = Integer.parseInt(Id.getText()) - 1;
            Id.setText("" + UpdateId);
        }

    }

    public void moverLinhaParaBaixo() {

        if (LinhaSelecionada == false) {

            Model = (DefaultTableModel) datos.getModel();
            LinhaSelecionada = true;

        }

        index = datos.getSelectedRow();
        if (index < Model.getRowCount() - 1) {

            Model.moveRow(index, index, index + 1);
            datos.setRowSelectionInterval(index + 1, index + 1);
            datos.setSelectionBackground(Color.BLACK);
            int UpdateId = Integer.parseInt(Id.getText()) + 1;
            Id.setText("" + UpdateId);

        }

    }

    public void limparCampos() {

        txtDestino.setText("");
        txtOrigem.setText("");
        txtPatronOrigem.setText("");
        txtInstancia.setText("");
        txtProcesso.setText("");
        Id.setText("-1");
        txtCalcDataOrigem.setText("0");
        txtCalcDataDestino.setText("0");
        txtPatronOrigem.setText("");
        txtPatronDestino.setText("");
        txtInternalFolder.setText("-");

    }

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

    public void desativaSeletorConfirmaCancela() {

        btnConfirmar.setEnabled(false);
        btnCancelar.setEnabled(false);
        datos.setEnabled(true);

    }

    public void comportamentoBotoesAtivacaoAdicao() {

        datos.setEnabled(false);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnConfirmar.setEnabled(true);
        btnCancelar.setEnabled(true);

    }

    public void comportamentoBotoesAtivacaoEdicao() {
        datos.setEnabled(true);
        btnAdd.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnConfirmar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnUp.setEnabled(true);
        btnDown.setEnabled(true);

    }

    public void comportamentoBotoesAtivacaoExclusao() {

        btnAdd.setEnabled(false);
        btnAlterar.setEnabled(false);
        btnConfirmar.setEnabled(true);
        btnCancelar.setEnabled(true);

    }

    public void comportamentoPosCofirmacao() throws FileNotFoundException {

        btnCancelar.setEnabled(false);
        btnConfirmar.setEnabled(false);
        btnAdd.setEnabled(true);
        btnExcluir.setEnabled(true);
        btnAlterar.setEnabled(true);
        desativaCampos();
        desativaSeletorConfirmaCancela();
        datos.setEnabled(true);
        this.Servico.gravarTxt();
        limparCampos();

    }

    public void transfereDadosTabelaParaCaixa() {

        int LinhaSelecionada = datos.getSelectedRow();

        Id.setText("" + datos.getSelectedRow());
        txtInstancia.setText((String) datos.getValueAt(LinhaSelecionada, 0));
        cbPlaza.setSelectedItem((String) datos.getValueAt(LinhaSelecionada, 1));
        txtProcesso.setText((String) datos.getValueAt(LinhaSelecionada, 2));
        cbTipo.setSelectedIndex(Integer.parseInt("" + datos.getValueAt(LinhaSelecionada, 3)));
        txtOrigem.setText((String) datos.getValueAt(LinhaSelecionada, 4));
        txtPatronOrigem.setText((String) datos.getValueAt(LinhaSelecionada, 5));
        cb_acao_origem.setSelectedItem(datos.getValueAt(LinhaSelecionada, 6).toString());
        txtCalcDataOrigem.setText("" + datos.getValueAt(LinhaSelecionada, 7));
        txtDestino.setText((String) datos.getValueAt(LinhaSelecionada, 8));
        txtPatronDestino.setText((String) datos.getValueAt(LinhaSelecionada, 9));
        cb_acao_destino.setSelectedItem(datos.getValueAt(LinhaSelecionada, 10).toString());
        txtCalcDataDestino.setText("" + datos.getValueAt(LinhaSelecionada, 11));
        cbMode.setSelectedItem(datos.getValueAt(LinhaSelecionada, 12).toString());
        cbOwner.setSelectedItem(datos.getValueAt(LinhaSelecionada, 13).toString());
        txtInternalFolder.setText("" + datos.getValueAt(LinhaSelecionada, 14));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pn = new javax.swing.JPanel();
        lblOwner = new javax.swing.JLabel();
        lblOrigem = new javax.swing.JLabel();
        Id = new javax.swing.JLabel();
        cbPlaza = new javax.swing.JComboBox<>();
        cbMode = new javax.swing.JComboBox<>();
        txtProcesso = new javax.swing.JTextField();
        txtOrigem = new javax.swing.JTextField();
        txtDestino = new javax.swing.JTextField();
        txtInstancia = new javax.swing.JTextField();
        cbOwner = new javax.swing.JComboBox<>();
        lblPraca = new javax.swing.JLabel();
        txtPatronOrigem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        datos = new javax.swing.JTable();
        lblProcesso = new javax.swing.JLabel();
        PB = new javax.swing.JProgressBar();
        btnAlterar = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        lblMode = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        txt_tool_tip = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        lblInstancia = new javax.swing.JLabel();
        btnDown = new javax.swing.JButton();
        btnUp = new javax.swing.JButton();
        txtPatronDestino = new javax.swing.JTextField();
        txtInternalFolder = new javax.swing.JTextField();
        lblPadraoD = new javax.swing.JLabel();
        lblDestino2 = new javax.swing.JLabel();
        lblDestino3 = new javax.swing.JLabel();
        lblPadraoO = new javax.swing.JLabel();
        cb_acao_origem = new javax.swing.JComboBox<>();
        cb_acao_destino = new javax.swing.JComboBox<>();
        lblPadraoO1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblPadraoD1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtCalcDataOrigem = new javax.swing.JTextField();
        lblPadraoD2 = new javax.swing.JLabel();
        txtCalcDataDestino = new javax.swing.JTextField();
        lblPadraoD3 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();

        setClosable(true);

        Pn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblOwner.setText("Owner");
        Pn.add(lblOwner, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 100, 20));

        lblOrigem.setText("Origem");
        Pn.add(lblOrigem, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 90, 20));

        Id.setText("-1");
        Pn.add(Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 40, 30));

        cbPlaza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbPlaza.setEnabled(false);
        Pn.add(cbPlaza, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 160, -1));

        cbMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "copy", "FTP_COPY", "FTP_COPY_EXTRACT", "FTP_UPLOAD", "ADD_ZIP" }));
        cbMode.setEnabled(false);
        Pn.add(cbMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 160, -1));

        txtProcesso.setEnabled(false);
        Pn.add(txtProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 260, -1));

        txtOrigem.setEnabled(false);
        Pn.add(txtOrigem, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 360, -1));

        txtDestino.setEnabled(false);
        Pn.add(txtDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 360, -1));

        txtInstancia.setEnabled(false);
        Pn.add(txtInstancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 260, -1));

        cbOwner.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "local" }));
        cbOwner.setEnabled(false);
        Pn.add(cbOwner, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, 160, -1));

        lblPraca.setText("Base");
        Pn.add(lblPraca, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 70, 20));

        txtPatronOrigem.setEnabled(false);
        Pn.add(txtPatronOrigem, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 160, -1));

        datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Instancia", "Praça", "Processo", "Tipo", "Origem", "Padrão Origem", "Ação sobre Padrão", "Calc Data", "Destino", "Padrão Destino", "Ação sobre Padrão", "Calc Data", "Tipo", "Owner", "Internal Folder"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        datos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        datos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        datos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                datosMousePressed(evt);
            }
        });
        datos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                datosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                datosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                datosKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(datos);

        Pn.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 570, 470));

        lblProcesso.setText("Processo");
        Pn.add(lblProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 70, 20));
        Pn.add(PB, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 500, 570, 30));

        btnAlterar.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\edit.png")); // NOI18N
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        Pn.add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 490, 50, 40));

        btnAdd.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\Add.png")); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        Pn.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 50, 40));

        btnExcluir.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\delete.png")); // NOI18N
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        Pn.add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 50, 40));

        lblMode.setText("Mode");
        Pn.add(lblMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 100, 20));

        btnConfirmar.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\confirm.png")); // NOI18N
        btnConfirmar.setEnabled(false);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });
        Pn.add(btnConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, 50, 40));

        txt_tool_tip.setBackground(new java.awt.Color(255, 102, 102));
        txt_tool_tip.setForeground(new java.awt.Color(255, 0, 0));
        txt_tool_tip.setText("Modo de espera");
        Pn.add(txt_tool_tip, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 190, 30));

        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\cancel.png")); // NOI18N
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        Pn.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 490, 50, 40));

        lblInstancia.setText("Instancia");
        Pn.add(lblInstancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 60, 40));

        btnDown.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\bajo.png")); // NOI18N
        btnDown.setEnabled(false);
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownActionPerformed(evt);
            }
        });
        Pn.add(btnDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, 50, 40));

        btnUp.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\cima.png")); // NOI18N
        btnUp.setEnabled(false);
        btnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpActionPerformed(evt);
            }
        });
        Pn.add(btnUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, 50, 40));

        txtPatronDestino.setEnabled(false);
        Pn.add(txtPatronDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 160, -1));

        txtInternalFolder.setText("-");
        txtInternalFolder.setEnabled(false);
        txtInternalFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInternalFolderActionPerformed(evt);
            }
        });
        Pn.add(txtInternalFolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 160, -1));

        lblPadraoD.setText("Ação");
        Pn.add(lblPadraoD, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 60, 20));

        lblDestino2.setText("Internal Folder");
        Pn.add(lblDestino2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 90, 20));

        lblDestino3.setText("Destino");
        Pn.add(lblDestino3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 90, 20));

        lblPadraoO.setText("Padrão");
        Pn.add(lblPadraoO, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 60, 20));

        cb_acao_origem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diario", "HexaDecimal", "Semanal" }));
        cb_acao_origem.setEnabled(false);
        Pn.add(cb_acao_origem, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 160, -1));

        cb_acao_destino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diario", "HexaDecimal", "Semanal" }));
        cb_acao_destino.setEnabled(false);
        Pn.add(cb_acao_destino, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 160, -1));

        lblPadraoO1.setText("Padrão");
        Pn.add(lblPadraoO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 60, 20));
        Pn.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 520, -1));
        Pn.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 520, 10));

        lblPadraoD1.setText("+/-");
        Pn.add(lblPadraoD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 60, 20));
        Pn.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 520, 20));

        txtCalcDataOrigem.setText("0");
        txtCalcDataOrigem.setEnabled(false);
        Pn.add(txtCalcDataOrigem, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 60, -1));

        lblPadraoD2.setText("Ação");
        Pn.add(lblPadraoD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 60, 20));

        txtCalcDataDestino.setText("0");
        txtCalcDataDestino.setEnabled(false);
        Pn.add(txtCalcDataDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, 60, -1));

        lblPadraoD3.setText("+/-");
        Pn.add(lblPadraoD3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 60, 20));

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FTP", "FTP URL", "SFTP", "Local" }));
        cbTipo.setEnabled(false);
        Pn.add(cbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pn, javax.swing.GroupLayout.DEFAULT_SIZE, 1108, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Pn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void datosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datosMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_datosMouseClicked

    private void datosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datosMousePressed

        transfereDadosTabelaParaCaixa();

    }//GEN-LAST:event_datosMousePressed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        AtivaAdicao();
        txt_tool_tip.setText(Modo);

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

        AtivaExclusao();
        txt_tool_tip.setText(Modo);

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed

        AtivaEdicao();
        txt_tool_tip.setText(Modo);

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        cargaInicialBotoes();
        desativaCampos();
        desativaSeletorConfirmaCancela();
        txt_tool_tip.setText("Modo de espera");

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void datosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datosKeyPressed

        transfereDadosTabelaParaCaixa();
    }//GEN-LAST:event_datosKeyPressed

    private void datosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datosKeyTyped

        transfereDadosTabelaParaCaixa();
    }//GEN-LAST:event_datosKeyTyped

    private void datosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datosKeyReleased

        transfereDadosTabelaParaCaixa();

    }//GEN-LAST:event_datosKeyReleased

    public void validaInsercao() {

        IOConstraints.validaPreenchimentoInstancia(txtInstancia.getText());
        IOConstraints.validaPreenchimentoOrigem(txtOrigem.getText());
        IOConstraints.validaPreenchimentoDestino(txtDestino.getText());
        IOConstraints.validaPreenchimentoPadrao(txtPatronOrigem.getText());

    }


    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed

        try {

            inciaServicoEventos();

            switch (Modo) {
                case "Modo de adição" -> {
                    validaInsercao();
                    this.Servico.addRegistro();
                }
                case "Modo de deleção" -> {
                    inciaServicoEventos();
                    this.Servico.excluirRegistro();
                }
                case "Modo de edição"  -> {
                    validaInsercao();
                    this.Servico.alterarRegistro();
                }
            }

            
            comportamentoPosCofirmacao();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Erro ao escrever o arquivo : " + e);

        } finally {

            UtilTable.ajustarTabela(datos);

        }

    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpActionPerformed

        moverLinhaParaCima();


    }//GEN-LAST:event_btnUpActionPerformed

    private void btnDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownActionPerformed

        moverLinhaParaBaixo();
    }//GEN-LAST:event_btnDownActionPerformed

    private void txtInternalFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInternalFolderActionPerformed

    }//GEN-LAST:event_txtInternalFolderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Id;
    private javax.swing.JProgressBar PB;
    private javax.swing.JPanel Pn;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnDown;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnUp;
    private javax.swing.JComboBox<String> cbMode;
    private javax.swing.JComboBox<String> cbOwner;
    private javax.swing.JComboBox<String> cbPlaza;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JComboBox<String> cb_acao_destino;
    private javax.swing.JComboBox<String> cb_acao_origem;
    private javax.swing.JTable datos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblDestino2;
    private javax.swing.JLabel lblDestino3;
    private javax.swing.JLabel lblInstancia;
    private javax.swing.JLabel lblMode;
    private javax.swing.JLabel lblOrigem;
    private javax.swing.JLabel lblOwner;
    private javax.swing.JLabel lblPadraoD;
    private javax.swing.JLabel lblPadraoD1;
    private javax.swing.JLabel lblPadraoD2;
    private javax.swing.JLabel lblPadraoD3;
    private javax.swing.JLabel lblPadraoO;
    private javax.swing.JLabel lblPadraoO1;
    private javax.swing.JLabel lblPraca;
    private javax.swing.JLabel lblProcesso;
    private javax.swing.JTextField txtCalcDataDestino;
    private javax.swing.JTextField txtCalcDataOrigem;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtInstancia;
    private javax.swing.JTextField txtInternalFolder;
    private javax.swing.JTextField txtOrigem;
    private javax.swing.JTextField txtPatronDestino;
    private javax.swing.JTextField txtPatronOrigem;
    private javax.swing.JTextField txtProcesso;
    private javax.swing.JLabel txt_tool_tip;
    // End of variables declaration//GEN-END:variables
}
