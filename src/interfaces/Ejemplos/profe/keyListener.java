package interfaces.Ejemplos.profe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyListener extends JFrame implements KeyListener {

    private JTextField textField;
    private JLabel label;
    private JLabel label2;
    private JButton restart;
    private int letra;

    public keyListener() {
        // Configurar el JFrame
        setTitle("Ejemplo de KeyListener");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Crear el campo de texto
        textField = new JTextField(20);
        textField.addKeyListener(this);  // Añadir el KeyListener al JTextField


        // Crear una etiqueta para mostrar las teclas presionadas
        label = new JLabel("Presiona una tecla...");
        label2 = new JLabel("Teclas: ");
        restart = new JButton("Restart");

        // Añadir los componentes al frame
        add(textField);
        add(label);
        add(label2);
        add(restart);

        // Hacer visible la ventana
        setVisible(true);

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                letra = 0;
                label2.setText("Teclas: 0");
            }
        });
    }



    // Método que se invoca cuando se presiona una tecla
    @Override
    public void keyPressed(KeyEvent e) {
        label.setText("Tecla presionada: " + KeyEvent.getKeyText(e.getKeyCode()));

    }

    // Método que se invoca cuando se libera una tecla
    @Override
    public void keyReleased(KeyEvent e) {
        label.setText("Tecla liberada: " + KeyEvent.getKeyText(e.getKeyCode()));
        letra++;
        label2.setText("Teclas: " + letra);
    }

    // Método que se invoca cuando se escribe un carácter
    @Override
    public void keyTyped(KeyEvent e) {

        // Este método se usa cuando se escribe un carácter (letras, números, etc.)
        // No se maneja la tecla modificadora (Shift, Ctrl, etc.)
    }

    public static void main(String[] args) {
        // Crear la ventana del ejemplo
        new keyListener();
    }
}

