package interfaces.Practica3ExtraGood;

import javax.swing.*;

public class VentanaListaEmpleados extends JDialog {

    public VentanaListaEmpleados(JFrame parentFrame) {
        super(parentFrame, "Lista de Empleados", true);

        String[] columnas = {"Nombre", "Apellido", "Departamento", "Actividad"};
        String[][] datos = VentanaAgregarEmpleado.empleados.toArray(new String[0][]);

        JTable tablaEmpleados = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);

        add(scrollPane);
        setSize(400, 200);
    }
}
