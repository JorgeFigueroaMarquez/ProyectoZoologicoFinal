/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import Control.ItinerarioControl;
import Control.ZonaControl;
import Interfaces.INegocios;
import entidades.Itinerario;
import entidades.Zona;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;

/**
 *
 * @author mj_es
 */
public class FrmItinerario extends javax.swing.JFrame {
    
    private static FrmItinerario registrarItinerario;
    
    private INegocios controlItinerarios = new ItinerarioControl();
    private INegocios controlZonas = new ZonaControl();
    private List<Zona> listaZonasDisponibles = new ArrayList<>();
    private List<Zona> listaZonasSeleccionados;
    private List<String> listaDiasDisponibles = new ArrayList<>();
    private List<String> listaDiasSeleccionados = new ArrayList<>();
    
    
    public static FrmItinerario getInstance() {
        if (FrmItinerario.registrarItinerario != null) {
            return registrarItinerario;
        } else {
            FrmItinerario.registrarItinerario = new FrmItinerario();
            return registrarItinerario;
        }
    }  
    
    
    /**
     * Creates new form FrmItinerario
     */
    public FrmItinerario() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        this.listaDiasDisponibles.addAll(Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"));
        this.listaZonasDisponibles.addAll(controlZonas.recuperarDatos());
        this.listaZonasSeleccionados = new ArrayList<>();
        
        this.listaDiasSeleccionados = new ArrayList<>();
    
        llenarTablaZonasDisponibles();
        llenarTablaDiasDisponibles();
    }

    /**
     * Metodo que verifica itinerario existencias
     * @return 
     */
    private boolean verificaItinerarioExistente(){
        List<Itinerario> listaItinerarios =  controlItinerarios.recuperarDatos();
        
        if(listaItinerarios.isEmpty()){
                this.txtDuracion.setEditable(true);
            this.txtLongitud.setEditable(true);
            this.txtVisitantes.setEditable(true);
            this.txtNumAnimales.setEditable(true);
            this.btnGuardar.setEnabled(true);
            }
        
        for(int i=0; i< listaItinerarios.size(); i++){
            
            
        if(this.txtNombre.getText().equalsIgnoreCase(listaItinerarios.get(i).getNombre())){
            
            this.txtId.setEditable(false);
            this.txtDuracion.setEditable(false);
            this.txtLongitud.setEditable(false);
            this.txtVisitantes.setEditable(false);
            this.txtNumAnimales.setEditable(false);
            
            this.txtId.setText(listaItinerarios.get(i).getId().toString());
            this.txtDuracion.setText(listaItinerarios.get(i).getDuracion());
            this.txtLongitud.setText(String.valueOf(listaItinerarios.get(i).getLongitud()));
            this.txtVisitantes.setText(String.valueOf(listaItinerarios.get(i).getMaxVisitantes()));
            this.txtNumAnimales.setText(String.valueOf(listaItinerarios.get(i).getNumEspeciesVisita()));
            
            JOptionPane.showMessageDialog(this, "El Itinerario se encuentra registrado", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.btnGuardar.setEnabled(false);
            return true;
        }
     
        }
        this.txtDuracion.setEditable(true);
            this.txtLongitud.setEditable(true);
            this.txtVisitantes.setEditable(true);
            
            this.btnGuardar.setEnabled(true);
            JOptionPane.showMessageDialog(this, "El Itinerario no se encuentra registrado", "Información", JOptionPane.INFORMATION_MESSAGE);
        return false;
    }
    
    /**
     * Metodo que guarda itinerarios
     */
    private void guarda(){
        
        if(verificaItinerarioExistente()!=true){
            Itinerario itinerario = new Itinerario();
            
            itinerario.setNombre(this.txtNombre.getText());
            if(this.txtDuracion.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Ingrese la duración", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }else{
               itinerario.setDuracion(this.txtDuracion.getText()); 
            }
            if(this.txtLongitud.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Ingrese la longitud", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }else{
                String validacion = "^[0-9]";
                Pattern pat = Pattern.compile(validacion);   
                Matcher mat = pat.matcher(this.txtLongitud.getText());
                if(mat.find()==true){
                  itinerario.setLongitud((Integer.parseInt(this.txtLongitud.getText())));  
                }else{
                  JOptionPane.showMessageDialog(this, "Ingrese la longitud en valor númerico", "Información", JOptionPane.INFORMATION_MESSAGE);
                  return;
                }
                
            }
            if(this.txtVisitantes.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Ingrese el número máximo de visitantes", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }else{
                String validacion = "^[0-9]";
                Pattern pat = Pattern.compile(validacion);   
                Matcher mat = pat.matcher(this.txtLongitud.getText());
                if(mat.find()==true){
                   itinerario.setMaxVisitantes(Integer.parseInt(this.txtVisitantes.getText()));  
                }else{
                    JOptionPane.showMessageDialog(this, "Ingrese los visitantes en valor númerico", "Información", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
               
            }
            
            if(this.txtNumAnimales.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Introduzca el número de animales", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }else{
                String validacion = "^[0-9]";
                Pattern pat = Pattern.compile(validacion);   
                Matcher mat = pat.matcher(this.txtNumAnimales.getText());
                if(mat.find()==true){
                   itinerario.setNumEspeciesVisita(Integer.parseInt(this.txtVisitantes.getText()));  
                }else{
                    JOptionPane.showMessageDialog(this, "Ingrese los animales en valor númerico", "Información", JOptionPane.INFORMATION_MESSAGE);
                    return;
            }
         }
            
            
            List<ObjectId> listaZonasAgregar = new ArrayList<>();
            
            for(int i=0; i < listaZonasSeleccionados.size(); i++){
                listaZonasAgregar.add(listaZonasSeleccionados.get(i).getId());   
            }
            
            List<String> listaDiasAgregar = new ArrayList<>();
            for(int j=0; j < listaDiasSeleccionados.size(); j++){
                listaDiasAgregar.add(listaDiasSeleccionados.get(j));
            }
            
            if(listaZonasSeleccionados.isEmpty()){
                JOptionPane.showMessageDialog(this, "Ingrese al menos una zona", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }else{
                itinerario.setZonasRecorridas(listaZonasAgregar);
            }
            
            if(listaDiasSeleccionados.isEmpty()){
                JOptionPane.showMessageDialog(this, "Ingrese al menos un día", "Información", JOptionPane.INFORMATION_MESSAGE);
            }else{
                itinerario.setDiasSemana(listaDiasAgregar);
            }
            
            
            //controlItinerarios.verificar(itinerario);
            controlItinerarios.guarda(itinerario);
            JOptionPane.showMessageDialog(this, "El Itinerario se guardó con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Se guardó");
            
        }
        
        
    }
    
    
    
    /**
     * Metodo que llena tabla zonas disponibles
     */
    private void llenarTablaZonasDisponibles() {
       // listaItinerariosDisponibles = controlItinerarios.recuperarDatos();
        
        DefaultTableModel modeloTablaDisponibles = (DefaultTableModel) this.tblZonasDisponibles.getModel();
        modeloTablaDisponibles.setRowCount(0);
        this.listaZonasDisponibles.forEach(zonas -> {
            
            Object[] fila = new Object[2];
            fila[0] = zonas.getNombre();
            fila[1] = zonas.getExtension();
            
            modeloTablaDisponibles.addRow(fila);
            
        });
    }
    
    /**
     * Metodo que llena tabla seleccionada
     */
    private void llenarTablaZonasSeleccionadas(){
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblZonasSeleccionados.getModel();
        modeloTabla.setRowCount(0);
        this.listaZonasSeleccionados.forEach(zona -> {
            Object[] fila = new Object[2];
            fila[0] = zona.getNombre();
            fila[1] = zona.getExtension();
            
            modeloTabla.addRow(fila);
        });
    }
    
    /**
     * Metodo que agrega una zona a la lista
     */
    private void agregarZonaALista(){ 
        listaZonasSeleccionados.add(getZonaAgregar());
        listaZonasDisponibles.remove(getZonaAgregar());
        actualizarLista();
    }
    
    /**
     * Metodo que elimina una zona a la lista
     */
    private void eliminarZonaLista() {
       // System.out.println(getZonaEliminar());
        this.listaZonasDisponibles.add(getZonaEliminar());
//        listaZonasSeleccionados.remo
        listaZonasSeleccionados.remove(getZonaEliminar());
      //  System.out.println(listaZonasSeleccionados);
        actualizarLista();
    }
    
    /**
     * Metodo que actualiza lista
     */
    private void actualizarLista() {
        this.llenarTablaZonasDisponibles();
        this.llenarTablaZonasSeleccionadas();
        this.llenarTablaDiasDisponibles();
        this.llenarTablaDiasSeleccionados();
        
        if (this.tblZonasDisponibles.getRowCount() == 0) {
            this.bntAgregarZona.setEnabled(false);
        }
        if (this.tblZonasDisponibles.getRowCount() > 0) {
            this.bntAgregarZona.setEnabled(true);
        }
        if (this.tblZonasSeleccionados.getRowCount() == 0) {
            this.bntEliminarZona.setEnabled(false);
        }
        if (this.tblZonasSeleccionados.getRowCount() > 0) {
            this.bntEliminarZona.setEnabled(true);
        }
        if (this.tblDiasDisponibles.getRowCount() == 0){
            this.btnAgregarDia.setEnabled(false);
        }
        if (this.tblDiasDisponibles.getRowCount() > 0){
            this.btnAgregarDia.setEnabled(true);
        }
        if (this.tblDiasSeleccionados.getRowCount() == 0){
            this.btnEliminarDia.setEnabled(false);
        }
        if (this.tblDiasSeleccionados.getRowCount() > 0){
            this.btnEliminarDia.setEnabled(true);
        }
    }
    
    /**
     * Metodo que obtiene la zona a eliminar
     * @return 
     */
     private Zona getZonaEliminar() {
        int indiceFilaSeleccionada = this.tblZonasSeleccionados.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblZonasSeleccionados.getModel();
            int indiceColumnaContinente = 0;
            
            String StringZonaASeleccionar = (String) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaContinente);
            
            List<Zona> listaZonas = controlZonas.recuperarDatos();
            for(int i=0; i < listaZonas.size(); i++){
                if(StringZonaASeleccionar.equalsIgnoreCase(listaZonas.get(i).getNombre())){
                    
                return  listaZonas.get(i);
                
                }
            }
        } 
        return null;
    }
    
     /**
      * Metodo que obtiene la zona a agregar
      * @return 
      */
    private Zona getZonaAgregar() {
        int indiceFilaSeleccionada = this.tblZonasDisponibles.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            
            DefaultTableModel modelo = (DefaultTableModel) this.tblZonasDisponibles.getModel();
            int indiceColumnaZona = 0;
            
            
            String StringZonaASeleccionar = (String) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaZona);
            
            List<Zona> listaZonas = controlZonas.recuperarDatos();
            for(int i=0; i < listaZonas.size(); i++){
                if(StringZonaASeleccionar.equalsIgnoreCase(listaZonas.get(i).getNombre())){
                 
                return listaZonas.get(i);
                }
            }
            
            
        } else {
            return null;
        }
        return null;
    }
    
    /**
     * Metodo que llena la tabla disponible
     */
    private void llenarTablaDiasDisponibles(){
        
        DefaultTableModel modeloTablaDisponibles = (DefaultTableModel) this.tblDiasDisponibles.getModel();
        modeloTablaDisponibles.setRowCount(0);
        this.listaDiasDisponibles.forEach(dias -> {
            
            Object[] fila = new Object[2];
            fila[0] = dias;
            
            modeloTablaDisponibles.addRow(fila);
            
        });
        
    }
    
    /**
     * Metodo que llena tabla de dia seleccionado
     */
    private void llenarTablaDiasSeleccionados(){
         DefaultTableModel modeloTabla = (DefaultTableModel) this.tblDiasSeleccionados.getModel();
        modeloTabla.setRowCount(0);
        this.listaDiasSeleccionados.forEach(dia -> {
            Object[] fila = new Object[1];
            fila[0] = dia;
            modeloTabla.addRow(fila);
        });
    }
    
    /**
     * Metodo que elimina dia de la lista
     */
    private void eliminarDiaLista() {
        String diaSeleccionado = this.getDiasEliminar();
        this.listaDiasDisponibles.add(diaSeleccionado);
        this.listaDiasSeleccionados.remove(diaSeleccionado);
        this.actualizarLista();
    }
    
    /**
     * Metodo que consigue dia a elimnar
     * @return 
     */
    private String getDiasEliminar() {
        int indiceFilaSeleccionada = this.tblDiasSeleccionados.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblDiasSeleccionados.getModel();
            int indiceColumnaContinente = 0;
            String diaASeleccionar = (String) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaContinente);
            return diaASeleccionar;
        } else {
            return null;
        }
    }
    
    /**
     * Metodo que consigue dia a agregar
     * @return 
     */
    private String getDiaAgregar() {
        int indiceFilaSeleccionada = this.tblDiasDisponibles.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblDiasDisponibles.getModel();
            int indiceColumnaContinente = 0;
            String diaASeleccionar = (String) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaContinente);
            return diaASeleccionar;
        } else {
            return null;
        }
    }
    
    /**
     * Metodo que agrega dia a la lista
     */
     private void agregarDiaALista(){ 
        this.listaDiasSeleccionados.add(getDiaAgregar());
        this.listaDiasDisponibles.remove(getDiaAgregar());
        actualizarLista();
    }
    
    
    /**
     * Metodo que limpia formulario
     */
    private void limpiarFormulario(){
        this.txtId.setText(null);
        this.txtNombre.setText(null);
        this.txtDuracion.setText(null);
        this.txtLongitud.setText(null);
        this.txtVisitantes.setText(null);
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblZonasDisponibles = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDuracion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        btnVerificar = new javax.swing.JButton();
        txtLongitud = new javax.swing.JTextField();
        txtVisitantes = new javax.swing.JTextField();
        asd = new javax.swing.JScrollPane();
        tblZonasSeleccionados = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDiasDisponibles = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDiasSeleccionados = new javax.swing.JTable();
        bntAgregarZona = new javax.swing.JButton();
        bntEliminarZona = new javax.swing.JButton();
        btnAgregarDia = new javax.swing.JButton();
        btnEliminarDia = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtNumAnimales = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Itinerario");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblZonasDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Extensión"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblZonasDisponibles);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel4.setText("Max. visitantes");

        jLabel3.setText("Longitud(mts)");

        txtDuracion.setEditable(false);

        jLabel2.setText("Duración(mins)");

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

        txtId.setEditable(false);

        jLabel6.setText("ID");

        jLabel7.setText("Zona");

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

        txtLongitud.setEditable(false);

        txtVisitantes.setEditable(false);

        tblZonasSeleccionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Extensión"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        asd.setViewportView(tblZonasSeleccionados);

        tblDiasDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Días"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblDiasDisponibles);

        tblDiasSeleccionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Días"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblDiasSeleccionados);

        bntAgregarZona.setText("Agregar Zona");
        bntAgregarZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAgregarZonaActionPerformed(evt);
            }
        });

        bntEliminarZona.setText("Eliminar Zona");
        bntEliminarZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEliminarZonaActionPerformed(evt);
            }
        });

        btnAgregarDia.setText("Agregar Día");
        btnAgregarDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDiaActionPerformed(evt);
            }
        });

        btnEliminarDia.setText("Eliminar Día");
        btnEliminarDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDiaActionPerformed(evt);
            }
        });

        jLabel5.setText("No. de Animales");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarDia)
                .addGap(186, 186, 186)
                .addComponent(btnEliminarDia)
                .addGap(107, 107, 107))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                .addComponent(txtDuracion, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtLongitud, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtVisitantes, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(txtNumAnimales, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(184, 184, 184)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(176, 176, 176)
                                        .addComponent(btnMenu))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(asd, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(bntAgregarZona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntEliminarZona)
                        .addGap(94, 94, 94))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(asd, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtVisitantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntAgregarZona)
                    .addComponent(bntEliminarZona))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNumAnimales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarDia)
                        .addGap(25, 25, 25)
                        .addComponent(btnMenu))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnGuardar)
                                    .addComponent(btnCancelar))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarDia)
                        .addGap(0, 44, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guarda();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        FrmPrincipal.getInstance().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnAgregarDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDiaActionPerformed
        agregarDiaALista();
    }//GEN-LAST:event_btnAgregarDiaActionPerformed

    private void bntAgregarZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAgregarZonaActionPerformed
        agregarZonaALista();
    }//GEN-LAST:event_bntAgregarZonaActionPerformed

    private void bntEliminarZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEliminarZonaActionPerformed
        eliminarZonaLista();
    }//GEN-LAST:event_bntEliminarZonaActionPerformed

    private void btnEliminarDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDiaActionPerformed
        eliminarDiaLista();
    }//GEN-LAST:event_btnEliminarDiaActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        verificaItinerarioExistente();
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            verificaItinerarioExistente();
    }//GEN-LAST:event_txtNombreKeyPressed
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmItinerario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmItinerario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmItinerario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmItinerario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmItinerario().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane asd;
    private javax.swing.JButton bntAgregarZona;
    private javax.swing.JButton bntEliminarZona;
    private javax.swing.JButton btnAgregarDia;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarDia;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblDiasDisponibles;
    private javax.swing.JTable tblDiasSeleccionados;
    private javax.swing.JTable tblZonasDisponibles;
    private javax.swing.JTable tblZonasSeleccionados;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLongitud;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumAnimales;
    private javax.swing.JTextField txtVisitantes;
    // End of variables declaration//GEN-END:variables
}
