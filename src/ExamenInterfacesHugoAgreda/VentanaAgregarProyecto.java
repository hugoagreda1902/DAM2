package ExamenInterfacesHugoAgreda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class VentanaAgregarProyecto extends JDialog {

    public static ArrayList<String[]> proyectos = new ArrayList<>();

    public VentanaAgregarProyecto(JFrame parentFrame) {
        super(parentFrame, "Agregar proyecto", true);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Aquí empezamos a añadir los campos que tiene que rellenar el usuario para que el proyecto se agrege correctamente
        JLabel nombre = new JLabel("Nombre:");
        add(nombre);
        // Este campo es obligatorio para el usuario
        JTextField textonombre = new JTextField(15);
        add(textonombre);

        // Este es el segundo parámetro que debe ingresar el usuario
        JLabel descripcion = new JLabel("Descripción:");
        add(descripcion);

        // No obstante este campo no es obligatorio
        JTextField textodescripcion = new JTextField(100);
        add(textodescripcion);

        // Seguimos con el campo de fecha, el cual es imprescindible para saber cuando se realizó el proyecto
        JLabel fecha = new JLabel("Fecha:");
        add(fecha);

        // Este campo también es obligatorio
        JTextField textofecha = new JTextField(15);
        add(textofecha);

        // Vamos con el último, aquí el usuario puede elegir entre 3 opciones (no es obligatorio)
        JLabel textodepartamento = new JLabel("Departamento:");
        add(textodepartamento);

        JComboBox<String> tiposDepartamento = new JComboBox<>(new String[]{"Móvil", "PC", "Web"});
        add(tiposDepartamento);

        // Este botón permite que los tatos introducidos se almacenen en un Array, sólo funciona si en los campos "nombre" y "fecha" hay datos
        JButton agregar = new JButton("Agregar");
        agregar.addActionListener(e -> {
            if (textonombre.getText().isEmpty() || textofecha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre y la fecha de inicio son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                proyectos.add(new String[]{textonombre.getText(), textofecha.getText()});
                JOptionPane.showMessageDialog(this, "Proyecto agregado exitosamente");
                dispose();
            }
        });

        add(agregar);
        setSize(500, 300);
    }
}