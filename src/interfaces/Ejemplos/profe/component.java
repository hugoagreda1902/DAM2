package interfaces.Ejemplos.profe;

import javax.swing.*;
import java.awt.*;

public class component {

    public static void main(String[] args) {
        // Crear el JFrame
        JFrame frame = new JFrame("Ejemplo de componentes");

        // Configurar el GridLayout: 6 filas, 2 columnas
        frame.setLayout(new GridLayout(6, 2, 10, 10));

        // Crear los componentes
        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField(15);

        JLabel genderLabel = new JLabel("Género:");
        JRadioButton maleRadioButton = new JRadioButton("Masculino");
        JRadioButton femaleRadioButton = new JRadioButton("Femenino");

        // Agrupar los botones de radio
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        JLabel languageLabel = new JLabel("Lenguajes de programación:");
        JCheckBox javaCheckBox = new JCheckBox("Java");
        JCheckBox pythonCheckBox = new JCheckBox("Python");
        JCheckBox jsCheckBox = new JCheckBox("JavaScript");

        JLabel countryLabel = new JLabel("País:");
        JComboBox<String> countryComboBox = new JComboBox<>(new String[] { "España", "México", "Argentina", "Chile" });

        JButton submitButton = new JButton("Enviar");

        // Añadir los componentes al JFrame
        frame.add(nameLabel);
        frame.add(nameField);

        frame.add(genderLabel);

        // Panel para agrupar los radio buttons
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        frame.add(genderPanel);

        frame.add(languageLabel);

        // Panel para agrupar los checkboxes
        JPanel languagePanel = new JPanel();
        languagePanel.add(javaCheckBox);
        languagePanel.add(pythonCheckBox);
        languagePanel.add(jsCheckBox);
        frame.add(languagePanel);

        frame.add(countryLabel);
        frame.add(countryComboBox);

        frame.add(new JLabel());  // Espacio vacío
        frame.add(submitButton);

        // Configurar el tamaño del JFrame
        frame.setSize(400, 300);

        // Configurar el cierre del JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hacer visible la ventana
        frame.setVisible(true);
    }
}
