package interfaces.Practica3ExtraGood;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de empleados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        // Configuración de layout y botones
        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Botón "Agregar"
        JButton agregarEmpleados = new JButton("Agregar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.getContentPane().add(agregarEmpleados, gbc);

        agregarEmpleados.addActionListener(e -> new VentanaAgregarEmpleado(frame).setVisible(true));

        // Botón "Lista"
        JButton listaEmp = new JButton("Lista");
        gbc.gridx = 1;
        frame.getContentPane().add(listaEmp, gbc);

        listaEmp.addActionListener(e -> new VentanaListaEmpleados(frame).setVisible(true));

        // Botón "Salir"
        JButton salir = new JButton("Salir");
        gbc.gridx = 2;
        frame.getContentPane().add(salir, gbc);

        salir.addActionListener(e -> frame.dispose());

        // Etiqueta de instrucciones
        JLabel texto = new JLabel("¿Qué quieres hacer?");
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.getContentPane().add(texto, gbc);

        frame.setVisible(true);
    }
}
