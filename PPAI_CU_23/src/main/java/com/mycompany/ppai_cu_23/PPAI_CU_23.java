package com.mycompany.ppai_cu_23;

import com.mycompany.ppai_cu_23.persistance.JpaHelper;
import com.mycompany.ppai_cu_23.services.PantallaRegistrarResultadoRevisionManual;

public class PPAI_CU_23 {

    public static void main(String[] args) {

        try {

            // instanciar PANTALLA
            PantallaRegistrarResultadoRevisionManual pantalla = new PantallaRegistrarResultadoRevisionManual();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Runtime.getRuntime().addShutdownHook(new Thread(JpaHelper::shutdown));
        }

    }

}
