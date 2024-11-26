package interfaces.Ejemplos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class focusListener {
    public static void main(String[] args) {
        // Crear un botón
        JButton boton = new JButton("Mi botón");

        // Crear un FocusListener
        FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Cambiar el color de fondo del botón a azul cuando gana el enfoque
                boton.setBackground(Color.BLUE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Cambiar el color de fondo del botón a gris cuando pierde el enfoque
                boton.setBackground(Color.red);
            }
        };

        // Agregar el FocusListener al botón
        boton.addFocusListener(focusListener);

        // Crear un JFrame y agregar el botón
        JFrame frame = new JFrame("Ejemplo FocusListener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(boton);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}
