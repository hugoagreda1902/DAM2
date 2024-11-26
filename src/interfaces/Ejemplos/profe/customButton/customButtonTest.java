package interfaces.Ejemplos.profe.customButton;
import javax.swing.*;

public class customButtonTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Prueba de Botón Redondo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        // Crear una instancia del botón redondo personalizado
        roundButton roundButton = new roundButton("Presiona");

        // Añadir el botón a la ventana
        frame.add(roundButton);
        frame.setVisible(true);
    }
}

