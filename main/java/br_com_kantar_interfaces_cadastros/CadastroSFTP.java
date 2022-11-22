/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br_com_kantar_interfaces_cadastros;

import br_com_kantar_controller.CadastroRotasController;
import br_com_kantar_controller.CadastroSFTPController;
import java.io.IOException;

/**
 *
 * @author Eduardo.Fernando
 */
public final class CadastroSFTP extends javax.swing.JInternalFrame {

    private CadastroSFTPController Controladora;

    public CadastroSFTP() throws IOException {

        initComponents();
        Controladora = new CadastroSFTPController(txtFTPNome, txtHost, txtPass, txtPorta, txtUser, cbtipo, area, datos, Id, btnAdd, btnAlterar, btnCancelar, btnConfirmar, btnDown, btnExcluir, btnUp, txt_tool_tip);

    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn = new javax.swing.JPanel();
        txtHost = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lblUser = new javax.swing.JLabel();
        txtPass = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        lblPorta = new javax.swing.JLabel();
        txtPorta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        txtFTPNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        datos = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnUp = new javax.swing.JButton();
        btnDown = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        Id = new javax.swing.JLabel();
        txt_tool_tip = new javax.swing.JLabel();
        lblPorta1 = new javax.swing.JLabel();
        cbtipo = new javax.swing.JComboBox<>();

        setClosable(true);

        pn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtHost.setEnabled(false);
        pn.add(txtHost, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 240, -1));

        jLabel1.setText("FTP");
        pn.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 60, 20));

        txtUser.setEnabled(false);
        pn.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 240, -1));

        lblUser.setText("User");
        pn.add(lblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 60, 20));

        txtPass.setEnabled(false);
        pn.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 240, -1));

        lblPass.setText("Pass");
        pn.add(lblPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 60, 20));

        lblPorta.setText("Porta");
        pn.add(lblPorta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 60, 20));

        txtPorta.setEnabled(false);
        pn.add(txtPorta, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 50, -1));

        area.setColumns(20);
        area.setLineWrap(true);
        area.setRows(5);
        area.setEnabled(false);
        jScrollPane1.setViewportView(area);

        pn.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 290, 70));

        txtFTPNome.setEnabled(false);
        pn.add(txtFTPNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 240, -1));

        jLabel2.setText("Host");
        pn.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 60, 20));

        datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FTP", "Host", "User", "Pass", "Porta", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        jScrollPane2.setViewportView(datos);

        pn.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 530, 430));

        btnAdd.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\Add.png")); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        pn.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 50, 40));

        btnExcluir.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\delete.png")); // NOI18N
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        pn.add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 50, 40));

        btnAlterar.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\edit.png")); // NOI18N
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        pn.add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 50, 40));

        btnUp.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\cima.png")); // NOI18N
        btnUp.setEnabled(false);
        btnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpActionPerformed(evt);
            }
        });
        pn.add(btnUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 50, 40));

        btnDown.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\bajo.png")); // NOI18N
        btnDown.setEnabled(false);
        btnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownActionPerformed(evt);
            }
        });
        pn.add(btnDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 50, 40));

        btnConfirmar.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\confirm.png")); // NOI18N
        btnConfirmar.setEnabled(false);
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });
        pn.add(btnConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 50, 40));

        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Temp\\img\\cancel.png")); // NOI18N
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pn.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 50, 40));

        Id.setText("-1");
        pn.add(Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 40, 30));

        txt_tool_tip.setBackground(new java.awt.Color(255, 102, 102));
        txt_tool_tip.setForeground(new java.awt.Color(255, 0, 0));
        txt_tool_tip.setText("Aguardando ação");
        pn.add(txt_tool_tip, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 220, 30));

        lblPorta1.setText("Tipo");
        pn.add(lblPorta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 60, 20));

        cbtipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0 - FTPS", "1 - FTP URL", "2 - SFTP", "3 - None" }));
        cbtipo.setEnabled(false);
        pn.add(cbtipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn, javax.swing.GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        Controladora.AtivaAdicao();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

        Controladora.AtivaExclusao();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed

        Controladora.AtivaEdicao();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarActionPerformed


    private void btnUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpActionPerformed

        Controladora.moverLinhaParaCima();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpActionPerformed

    private void btnDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownActionPerformed

        Controladora.moverLinhaParaBaixo();
    }//GEN-LAST:event_btnDownActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed

        Controladora.acaoConfirma();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        Controladora.acaoCancela();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void datosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datosMousePressed

        Controladora.transfereDadosTabelaParaCaixa();
    }//GEN-LAST:event_datosMousePressed

    private void datosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datosMouseClicked

        Controladora.transfereDadosTabelaParaCaixa();
    }//GEN-LAST:event_datosMouseClicked

    private void datosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datosKeyPressed
        Controladora.transfereDadosTabelaParaCaixa();
    }//GEN-LAST:event_datosKeyPressed

    private void datosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datosKeyReleased

        Controladora.transfereDadosTabelaParaCaixa();
    }//GEN-LAST:event_datosKeyReleased

    private void datosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datosKeyTyped
        Controladora.transfereDadosTabelaParaCaixa();
    }//GEN-LAST:event_datosKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Id;
    private javax.swing.JTextArea area;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnDown;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnUp;
    private javax.swing.JComboBox<String> cbtipo;
    private javax.swing.JTable datos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblPorta;
    private javax.swing.JLabel lblPorta1;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel pn;
    private javax.swing.JTextField txtFTPNome;
    private javax.swing.JTextField txtHost;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtPorta;
    private javax.swing.JTextField txtUser;
    private javax.swing.JLabel txt_tool_tip;
    // End of variables declaration//GEN-END:variables
}
