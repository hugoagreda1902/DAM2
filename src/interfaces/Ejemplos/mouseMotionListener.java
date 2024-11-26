package interfaces.Ejemplos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class mouseMotionListener {
    public static void main(String[] args) {
        // Crear un botón
        JButton boton = new JButton("Mi botón");

        // Crear un MouseMotionListener
        MouseMotionListener mouseMotionListener = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // No hacer nada cuando se arrastra el mouse
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // Cambiar el color de fondo del botón a azul cuando se mueve el mouse sobre él
                boton.setBackground(Color.BLUE);
            }
        };

        // Agregar el MouseMotionListener al botón
        boton.addMouseMotionListener(mouseMotionListener);

        // Crear un JFrame y agregar el botón
        JFrame frame = new JFrame("Ejemplo MouseMotionListener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(boton);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}
