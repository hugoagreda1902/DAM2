public class Profesor extends Thread{


    Bean b;


    public Profesor(Bean b) {

        this.b = b;
    }



    public void run(){
        while (b.numeroSaludos < 10) {
            if (!b.saludo){
            System.out.println("Guten Morgen die Klasse");
            b.saludo = true;
            }else {
                synchronized (b) {
                    b.notifyAll();
                }
            }
        }
    }
}
