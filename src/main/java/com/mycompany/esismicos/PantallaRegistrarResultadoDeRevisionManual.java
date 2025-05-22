package com.mycompany.esismicos;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane; // ¡NUEVA! Para la tabla con scroll
import javax.swing.table.DefaultTableModel; // ¡NUEVA! Para el modelo de la tabla
import java.awt.BorderLayout; // ¡NUEVA! Para el layout del panel
import java.time.LocalDateTime; // Necesario para LocalDateTime
import java.time.format.DateTimeFormatter; // Para formatear la fecha/hora
import java.util.List; // Necesario para List

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

        JPanel panelRegistroManual = new JPanel(new BorderLayout(10, 10));
        panelRegistroManual.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel para el botón "Seleccionar Evento"
        JPanel panelBotonSeleccionar = new JPanel(new BorderLayout()); // Usamos BorderLayout para posicionar a la izquierda
        panelBotonSeleccionar.add(btnSeleccionarEvento, BorderLayout.WEST); // Lo posicionamos a la izquierda

        JPanel panelInferior = new JPanel(new BorderLayout()); // Cambiado a BorderLayout para contener el nuevo panel del botón
        panelInferior.add(panelBotonSeleccionar, BorderLayout.WEST); // Añade el panel del botón a la izquierda de la parte inferior
        panelInferior.add(new JLabel("Otros elementos"), BorderLayout.CENTER); // Otros elementos

        panelRegistroManual.add(new JScrollPane(grillaEventoSismico), BorderLayout.CENTER);
        panelRegistroManual.add(panelInferior, BorderLayout.SOUTH); // El panelInferior ahora contiene el botón

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

    public void mostrarDatosEventoSismico() {
        // Lógica para poblar los labels con datos de un evento sísmico
    }

    public void mostrarOpcionVisualizarMapa() {
        // Lógica para mostrar el botón de visualizar mapa
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
