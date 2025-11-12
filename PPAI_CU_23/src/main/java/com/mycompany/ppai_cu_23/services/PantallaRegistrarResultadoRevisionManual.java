package com.mycompany.ppai_cu_23.services;

import com.mycompany.ppai_cu_23.utils.Debugger;
import com.mycompany.ppai_cu_23.utils.Enums;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;



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

        configurarAtajosDeTeclado();

        this.cargarImagenEscalada(lbl_imagenfuji, "/images/fuji.jpg");

        this.cargarImagenEscalada(lbl_clima, "/images/clima.jpeg");

    }

    // METODOS DOMINIO
    
    public void seleccionOpcionRegistrarResultadoManual(){
        
        Debugger.mensajePantalla("seleccionOpcionRegistrarResultadoManual()");
        
        // asignar dependencia PANTALLA-GESTOR
        this.gestor = new GestorRegistrarResultadoDeRevisionManual(this);
        
        this.gestor.registrarNuevaRevisionManual();

        this.habilitarVentanaRegistrar();
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
            + "Magnitud: " + datosEventoSismico[3]
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
        rellenarComboBox(this.cbx_alcance, Enums.cargarNombresAlcanceSismo());
        //origen
        rellenarComboBox(this.cbx_origen, Enums.cargarNombresOrigenDeGeneracion());
        
        // setear los datos del selecionado Evento a los CBX
        //datosEventoSismico = [alcance,clasificacion, origen, magnitud ]
        this.cbx_magnitud.setSelectedItem(datosEventoSismico[3]); 
        this.cbx_alcance.setSelectedItem(datosEventoSismico[0]); 
        this.cbx_origen.setSelectedItem(datosEventoSismico[2]); 
        
    }
    
    public void mostrarOpcionesDeAccion(){
         
        // rellenar cbx acciones
        
        rellenarComboBox(this.cbx_accion, Enums.nombresAccion);
        
        // setear la accion Rechazar
        this.cbx_accion.setSelectedItem(Enums.nombresAccion[1]);
        
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
        this.modeloTabla.setColumnIdentifiers(Enums.columnasTablaAutoDetectados);
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

        JTableHeader header = tbl_eventosAutoDetectados.getTableHeader();

        header.setFont(new Font("Cantarell", Font.BOLD, 15));

        header.setPreferredSize(new Dimension(100, 25));

        header.setBackground(new Color(234,247,234));
        header.setForeground(new Color(18,50,18));
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

    private void cargarImagenEscalada(JLabel label, String rutaImagen) {
        try {
            URL imageUrl = getClass().getResource(rutaImagen);
            if (imageUrl == null) {
                System.err.println("Imagen no encontrada: " + rutaImagen);
                label.setText("No encontrada");
                return;
            }

            BufferedImage imagenOriginal = ImageIO.read(imageUrl);

            int targetAncho = label.getPreferredSize().width;
            int targetAlto = label.getPreferredSize().height;

            if (targetAncho <= 0 || targetAlto <= 0) {
                Container contenedor = label.getParent();
                targetAncho = contenedor.getWidth();
                targetAlto = contenedor.getHeight();
            }

            int nuevoAncho = targetAncho;
            int nuevoAlto = targetAlto;

            Image imagenEscalada = imagenOriginal.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);

            label.setIcon(new ImageIcon(imagenEscalada));

        } catch (Exception e) {
            e.printStackTrace();
            label.setText("Error al cargar");
        }
    }

    private void configurarAtajosDeTeclado() {

        String accionEnterPanel1 = "clickSeleccionarEvento";
        String accionEnterPanel2 = "clickConfirmarAccion";
        String accionCancelPanel1 = "clickCancelarListaEvento";
        String accionCancelPanel2 = "clickCancelarAccion";

        KeyStroke enterKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

        JPanel panel1 = this.pnl_registrar;
        JButton botonPanel1 = this.btn_seleccionarEvento;
        JButton cancelarPanel1 = this.btn_cancelarEnListaEventos;

        JTable tabla = this.tbl_eventosAutoDetectados;

        JPanel panel2 = this.pnl_modificarEvento;
        JButton botonPanel2 = this.btn_confirmarAccion;
        JButton cancelarPanel2 = this.btn_cancelarEnDetalles;

        Action accionPresionarEnter1 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonPanel1.doClick();
            }
        };

        Action accionPresionarEscape1 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarPanel1.doClick();
            }
        };

        Action accionPresionarEnter2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonPanel2.doClick();
            }
        };

        Action accionPresionarEscape2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarPanel2.doClick();
            }
        };

        // Conectamos la tecla 'Control Derecha' al panel 2

        panel1.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
            .put(enterKeyStroke, accionEnterPanel1);
        panel1.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
            .put(escapeKeyStroke, accionCancelPanel1);

        panel1.getActionMap()
            .put(accionEnterPanel1, accionPresionarEnter1);
        panel1.getActionMap()
            .put(accionCancelPanel1, accionPresionarEscape1);

        tabla.getInputMap(JComponent.WHEN_FOCUSED)
            .put(enterKeyStroke, "none");
        tabla.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
            .put(enterKeyStroke, accionEnterPanel1);
        tabla.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
            .put(escapeKeyStroke, accionCancelPanel1);

        panel2.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
            .put(enterKeyStroke, accionEnterPanel2);
        panel2.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
            .put(escapeKeyStroke, accionCancelPanel2);

        panel2.getActionMap()
            .put(accionEnterPanel2, accionPresionarEnter2);
        panel2.getActionMap()
            .put(accionCancelPanel2, accionPresionarEscape2);

    }

    private int dialogoConfirmacion(String titulo, String mensaje){
        int tipoDeBotones = JOptionPane.OK_CANCEL_OPTION;
        int tipoDeIcono = JOptionPane.WARNING_MESSAGE;

        return JOptionPane.showConfirmDialog(
            this,
            mensaje,
            titulo,
            tipoDeBotones,
            tipoDeIcono
        );
    }

    private void ejecutarConModalCarga(Runnable tarea, String mensajeCarga) {
        // CREAMOS EL MODAL
        final JDialog dialog = new JDialog(this, "Cargando", true);

        // PANEL PRINCIPAL
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(new Color(234, 247, 234));

        // TEXTO
        JLabel labelTexto = new JLabel(mensajeCarga);
        labelTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTexto.setFont(new Font("Cantarell", Font.PLAIN, 14));
        labelTexto.setForeground(new Color(18, 50, 18));

        // BARRA DE PROGRESO INDETERMINADA
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setPreferredSize(new Dimension(300, 25));
        progressBar.setMaximumSize(new Dimension(300, 25));
        progressBar.setBackground(new Color(169, 217, 169));
        progressBar.setForeground(new Color(90, 138, 90));

        // AGREGAR COMPONENTES
        panel.add(labelTexto);
        panel.add(Box.createVerticalStrut(20));
        panel.add(progressBar);

        // CONFIGURAR DIALOG
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setResizable(false);

        // CREAMOS UN TRABAJO EN OTRO HILO
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                tarea.run();
                return null;
            }

            @Override
            protected void done() {
                dialog.dispose();
            }
        };

        worker.execute();
        dialog.setVisible(true);
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
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jtp_pestañas = new javax.swing.JTabbedPane();
        pnl_main = new javax.swing.JPanel();
        btn_registrarResultadoRevisiónManual = new javax.swing.JButton();
        lbl_clima = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnl_registrar = new javax.swing.JPanel();
        lbl_tituloTablaEventos = new javax.swing.JLabel();
        scrl_tablaEventos = new javax.swing.JScrollPane();
        tbl_eventosAutoDetectados = new javax.swing.JTable();
        btn_cancelarEnListaEventos = new javax.swing.JButton();
        btn_seleccionarEvento = new javax.swing.JButton();
        lbl_imagenfuji = new javax.swing.JLabel();
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

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl_main.setBackground(new java.awt.Color(234, 247, 234));
        pnl_main.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 125, 255), 2, true));

        btn_registrarResultadoRevisiónManual.setBackground(new java.awt.Color(90, 138, 90));
        btn_registrarResultadoRevisiónManual.setForeground(new java.awt.Color(244, 251, 244));
        btn_registrarResultadoRevisiónManual.setText("Registrar Resultado de Revisión Manual");
        btn_registrarResultadoRevisiónManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarResultadoRevisiónManualActionPerformed(evt);
            }
        });

        lbl_clima.setPreferredSize(new java.awt.Dimension(325, 155));

        jLabel1.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(58, 90, 58));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ProtEarth System");

        jLabel2.setForeground(new java.awt.Color(121, 148, 121));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Todo es mas seguro con un Sismografo");

        javax.swing.GroupLayout pnl_mainLayout = new javax.swing.GroupLayout(pnl_main);
        pnl_main.setLayout(pnl_mainLayout);
        pnl_mainLayout.setHorizontalGroup(
            pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_mainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_mainLayout.createSequentialGroup()
                        .addGap(0, 277, Short.MAX_VALUE)
                        .addGroup(pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_clima, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_registrarResultadoRevisiónManual, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        pnl_mainLayout.setVerticalGroup(
            pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_mainLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addComponent(lbl_clima, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                .addComponent(btn_registrarResultadoRevisiónManual)
                .addContainerGap())
        );

        jtp_pestañas.addTab("main", pnl_main);

        pnl_registrar.setBackground(new java.awt.Color(234, 247, 234));

        lbl_tituloTablaEventos.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        lbl_tituloTablaEventos.setForeground(new java.awt.Color(18, 50, 18));
        lbl_tituloTablaEventos.setText("Eventos Sismicos AutoDetectados pendientes de Revision:");

        scrl_tablaEventos.setBackground(new java.awt.Color(169, 217, 169));

        tbl_eventosAutoDetectados.setBackground(new java.awt.Color(169, 217, 169));
        tbl_eventosAutoDetectados.setFont(new java.awt.Font("Cantarell", 0, 17)); // NOI18N
        tbl_eventosAutoDetectados.setForeground(new java.awt.Color(8, 40, 8));
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
        tbl_eventosAutoDetectados.setGridColor(new java.awt.Color(169, 217, 169));
        tbl_eventosAutoDetectados.setRowHeight(30);
        tbl_eventosAutoDetectados.setSelectionBackground(new java.awt.Color(148, 174, 192));
        tbl_eventosAutoDetectados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_eventosAutoDetectados.getTableHeader().setReorderingAllowed(false);
        scrl_tablaEventos.setViewportView(tbl_eventosAutoDetectados);

        btn_cancelarEnListaEventos.setBackground(new java.awt.Color(160, 90, 90));
        btn_cancelarEnListaEventos.setForeground(new java.awt.Color(244, 251, 244));
        btn_cancelarEnListaEventos.setText("Cancelar");
        btn_cancelarEnListaEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarEnListaEventosActionPerformed(evt);
            }
        });

        btn_seleccionarEvento.setBackground(new java.awt.Color(90, 138, 90));
        btn_seleccionarEvento.setForeground(new java.awt.Color(244, 251, 244));
        btn_seleccionarEvento.setText("Seleccionar Evento Sismico");
        btn_seleccionarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seleccionarEventoActionPerformed(evt);
            }
        });

        lbl_imagenfuji.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_imagenfuji.setPreferredSize(new java.awt.Dimension(605, 154));
        lbl_imagenfuji.setRequestFocusEnabled(false);

        javax.swing.GroupLayout pnl_registrarLayout = new javax.swing.GroupLayout(pnl_registrar);
        pnl_registrar.setLayout(pnl_registrarLayout);
        pnl_registrarLayout.setHorizontalGroup(
            pnl_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_registrarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cancelarEnListaEventos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_seleccionarEvento)
                .addGap(7, 7, 7))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_registrarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_imagenfuji, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrl_tablaEventos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_registrarLayout.createSequentialGroup()
                        .addComponent(lbl_tituloTablaEventos)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl_registrarLayout.setVerticalGroup(
            pnl_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_registrarLayout.createSequentialGroup()
                .addComponent(lbl_imagenfuji, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_tituloTablaEventos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrl_tablaEventos, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_registrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancelarEnListaEventos)
                    .addComponent(btn_seleccionarEvento))
                .addContainerGap())
        );

        jtp_pestañas.addTab("eventosAutodetectados", pnl_registrar);

        pnl_modificarEvento.setBackground(new java.awt.Color(234, 247, 234));

        lbl_tituloDetalleEvento.setForeground(new java.awt.Color(28, 60, 28));
        lbl_tituloDetalleEvento.setText("Detalles del Evento Sismico Seleccionado:");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txa_valoresDatos.setEditable(false);
        txa_valoresDatos.setBackground(new java.awt.Color(234, 247, 234));
        txa_valoresDatos.setColumns(20);
        txa_valoresDatos.setFont(new java.awt.Font("Cantarell", 0, 19)); // NOI18N
        txa_valoresDatos.setForeground(new java.awt.Color(18, 50, 18));
        txa_valoresDatos.setRows(5);
        txa_valoresDatos.setText("alcance_clacif_origen");
        txa_valoresDatos.setDisabledTextColor(new java.awt.Color(18, 50, 18));
        txa_valoresDatos.setEnabled(false);
        txa_valoresDatos.setOpaque(false);
        jScrollPane1.setViewportView(txa_valoresDatos);

        lbl_imagen.setBackground(new java.awt.Color(139, 195, 139));
        lbl_imagen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_imagen.setText("imagen_sismograma");
        lbl_imagen.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jScrollPane3.setViewportView(lbl_imagen);

        btn_visualizarMapa.setBackground(new java.awt.Color(139, 195, 139));
        btn_visualizarMapa.setText("Visualizar Mapa del Evento Sismico");

        lbl_tituloModificarEvento.setFont(new java.awt.Font("Cantarell", 0, 19)); // NOI18N
        lbl_tituloModificarEvento.setForeground(new java.awt.Color(18, 50, 18));
        lbl_tituloModificarEvento.setText("Modificar Evento Sismico:");

        lvl_magnitud.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        lvl_magnitud.setForeground(new java.awt.Color(28, 60, 28));
        lvl_magnitud.setText("Magnitud:");

        lvl_alcance.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        lvl_alcance.setForeground(new java.awt.Color(28, 60, 28));
        lvl_alcance.setText("Alcance:");

        lvl_origen.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        lvl_origen.setForeground(new java.awt.Color(28, 60, 28));
        lvl_origen.setText("Origen:");

        cbx_magnitud.setBackground(new java.awt.Color(148, 174, 192));
        cbx_magnitud.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1" }));
        cbx_magnitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_magnitudActionPerformed(evt);
            }
        });

        cbx_alcance.setBackground(new java.awt.Color(148, 174, 192));
        cbx_alcance.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1" }));

        cbx_origen.setBackground(new java.awt.Color(148, 174, 192));
        cbx_origen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1" }));

        lvl_accion.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        lvl_accion.setForeground(new java.awt.Color(58, 90, 58));
        lvl_accion.setText("Accion:");

        cbx_accion.setBackground(new java.awt.Color(148, 174, 192));
        cbx_accion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_cancelarEnDetalles.setBackground(new java.awt.Color(160, 90, 90));
        btn_cancelarEnDetalles.setForeground(new java.awt.Color(244, 251, 244));
        btn_cancelarEnDetalles.setText("Cancelar");
        btn_cancelarEnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarEnDetallesActionPerformed(evt);
            }
        });

        btn_confirmarAccion.setBackground(new java.awt.Color(90, 138, 90));
        btn_confirmarAccion.setForeground(new java.awt.Color(244, 251, 244));
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
                        .addGroup(pnl_modificarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_tituloDetalleEvento)
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
                                .addComponent(lvl_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_tituloModificarEvento))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnl_modificarEventoLayout.createSequentialGroup()
                .addGap(0, 331, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_modificarEventoLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnl_modificarEventoLayout.setVerticalGroup(
            pnl_modificarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_modificarEventoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_tituloDetalleEvento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_modificarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
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

    private void btn_seleccionarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionarEventoActionPerformed

        String mensaje = "¿Estás seguro de que deseas seleccionar el evento sismico? Esto lo bloqueara para otros usuarios";
        String titulo = "Seleccion";

        int respuesta = dialogoConfirmacion(titulo, mensaje);

        if (respuesta == JOptionPane.OK_OPTION) {
            ejecutarConModalCarga(
                () -> this.tomarSeleccionEventoSismico(),
                "Bloqueando evento sismico..."
            );
        }

    }//GEN-LAST:event_btn_seleccionarEventoActionPerformed

    private void btn_confirmarAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_confirmarAccionActionPerformed

        String mensaje = "¿Estás seguro de que deseas aceptar la accion el evento sismico? Esto es una accion permanente";
        String titulo = "Accion";

        int respuesta = dialogoConfirmacion(titulo, mensaje);

        if (respuesta == JOptionPane.OK_OPTION) {
            ejecutarConModalCarga(
                () -> this.tomarOpcionRechazarEventoSeleccionado(),
                "Cambiando de estado..."
            );
        }

    }//GEN-LAST:event_btn_confirmarAccionActionPerformed

    private void btn_cancelarEnListaEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarEnListaEventosActionPerformed
        this.cursoAlternativo_A8_cancelarCU();
    }//GEN-LAST:event_btn_cancelarEnListaEventosActionPerformed

    private void btn_cancelarEnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarEnDetallesActionPerformed
        String mensaje = "¿Estás seguro de que cancelar la accion?";
        String titulo = "Cancelar";

        int respuesta = dialogoConfirmacion(titulo, mensaje);

        if (respuesta == JOptionPane.OK_OPTION) {
            this.cursoAlternativo_A8_cancelarCU();
        }
    }//GEN-LAST:event_btn_cancelarEnDetallesActionPerformed

    private void btn_registrarResultadoRevisiónManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarResultadoRevisiónManualActionPerformed
        ejecutarConModalCarga(
            () -> seleccionOpcionRegistrarResultadoManual(),
            "Buscando eventos pendientes de revisión..."
        );
    }//GEN-LAST:event_btn_registrarResultadoRevisiónManualActionPerformed

    private void cbx_magnitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_magnitudActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_magnitudActionPerformed

    
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
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jtp_pestañas;
    private javax.swing.JLabel lbl_clima;
    private javax.swing.JLabel lbl_imagen;
    private javax.swing.JLabel lbl_imagenfuji;
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
