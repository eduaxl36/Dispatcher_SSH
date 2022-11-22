/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br_com_kantar_interfaces_copiador;

import br_com_kantar_controller.CopiadorController;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo.Fernando
 */
public class Copiador extends javax.swing.JInternalFrame {

    /**
     * Creates new form Copiador
     */
    private CopiadorController Controladora;

    public Copiador() throws IOException {

        initComponents();
        iniciarControlador();
        Controladora.carregarCombos();
        Controladora.selecionarTodasRegioes();
               
    }

    public final void iniciarControlador() throws IOException{
    
    Controladora = new CopiadorController(PB, cb_itens, datos, dp_data, lstRegioes);
    
    
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
        jScrollPane1 = new javax.swing.JScrollPane();
        datos = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        PB = new javax.swing.JProgressBar();
        btnCopiar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstRegioes = new javax.swing.JList<>();

        setClosable(true);
        setTitle("Copiador");

        PN.setBackground(new java.awt.Color(255, 255, 255));
        PN.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cb_itens.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar Instancia" }));
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
        PN.add(cb_itens, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 160, -1));

        dp_data.setDateFormatString("yyyyMMdd");
        dp_data.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dp_dataPropertyChange(evt);
            }
        });
        PN.add(dp_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 160, -1));

        jLabel2.setText("Instancia");
        PN.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 91, -1));

        jLabel1.setText("Equipe Dodo");
        PN.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 210, -1));

        jLabel3.setText("Regioes");
        PN.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 70, -1));

        datos.setAutoCreateRowSorter(true);
        datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Instancia", "Região", "Processo", "Tipo", "Origem", "Destino", "Tipo", "Owner", "Internal Folder"
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
        PN.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 200, 10));
        PN.add(PB, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 890, 20));

        File Arq = new File("img/IcoCopiar");
        btnCopiar.setIcon(new javax.swing.ImageIcon("c:\\temp\\img\\IcoCopiar.png")); // NOI18N
        btnCopiar.setToolTipText("Hace la copia de los datos");
        btnCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarActionPerformed(evt);
            }
        });
        PN.add(btnCopiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 40, 50));

        jLabel5.setText("Data");
        PN.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 70, -1));

        lstRegioes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstRegioesValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstRegioes);

        PN.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 160, 110));

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

   
        try {
            iniciarControlador();
            this.Controladora.aplicarFiltroTabela();
        } catch (IOException ex) {
            Logger.getLogger(Copiador.class.getName()).log(Level.SEVERE, null, ex);
        }



    }//GEN-LAST:event_cb_itensItemStateChanged

    private void cb_itensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_itensActionPerformed

    }//GEN-LAST:event_cb_itensActionPerformed

    private void btnCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarActionPerformed
        try {
            
            iniciarControlador();
            this.Controladora.executarInstrucoes();
            
        } catch (IOException ex) {
            Logger.getLogger(Copiador.class.getName()).log(Level.SEVERE, null, ex);
        }
       


    }//GEN-LAST:event_btnCopiarActionPerformed

    private void dp_dataPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dp_dataPropertyChange

       
        try {
            
            iniciarControlador();
            this.Controladora.aplicarFiltroTabela();
            
        } catch (IOException ex) {
            Logger.getLogger(Copiador.class.getName()).log(Level.SEVERE, null, ex);
        }
            
     


    }//GEN-LAST:event_dp_dataPropertyChange

    private void lstRegioesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstRegioesValueChanged

        try {
            iniciarControlador();
            this.Controladora.aplicarFiltroTabela();
        } catch (IOException ex) {
            Logger.getLogger(Copiador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_lstRegioesValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar PB;
    private javax.swing.JPanel PN;
    private javax.swing.JButton btnCopiar;
    private javax.swing.JComboBox<String> cb_itens;
    private javax.swing.JTable datos;
    private com.toedter.calendar.JDateChooser dp_data;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> lstRegioes;
    // End of variables declaration//GEN-END:variables
}
