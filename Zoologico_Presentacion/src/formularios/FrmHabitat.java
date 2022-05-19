/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import Control.HabitatControl;
import Interfaces.INegocios;
import entidades.Habitat;
import java.awt.PopupMenu;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mj_es
 */
public class FrmHabitat extends javax.swing.JFrame {
    
    //private static Habitat habitat;
    private final INegocios control = new HabitatControl();
    private DefaultListModel mdlDisponibles;
    private DefaultListModel mdlSeleccionados = new DefaultListModel();
    private List<String> listaContinentesDisponibles = new ArrayList<>();
    private List<String> listaContinentesSeleccionados;
// private DefaultTableModel modeloTabla;
   // private DefaultTableModel tblHabitat = new DefaultTableModel;
    
    private static FrmHabitat registrarHabitat;

    /**
     * Metodo que crea una unica instancia del frame
     * @return 
     */
    public static FrmHabitat getInstance() {
        if (FrmHabitat.registrarHabitat != null) {
            return registrarHabitat;
        } else {
            FrmHabitat.registrarHabitat = new FrmHabitat();
            return registrarHabitat;
        }
    }  
    
    /**
     * Creates new form FrmHabitat
     */
    public FrmHabitat() {
       
        initComponents();
        this.setLocationRelativeTo(null);
        
        this.listaContinentesDisponibles.addAll(Arrays.asList("Ámerica", "África", "Europa", "Asia", "Oceanía"));
        this.listaContinentesSeleccionados = new ArrayList<>();
        despliegaInformacion();
        llenarTablaDisponibles();
        
    }
    
    /**
     * Metodo que despliega informacion de habitat
     */
    public void despliegaInformacion(){
        
        List<Habitat> listaHabitats = control.recuperarDatos();
       // System.out.println(listaHabitats.toString());
            DefaultTableModel modelo = (DefaultTableModel)tblHabitat.getModel();
            modelo.setRowCount(0);
        listaHabitats.forEach(habitats -> {
           
           // System.out.println(habitats.toString());
            Object[] fila = new Object [5];
           // fila[0]= habitats.getId();
           // fila[1]= habitats.getNombre();
            fila[0]= habitats.getClima();
            fila[1]= habitats.getVegetacionPredominate();
            fila[2]= habitats.getContinente();
            
            modelo.addRow(fila);
        });
        
    }
    
    /**
     * Metodo que verifica habitats existentes
     * @return 
     */
    public boolean verificarHabitatExistente(){
        List<Habitat> listaHabitats = control.recuperarDatos();
        
        for(int i=0; i<listaHabitats.size(); i++){
        if(this.txtNombreHabitat.getText().equalsIgnoreCase(listaHabitats.get(i).getNombre())){
            this.txtNombreHabitat.setText(listaHabitats.get(i).getNombre());
            this.txtId.setText(listaHabitats.get(i).getId().toString());
            this.txtClima.setText(listaHabitats.get(i).getClima());
            this.txtVegetacion.setText(listaHabitats.get(i).getVegetacionPredominate());
            //System.out.println(listaHabitats.get(i));
            this.txtContinentes.setText(listaHabitats.get(i).getContinente().toString());
            this.btnGuardar.setEnabled(false);
            JOptionPane.showMessageDialog(this, "El hábitat ya está registrada", "Información", JOptionPane.INFORMATION_MESSAGE);
            return true;
            
        }
    }       JOptionPane.showMessageDialog(this, "El hábitat no está registrado", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.txtClima.setEditable(true);
            this.txtVegetacion.setEditable(true);
            this.btnGuardar.setEnabled(true);
        return false;
}       
    
    /**
     * Metodo que guarda
     */
        private void guarda(){
           Habitat habitat = new Habitat();
           
           if(verificarHabitatExistente()!=true){
               habitat.setNombre(this.txtNombreHabitat.getText());
               if(!this.txtClima.getText().equals("")){
                habitat.setClima(this.txtClima.getText());  
               }else{
                   JOptionPane.showMessageDialog(this, "Introduzca el clima", "Información", JOptionPane.INFORMATION_MESSAGE);
                   return;
               }
               if(!this.txtVegetacion.getText().equals("")){
                 habitat.setVegetacionPredominate(this.txtVegetacion.getText());  
               }else{
                   JOptionPane.showMessageDialog(this, "El hábitat se guardó con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                   return;
               }
              
               if(listaContinentesSeleccionados.isEmpty()){
                   JOptionPane.showMessageDialog(this, "Selecciona al menos un continente", "Información", JOptionPane.INFORMATION_MESSAGE);
                   return;
               }else{
                 habitat.setContinente(listaContinentesSeleccionados);  
               }
               
               
              //control.verifica(habitat);
              control.guarda(habitat);
               JOptionPane.showMessageDialog(this, "El hábitat se guardó con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
               System.out.println("Se guardó");
               despliegaInformacion();
           }else{
               JOptionPane.showMessageDialog(this, "El hábitat no pudo ser guardado", "Información", JOptionPane.ERROR_MESSAGE);
           }
           
    }
        
     /**
      * Metodo que llena tabla
      */
    private void llenarTablaDisponibles() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblContinentesDisponibles.getModel();
        modeloTabla.setRowCount(0);
        this.listaContinentesDisponibles.forEach(continente -> {
            Object[] fila = new Object[1];
            fila[0] = continente;
            modeloTabla.addRow(fila);
        });
    }
    
    /**
     * Metodo que llena tabla
     */
    private void llenarTablaSeleccionados() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblContinentesSeleccionados.getModel();
        modeloTabla.setRowCount(0);
        this.listaContinentesSeleccionados.forEach(continente -> {
            Object[] fila = new Object[1];
            fila[0] = continente;
            modeloTabla.addRow(fila);
        });
    }
    
    /**
     * Metodo que actualiza la lista
     */
    private void actualizarLista() {
        this.llenarTablaDisponibles();
        this.llenarTablaSeleccionados();
        
        if (this.tblContinentesDisponibles.getRowCount() == 0) {
            this.btnAgregarContinentes.setEnabled(false);
        }
        if (this.tblContinentesDisponibles.getRowCount() > 0) {
            this.btnAgregarContinentes.setEnabled(true);
        }
        if (this.tblContinentesSeleccionados.getRowCount() == 0) {
            this.btnEliminarContinentes.setEnabled(false);
        }
        if (this.tblContinentesSeleccionados.getRowCount() > 0) {
            this.btnEliminarContinentes.setEnabled(true);
        }
    }
    
    /**
     * Metodo que elimina continente de la lista
     */
    private void eliminarContinenteLista() {
        String continenteSeleccionado = this.getContinenteEliminar();
        this.listaContinentesDisponibles.add(continenteSeleccionado);
        this.listaContinentesSeleccionados.remove(continenteSeleccionado);
        this.actualizarLista();
    }
    
    /**
     * Metodo que elimina contiennte
     * @return 
     */
    private String getContinenteEliminar() {
        int indiceFilaSeleccionada = this.tblContinentesSeleccionados.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblContinentesSeleccionados.getModel();
            int indiceColumnaContinente = 0;
            String continenteASeleccionar = (String) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaContinente);
            return continenteASeleccionar;
        } else {
            return null;
        }
    }
    
    /**
     * metodo que agrega continente
     * @return 
     */
    private String getContinenteAgregar() {
        int indiceFilaSeleccionada = this.tblContinentesDisponibles.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblContinentesDisponibles.getModel();
            int indiceColumnaContinente = 0;
            String continenteASeleccionar = (String) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaContinente);
            return continenteASeleccionar;
        } else {
            return null;
        }
    }
    
    /**
     * Metodo que agrega continente a la lista
     */
    private void agregarContinenteALista(){ 
        this.listaContinentesSeleccionados.add(getContinenteAgregar());
        this.listaContinentesDisponibles.remove(getContinenteAgregar());
        actualizarLista();
    }
    
//    private void agregarContinente() {
//        
//        List<Habitat> listaHabitats = control.recuperarDatos();
//        
//        Habitat habitat = new Habitat();
//        for(int i=0; i<listaHabitats.size(); i++){
//        if(this.txtNombreHabitat.getText().equalsIgnoreCase(listaHabitats.get(i).getNombre())){
//            habitat.setContinente(listaHabitats.get(i).getContinente());         
//            break;
//        }
//        
//        int fila = listaContinentesDisponibles.getSelectedIndex();
//        if (fila > -1) {
//            mdlSeleccionados.addElement(listaContinentesDisponibles.getSelectedValue());
//            if(habitat.getContinente().isEmpty()){
//               habitat.setContinente(Arrays.asList(listaContinentesDisponibles.getSelectedValue())); 
//            }else{
//                List<String> listaContinentes = habitat.getContinente();
//                listaContinentes.add(listaContinentesDisponibles.getSelectedValue());
//                habitat.setContinente(listaContinentes);
//            }
//            
//            mdlDisponibles = (DefaultListModel) listaContinentesDisponibles.getModel();
//            mdlDisponibles.remove(fila);
//            listaContinentesDisponibles.setModel(mdlDisponibles);
//            listaContinentesSeleccionados.setModel(mdlSeleccionados);
//            }
//        }
  //  }
    
//    private void eliminarContinente() {
//        int fila = listaContinentesSeleccionados.getSelectedIndex();
//
//        if (fila > -1) {
//            mdlDisponibles.addElement(listaContinentesSeleccionados.getSelectedValue());
//           // habitat.eliminarContinente(listaContinentesDisponibles.getSelectedValue());
//            mdlSeleccionados = (DefaultListModel) listaContinentesSeleccionados.getModel();
//            mdlSeleccionados.remove(fila);
//            listaContinentesSeleccionados.setModel(mdlSeleccionados);
//            listaContinentesDisponibles.setModel(mdlDisponibles);
//        }
//
//    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHabitat = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblContinentesDisponibles = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblContinentesSeleccionados = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtClima = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreHabitat = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        btnVerificar = new javax.swing.JButton();
        txtVegetacion = new javax.swing.JTextField();
        txtContinentes = new javax.swing.JTextField();
        btnAgregarContinentes = new javax.swing.JButton();
        btnEliminarContinentes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Habitat");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblHabitat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Clima", "Vegetacion", "Continente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHabitat.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(tblHabitat);
        tblHabitat.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jLabel5.setText("Continentes Disponibles");

        jLabel8.setText("Continente seleccionado");

        tblContinentesDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Continentes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblContinentesDisponibles.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(tblContinentesDisponibles);
        tblContinentesDisponibles.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblContinentesSeleccionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Continentes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblContinentesSeleccionados.setColumnSelectionAllowed(true);
        jScrollPane4.setViewportView(tblContinentesSeleccionados);
        tblContinentesSeleccionados.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(52, 52, 52)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

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

        jLabel4.setText("Continentes");

        jLabel3.setText("Vegetación");

        txtClima.setEditable(false);

        jLabel2.setText("Clima");

        jLabel1.setText("Nombre");

        txtNombreHabitat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreHabitatActionPerformed(evt);
            }
        });
        txtNombreHabitat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreHabitatKeyPressed(evt);
            }
        });

        txtId.setEditable(false);

        jLabel6.setText("ID");

        jLabel7.setText("Habitat");

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

        txtVegetacion.setEditable(false);

        txtContinentes.setEditable(false);

        btnAgregarContinentes.setText("Agregar");
        btnAgregarContinentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarContinentesActionPerformed(evt);
            }
        });

        btnEliminarContinentes.setText("Eliminar");
        btnEliminarContinentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarContinentesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombreHabitat)
                            .addComponent(txtClima)
                            .addComponent(txtVegetacion)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContinentes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerificar)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnMenu)
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAgregarContinentes)
                                .addGap(195, 195, 195)
                                .addComponent(btnEliminarContinentes)
                                .addGap(110, 110, 110))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreHabitat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtClima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtVegetacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtContinentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnCancelar)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarContinentes)
                    .addComponent(btnEliminarContinentes))
                .addGap(14, 14, 14)
                .addComponent(btnMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.txtNombreHabitat.setText(null);
        this.txtClima.setText(null);
        this.txtVegetacion.setText(null);
        this.txtId.setText(null);
        this.btnGuardar.setEnabled(true);
        this.txtContinentes.setText(null);
       // this.cmbContinente.setModel(null);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guarda();
        //control.guarda(habitat);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNombreHabitatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreHabitatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreHabitatActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        FrmPrincipal.getInstance().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        verificarHabitatExistente();
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void txtNombreHabitatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreHabitatKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            verificarHabitatExistente();
        }
    }//GEN-LAST:event_txtNombreHabitatKeyPressed

    private void btnAgregarContinentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarContinentesActionPerformed
        agregarContinenteALista();
    }//GEN-LAST:event_btnAgregarContinentesActionPerformed

    private void btnEliminarContinentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarContinentesActionPerformed
        eliminarContinenteLista(); 
    }//GEN-LAST:event_btnEliminarContinentesActionPerformed

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
            java.util.logging.Logger.getLogger(FrmHabitat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHabitat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHabitat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHabitat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHabitat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarContinentes;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarContinentes;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblContinentesDisponibles;
    private javax.swing.JTable tblContinentesSeleccionados;
    private javax.swing.JTable tblHabitat;
    private javax.swing.JTextField txtClima;
    private javax.swing.JTextField txtContinentes;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombreHabitat;
    private javax.swing.JTextField txtVegetacion;
    // End of variables declaration//GEN-END:variables
}
