public class Playground {

    public static void main(String[] args) {
        // Ejecuta la interfaz de usuario en el Event Dispatch Thread (EDT).
        // Esto es esencial para la seguridad y el buen funcionamiento de Swing.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // 1. Instancia tu pantalla
                PantallaRegistrarResultadoDeRevisionManual pantalla =
                    new PantallaRegistrarResultadoDeRevisionManual();

                // 2. Llama al método para hacerla visible
                pantalla.habilitarVentana();
            }
        });
    }
}
