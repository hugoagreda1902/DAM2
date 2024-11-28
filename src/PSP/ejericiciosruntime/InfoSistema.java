package ejericiciosruntime;

public class InfoSistema {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Número de procesadores disponibles: " + runtime.availableProcessors());
        System.out.println("Memoria total en la JVM: " + runtime.totalMemory() + " bytes");
    }
}
