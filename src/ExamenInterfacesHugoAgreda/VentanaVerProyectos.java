package ExamenInterfacesHugoAgreda;

import javax.swing.*;

public class VentanaVerProyectos extends JDialog {
    public VentanaVerProyectos(JFrame parentFrame) {
        String[] columnas = {"Nombre","Fecha"};
        // Aqui se transforman los datos, en un Array para su salida
        String[][] datos = VentanaAgregarProyecto.proyectos.toArray(new String[0][]);

        JTable tablaEmpleados = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);

        add(scrollPane);

        /*
        TODO: Lo que he echo aqui es añadir un botón te cuando le dad, te sale un mensaje de confirmación, pero lo que me sucede es,
         cuando lo meto en el código, sólo me aparece el botón y no la tabla.

        JButton confirmacion = new JButton("Confirmación");

        confirmacion.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "¿Estas seguro?");
        });
        add(confirmacion);
        */


        setSize(500, 300);
    }
}
