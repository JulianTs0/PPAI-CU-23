package com.mycompany.esismicos;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane; // ¡NUEVA! Para la tabla con scroll
import javax.swing.table.DefaultTableModel; // ¡NUEVA! Para el modelo de la tabla
import java.awt.CardLayout;
import java.awt.BorderLayout; // ¡NUEVA! Para el layout del panel
import java.time.LocalDateTime; // Necesario para LocalDateTime
import java.time.format.DateTimeFormatter; // Para formatear la fecha/hora
import java.util.List; // Necesario para List
import javax.swing.BorderFactory; // Importar para bordes
import javax.swing.BoxLayout; // Importar para BoxLayout
import javax.swing.Box; // Importar para Box.createVerticalStrut
import java.awt.Dimension;
import java.util.Map; // ¡NUEVA IMPORTACIÓN NECESARIA!

public class PantallaRegistrarResultadoDeRevisionManual extends JFrame {

    // --- Atributos de elementos del Front-end (componentes Swing) ---
    protected JButton btnRegistrarResultadoManual;
    protected JTable grillaEventoSismico;
    protected JLabel labelFechahoraOcurrencia;
    protected JLabel labelLongitudHipocentro;
    protected JLabel labelLatitudHipocentro;
    protected JLabel labelLongitudEpicentro;
    protected JLabel labelLatitudEpicentro;
    protected JLabel labelMagnitud;
    protected JButton btnOpcionVisualizarMapa;
    protected JButton btnOpcionModificarDatos;  
    protected JCheckBox checkboxOpcionesEvento;
    protected JLabel labelNombreAlcance;
    protected JLabel labelNombreClasificacion;
    protected JLabel labelNombreOrigen;
    protected JButton btnSeleccionarEvento;

    protected JPanel panelContenedorVistas; // Panel que contendrá las diferentes "pantallas"
    protected java.awt.CardLayout cardLayout; // El Layout Manager para alternar vistas

    protected JPanel panelDetallesEvento;
    protected JPanel panelContenidoInferior;
    protected JPanel panelRegistroManual;

    private GestorRegistrarResultadoDeRevisionManual gestor;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    // --- Constructor ---
    public PantallaRegistrarResultadoDeRevisionManual(GestorRegistrarResultadoDeRevisionManual gestor) {
        this.gestor = gestor; // Asigna la instancia del gestor (dependencia)

        // Configuración básica de la ventana (JFrame)
        this.setTitle("Registro de Resultado de Revisión Manual");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Inicialización de los componentes del Front-end
        btnRegistrarResultadoManual = new JButton("Registrar Resultado Manual");
        btnRegistrarResultadoManual.addActionListener(e -> opcionRegistrarResultadoManual());

        grillaEventoSismico = new JTable(); // Se inicializa vacía, se configura en habilitarVentana()

        labelFechahoraOcurrencia = new JLabel("Fecha y Hora de Ocurrencia:");
        labelLongitudHipocentro = new JLabel("Longitud Hipocentro:");
        labelLatitudHipocentro = new JLabel("Latitud Hipocentro:");
        labelLongitudEpicentro = new JLabel("Longitud Epicentro:");
        labelLatitudEpicentro = new JLabel("Latitud Epicentro:");
        labelMagnitud = new JLabel("Magnitud:");
        btnOpcionVisualizarMapa = new JButton("Visualizar Mapa");
        btnOpcionModificarDatos = new JButton("Modificar Datos");
        checkboxOpcionesEvento = new JCheckBox("Opciones de Evento");
        labelNombreAlcance = new JLabel("Nombre Alcance:");
        labelNombreClasificacion = new JLabel("Nombre Clasificación:");
        labelNombreOrigen = new JLabel("Nombre Origen:");

        btnSeleccionarEvento = new JButton("Seleccionar Evento");
        btnSeleccionarEvento.addActionListener(e -> tomarEventoSismicoARevisar());
        btnSeleccionarEvento.setEnabled(false); // Inicialmente deshabilitado, se habilita al seleccionar una fila

        // Configuración de CardLayout
        cardLayout = new java.awt.CardLayout();
        panelContenedorVistas = new JPanel(cardLayout);

        // --- Definición de las "vistas" o "paneles" ---
        JPanel panelInicial = new JPanel();
        panelInicial.add(btnRegistrarResultadoManual);

        panelRegistroManual = new JPanel(new BorderLayout(10, 10));
        panelRegistroManual.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel para el botón "Seleccionar Evento"
        JPanel panelBotonSeleccionar = new JPanel(new BorderLayout()); // Usamos BorderLayout para posicionar a la izquierda
        panelBotonSeleccionar.add(btnSeleccionarEvento, BorderLayout.WEST); // Lo posicionamos a la izquierda

        // *** NUEVA ZONA: Panel de Detalles del Evento (abajo de la grilla) ***
        panelDetallesEvento = new JPanel();
        panelDetallesEvento.setLayout(new BoxLayout(panelDetallesEvento, BoxLayout.X_AXIS));
        panelDetallesEvento.setBorder(BorderFactory.createTitledBorder("Detalles del Evento Seleccionado"));
        // MODIFICADO: Se establece inicialmente invisible
        panelDetallesEvento.setVisible(false);

        // Sub-panel para la mitad izquierda (Datos de las asociaciones)
        JPanel panelIzquierdoDetalles = new JPanel();
        panelIzquierdoDetalles.setLayout(new BoxLayout(panelIzquierdoDetalles, BoxLayout.Y_AXIS)); // Layout vertical para las labels
        panelIzquierdoDetalles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

        // Título para la sección de datos del evento sísmico
        JLabel tituloDatosEvento = new JLabel("Datos del Evento Sísmico");
        tituloDatosEvento.setFont(tituloDatosEvento.getFont().deriveFont(16f)); // Ajustar tamaño de fuente
        // Alineación del título (opcional, pero útil para BoxLayout)
        tituloDatosEvento.setAlignmentX(LEFT_ALIGNMENT); // Asegura que el título se alinee a la izquierda

        panelIzquierdoDetalles.add(tituloDatosEvento);
        panelIzquierdoDetalles.add(Box.createVerticalStrut(10)); // Espacio vertical

        labelNombreClasificacion.setAlignmentX(LEFT_ALIGNMENT); // Alinea a la izquierda
        labelNombreOrigen.setAlignmentX(LEFT_ALIGNMENT);       // Alinea a la izquierda
        labelNombreAlcance.setAlignmentX(LEFT_ALIGNMENT);      // Alinea a la izquierda


        // Labels para mostrar los nombres de las asociaciones (reutilizando los atributos existentes)
        panelIzquierdoDetalles.add(labelNombreClasificacion);
        panelIzquierdoDetalles.add(labelNombreOrigen);
        panelIzquierdoDetalles.add(labelNombreAlcance);

        panelIzquierdoDetalles.add(Box.createVerticalGlue());

        // Añadir los sub-paneles a la nueva zona de detalles
        panelDetallesEvento.add(panelIzquierdoDetalles);
        // Puedes agregar más paneles a la derecha si es necesario en el futuro


        // Panel inferior que contendrá el botón de seleccionar y la nueva zona de detalles
        // YA ESTÁ DECLARADO ARRIBA COMO ATRIBUTO DE INSTANCIA
        panelContenidoInferior = new JPanel(new BorderLayout(0, 10));
        panelContenidoInferior.setPreferredSize(new Dimension(0, 250));

        panelContenidoInferior.add(panelBotonSeleccionar, BorderLayout.NORTH);
        panelContenidoInferior.add(panelDetallesEvento, BorderLayout.CENTER);

        panelRegistroManual.add(new JScrollPane(grillaEventoSismico), BorderLayout.CENTER); // La grilla en el centro
        panelRegistroManual.add(panelContenidoInferior, BorderLayout.SOUTH); // El panel contenido inferior en la parte inferior

        JScrollPane scrollPane = new JScrollPane(grillaEventoSismico);

        // Panel intermedio para aplicar márgenes a los lados de la tabla
        JPanel panelTablaConMargenes = new JPanel(new BorderLayout());
        panelTablaConMargenes.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // 20px a izquierda y derecha
        panelTablaConMargenes.add(scrollPane, BorderLayout.CENTER); // El scrollPane ocupa el centro de este panel

        panelRegistroManual.add(panelTablaConMargenes, BorderLayout.CENTER); // Ahora añadimos este panel con márgenes al centro
        panelRegistroManual.add(panelContenidoInferior, BorderLayout.SOUTH);

        // Añadir las vistas al panelContenedorVistas
        panelContenedorVistas.add(panelInicial, "Inicial");
        panelContenedorVistas.add(panelRegistroManual, "RegistroManual");

        // Añadir el panel contenedor de vistas al JFrame
        this.add(panelContenedorVistas);

        // Mostrar la primera vista (la inicial) al iniciar
        cardLayout.show(panelContenedorVistas, "Inicial");

        this.setVisible(true);
    }

    // --- Métodos de la Pantalla (vacíos como solicitado) ---

    /**
     * Este método se encarga de iniciar el caso de uso y **habilita la ventana directamente**.
     */
    public void opcionRegistrarResultadoManual() {
        this.habilitarVentana(); // La pantalla se habilita/configura visualmente
        // ¡Aquí la pantalla DELEGA al gestor!
        if (gestor != null) { // Siempre es buena práctica verificar que la dependencia no sea nula
            gestor.registrarNuevaRevisionManual(); // Llamada al método del gestor
        } else {
            System.err.println("Error: El Gestor no ha sido inicializado en la Pantalla.");
        }
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void habilitarVentana() {
      // Cambia la "tarjeta" visible en el CardLayout a la de "RegistroManual".
      cardLayout.show(panelContenedorVistas, "RegistroManual");
      this.setTitle("Ejemplo"); // Cambia el título de la ventana

      // --- Configuración de la grilla (JTable) con modelo vacío ---
      // Definir los nombres de las columnas usando el texto de los JLabel
      // IMPORTANTE: Asegúrate de que los JLabel estén inicializados en el constructor antes de llegar aquí.
      String[] columnNames = {
          "Nro.", // Mantenemos este para la numeración
          labelFechahoraOcurrencia.getText(),
          labelLatitudHipocentro.getText(),
          labelLongitudHipocentro.getText(),
          labelLatitudEpicentro.getText(),
          labelLongitudEpicentro.getText(),
          labelMagnitud.getText()
      };

      // Crear un modelo de tabla vacío, solo con las columnas definidas
      DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) { // 0 filas iniciales
          @Override
          public boolean isCellEditable(int row, int column) {
              // Hacemos que la tabla no sea editable directamente desde la GUI
              return false;
          }
      };

      // Asignar el modelo a la grilla
      grillaEventoSismico.setModel(tableModel);

      // Opcional: Ajustar el tamaño de las columnas para que se adapten al contenido (actualmente vacío)
      grillaEventoSismico.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

      grillaEventoSismico.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Solo nos interesa el estado final de la selección
                boolean hayFilaSeleccionada = grillaEventoSismico.getSelectedRow() != -1;
                btnSeleccionarEvento.setEnabled(hayFilaSeleccionada);
            }
      });

        // MODIFICADO: Asegurarse de que el panel de detalles esté oculto
        // al habilitar la ventana, por si el usuario regresa a esta vista.
        panelDetallesEvento.setVisible(false);
        panelContenidoInferior.revalidate(); // Revalida el layout del panel contenedor
        panelContenidoInferior.repaint();   // Repinta el panel contenedor
    }

    public void setGestor(GestorRegistrarResultadoDeRevisionManual gestor) {
        this.gestor = gestor;
    }

    public void mostrarEventoSismicoOrdenados(List<Object[]> eventosOrdenados) {
        System.out.println("Pantalla: Recibiendo " + eventosOrdenados.size() + " eventos ordenados para mostrar en la grilla.");
        DefaultTableModel model = (DefaultTableModel) grillaEventoSismico.getModel();

        // Limpiar todas las filas existentes en la tabla
        model.setRowCount(0);

        int numeroFila = 1; // Para la columna "Nro."
        for (Object[] eventoData : eventosOrdenados) {
            // Los índices de 'eventoData' corresponden a cómo los preparaste en el Gestor:
            // [0] = LocalDateTime fechaHoraOcurrencia
            // [1] = double latitudEpicentro
            // [2] = double longitudEpicentro
            // [3] = double latitudHipocentro
            // [4] = double longitudHipocentro
            // [5] = double magnitud
            // [6] = EventoSismico original (NO lo mostramos directamente en la tabla)

            // Crea un nuevo array con los datos que realmente se mostrarán en la JTable
            // Asegúrate de que el orden de estos datos coincida con las 'columnNames' definidas en habilitarVentana()
            Object[] rowForTable = new Object[7];
            rowForTable[0] = numeroFila++;
            rowForTable[1] = ((LocalDateTime) eventoData[0]).format(DATE_TIME_FORMATTER); // Formatear fecha y hora
            rowForTable[2] = eventoData[1]; // Latitud Epicentro
            rowForTable[3] = eventoData[2]; // Longitud Epicentro
            rowForTable[4] = eventoData[3]; // Latitud Hipocentro
            rowForTable[5] = eventoData[4]; // Longitud Hipocentro
            rowForTable[6] = eventoData[5]; // Magnitud

            model.addRow(rowForTable);
            System.out.println("Pantalla: Añadida fila a la grilla para el evento: " + rowForTable[1]);
        }
        System.out.println("Pantalla: Grilla 'grillaEventoSismico' actualizada completamente.");
        btnSeleccionarEvento.setEnabled(false);

        // MODIFICADO: Si hay eventos para mostrar, asegúrate de que el panel de detalles
        // esté oculto al recargar la grilla, esperando una nueva selección.
        panelDetallesEvento.setVisible(false);
        panelContenidoInferior.revalidate();
        panelContenidoInferior.repaint();
    }

    public void tomarEventoSismicoARevisar() {
        System.out.println("Pantalla: Método tomarEventoSismicoARevisar() ejecutado por el botón.");
        int selectedRow = grillaEventoSismico.getSelectedRow();

        if (selectedRow != -1) { // Verifica que haya una fila seleccionada
            System.out.println("Pantalla: Fila seleccionada en la grilla: " + selectedRow);
            if (gestor != null) {
                // Delega al gestor, pasándole el índice de la fila seleccionada
                gestor.tomarSeleccionEventoSismico(selectedRow);
                // Aquí podrías deshabilitar el botón o la tabla, o avanzar a la siguiente vista
                // para indicar que el evento ha sido "tomado".
            } else {
                System.err.println("Error: El Gestor no está disponible para procesar la selección.");
            }
        } else {
            System.out.println("Pantalla: No hay ninguna fila seleccionada en la grilla. Por favor, seleccione un evento.");
            // Podrías mostrar un mensaje de error al usuario aquí
        }
    }

    public void mostrarDatosEventoSismico(List<String> datos) {
        System.out.println("Pantalla: Recibidos datos del evento sísmico para mostrar en los labels:");
        // Asignar los datos a los JLabels
        if (datos != null && datos.size() >= 3) {
            // Asumiendo el orden: [0] = Clasificación, [1] = Origen, [2] = Alcance
            labelNombreClasificacion.setText("Clasificación: " + datos.get(0));
            labelNombreOrigen.setText("Origen: " + datos.get(1));
            labelNombreAlcance.setText("Alcance: " + datos.get(2));
            System.out.println("  - Clasificación: " + datos.get(0));
            System.out.println("  - Origen: " + datos.get(1));
            System.out.println("  - Alcance: " + datos.get(2));
        } else {
            labelNombreClasificacion.setText("Clasificación: N/A");
            labelNombreOrigen.setText("Origen: N/A");
            labelNombreAlcance.setText("Alcance: N/A");
            System.out.println("  - No se recibieron datos suficientes o válidos para las asociaciones.");
        }

        // MODIFICADO: Hacer visible el panel de detalles del evento.
        // Ahora funcionará porque 'panelDetallesEvento' y 'panelContenidoInferior'
        // son atributos de instancia e inicializados correctamente antes.
        panelDetallesEvento.setVisible(true);
        panelContenidoInferior.revalidate(); // Revalida el layout del panel contenedor
        panelContenidoInferior.repaint();   // Repinta el panel contenedor

        System.out.println("Pantalla: Panel de detalles del evento ahora visible.");

    }

    public void mostrarOpcionVisualizarMapa(Map<String, String> rutasSismogramas) {
        System.out.println("\n--- Pantalla: Método mostrarOpcionVisualizarMapa() ejecutado ---");
        System.out.println("Pantalla: Recibidas las siguientes rutas de sismogramas para visualizar:");

        if (rutasSismogramas != null && !rutasSismogramas.isEmpty()) {
            for (Map.Entry<String, String> entry : rutasSismogramas.entrySet()) {
                System.out.println("  - Estación: '" + entry.getKey() + "' -> Ruta Sismograma: '" + entry.getValue() + "'");
            }
            // En el futuro, aquí habilitarías un botón o componente visual para mostrar las imágenes.
            actualizarEstadoPantalla("Rutas de sismogramas recibidas. Opción para visualizar mapa/sismogramas disponible.");
        } else {
            System.out.println("  - No se recibieron rutas de sismogramas para mostrar.");
            actualizarEstadoPantalla("No hay sismogramas para visualizar.");
        }
        System.out.println("--- Fin del método mostrarOpcionVisualizarMapa() en Pantalla ---");
    }

    public void tomarOpcionVisualizarmMapa() {
        // Lógica para manejar la acción de visualizar mapa
    }

    public void mostrarOpcionModificarDatos() {
        // Lógica para mostrar el botón de modificar datos
    }

    public void tomarOpcionModificarDatos() {
        // Lógica para manejar la acción de modificar datos
    }

    public void mostrarOpcionesDeEvento() {
        // Lógica para mostrar las opciones del checkbox
    }

    public void tomarOpcionesDeEvento() {
        // Lógica para manejar la selección del checkbox
    }

    public void clasificarDatosPorEstacionSismologica() {
        // Lógica para clasificar datos
    }

  public void actualizarEstadoPantalla(String nuevoEstado) {
        System.out.println("Pantalla: Recibido del Gestor - Nuevo estado: " + nuevoEstado);
        // Aquí podrías actualizar un JLabel en tu interfaz de usuario
    }
}
