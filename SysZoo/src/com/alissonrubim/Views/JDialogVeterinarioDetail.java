/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Views;

import com.alissonrubim.Models.DAO.FuncionarioDAO;
import com.alissonrubim.Models.DAO.VeterinarioDAO;
import com.alissonrubim.Models.Especie;
import com.alissonrubim.Models.Funcionario;
import com.alissonrubim.Models.Veterinario;
import com.alissonrubim.Models.Util.Format;
import com.alissonrubim.Models.Util.Util;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alisson.rubim
 */
public class JDialogVeterinarioDetail extends javax.swing.JDialog {

    /**
     * Creates new form JDialogFuncionarioDetail
     */
    public JDialogVeterinarioDetail(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadComboFuncionario();
    }
    
    /********************************************* IMPLEMENTS ************************************************/
    public JDialogVeterinarioDetail(java.awt.Frame parent, boolean modal, Veterinario f) {
        super(parent, modal);
        initComponents();
        loadComboFuncionario();
        loadDetail(f);
    }
    private Veterinario  currentVeterinario = null;
    private void loadDetail(Veterinario a){
        currentVeterinario = a;
        
        int index = Util.findObjectIndexInArray(listFuncionario, a.getFuncionario().getId());   
        if(index >= 0)
            jComboBoxFuncionario.setSelectedIndex(index+1); //+1 porque tem sempre a opção SELECIONAR
        
        jTextFieldRegistroCRMV.setText(a.getRegistroCRMV());
        jFormattedTextFieldDataCRMV.setText(Format.DateToString(a.getDataCRMV(), "dd/MM/yyyy"));
    }
    
    private ArrayList<Funcionario> listFuncionario = new ArrayList<>();
    private void loadComboFuncionario(){
        try {
            listFuncionario = new FuncionarioDAO().getAll();
            jComboBoxFuncionario.insertItemAt("(selecione)", jComboBoxFuncionario.getItemCount());
            jComboBoxFuncionario.setSelectedIndex(0);
            for(Funcionario e : listFuncionario)
                jComboBoxFuncionario.insertItemAt(e.getNome(), jComboBoxFuncionario.getItemCount());
        } catch (SQLException | ClassNotFoundException ex) {
           JOptionPane.showMessageDialog(this, "Ocorreu um erro ao recuperar a lista de funcionários", "Atenção", JOptionPane.OK_OPTION);
        }
        
    }
    private void onConfirm(){
        int index = jComboBoxFuncionario.getSelectedIndex();
        if(index < 1){
            JOptionPane.showMessageDialog(this, "O campo funcionário é de preenchimento obrigatório", "Atenção", JOptionPane.OK_OPTION);
            return;
        }
        
        String registroCMRV = jTextFieldRegistroCRMV.getText();
        if(registroCMRV.isEmpty()){
            JOptionPane.showMessageDialog(this, "O campo registro CRMV é de preenchimento obrigatório", "Atenção", JOptionPane.OK_OPTION);
            return;
        }
        
        String dateCMRV = jFormattedTextFieldDataCRMV.getText();
        if(dateCMRV.isEmpty()){
            JOptionPane.showMessageDialog(this, "O campo data CRMV é de preenchimento obrigatório", "Atenção", JOptionPane.OK_OPTION);
            return;
        }
        
        Date dateCMRVconverted = null;
        try {
            dateCMRVconverted = Format.StringToDate(dateCMRV, "dd/MM/yyyy");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "O campo data CRMV não contém um valor válido!", "Atenção", JOptionPane.OK_OPTION);
            return;
        }
        
        Funcionario funcionario = listFuncionario.get(index-1);
               
        if(this.currentVeterinario == null){
            Veterinario e = new Veterinario(-1, funcionario, registroCMRV, dateCMRVconverted);
            try {
                new VeterinarioDAO().insert(e);
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao inserir um novo veterinário: " + ex.getMessage(), "Atenção", JOptionPane.OK_OPTION);
            }
        }else{
            this.currentVeterinario.setFuncionario(funcionario);            
            try {
                new VeterinarioDAO().update(this.currentVeterinario);
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao atualizar o veterinário", "Atenção", JOptionPane.OK_OPTION);
            }
        }
        setVisible(false);
        
    }
    
    private void onClose(){
       setVisible(false); 
    }
/************************************************* IMPLEMENTS *************************************************/
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxFuncionario = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldRegistroCRMV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldDataCRMV = new javax.swing.JFormattedTextField();
        jButtonConfirm = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Veterinário"));

        jLabel1.setText("Funcionário:");

        jLabel2.setText("Registro CRMV:");

        jLabel3.setText("Data CRMV:");

        jFormattedTextFieldDataCRMV.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxFuncionario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldRegistroCRMV)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 366, Short.MAX_VALUE))
                    .addComponent(jFormattedTextFieldDataCRMV))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldRegistroCRMV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldDataCRMV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jButtonConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alissonrubim/Resources/save_24x24.png"))); // NOI18N
        jButtonConfirm.setText("Confirmar");
        jButtonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmActionPerformed(evt);
            }
        });

        jButtonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alissonrubim/Resources/cancel_24x24.png"))); // NOI18N
        jButtonCancel.setText("Cancelar");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmActionPerformed
        onConfirm();
    }//GEN-LAST:event_jButtonConfirmActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        onClose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDialogVeterinarioDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogVeterinarioDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogVeterinarioDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogVeterinarioDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogVeterinarioDetail dialog = new JDialogVeterinarioDetail(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonConfirm;
    private javax.swing.JComboBox<String> jComboBoxFuncionario;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataCRMV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldRegistroCRMV;
    // End of variables declaration//GEN-END:variables
}
