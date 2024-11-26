package interfaces.Practica2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class main {
    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Práctica 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);

        // Establecer el layout a GridLayout
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Añadir la etiqueta "texto1" con todos sus parámetros
        JLabel texto1 = new JLabel("Campo de texto:");
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        frame.add(texto1, c);

        // Añadir la caja de texto "escribir" con todos sus parámetros
        JTextField escribir = new JTextField(10);
        c.insets = new Insets(0,5,0,0);
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.LINE_START;
        frame.add(escribir, c);

        // Añadir el botón "aceptar" con todos sus parámetros
        JButton aceptar = new JButton("Aceptar");
        c.insets = new Insets(0,5,0,0);
        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        frame.add(aceptar, c);

        // Añadir el panel "panel" con su respectivo Layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Añadir el area de texto "area"
        JTextArea area = new JTextArea(10,20);

        panel.add(new JScrollPane(area), BorderLayout.CENTER);

        c.insets = new Insets(10,0,0,0);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        frame.add(panel, c);


        area.addMouseListener(new MouseAdapter() {
            @Override
            // Acción para cuando el ratón entre en el área de texto, lo escriba en el "area"
            public void mouseEntered(MouseEvent e) {
                area.append("Mouse entró en el área de texto.\n");
            }

            @Override
            // Acción para cuando el ratón salga en el área de texto, lo escriba en el "area"
            public void mouseExited(MouseEvent e) {
                area.append("Mouse salió del área de texto.\n");
            }
        });

        escribir.addKeyListener(new KeyAdapter() {
            @Override
            // Acción para cuando una tecla se presiona, lo escriba en el "area"
            public void keyPressed(KeyEvent e) {
                area.append("Tecla presionada: " + e.getKeyChar() + "\n");
            }

            @Override
            // Acción para cuando una tecla es liberada, lo escriba en el "area"
            public void keyReleased(KeyEvent e) {
                area.append("Tecla liberada: " + e.getKeyChar() + ".\n");
            }
        });

        escribir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Acción para cuando el ratón entre en el campo de texto (para escribir), lo escriba en el "area"
                area.append("Campo de texto ha ganado el foco.\n");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Acción para cuando el ratón salga del campo de texto, lo escriba en el "area"
                area.append("Campo de texto ha perdido el foco.\n");
            }
        });

        aceptar.addActionListener(new ActionListener() {
            @Override
            // Acción para cuando el botón se presiona, lo escriba en el "area"
            public void actionPerformed(ActionEvent e) {
                area.append("Botón presionado.\n");
            }
        });

        frame.setVisible(true);
    }
}