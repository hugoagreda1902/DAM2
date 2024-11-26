package interfaces.Ejemplos.profe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dialog{
    public static void main(String[] args) {
        // Crear el JFrame (ventana principal)
        JFrame frame = new JFrame("Ejemplo de JDialog");

        // Configurar el tamaño del JFrame
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Crear el botón que abrirá el diálogo
        JButton showDialogButton = new JButton("Mostrar Diálogo");

        // Acción para mostrar el diálogo cuando se haga clic en el botón
        showDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al método para mostrar el diálogo
                showCustomDialog(frame);
            }
        });

        // Añadir el botón al JFrame
        frame.add(showDialogButton);

        // Hacer visible el JFrame
        frame.setVisible(true);
    }

    // Método para mostrar el JDialog personalizado
    private static void showCustomDialog(JFrame parentFrame) {
        // Crear el JDialog de Confrmación
        JDialog dialog = new JDialog(parentFrame, "Confirmación", true);
        // "true" hace que el diálogo sea modal (bloquea la ventana principal)

        // Configurar el tamaño del diálogo
        dialog.setSize(250, 150);
        dialog.setLayout(new FlowLayout());

        // Crear la etiqueta del mensaje
        JLabel messageLabel = new JLabel("¿Desea continuar?");
        dialog.add(messageLabel);

        // Crear los botones de Aceptar y Cancelar
        JButton okButton = new JButton("Aceptar");
        JButton cancelButton = new JButton("Cancelar");

        // Acción para el botón "Aceptar"
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(parentFrame, "Has aceptado.");
                dialog.dispose(); // Cerrar el diálogo
            }
        });

        // Acción para el botón "Cancelar"
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(parentFrame, "Has cancelado.");
                dialog.dispose(); // Cerrar el diálogo
            }
        });

        // Añadir los botones al diálogo
        dialog.add(okButton);
        dialog.add(cancelButton);

        // Mostrar el diálogo
        dialog.setVisible(true);
    }
}


