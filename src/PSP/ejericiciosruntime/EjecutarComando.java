package ejericiciosruntime;

public class EjecutarComando {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("notepad"); // Windows
            // runtime.exec("gedit"); // Linux
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
