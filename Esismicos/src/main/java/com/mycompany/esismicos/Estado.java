package com.mycompany.esismicos;

public class Estado {

    // Atributos
    private String ambito;
    private String nombreEstado;

    public Estado(String ambito, String nombreEstado) {
        this.ambito = ambito;
        this.nombreEstado = nombreEstado;
    }

    // Getters y Setters
    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    // --- Métodos solicitados (void y vacíos) ---

    // COMPARAR ESTADOS
    public boolean esEstado(String nombreEstado){
        return this.nombreEstado.equals(nombreEstado);
    }

    public boolean esPendienteDeRevision() {
        return esEstado(DataBase.estadoPendiente.getNombreEstado());
    }

    public boolean esEstadoBloqueadoARevisar() {
        System.out.println("Estado: Comprobando si el nombre '" + this.nombreEstado + "' es 'Bloqueado A Revisar'.");
        return this.nombreEstado != null && this.nombreEstado.equals("Bloqueado A Revisar");
    }
    
    public boolean esRechazado() {
        return esEstado(DataBase.estadoRechazado.getNombreEstado());
    }

    // COMPARAR ABBITOS

    // ESTE METODO NO CORRESPONDE DEBIDO A QUE NO SE PLANIFICO ASI EN LOS DIAGRAMAS
    public boolean esAmbito(String ambito){
        return this.ambito.equals(ambito);
    }

    public boolean esAmbitoEventoSismico() {
        System.out.println("Estado: Comprobando si el ámbito '" + this.ambito + "' es 'Evento Sismico'.");
        return this.ambito != null && this.ambito.equals("EventoSismico");
    }

    // --- Método toString() (incluido por tu nueva directriz) ---
    @Override
    public String toString() {
        return "Estado{" +
               "ambito='" + ambito + '\'' +
               ", nombreEstado='" + nombreEstado + '\'' +
               '}';
    }
}
