/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br_com_kantar_interfaces;

import br_com_kantar_services.ConfiguracoesServices;
import br_com_kantar_services.CopiadorServices;
import static br_com_util.UtilTable.autoResizeTable;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo.Fernando
 */
public class Copiador extends javax.swing.JInternalFrame {

    /**
     * Creates new form Copiador
     */
    ConfiguracoesServices ServicoConfig = null;
    CopiadorServices ServicoCopiador = null;
    DefaultTableModel Modelo = null;

    public Copiador() throws IOException {

        initComponents();
        carregarDados();
        ServicoConfig = new ConfiguracoesServices();

    }

    public final void carregarDados() throws IOException {

        Calendar Calendario = Calendar.getInstance();

        dp_data.setDate(Calendario.getTime());

        this.ServicoCopiador.obterInstancias().forEach(x -> {

            cb_itens.addItem(x);

        });

        this.ServicoCopiador.obterProcessos().forEach(x -> {

            cbProcesso.addItem(x);

        });


        
    }

    public List<String> retornaSelecionados() {

        List<String> SelectedRegions = new ArrayList();

        if (ck_ar.isSelected()) {
            SelectedRegions.add("AR");
        }
        if (ck_gba.isSelected()) {
            SelectedRegions.add("GBA");
        }

        if (ck_india.isSelected()) {
            SelectedRegions.add("INDIA");
        }

        if (ck_int.isSelected()) {
            SelectedRegions.add("INTERIOR");

        }

        return SelectedRegions;
    }

    public void zerarTabela() {

        Modelo = (DefaultTableModel) datos.getModel();
        Modelo.setNumRows(0);

    }

    public void aplicarFiltroTabela() {

        try {

            zerarTabela();

            LocalDate DataInput = dp_data.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            ServicoCopiador = new CopiadorServices(DataInput, Modelo, datos);
            ServicoCopiador.filtrarTabelaCopiador(retornaSelecionados(), cb_itens.getSelectedItem().toString(), cbProcesso.getSelectedItem().toString());

        } catch (IOException e) {

            System.out.println("Falha ao recuperar os dados : " + e.getMessage());

        } finally {

            datos.setAutoResizeMode(datos.AUTO_RESIZE_OFF);
            autoResizeTable(datos, isClosed, SOMEBITS);

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PN = new javax.swing.JPanel();
        cb_itens = new javax.swing.JComboBox<>();
        dp_data = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ck_india = new javax.swing.JCheckBox();
        ck_int = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        datos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ck_ar = new javax.swing.JCheckBox();
        PB = new javax.swing.JProgressBar();
        ck_gba = new javax.swing.JCheckBox();
        btnCopiar = new javax.swing.JButton();
        cbProcesso = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Copiador");

        PN.setBackground(new java.awt.Color(255, 255, 255));
        PN.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cb_itens.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecicone instancia" }));
        cb_itens.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_itensItemStateChanged(evt);
            }
        });
        cb_itens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_itensActionPerformed(evt);
            }
        });
        PN.add(cb_itens, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 154, -1));

        dp_data.setDateFormatString("yyyyMMdd");
        dp_data.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dp_dataPropertyChange(evt);
            }
        });
        PN.add(dp_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 155, -1));

        jLabel2.setText("Instancia");
        PN.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 91, -1));

        jLabel1.setText("Desarollo - Equipo Reinal (Brazil)");
        PN.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 210, -1));

        jLabel3.setText("Plaza de los Archivos");
        PN.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 130, -1));

        ck_india.setText("India");
        ck_india.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ck_indiaItemStateChanged(evt);
            }
        });
        ck_india.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ck_indiaPropertyChange(evt);
            }
        });
        PN.add(ck_india, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 79, -1));

        ck_int.setText("Interior");
        ck_int.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ck_intItemStateChanged(evt);
            }
        });
        ck_int.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ck_intStateChanged(evt);
            }
        });
        ck_int.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ck_intPropertyChange(evt);
            }
        });
        PN.add(ck_int, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 85, -1));

        datos.setAutoCreateRowSorter(true);
        datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Instancia", "Plaza", "Processo", "Tipo", "Origen", "Destino", "Tipo", "Owner", "Internal Folder"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(datos);

        PN.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 890, 240));

        jLabel4.setText("Processo");
        PN.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 70, 20));
        PN.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 200, 10));

        ck_ar.setText("Ar");
        ck_ar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ck_arItemStateChanged(evt);
            }
        });
        ck_ar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ck_arPropertyChange(evt);
            }
        });
        PN.add(ck_ar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 79, -1));
        PN.add(PB, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 890, 20));

        ck_gba.setText("Gba");
        ck_gba.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ck_gbaItemStateChanged(evt);
            }
        });
        ck_gba.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ck_gbaPropertyChange(evt);
            }
        });
        PN.add(ck_gba, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 85, -1));

        File Arq = new File("img/IcoCopiar");
        btnCopiar.setIcon(new javax.swing.ImageIcon("c:\\temp\\img\\IcoCopiar.png")); // NOI18N
        btnCopiar.setToolTipText("Hace la copia de los datos");
        btnCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarActionPerformed(evt);
            }
        });
        PN.add(btnCopiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 40, 50));

        cbProcesso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o processo" }));
        cbProcesso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProcessoItemStateChanged(evt);
            }
        });
        cbProcesso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProcessoActionPerformed(evt);
            }
        });
        PN.add(cbProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 154, -1));

        jLabel5.setText("Fecha");
        PN.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 70, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PN, javax.swing.GroupLayout.PREFERRED_SIZE, 1181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PN, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_itensItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_itensItemStateChanged
        aplicarFiltroTabela();

        // TODO add your handling code here:
    }//GEN-LAST:event_cb_itensItemStateChanged

    private void ck_indiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ck_indiaItemStateChanged
        aplicarFiltroTabela();

        // TODO add your handling code here:
    }//GEN-LAST:event_ck_indiaItemStateChanged

    private void ck_indiaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ck_indiaPropertyChange

        // TODO add your handling code here:
    }//GEN-LAST:event_ck_indiaPropertyChange

    private void ck_intItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ck_intItemStateChanged
        aplicarFiltroTabela();

        // TODO add your handling code here:
    }//GEN-LAST:event_ck_intItemStateChanged

    private void ck_intPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ck_intPropertyChange

        // TODO add your handling code here:
    }//GEN-LAST:event_ck_intPropertyChange

    private void ck_arItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ck_arItemStateChanged
        aplicarFiltroTabela();
        // TODO add your handling code here:
    }//GEN-LAST:event_ck_arItemStateChanged

    private void ck_arPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ck_arPropertyChange

        // TODO add your handling code here:
    }//GEN-LAST:event_ck_arPropertyChange

    private void ck_gbaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ck_gbaItemStateChanged
        aplicarFiltroTabela();        // TODO add your handling code here:
    }//GEN-LAST:event_ck_gbaItemStateChanged

    private void ck_gbaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ck_gbaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_ck_gbaPropertyChange

    private void cb_itensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_itensActionPerformed

// TODO add your handling code here:
    }//GEN-LAST:event_cb_itensActionPerformed

    private void ck_intStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ck_intStateChanged

// TODO add your handling code here:
    }//GEN-LAST:event_ck_intStateChanged

    private void btnCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarActionPerformed

        new Thread() {

            @Override
            public void run() {
                PB.setIndeterminate(true);
                try {

                    LocalDate DataInput = dp_data.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    ServicoCopiador = new CopiadorServices(DataInput, Modelo, datos);
                    ServicoCopiador.executor();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "Falha " + ex.getMessage());
                    PB.setIndeterminate(false);
                }
                PB.setIndeterminate(false);
            }

        }.start();

    }//GEN-LAST:event_btnCopiarActionPerformed

    private void dp_dataPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dp_dataPropertyChange

        aplicarFiltroTabela();

        // TODO add your handling code here:
    }//GEN-LAST:event_dp_dataPropertyChange

    private void cbProcessoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProcessoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbProcessoItemStateChanged

    private void cbProcessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProcessoActionPerformed
        aplicarFiltroTabela();

        // TODO add your handling code here:
    }//GEN-LAST:event_cbProcessoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar PB;
    private javax.swing.JPanel PN;
    private javax.swing.JButton btnCopiar;
    private javax.swing.JComboBox<String> cbProcesso;
    private javax.swing.JComboBox<String> cb_itens;
    private javax.swing.JCheckBox ck_ar;
    private javax.swing.JCheckBox ck_gba;
    private javax.swing.JCheckBox ck_india;
    private javax.swing.JCheckBox ck_int;
    private javax.swing.JTable datos;
    private com.toedter.calendar.JDateChooser dp_data;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
