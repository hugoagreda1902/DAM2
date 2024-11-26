package interfaces.Practica3ExtraGood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class VentanaAgregarEmpleado extends JDialog {

    public static ArrayList<String[]> empleados = new ArrayList<>();

    public VentanaAgregarEmpleado(JFrame parentFrame) {
        super(parentFrame, "Agregar Empleado", true);
        setLayout(new GridLayout(7, 2, 10, 10));

        JLabel nombre = new JLabel("Nombre:");
        add(nombre);

        JTextField textonombre = new JTextField(15);
        add(textonombre);

        JLabel apellido = new JLabel("Apellido:");
        add(apellido);

        JTextField textoapellido = new JTextField(15);
        add(textoapellido);

        JLabel textodepartamento = new JLabel("Departamento:");
        add(textodepartamento);

        JComboBox<String> tiposDepartamento = new JComboBox<>(new String[]{"Ventas", "IT", "Recursos Humanos"});
        add(tiposDepartamento);

        JLabel actividadLabel = new JLabel("Actividad:");
        add(actividadLabel);

        JCheckBox activoCheckBox = new JCheckBox("Activo");
        JCheckBox noActivoCheckBox = new JCheckBox("No activo");

        JPanel actividadPanel = new JPanel();
        actividadPanel.add(activoCheckBox);
        actividadPanel.add(noActivoCheckBox);
        add(actividadPanel);

        activoCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                activoCheckBox.setBackground(Color.GREEN);
                noActivoCheckBox.setSelected(false);
            } else {
                activoCheckBox.setBackground(null);
            }
        });

        noActivoCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                noActivoCheckBox.setBackground(Color.RED);
                activoCheckBox.setSelected(false);
            } else {
                noActivoCheckBox.setBackground(null);
            }
        });

        JButton enviar = new JButton("Enviar");
        enviar.addActionListener(e -> {
            if (textonombre.getText().isEmpty() || textoapellido.getText().isEmpty() || (!activoCheckBox.isSelected() && !noActivoCheckBox.isSelected())) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String actividad = activoCheckBox.isSelected() ? "Activo" : "No activo";
                empleados.add(new String[]{textonombre.getText(), textoapellido.getText(), (String) tiposDepartamento.getSelectedItem(), actividad});
                JOptionPane.showMessageDialog(this, "Empleado agregado exitosamente");
                dispose();
            }
        });

        add(enviar);
        setSize(500, 300);
    }
}