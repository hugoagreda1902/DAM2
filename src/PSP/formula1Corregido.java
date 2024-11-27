package PSP;

class CarreraF1 extends Thread {
    private String piloto; // Variable para almacenar el nombre del piloto

    // Constructor que inicializa el piloto
    public CarreraF1(String piloto) {
        this.piloto = piloto;
    }

    // Método que se ejecuta cuando el hilo comienza
    @Override
    public void run() {
        // Simula las 78 vueltas de la carrera
        for (int vuelta = 1; vuelta <= 78; vuelta++) {
            // Imprime en consola que el piloto completó la vuelta
            System.out.println("Piloto " + piloto + " completó la vuelta " + vuelta);
            try {
                // Simula el tiempo que tarda en completar cada vuelta (de forma aleatoria entre 0 y 500 ms)
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {
                // Si ocurre una interrupción, muestra un mensaje indicando que el piloto tuvo un problema
                System.out.println("El piloto " + piloto + " tuvo un problema.");
            }
        }
        // Imprime que el piloto ha terminado la carrera
        System.out.println("¡El piloto " + piloto + " ha terminado la carrera!");
    }

    // Método main que crea y arranca los hilos para cada piloto
    public static void main(String[] args) {
        // Lista de pilotos que participan en la carrera
        String[] pilotos = {"Hamilton", "Vettel", "Raikkonen", "Alonso", "Sainz Jr", "Bottas", "Vandoorne"};

        // Para cada piloto, se crea un hilo y se inicia
        for (String piloto : pilotos) {
            new CarreraF1(piloto).start(); // Crear un nuevo hilo para cada piloto y arrancarlo
        }
    }
}
