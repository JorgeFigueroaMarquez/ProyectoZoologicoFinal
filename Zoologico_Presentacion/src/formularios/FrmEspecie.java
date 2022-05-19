/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import Control.CuidadorControl;
import Control.EspeciesControl;
import Control.HabitatControl;
import Interfaces.INegocios;
import entidades.Cuidador;
import entidades.Especie;
import entidades.Habitat;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;

/**
 *
 * @author mj_es
 */
public class FrmEspecie extends javax.swing.JFrame {
    
    private static FrmEspecie frmEspecie;
   // private static Especie especie;
    private final INegocios controlEspecies = new EspeciesControl();
    private final INegocios controlCuidadores = new CuidadorControl();
    private final INegocios controlHabitats = new HabitatControl();
    private List<Especie> listaEspecies;
    private List<Cuidador> listaCuidadores;
    private List<Habitat> listaHabitats;
    /**
     * Creates new form FrmEspecie
     */
    public FrmEspecie() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        listaHabitats = controlHabitats.recuperarDatos();
        listaCuidadores = controlCuidadores.recuperarDatos();
        listaEspecies = controlEspecies.recuperarDatos();
    }
    
    public static FrmEspecie getInstance() {
        if (FrmEspecie.frmEspecie != null) {
            return frmEspecie;
        } else {
            FrmEspecie.frmEspecie = new FrmEspecie();
            return frmEspecie;
        }
    }  
    
    
    /**
     * Metodo que guarda
     * @return 
     */
    private void guarda(){
        
        //System.out.println(verificaEspecieExistente());

        Especie especie = new Especie(); 
        if(verificaEspecieExistente()==false){
           
          
           if(!(this.txtNombre.getText().equals(""))){
           especie.setNombreEspañol(this.txtNombre.getText());
           }else{
               JOptionPane.showMessageDialog(this, "Nombre no válido", "Información", JOptionPane.INFORMATION_MESSAGE);
               return;
           }
           if(!(this.txtNombreCientifico.getText().equals(""))){
               especie.setNombreCientifico(this.txtNombreCientifico.getText());
           }else{
               JOptionPane.showMessageDialog(this, "Falta nombre científico", "Información", JOptionPane.INFORMATION_MESSAGE);
               return;
           }
           if(!(this.txtDescripcion.getText().equals(""))){
               especie.setDescripcionGeneral(this.txtDescripcion.getText());
           }else{
               JOptionPane.showMessageDialog(this, "Falta descripción de la especie", "Información", JOptionPane.INFORMATION_MESSAGE);
               return;
           }
           
           String idHabitat =(String) cmbHabitat.getSelectedItem();
           for(int i=0; i<listaHabitats.size(); i++){
               if(listaHabitats.get(i).getNombre().equalsIgnoreCase(idHabitat)){
                   especie.setHabitats(Arrays.asList(listaHabitats.get(i).getId()));
               }
           
           }
           
           
           String idCuidador = (String) cmbCuidador.getSelectedItem();
           for(int j=0; j < listaCuidadores.size(); j++){
               if(listaCuidadores.get(j).getNombre().equalsIgnoreCase(idCuidador)){
                   especie.setIdCuidadores(Arrays.asList(listaCuidadores.get(j).getId()));
               }
           }
           
           
           
        
           controlEspecies.guarda(especie);
           JOptionPane.showMessageDialog(this, "La especie se registró con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Se guardó");
        }else{
            JOptionPane.showMessageDialog(this, "No se pudo registrar la especie", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }
    
    /**
     * Metodo que verifica la existencia de especie
     * @return 
     */
    private boolean verificaEspecieExistente() {

        for (int i = 0; i < listaEspecies.size(); i++) {
            
            if (this.txtNombre.getText().equalsIgnoreCase(listaEspecies.get(i).getNombreEspañol())) {
                cmbHabitat.removeAllItems();
                cmbCuidador.removeAllItems();
        
        
                JOptionPane.showMessageDialog(this, "La especie ya está registrada", "Información", JOptionPane.INFORMATION_MESSAGE);

                this.txtNombre.setText(listaEspecies.get(i).getNombreEspañol());

                this.txtNombreCientifico.setText(listaEspecies.get(i).getNombreCientifico());
                this.txtDescripcion.setText(listaEspecies.get(i).getDescripcionGeneral());

                for (int j = 0; j < listaCuidadores.size(); j++) {
                    List<ObjectId> idCuidador;
                    idCuidador = listaEspecies.get(i).getIdCuidadores();

                    if (idCuidador.get(0).equals(listaCuidadores.get(j).getId())) {
                        this.cmbCuidador.addItem(listaCuidadores.get(j).getNombre());
//                        break;
                    }
                }

             
                for (int k = 0; k < listaHabitats.size(); k++) {
                    if (listaHabitats.get(k).getId().equals(listaEspecies.get(i).getHabitats().get(0))) {
                        System.out.println(listaHabitats.get(k).getNombre());
                        System.out.println(listaEspecies.get(i).getHabitats().get(0));
                        this.cmbHabitat.addItem(listaHabitats.get(k).getNombre());
                    }
                }

                this.btnGuardar.setEnabled(false);

                return true;

            }else{
                cmbHabitat.removeAllItems();
                cmbCuidador.removeAllItems();
                
                this.txtNombreCientifico.setEditable(true);
                this.txtDescripcion.setEditable(true);
                llenarComboBoxes();
                
                JOptionPane.showMessageDialog(this, "La especie no está registrada", "Información", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        
              
        }
        return false;
    }
        
//        this.txtNombreCientifico.setText(null);
//        this.txtDescripcion.setText(null);
        
//                llenarComboBoxes();
//                
//                this.btnGuardar.setEnabled(true);
//                
//                return false;
      //  JOptionPane.showMessageDialog(this, "La especie no está registrada", "Información", JOptionPane.INFORMATION_MESSAGE);
        
 //   }
    
    /**
     * Metodo que llena comboboxes
     */
    private void llenarComboBoxes(){
        listaCuidadores = controlCuidadores.recuperarDatos();
        listaHabitats = controlHabitats.recuperarDatos();
        
            for (int i = 0; i < listaHabitats.size(); i++) {
                Habitat habitat = listaHabitats.get(i);
                cmbHabitat.addItem(habitat.getNombre());
            }

            for (int i = 0; i < listaCuidadores.size(); i++) {
                Cuidador cuidador = listaCuidadores.get(i);
                cmbCuidador.addItem(cuidador.getNombre());
    }
    
}
    /**
     * Metodo que limpia formulario
     */
    private void limpiarFormulario(){
        this.txtNombre.setText(null);
        this.txtNombreCientifico.setText(null);
        this.txtDescripcion.setText(null);
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombreCientifico = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        cmbHabitat = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cmbCuidador = new javax.swing.JComboBox<>();
        btnMenu = new javax.swing.JButton();
        btnVerificar = new javax.swing.JButton();
        btnAnimal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Especie");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel4.setText("Habitat natural");

        jLabel3.setText("Descripción ");

        txtNombreCientifico.setEditable(false);

        jLabel2.setText("Nombre Cientifico");

        jLabel1.setText("Nombre");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        jLabel7.setText("Especie");

        txtDescripcion.setEditable(false);
        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel8.setText("Cuidador");

        btnMenu.setText("Volver menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        btnAnimal.setText("Animal");
        btnAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnimalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnMenu))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbHabitat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombre)
                            .addComponent(txtNombreCientifico)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                            .addComponent(cmbCuidador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnAnimal)))
                        .addGap(0, 46, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel7)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreCientifico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnAnimal)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbHabitat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(cmbCuidador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancelar)
                            .addComponent(btnGuardar)))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMenu)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        FrmPrincipal.getInstance().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guarda();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        verificaEspecieExistente();
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btnAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnimalActionPerformed
        FrmAnimal.getInstance().setVisible(true);
    }//GEN-LAST:event_btnAnimalActionPerformed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           verificaEspecieExistente();
       }
    }//GEN-LAST:event_txtNombreKeyPressed

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
            java.util.logging.Logger.getLogger(FrmEspecie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEspecie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEspecie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEspecie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEspecie().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnimal;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JComboBox<String> cmbCuidador;
    private javax.swing.JComboBox<String> cmbHabitat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreCientifico;
    // End of variables declaration//GEN-END:variables
}
