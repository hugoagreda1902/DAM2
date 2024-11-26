package interfaces.Ejemplos.profe.customButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class roundButton extends JButton {
    private Color color;

    public roundButton(String label) {
        super(label);
        color = Color.GREEN;  // Color inicial
        setFocusPainted(false);
        setContentAreaFilled(false);

        // Añadir un ActionListener para cambiar el color al ser presionado
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambia de color cuando se presiona el botón
                color = color == Color.GREEN ? Color.RED : Color.GREEN;
                repaint();  // Vuelve a dibujar el botón
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(color.darker());
        } else {
            g.setColor(color);
        }

        // Dibujar un círculo que representa el botón
        g.fillOval(0, 0, getWidth(), getHeight());

        // Dibujar el texto en el centro del botón
        g.setColor(Color.BLACK);
        g.setFont(getFont());
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - 2;
        g.drawString(getText(), x, y);

        // Esto asegura que las propiedades del botón se mantengan
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);  // Dimensión del botón redondo
    }

    @Override
    public boolean contains(int x, int y) {
        // Verificar que el clic se encuentre dentro del círculo
        int radius = getWidth() / 2;
        return (x - radius) * (x - radius) + (y - radius) * (y - radius) <= radius * radius;
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana
        new roundButton("BOTON");
    }
}

