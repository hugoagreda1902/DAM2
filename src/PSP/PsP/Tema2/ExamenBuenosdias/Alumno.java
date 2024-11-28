public class Alumno extends Thread{

    String nombre;
    Bean b;


    public Alumno(String nombre, Bean b) {
        this.nombre = nombre;
        this.b = b;
    }

    public void run(){

        synchronized (b){
            try {
                System.out.println("Espero a que Salude el profesor " + " ID: "+Thread.currentThread().getId());
                b.esperan ++;
                if (b.esperan == 9) {
                    b.saludo = false;
                }
                b.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (b) {
                b.numeroSaludos++;
                System.out.println("Morgen Ich bin " + nombre +" ID: " + Thread.currentThread().getId());
                System.out.println("Me siento en mi silla, que pase el siguiente " + Thread.currentThread().getId());
            }

        }



    }
}
