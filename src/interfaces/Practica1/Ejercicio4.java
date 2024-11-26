package interfaces.Practica1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio4 {
    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame datos = new JFrame("Introduce los datos");
        datos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establecer el layout manager a GridBagLayout
        datos.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Agregar los componentes al frame con GridBagLayout
        JLabel nombre = new JLabel("Nombre: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        datos.add(nombre,gbc);

        JTextField nombreTexo = new JTextField(12);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        datos.add(nombreTexo,gbc);

        JLabel apellido = new JLabel("Apellido: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        datos.add(apellido,gbc);

        JTextField apellidoTexto = new JTextField(12);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        datos.add(apellidoTexto, gbc);

        JButton enviar = new JButton("enviar");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        datos.add(enviar, gbc);

        JButton cancelar = new JButton("cancelar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER ;
        datos.add(cancelar, gbc);


        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Datos introducidos");
            }
        });

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                datos.dispose();
            }
        });

        // Realizar el empaquetado de los componentes de la ventana
        datos.pack();

        // Activar la visibilidad de la ventana
        datos.setVisible(true);
    }
}