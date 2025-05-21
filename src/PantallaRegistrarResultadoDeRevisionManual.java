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

    protected JPanel panelContenedorVistas; // Panel que contendrá las diferentes "pantallas"
    protected java.awt.CardLayout cardLayout; // El Layout Manager para alternar vistas


    // --- Constructor ---
  public PantallaRegistrarResultadoDeRevisionManual() {
    // Ya no necesitamos un atributo para la lista de eventos aquí si se inicializa vacía
    // private List<EventoSismico> eventosSismicos;

    // Configuración básica de la ventana (JFrame)
    this.setTitle("Registro de Resultado de Revisión Manual");
    this.setSize(800, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    // Inicialización de los componentes del Front-end
    btnRegistrarResultadoManual = new JButton("Registrar Resultado Manual");
    btnRegistrarResultadoManual.addActionListener(e -> opcionRegistrarResultadoManual());

    // La grilla se inicializa aquí, pero sus datos se cargarán (o no) en habilitarVentana()
    grillaEventoSismico = new JTable();

    labelFechahoraOcurrencia = new JLabel("Fecha y Hora de Ocurrencia");
    labelLongitudHipocentro = new JLabel("Longitud Hipocentro");
    labelLatitudHipocentro = new JLabel("Latitud Hipocentro");
    labelLongitudEpicentro = new JLabel("Longitud Epicentro");
    labelLatitudEpicentro = new JLabel("Latitud Epicentro");
    labelMagnitud = new JLabel("Magnitud");
    btnOpcionVisualizarMapa = new JButton("Visualizar Mapa");
    btnOpcionModificarDatos = new JButton("Modificar Datos");
    checkboxOpcionesEvento = new JCheckBox("Opciones de Evento");
    labelNombreAlcance = new JLabel("Nombre Alcance:");
    labelNombreClasificacion = new JLabel("Nombre Clasificación:");
    labelNombreOrigen = new JLabel("Nombre Origen:");

    // Configuración de CardLayout
    cardLayout = new java.awt.CardLayout();
    panelContenedorVistas = new JPanel(cardLayout);

    // *** Definición de las "vistas" o "paneles" ***
    JPanel panelInicial = new JPanel();
    panelInicial.add(btnRegistrarResultadoManual);

    // El panel de registro manual ahora se preparará para la tabla
    JPanel panelRegistroManual = new JPanel(new BorderLayout(10, 10)); // Usamos BorderLayout para la tabla y otros elementos
    panelRegistroManual.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes a los costados

    // Placeholder para los elementos que irán abajo de la tabla
    JPanel panelInferior = new JPanel();
    panelInferior.add(new JLabel("Aquí irán otros elementos después de la tabla")); // Ejemplo

    panelRegistroManual.add(new JScrollPane(grillaEventoSismico), BorderLayout.CENTER); // Tabla centrada y vertical
    panelRegistroManual.add(panelInferior, BorderLayout.SOUTH); // Elementos de abajo

    // Añadir las vistas al panelContenedorVistas, cada una con un nombre de "tarjeta"
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
        // Llama a habilitarVentana() para que cambie la vista.
        this.habilitarVentana();
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
    }

    public void mostrarEventoSismicoOrdenados() {
        // Lógica para ordenar y mostrar eventos sísmicos en la grilla
    }

    public void tomarEventoSismicoARevisar() {
        // Lógica para seleccionar un evento sísmico de la grilla
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
}
