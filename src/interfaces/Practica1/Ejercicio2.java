package interfaces.Practica1;
import javax.swing.*;
import java.awt.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 5)); // 4 rows, 4 columns

        // Crear los botones numéricos
        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");

        // Crear los botones de operaciones
        JButton mas = new JButton("+");
        JButton menos = new JButton("-");
        JButton multiplicador = new JButton("*");
        JButton division = new JButton("/");
        JButton igual = new JButton("=");
        JButton porcentaje = new JButton("%");
        JButton ce = new JButton("CE");
        JButton c = new JButton("C");
        JButton UNOentreX = new JButton("1/x");
        JButton MasMenos = new JButton("+/-");
        JButton coma = new JButton(",");

        frame.add(porcentaje);
        frame.add(ce);
        frame.add(c);
        frame.add(UNOentreX);
        frame.add(button7);
        frame.add(button8);
        frame.add(button9);
        frame.add(multiplicador);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(menos);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(mas);
        frame.add(MasMenos);
        frame.add(button0);
        frame.add(coma);
        frame.add(igual);

        // Mostrar la ventana principal
        frame.pack();
        frame.setVisible(true);
    }
}