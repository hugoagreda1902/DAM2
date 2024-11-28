// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Bean b = new Bean();
        Profesor p1 = new Profesor(b);
        Alumno a1 = new Alumno("Paco",b);
        Alumno a2 = new Alumno("Adolfo",b);
        Alumno a3= new Alumno("Pablo",b);
        Alumno a4 = new Alumno("Pedro",b);
        Alumno a5 = new Alumno("Mariano",b);
        Alumno a6 = new Alumno("Jesus",b);
        Alumno a7 = new Alumno("Angela",b);
        Alumno a8 = new Alumno("Eins",b);
        Alumno a9 = new Alumno("Pepe",b);
        Alumno a10 = new Alumno("Amador",b);

        a1.start();
        a2.start();
        a3.start();
        a4.start();
        a5.start();
        a6.start();
        a7.start();
        a8.start();
        a9.start();
        a10.start();
        p1.start();

        try {
            a1.join();
            a2.join();
            a3.join();
            a4.join();
            a5.join();
            a6.join();
            a7.join();
            a8.join();
            a9.join();
            a10.join();
            p1.join();


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Wie alt bist du");


    }
}