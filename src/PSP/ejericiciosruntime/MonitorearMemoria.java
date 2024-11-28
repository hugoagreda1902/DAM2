package ejericiciosruntime;

public class MonitorearMemoria {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Memoria libre antes de la asignación: " + runtime.freeMemory() + " bytes");

        int[] array = new int[1000000]; // Asignación de memoria
        System.out.println("Memoria libre después de la asignación: " + runtime.freeMemory() + " bytes");

        array = null;
        runtime.gc(); // Solicitar al recolector de basura

        System.out.println("Memoria libre después de GC: " + runtime.freeMemory() + " bytes");
    }
}
