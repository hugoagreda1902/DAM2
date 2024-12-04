package interfaces.ExamenInterfacesHugoAgreda;

import javax.swing.*;
import java.awt.*;

/*
Este programa se centra en gestionar los proyectos, permitiendo:
- Agregar un proyecto
- Ver la lista de los proyectos
- Salir del programa

1 - Agregar un proyecto: El proyecto se añadira siempre y cuando tengo 2 campos obligatorios rellenos, estos son "nombre" y "fecha"
2 - Lista de proyectos: Una vez añadido el proyecto, se mostrará en esta ventana en formato tabla, la cual tendrán los datos "nombre" y "fecha"
3 - Salir: Este botón te permite salir del programa
 */
public class VentanaPrincipalProyecto {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de proyectos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        // Configuración de layout y botones:
        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Botón "anadirProyecto": Cuando el usuario haga clic en este botón, le llevará a la ventana donde podrá añadir los proyectos con todos sus datos
        JButton anadirProyecto = new JButton("Añadir proyecto");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.getContentPane().add(anadirProyecto, gbc);

        anadirProyecto.addActionListener(e -> new VentanaAgregarProyecto(frame).setVisible(true));

        // Botón "listaProyecto": Una vez añadido un proyecto, el usuario lo podrá ver en esta ventana en formato tabla
        JButton listaProyecto = new JButton("Ver Proyectos");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.getContentPane().add(listaProyecto, gbc);

        listaProyecto.addActionListener(e -> new VentanaVerProyectos(frame).setVisible(true));

        // Botón "salir": Este botón le permite al usuario salir del programa
        JButton salir = new JButton("Salir");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.getContentPane().add(salir, gbc);
        salir.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Has salido del programa");
        });

        frame.setVisible(true);
    }
}

