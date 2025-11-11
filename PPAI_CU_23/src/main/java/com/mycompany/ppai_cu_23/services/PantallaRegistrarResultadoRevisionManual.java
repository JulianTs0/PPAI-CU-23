package com.mycompany.ppai_cu_23.services;

//import java.net.URL;
import com.mycompany.ppai_cu_23.persistance.DataBaseService;
import com.mycompany.ppai_cu_23.utils.Debugger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class PantallaRegistrarResultadoRevisionManual extends javax.swing.JFrame {
    
    // ATRIBUTO
    private DefaultTableModel modeloTabla = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
        
    // DEPENDENCIA
    private GestorRegistrarResultadoDeRevisionManual gestor;
    
    // CONSTRUCTOR
    
    // PASO 0
    // MAIN -> PANTALLA
    public PantallaRegistrarResultadoRevisionManual() {
        // inicializar JFrame
        initComponents();
        // (SOPORTE FRONT)
        initComponents2();
    }
    
    // SETTERS
    
    // dependencia a Gestor
    public void setGestor(GestorRegistrarResultadoDeRevisionManual gestor) {
        this.gestor = gestor;
    }
    
    // METODOS DOMINIO 
    
    public void seleccionOpcionRegistrarResultadoManual(){
        
        Debugger.mensajePantalla("seleccionOpcionRegistrarResultadoManual()");
        
        this.habilitarVentanaRegistrar();
        
        // asignar dependencia PANTALLA-GESTOR
        this.gestor = new GestorRegistrarResultadoDeRevisionManual(this);
        
        this.gestor.registrarNuevaRevisionManual();
    }
    
    public void habilitarVentanaRegistrar(){
        //habilitar panel Registrar y ocultar el resto de paneles
        this.cambiarPanel(1);
    }
    
    public void mostrarEventoSismicoOrdenados(String[][] datosEventos){
        
        // comprobar que haya autodetectados 
        if (datosEventos.length != 0) {
            // si los hay, -> CURSO-NORMAL
            
            // MODELO RELLENA
            this.generarModeloTablaRellena();
            for (String[] dato : datosEventos) {
                this.modeloTabla.addRow(dato);
            }
        }
        else{
            // si no encuentra, -> CURSO-ALTERNATIVO
            this.cursoAlternativo_A1_noSeEncontraronAutoDetectados();
        }
    }
    
    public void tomarSeleccionEventoSismico(){
        
        Debugger.mensajePantalla("tomarSeleccionEventoSismico() : fila=" + (this.tbl_eventosAutoDetectados.getSelectedRow() + 1));
        
        this.gestor.tomarSeleccionEventoSismico(this.tbl_eventosAutoDetectados.getSelectedRow());
    }
    
    public void mostrarDatosEventoSismico(String [] datosEventoSismico, String rutaImagen){
        
        //habilitar panel MostrarDatos y ocultar el resto de paneles
        this.cambiarPanel(2);
        
        // mostrar datos de obtenerDatosEventoSismico()
        this.txa_valoresDatos.setText(
            "Alcance: " + datosEventoSismico[0] + "\n" + 
            "Clasificacion: " + datosEventoSismico[1] + "\n" + 
            "Origen: " + datosEventoSismico[2] + "\n" 
            //+ "Magnitud: " + datosEventoSismico[3]
        );
        
        //mostrar SISMOGRAMA
        this.lbl_imagen.setText("");
        this.lbl_imagen.setIcon(new ImageIcon(getClass().getResource(rutaImagen)));
    }
    
    public void mostrarOpcionVisualizarMapa(){
        // redundante: el BOTON VisualizarMapa ya estaba visible
        this.btn_visualizarMapa.setVisible(true);
    }
    
    public void habilitarModificaiconDatosEvento(String [] datosEventoSismico){
        // rellenar las 3 combobox  con los NOMBRES sacados de DATA-BASE
        //magnitud
        rellenarComboBox(this.cbx_magnitud,new String[] {"1","2","3","4","5","6","7"});
        // alcance
        rellenarComboBox(this.cbx_alcance, DataBaseService.cargarNombresAlcanceSismo());
        //origen
        rellenarComboBox(this.cbx_origen, DataBaseService.cargarNombresOrigenDeGeneracion());
        
        // setear los datos del selecionado Evento a los CBX
        //datosEventoSismico = [alcance,clasificacion, origen, magnitud ]
        this.cbx_magnitud.setSelectedItem(datosEventoSismico[3]); 
        this.cbx_alcance.setSelectedItem(datosEventoSismico[0]); 
        this.cbx_origen.setSelectedItem(datosEventoSismico[2]); 
        
    }
    
    public void mostrarOpcionesDeAccion(){
         
        // rellenar cbx acciones
        
        rellenarComboBox(this.cbx_accion, DataBaseService.nombresAccion);
        
        // setear la accion Rechazar
        this.cbx_accion.setSelectedItem(DataBaseService.nombresAccion[1]);
        
        // redundante: el BOTON VisualizarMapa ya estaba visible
        this.btn_confirmarAccion.setVisible(true);
    }
    
    public void accionBotonAceptar(){
        // opcion RECHAZAR seleccionada
        if (this.cbx_accion.getSelectedIndex() == 1) {
            tomarOpcionRechazarEventoSeleccionado();
        }
    }
    
    public void tomarOpcionRechazarEventoSeleccionado(){
        
        // DEBUGGER mensaje
        Debugger.mensajePantalla("tomarOpcionRechazarEventoSeleccionado()");
        
        // toma DATOS-SELECCIONADOS para que GESTOR pueda verificar
        String[] datosSeleccionados = {
            "" + this.cbx_magnitud.getSelectedItem(),
            "" + this.cbx_alcance.getSelectedItem(),
            "" + this.cbx_origen.getSelectedItem(),
            "" + this.cbx_accion.getSelectedItem()
        };
        // [magnitud, alcance, origen, accionSeleccionada]
        
        // pasar a GESTOR los DATOS-SELECCIONADOS
        this.gestor.tomarOpcionRechazarEventoSeleccionado(datosSeleccionados);
    }
    
    
    public void cursoAlternativo_A1_noSeEncontraronAutoDetectados(){
        
        // modelo VACIA
        this.generarModeloTablaVacia();
        
        // mostrar mensaje en la misma TABLA
        String[] fila1 = {"No se encontraron Eventos Sismicos Autodetectados"};
        this.modeloTabla.addRow(fila1);
        
        //deshabilitar BOTON-SELECCIONAR-EVENTO
        this.btn_seleccionarEvento.setEnabled(false);
        
        // DEBUGGER
        Debugger.mensajeEvento("cursoAlternativo_A1_noSeEncontraronAutoDetectados()");
    }
    
    public void cursoAlternativo_A8_cancelarCU(){
        Debugger.mensajePantalla("cursoAlternativo_A8_cancelarCU()");
        this.gestor.fin_casoDeUso_23();
    }
    
    // METODOS SOPORTE DE FRONT
    
    private void generarModeloTablaRellena(){
        // ASIGNAR MODELO A LA TABLA (CANTIDAD Y NOMBRES DE COLUMNAS)
        // buscar CABECERAS (nombres de columnas) en Database y asignarlas al MODELO
        this.modeloTabla.setColumnIdentifiers(DataBaseService.columnasTablaAutoDetectados);
        // asignar el MODELO a la TABLA
        this.tbl_eventosAutoDetectados.setModel(modeloTabla);
        this.modeloTabla.setRowCount(0);
    }
    
    private void generarModeloTablaVacia(){
        String[] vacia = {"(Tabla vacia)"};
        this.modeloTabla.setColumnIdentifiers(vacia);
        this.tbl_eventosAutoDetectados.setModel(modeloTabla);
        this.modeloTabla.setRowCount(0);
    }
    
    private void initComponents2(){
        
        // ocultar pestañas        
        this.jtp_pestañas.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
                return 0; // Oculta la barra de pestañas
            }
        });
        
        this.setVisible(true);
        
        this.cambiarPanel(0);
    }
    
    public void cambiarPanel(int indice){
        this.jtp_pestañas.setSelectedIndex(indice);
        for (int i = 0; i < 3; i++) {
            this.jtp_pestañas.setEnabledAt(i, i == indice);
        }
    }
    
    private void rellenarComboBox (JComboBox cbx, String[] nombres){
        DefaultComboBoxModel<String> modelo = (DefaultComboBoxModel<String>) cbx.getModel();
        modelo.removeAllElements();
        for (String nombre : nombres) {
            modelo.addElement(nombre);        
        } 
    }
    
    
    // CODIGO GENERADO
    
    private static final java.util.logging.Logger logger = 
            java.util.logging.Logger.getLogger(PantallaRegistrarResultadoRevisionManual.class.getName()); 
    
    // initComponents(); 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jtp_pestañas = new javax.swing.JTabbedPane();
        pnl_main = new javax.swing.JPanel();
        btn_registrarResultadoRevisiónManual = new javax.swing.JButton();
        pnl_registrar = new javax.swing.JPanel();
        lbl_tituloTablaEventos = new javax.swing.JLabel();
        scrl_tablaEventos = new javax.swing.JScrollPane();
        tbl_eventosAutoDetectados = new javax.swing.JTable();
        btn_cancelarEnListaEventos = new javax.swing.JButton();
        btn_seleccionarEvento = new javax.swing.JButton();
        pnl_modificarEvento = new javax.swing.JPanel();
        lbl_tituloDetalleEvento = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txa_valoresDatos = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        lbl_imagen = new javax.swing.JLabel();
        btn_visualizarMapa = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        lbl_tituloModificarEvento = new javax.swing.JLabel();
        lvl_magnitud = new javax.swing.JLabel();
        lvl_alcance = new javax.swing.JLabel();
        lvl_origen = new javax.swing.JLabel();
        cbx_magnitud = new javax.swing.JComboBox<>();
        cbx_alcance = new javax.swing.JComboBox<>();
        cbx_origen = new javax.swing.JComboBox<>();
        jSeparator4 = new javax.swing.JSeparator();
        lvl_accion = new javax.swing.JLabel();
        cbx_accion = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        btn_cancelarEnDetalles = new javax.swing.JButton();
        btn_confirmarAccion = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl_main.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 125, 255), 2, true));

        btn_registrarResultadoRevisiónManual.setText("Registrar Resultado de Revisión Manual");
        btn_registrarResultadoRevisiónManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarResultadoRevisiónManualActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_mainLayout = new javax.swing.GroupLayout(pnl_main);
        pnl_main.setLayout(pnl_mainLayout);
        pnl_mainLayout.setHorizontalGroup(
            pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_mainLayout.createSequentialGroup()
                .addContainerGap(284, Short.MAX_VALUE)
                .addComponent(btn_registrarResultadoRevisiónManual)
                .addContainerGap())
        );
        pnl_mainLayout.setVerticalGroup(
            pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_mainLayout.createSequentialGroup()
                .addContainerGap(365, Short.MAX_VALUE)
                .addComponent(btn_registrarResultadoRevisiónManual)
                .addContainerGap())
        );

        jtp_pestañas.addTab("main", pnl_main);

        lbl_tituloTablaEventos.setText("Eventos Sismicos AutoDetectados pendientes de Revision:");

        tbl_eventosAutoDetectados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_eventosAutoDetectados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_eventosAutoDetectados.getTableHeader().setReorderingAllowed(false);
        scrl_tablaEventos.setViewportView(tbl_eventosAutoDetectados);

        btn_cancelarEnListaEventos.setText("Cancelar");
        btn_cancelarEnListaEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarEnListaEventosActionPerformed(evt);
            }
        });

        btn_seleccionarEvento.setText("Seleccionar Evento Sismico");
        btn_seleccionarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seleccionarEventoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_registrarLayout = new javax.swing.GroupLayout(pnl_registrar);
        pnl_registrar.setLayout(pnl_registrarLayout);
        pnl_registrarLayout.setHorizontalGroup(
            pnl_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_registrarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrl_tablaEventos, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                    .addGroup(pnl_registrarLayout.createSequentialGroup()
                        .addComponent(lbl_tituloTablaEventos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_registrarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_cancelarEnListaEventos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_seleccionarEvento)))
                .addContainerGap())
        );
        pnl_registrarLayout.setVerticalGroup(
            pnl_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_registrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_tituloTablaEventos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrl_tablaEventos, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_seleccionarEvento)
                    .addComponent(btn_cancelarEnListaEventos))
                .addContainerGap())
        );

        jtp_pestañas.addTab("eventosAutodetectados", pnl_registrar);

        lbl_tituloDetalleEvento.setText("Detalles del Evento Sismico Seleccionado:");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txa_valoresDatos.setEditable(false);
        txa_valoresDatos.setColumns(20);
        txa_valoresDatos.setRows(5);
        txa_valoresDatos.setText("alcance_clacif_origen");
        jScrollPane1.setViewportView(txa_valoresDatos);

        lbl_imagen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_imagen.setText("imagen_sismograma");
        lbl_imagen.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jScrollPane3.setViewportView(lbl_imagen);

        btn_visualizarMapa.setText("Visualizar Mapa del Evento Sismico");

        lbl_tituloModificarEvento.setText("Modificar Evento Sismico:");

        lvl_magnitud.setText("Magnitud:");

        lvl_alcance.setText("Alcance:");

        lvl_origen.setText("Origen:");

        cbx_magnitud.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1" }));

        cbx_alcance.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1" }));

        cbx_origen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1" }));

        lvl_accion.setText("Accion:");

        cbx_accion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_cancelarEnDetalles.setText("Cancelar");
        btn_cancelarEnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarEnDetallesActionPerformed(evt);
            }
        });

        btn_confirmarAccion.setText("Confirmar Accion");
        btn_confirmarAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_confirmarAccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_modificarEventoLayout = new javax.swing.GroupLayout(pnl_modificarEvento);
        pnl_modificarEvento.setLayout(pnl_modificarEventoLayout);
        pnl_modificarEventoLayout.setHorizontalGroup(
            pnl_modificarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_modificarEventoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_modificarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addGroup(pnl_modificarEventoLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_modificarEventoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnl_modificarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_modificarEventoLayout.createSequentialGroup()
                                .addComponent(btn_cancelarEnDetalles)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_confirmarAccion))
                            .addComponent(btn_visualizarMapa, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_modificarEventoLayout.createSequentialGroup()
                                .addComponent(lvl_accion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_accion, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnl_modificarEventoLayout.createSequentialGroup()
                        .addGroup(pnl_modificarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_tituloDetalleEvento)
                            .addComponent(lbl_tituloModificarEvento)
                            .addGroup(pnl_modificarEventoLayout.createSequentialGroup()
                                .addComponent(cbx_magnitud, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_alcance, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_modificarEventoLayout.createSequentialGroup()
                                .addComponent(lvl_magnitud, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lvl_alcance, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lvl_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 152, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl_modificarEventoLayout.setVerticalGroup(
            pnl_modificarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_modificarEventoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_tituloDetalleEvento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_modificarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_visualizarMapa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_tituloModificarEvento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_modificarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lvl_magnitud)
                    .addComponent(lvl_alcance)
                    .addComponent(lvl_origen))
                .addGroup(pnl_modificarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_magnitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_alcance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_origen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_modificarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_accion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lvl_accion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_modificarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_confirmarAccion)
                    .addComponent(btn_cancelarEnDetalles))
                .addContainerGap())
        );

        jtp_pestañas.addTab("detalles_omdificar_Evento", pnl_modificarEvento);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtp_pestañas)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtp_pestañas)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_registrarResultadoRevisiónManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarResultadoRevisiónManualActionPerformed
        this.seleccionOpcionRegistrarResultadoManual();
    }//GEN-LAST:event_btn_registrarResultadoRevisiónManualActionPerformed

    private void btn_seleccionarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionarEventoActionPerformed
        tomarSeleccionEventoSismico();
    }//GEN-LAST:event_btn_seleccionarEventoActionPerformed

    private void btn_confirmarAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_confirmarAccionActionPerformed
        this.tomarOpcionRechazarEventoSeleccionado();
    }//GEN-LAST:event_btn_confirmarAccionActionPerformed

    private void btn_cancelarEnListaEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarEnListaEventosActionPerformed
        this.cursoAlternativo_A8_cancelarCU();
    }//GEN-LAST:event_btn_cancelarEnListaEventosActionPerformed

    private void btn_cancelarEnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarEnDetallesActionPerformed
        this.cursoAlternativo_A8_cancelarCU();
    }//GEN-LAST:event_btn_cancelarEnDetallesActionPerformed

    
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new PantallaRegistrarResultadoRevisionManual().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelarEnDetalles;
    private javax.swing.JButton btn_cancelarEnListaEventos;
    private javax.swing.JButton btn_confirmarAccion;
    private javax.swing.JButton btn_registrarResultadoRevisiónManual;
    private javax.swing.JButton btn_seleccionarEvento;
    private javax.swing.JButton btn_visualizarMapa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbx_accion;
    private javax.swing.JComboBox<String> cbx_alcance;
    private javax.swing.JComboBox<String> cbx_magnitud;
    private javax.swing.JComboBox<String> cbx_origen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jtp_pestañas;
    private javax.swing.JLabel lbl_imagen;
    private javax.swing.JLabel lbl_tituloDetalleEvento;
    private javax.swing.JLabel lbl_tituloModificarEvento;
    private javax.swing.JLabel lbl_tituloTablaEventos;
    private javax.swing.JLabel lvl_accion;
    private javax.swing.JLabel lvl_alcance;
    private javax.swing.JLabel lvl_magnitud;
    private javax.swing.JLabel lvl_origen;
    private javax.swing.JPanel pnl_main;
    private javax.swing.JPanel pnl_modificarEvento;
    private javax.swing.JPanel pnl_registrar;
    private javax.swing.JScrollPane scrl_tablaEventos;
    private javax.swing.JTable tbl_eventosAutoDetectados;
    private javax.swing.JTextArea txa_valoresDatos;
    // End of variables declaration//GEN-END:variables
}
