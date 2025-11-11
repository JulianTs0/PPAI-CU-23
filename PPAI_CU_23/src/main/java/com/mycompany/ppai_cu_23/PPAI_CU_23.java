package com.mycompany.ppai_cu_23;

import com.mycompany.ppai_cu_23.persistance.JpaUtil;
import com.mycompany.ppai_cu_23.services.PantallaRegistrarResultadoRevisionManual;
import com.mycompany.ppai_cu_23.persistance.DataBaseService;

public class PPAI_CU_23 {

    public static void main(String[] args) {

        try {
            System.out.println("Hello_World");

            DataBaseService.inicializarCache();

            // instanciar PANTALLA
            PantallaRegistrarResultadoRevisionManual pantalla = new PantallaRegistrarResultadoRevisionManual();

        } catch (Exception e) {
            System.err.println("Error fatal al iniciar la aplicación.");
            e.printStackTrace();
            // Aquí podrías mostrar un JOptionPane de error
        } finally {
            // 3. REGISTRAR UN "SHUTDOWN HOOK"
            // Esto asegura que el pool de conexiones de JPA se cierre
            // cuando el usuario cierra la aplicación.
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Cerrando la fábrica de EntityManager...");
                JpaUtil.shutdown();
            }));
        }

    }

}
