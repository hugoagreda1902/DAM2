package interfaces.Practica1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio5 {
    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame ventanaPrincipal = new JFrame("Cambiar");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el botón
        JButton boton = new JButton("¡Todavia no!");

        // Agregar un listener al botón
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cambiar el texto del botón
                if (boton.getText().equals("¡Todavia no!")) {
                    boton.setText("¡Ahora si!");
                } else {
                    boton.setText("Todavia no");
                }
            }
        });

        // Agregar el botón a la ventana
        ventanaPrincipal.add(boton);

        // Realizar el empaquetado de los componentes de la ventana
        ventanaPrincipal.pack();

        // Activar la visibilidad de la ventana
        ventanaPrincipal.setVisible(true);
    }
}
