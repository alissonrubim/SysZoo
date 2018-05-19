/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alissonrubim.Views;

import com.alissonrubim.Models.DAO.FuncionarioDAO;
import com.alissonrubim.Models.DAO.ReceitaDAO;
import com.alissonrubim.Models.DAO.VeterinarioDAO;
import com.alissonrubim.Models.Especie;
import com.alissonrubim.Models.Receita;
import com.alissonrubim.Models.ReceitaMedicamento;
import com.alissonrubim.Models.Veterinario;
import com.alissonrubim.Models.Util.Format;
import com.alissonrubim.Models.Util.Util;
import java.awt.Frame;
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
public class JDialogReceitaDetail extends javax.swing.JDialog {

    /**
     * Creates new form JDialogFuncionarioDetail
     */
    public JDialogReceitaDetail(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadComboVeterinario();
        renderReceitaMedicamentoCRUD(null);
    }
    
    /********************************************* IMPLEMENTS ************************************************/
    public JDialogReceitaDetail(java.awt.Frame parent, boolean modal, Receita f) {
        super(parent, modal);
        initComponents();
        loadComboVeterinario();
        renderReceitaMedicamentoCRUD(f);
        loadDetail(f);
    }
    
    private void renderReceitaMedicamentoCRUD(Receita f){ 
        this.receitaMedicamentoResume = new JPanelReceitaMedicamentoResume((Frame) this.getParent(), f);
        receitaMedicamentoResume.setBounds(10, 20, jPanelMedicamentos.getWidth() - 10*2, jPanelMedicamentos.getHeight() - 30);
        receitaMedicamentoResume.setVisible(true);
        jPanelMedicamentos.add(receitaMedicamentoResume);
    }
    
    private JPanelReceitaMedicamentoResume receitaMedicamentoResume;
    private Receita current = null;
    private void loadDetail(Receita a){
        current = a;
        
        int index = Util.findObjectIndexInArray(listVeterinario, a.getVeterinario().getId());   
        if(index >= 0)
            jComboBoxVeterinario.setSelectedIndex(index+1); //+1 porque tem sempre a opção SELECIONAR
        
        jTextAreaObservacao.setText(a.getObservacao());
        jFormattedTextFieldData.setText(Format.DateToString(a.getData(), "dd/MM/yyyy"));
    }
    
    private ArrayList<Veterinario> listVeterinario = new ArrayList<>();
    private void loadComboVeterinario(){
        try {
            listVeterinario = new VeterinarioDAO().getAll();
            jComboBoxVeterinario.insertItemAt("(selecione)", jComboBoxVeterinario.getItemCount());
            jComboBoxVeterinario.setSelectedIndex(0);
            for(Veterinario e : listVeterinario)
                jComboBoxVeterinario.insertItemAt(e.getFuncionario().getNome(), jComboBoxVeterinario.getItemCount());
        } catch (SQLException | ClassNotFoundException ex) {
           JOptionPane.showMessageDialog(this, "Ocorreu um erro ao recuperar a lista de veterinários", "Atenção", JOptionPane.OK_OPTION);
        }
        
    }
    private void onConfirm(){
        int index = jComboBoxVeterinario.getSelectedIndex();
        if(index < 1){
            JOptionPane.showMessageDialog(this, "O campo veterinário é de preenchimento obrigatório", "Atenção", JOptionPane.OK_OPTION);
            return;
        }
        
        String data = jFormattedTextFieldData.getText();
        if(data.isEmpty()){
            JOptionPane.showMessageDialog(this, "O campo data é de preenchimento obrigatório", "Atenção", JOptionPane.OK_OPTION);
            return;
        }
        
        String observacao = jTextAreaObservacao.getText();
        if(observacao.isEmpty()){
            JOptionPane.showMessageDialog(this, "O campo observação é de preenchimento obrigatório", "Atenção", JOptionPane.OK_OPTION);
            return;
        }
        
        Date dateconverted = null;
        try {
            dateconverted = Format.StringToDate(data, "dd/MM/yyyy");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "O campo data não contém um valor válido!", "Atenção", JOptionPane.OK_OPTION);
            return;
        }
        
        ArrayList<ReceitaMedicamento> receitaMedicamentos =  receitaMedicamentoResume.getList();
        if(receitaMedicamentos.size() == 0){
            JOptionPane.showMessageDialog(this, "É necessário adicionar ao menos um medicamento", "Atenção", JOptionPane.OK_OPTION);
            return;
        }
        
        Veterinario veterinario = listVeterinario.get(index-1);
               
        if(this.current == null){
            Receita e = new Receita(-1, veterinario, dateconverted, observacao, receitaMedicamentos);
            try {
                new ReceitaDAO().insert(e);
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao inserir uma nova receita: " + ex.getMessage(), "Atenção", JOptionPane.OK_OPTION);
            }
        }else{
            this.current.setVeterinario(veterinario);
            this.current.setData(dateconverted);
            this.current.setObservacao(observacao);
            this.current.setMedicamentos(receitaMedicamentos);
            try {
                new ReceitaDAO().update(this.current);
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao atualizar a receita:" + ex.getMessage(), "Atenção", JOptionPane.OK_OPTION);
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
        jComboBoxVeterinario = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldData = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jPanelMedicamentos = new javax.swing.JPanel();
        jButtonConfirm = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Receita"));

        jLabel1.setText("Veterinátio:");

        jLabel3.setText("Data:");

        jFormattedTextFieldData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setRows(5);
        jScrollPane1.setViewportView(jTextAreaObservacao);

        jLabel4.setText("Observação:");

        jPanelMedicamentos.setBorder(javax.swing.BorderFactory.createTitledBorder("Medicamento"));

        javax.swing.GroupLayout jPanelMedicamentosLayout = new javax.swing.GroupLayout(jPanelMedicamentos);
        jPanelMedicamentos.setLayout(jPanelMedicamentosLayout);
        jPanelMedicamentosLayout.setHorizontalGroup(
            jPanelMedicamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );
        jPanelMedicamentosLayout.setVerticalGroup(
            jPanelMedicamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelMedicamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jComboBoxVeterinario, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jFormattedTextFieldData, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxVeterinario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelMedicamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonConfirm))
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(JDialogReceitaDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogReceitaDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogReceitaDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogReceitaDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogReceitaDetail dialog = new JDialogReceitaDetail(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jComboBoxVeterinario;
    private javax.swing.JFormattedTextField jFormattedTextFieldData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelMedicamentos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaObservacao;
    // End of variables declaration//GEN-END:variables
}
