package interfaces.Practica2Solprofe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class main extends JFrame {
    private JTextField textField;
    private JButton button;
    private JTextArea textArea;

    public main() {
        super("Demostración de Listeners");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout());

        // Crear los componentes
        textField = new JTextField(15);
        button = new JButton("Clic aquí");
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        // Añadir los componentes a la ventana
        add(new JLabel("Campo de texto:"));
        add(textField);
        add(button);
        add(new JScrollPane(textArea));

        // Agregar los listeners
        addKeyListenerToTextField();
        addFocusListenerToTextField();
        addActionListenerToButton();
        addMouseListenerToTextArea();

        // Mostrar la ventana
        setVisible(true);
    }

    // Método para añadir KeyListener al campo de texto
    private void addKeyListenerToTextField() {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            //el usuario presiona una tecla
            public void keyPressed(KeyEvent e) {
                textArea.append("Tecla presionada: " + e.getKeyChar() + "\n");
            }

            @Override
            //el usuario libera una tecla
            public void keyReleased(KeyEvent e) {
                textArea.append("Tecla liberada: " + e.getKeyChar() + "\n");
            }
        });
    }

    // Método para añadir FocusListener al campo de texto
    private void addFocusListenerToTextField() {
        textField.addFocusListener(new FocusAdapter() {
            @Override
            //el usuario pincha en la caja de texto
            public void focusGained(FocusEvent e) {
                textArea.append("Campo de texto ha ganado el foco.\n");
            }

            @Override
            //el usuario quita el ratón de la caja de texto
            public void focusLost(FocusEvent e) {
                textArea.append("Campo de texto ha perdido el foco.\n");
            }
        });
    }

    // Método para añadir ActionListener al botón
    private void addActionListenerToButton() {
        button.addActionListener(new ActionListener() {
            @Override

            //el usuario presiona el botón
            public void actionPerformed(ActionEvent e) {
                textArea.append("Botón presionado.\n");
            }
        });
    }

    // Método para añadir MouseListener al área de texto
    private void addMouseListenerToTextArea() {
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            //recoge las coordenadas del click del usuario en la caja de texto
            public void mouseClicked(MouseEvent e) {
                textArea.append("Mouse clic en el área de texto. Coordenadas: (" + e.getX() + ", " + e.getY() + ")\n");
            }
            //el usuario entra en la caja de texto
            @Override
            public void mouseEntered(MouseEvent e) {
                textArea.append("Mouse entró en el área de texto.\n");
            }

            @Override

            //el usuario sale de la caja texto
            public void mouseExited(MouseEvent e) {
                textArea.append("Mouse salió del área de texto.\n");
            }
        });
    }

    public static void main(String[] args) {
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new main();
            }
        });
    }
}
