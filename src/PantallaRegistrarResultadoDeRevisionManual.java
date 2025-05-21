import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.CardLayout;

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
        // Configuración básica de la ventana (JFrame)
        this.setTitle("Registro de Resultado de Revisión Manual");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Inicialización de los componentes del Front-end
        btnRegistrarResultadoManual = new JButton("Registrar Resultado Manual");
        // === El ActionListener ahora llama de nuevo a opcionRegistrarResultadoManual() ===
        btnRegistrarResultadoManual.addActionListener(e -> opcionRegistrarResultadoManual());
        // ==============================================================================

        grillaEventoSismico = new JTable(); // Necesitaría un TableModel para datos reales
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

        // Configuración de CardLayout
        cardLayout = new java.awt.CardLayout();
        panelContenedorVistas = new JPanel(cardLayout);

        // *** Definición de las "vistas" o "paneles" ***
        JPanel panelInicial = new JPanel();
        panelInicial.add(btnRegistrarResultadoManual);

        JPanel panelRegistroManual = new JPanel();
        // Aquí irían los componentes específicos para el formulario de registro manual

        // Añadir las vistas al panelContenedorVistas, cada una con un nombre de "tarjeta"
        panelContenedorVistas.add(panelInicial, "Inicial");
        panelContenedorVistas.add(panelRegistroManual, "RegistroManual");

        // Añadir el panel contenedor de vistas al JFrame
        this.add(panelContenedorVistas);

        // Mostrar la primera vista (la inicial) al iniciar
        cardLayout.show(panelContenedorVistas, "Inicial");

        this.setVisible(true); // Hace la ventana visible inmediatamente al construir el objeto
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

    // --- Método main: El punto de entrada de la aplicación ---
    public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
          @Override
            public void run() {
              PantallaRegistrarResultadoDeRevisionManual pantalla = new PantallaRegistrarResultadoDeRevisionManual();
            }
      });
    }
}
