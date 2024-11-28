import java.io.IOException;

public class ejemplo1Runtime {
    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("notepad.exe");
            process.waitFor();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long freeMemory = runtime.freeMemory();
        System.out.println("Memoria libre: " + freeMemory + " bytes");
        int process1 = runtime.availableProcessors();
        System.out.println("procesadores disponibles " +process1);

    }
}
