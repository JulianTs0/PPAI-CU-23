package com.mycompany.esismicos;

import com.mycompany.esismicos.ClasificacionSismo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities; // Importa SwingUtilities

public class Playground {

    public static void main(String[] args) {
        System.out.println("Iniciando el Playground de Eventos Sísmicos...");
        System.out.println("----------------------------------------------\n");

        //  TODOS LOS OBJETOS QUE SIMULABAN UNA DB AHORA SON ESTATICOS EN LA CLASE DataBase

        // 3. Imprimir información de los Eventos Sísmicos creados (esto se puede dejar o quitar si solo quieres la GUI)
        System.out.println("--- Detalles de los Eventos Sísmicos Creados ---\n");
        for (int i = 0; i < DataBase.eventosSismicos.size(); i++) {
            EventoSismico evento = DataBase.eventosSismicos.get(i);
            System.out.println("Evento Sísmico " + (i + 1) + ":");
            System.out.println(evento);

            System.out.println("  Cambios de Estado:");
            for (CambioDeEstado ce : evento.getCambiosDeEstado()) {
                System.out.println("    - " + ce);
            }

            System.out.println("  Series Temporales:");
            for (SerieTemporal st : evento.getSeriesTemporales()) {
                System.out.println("    - " + st);
                System.out.println("      Muestras Sísmicas en esta Serie:");
                for (MuestraSismica ms : st.getMuestrasSismicas()) {
                    System.out.println("        - " + ms);
                    System.out.println("          Detalles de la Muestra:");
                    for (DetalleMuestraSismica dms : ms.getDetalles()) {
                        System.out.println("            -> " + dms.getDatos()); // Usamos getDatos() para formato específico
                    }
                }
            }
            System.out.println("\n----------------------------------------------\n");
        }

        // 4. Ejemplos adicionales de uso de algunas instancias base
        System.out.println("--- Ejemplos de instancias base ---\n");
        System.out.println("Empleado 1: " + DataBase.emp1);
        System.out.println("Estado Validado: " + DataBase.estadoValidado);
        System.out.println("Tipo de Dato Magnitud: " + DataBase.tipoMagnitud.getDatos());
        System.out.println("Estación Sismológica 1: " + DataBase.est1);
        System.out.println("Sismógrafo 1: " + DataBase.sismografo1);

        System.out.println("\nPlayground finalizado. Lanzando la interfaz gráfica...\n");

        // --- Cargar la instancia de la Pantalla ---
        SwingUtilities.invokeLater(new Runnable() {
            @Override
              public void run() {
                // 1. Crea la instancia de la Pantalla.
                PantallaRegistrarResultadoDeRevisionManual pantallaInstancia = new PantallaRegistrarResultadoDeRevisionManual(null);

                // 2. Crea la instancia del Gestor, pasándole la 'pantallaInstancia' DIRECTAMENTE.
                GestorRegistrarResultadoDeRevisionManual gestorInstancia = new GestorRegistrarResultadoDeRevisionManual( 
                    null, // nombreAlcance
                    null, // nombreClasificacion
                    null, // nombreOrigen
                    null, // fechaHoraRevision
                    pantallaInstancia, // Aquí se inyecta la referencia de la Pantalla en el Gestor (tipo concreto) 
                    DataBase.eventosSismicos,
                    DataBase.sesionActual
                );

                // 3. Cierra el ciclo: inyecta la instancia del Gestor en la Pantalla usando el setter.
                pantallaInstancia.setGestor(gestorInstancia);
            }
        });
    }
}
