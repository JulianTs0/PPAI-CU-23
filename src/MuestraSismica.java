import java.util.ArrayList; // Para la relación de composición con múltiples elementos
import java.util.List;      // Para la interfaz List

public class MuestraSismica {

    // Atributos
    private String fechaHoraMuestra;

    // Composición: Relación de uno a muchos con DetalleMuestraSismica
    private List<DetalleMuestraSismica> detallesMuestra;

    // Constructor
    public MuestraSismica(String fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
        this.detallesMuestra = new ArrayList<>(); // Inicializa la lista vacía al crear la MuestraSismica
    }

    // Getter para fechaHoraMuestra
    public String getFechaHoraMuestra() {
        return fechaHoraMuestra;
    }

    // Setter para fechaHoraMuestra (si es necesario modificarla después de la creación)
    public void setFechaHoraMuestra(String fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
    }

    // Métodos para manejar los DetalleMuestraSismica (composición)

    /**
     * Permite agregar un DetalleMuestraSismica a esta muestra.
     * Ya no imprime nada, solo realiza la acción.
     * @param detalle El objeto DetalleMuestraSismica a añadir.
     */
    public void addDetalle(DetalleMuestraSismica detalle) {
        if (detalle != null) {
            this.detallesMuestra.add(detalle);
        }
    }

    /**
     * Obtiene la lista de todos los detalles de datos de las muestras asociadas.
     * @return Una lista de objetos DetalleMuestraSismica.
     */
    public List<DetalleMuestraSismica> getDetalles() {
        // Devolvemos una copia defensiva para proteger la lista interna
        return new ArrayList<>(this.detallesMuestra);
    }

    /**
     * Crea un nuevo DetalleMuestraSismica y lo asocia a esta MuestraSismica.
     * No retorna nada (void) y ya no imprime mensajes.
     * @param valor El valor del detalle de la muestra.
     * @param tipoDeDato El tipo de dato asociado a este detalle.
     */
    public void crearDetalleMuestra(double valor, TipoDeDato tipoDeDato) {
        DetalleMuestraSismica nuevoDetalle = new DetalleMuestraSismica(valor, tipoDeDato);
        this.detallesMuestra.add(nuevoDetalle);
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void getDatos() {
        // Este método está intencionalmente vacío según tu solicitud.
        // Aquí es donde en el futuro implementarías la lógica para "obtener"
        // los datos, sin usar System.out.println() para mantener la lógica
        // de la clase separada de la presentación.
    }
}
