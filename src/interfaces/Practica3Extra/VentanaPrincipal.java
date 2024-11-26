package interfaces.Practica3Extra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;


public class VentanaPrincipal {

    private static ArrayList<String[]> empleados = new ArrayList<>();

    public static void main(String[] args) {
        // Crear el JFrame (ventana principal)
        JFrame frame = new JFrame("Gestión de empleados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Configurar el tamaño del JFrame
        frame.setSize(500, 300);

        // Crear y añadir el primer botón
        JButton agregarEmpleados = new JButton("Agregar");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.getContentPane().add(agregarEmpleados, gbc);

        // Acción para mostrar el diálogo cuando se haga clic en el botón "pagar"
        agregarEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al método para mostrar el diálogo
                VentanaAgredarEmpleado(frame);
            }
        });

        // Crear y añadir el segundo botón
        JButton listaEmp = new JButton("Lista");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.getContentPane().add(listaEmp, gbc);

        listaEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al método para mostrar el diálogo
                JCheckBoxPersonalizado(frame);
            }
        });

        // Crear y añadir el tercer botón
        JButton salir = new JButton("Salir");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.getContentPane().add(salir, gbc);

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cerrar el diálogo
            }
        });

        // Crear y añadir una etiqueta
        JLabel texto = new JLabel("¿Que quieres hacer?");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.getContentPane().add(texto, gbc);

        // Hacer visible el JFrame
        frame.setVisible(true);
    }

    private static void VentanaAgredarEmpleado(JFrame parentFrame) {
        // Crear el JDialog de Confrmación
        JDialog dialog = new JDialog(parentFrame, "Confirmación", true);
        // "true" hace que el diálogo sea modal (bloquea la ventana principal)

        // Configurar el tamaño del diálogo

        // Configurar el GridLayout: 6 filas, 2 columnas
        dialog.setLayout(new GridLayout(7, 2, 10, 10));
        JLabel nombre = new JLabel("Nombre:");
        JTextField textonombre = new JTextField(15);

        JLabel apellido = new JLabel("Apellido:");
        JTextField textoapellido = new JTextField(15);

        dialog.add(nombre);
        dialog.add(textonombre);

        dialog.add(apellido);
        dialog.add(textoapellido);

        JLabel departamneto = new JLabel("Departamento:");
        JComboBox<String> tiposDepartamento = new JComboBox<>(new String[] { "Ventas", "IT", "Recursos Humanos"});

        dialog.add(departamneto);
        dialog.add(tiposDepartamento);

        JLabel actividadLabel = new JLabel("Actividad:");
        JCheckBox activoCheckBox = new JCheckBox("Activo");
        JCheckBox noActivoCheckBox = new JCheckBox("No activo");

        activoCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                activoCheckBox.setBackground(Color.GREEN); // Color verde cuando está seleccionado
                noActivoCheckBox.setSelected(false);
            } else {
                activoCheckBox.setBackground(null); // Vuelve al color original cuando se deselecciona
            }
        });

        noActivoCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                noActivoCheckBox.setBackground(Color.RED);// Color rojo cuando está seleccionado
                activoCheckBox.setSelected(false);
            } else {
                noActivoCheckBox.setBackground(null); // Vuelve al color original cuando se deselecciona
            }
        });

        dialog.add(actividadLabel);

        // Panel para agrupar los checkboxes
        JPanel actividadPanel = new JPanel();
        actividadPanel.add(activoCheckBox);
        actividadPanel.add(noActivoCheckBox);
        dialog.add(actividadPanel);

        JButton enviar= new JButton("Enviar");

        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textonombre.getText().equals("")) {
                    JOptionPane.showMessageDialog(parentFrame,"Falta el nombre por ingresar","Error", JOptionPane.ERROR_MESSAGE);
                } else if (textoapellido.getText().equals("")) {
                    JOptionPane.showMessageDialog(parentFrame,"Falta el apellido por ingresar","Error", JOptionPane.ERROR_MESSAGE);
                } else if (!activoCheckBox.isSelected() && !noActivoCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(parentFrame,"Falta actividad por seleccionar","Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String actividad = activoCheckBox.isSelected() ? "Activo" : "No activo";
                    empleados.add(new String[]{textonombre.getText(), textoapellido.getText(), (String) tiposDepartamento.getSelectedItem(), actividad});
                    JOptionPane.showMessageDialog(parentFrame,"Has enviado los datos");
                    dialog.dispose(); // Cerrar el diálogo
                }
            }
        });

        dialog.add(enviar);

        dialog.setSize(500, 300);
        dialog.setVisible(true);
    }

    private static void JCheckBoxPersonalizado (JFrame parentFrame) {
        // Crear datos para JTable a partir de ArrayList
        String[] columnas = {"Nombre", "Apellido", "Departamento", "Actividad"};
        String[][] datos = empleados.toArray(new String[0][]);

        // Crear la JTable y mostrarla en un JScrollPane
        JTable tablaEmpleados = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);

        // Crear un JDialog para mostrar la tabla
        JDialog dialog = new JDialog(parentFrame, "Lista de Empleados", true);
        dialog.add(scrollPane);
        dialog.setSize(400, 200);
        dialog.setVisible(true);
    }

}
