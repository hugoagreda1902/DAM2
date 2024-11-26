package interfaces.Ejemplos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Ejemplo {
    public static void main(String[] args) {
        // Crear el JFrame (ventana principal)
        JFrame frame = new JFrame("Confirmación de compra");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Configurar el tamaño del JFrame
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear y añadir el botón "pagar"
        JButton pagar = new JButton("Pagar");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.getContentPane().add(pagar, gbc);

        FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        };

        pagar.addFocusListener(focusListener);

        // Crear y añadir el botón "cancelar"
        JButton cancelar = new JButton("Cancelar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.getContentPane().add(cancelar, gbc);

        // Crear y añadir la etiqueta "texto"
        JLabel texto = new JLabel("¿Elija una opción?");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.getContentPane().add(texto, gbc);

        // Hacer visible el JFrame
        frame.setVisible(true);

        // Acción para mostrar el diálogo cuando se haga clic en el botón "pagar"
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al método para mostrar el diálogo
                JDialog cancelar = new JDialog(frame, "Cancelar", true);
                JOptionPane.showMessageDialog(cancelar, "Has cancelado");
                frame.dispose();
            }
        });


    }



    // Método para mostrar el JDialog
    private static void segundaVentana(JFrame parentFrame) {
        // Crear el JDialog de Métodos de pago. "true" hace que el diálogo sea modal (bloquea la ventana principal)
        JDialog dialog = new JDialog(parentFrame, "Métodos de pago", true);

        // Configurar el tamaño del diálogo
        dialog.setSize(500, 80);
        dialog.setLayout(new FlowLayout());

        // Crear y añadir la etiqueta opcion
        JLabel opcion = new JLabel("¿Que método quieres?");
        dialog.add(opcion);

        // Crear y añadir los botones
        JButton bizum = new JButton("Bizum");
        JButton transferencia = new JButton("Transferencia");
        JButton cheque = new JButton("Cheque");
        dialog.add(bizum);
        dialog.add(transferencia);
        dialog.add(cheque);

        // Agregar ActionListener al botón "Bizum"
        bizum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(dialog, "Has seleccionado Bizum");
                parentFrame.dispose();
            }
        });

        // Agregar ActionListener al botón "Tranferencia"
        transferencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(dialog, "Has seleccionado Transferencia");
                parentFrame.dispose();
            }
        });

        // Agregar ActionListener al botón "Cheque"
        cheque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(dialog, "Has seleccionado Cheque");
                parentFrame.dispose();
            }
        });

        // Hacer visible el diálogo
        dialog.setVisible(true);
    }
}

/*
 A) Para crear una ventana, es necesario usar JFrane, esta clase representa una ventana con título y un contenido
 B) El evento escuchador será necesario asociarlo en la primera ventana
 C) Esos componentes se utilizarán en la segunda ventana (aunque no he usado JRadioButton)
 */

