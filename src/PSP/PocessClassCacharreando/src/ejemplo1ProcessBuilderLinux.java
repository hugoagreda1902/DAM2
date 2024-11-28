import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ejemplo1ProcessBuilderLinux {
    public static void main(String[] args) {

        try {
            ProcessBuilder pb = new ProcessBuilder("wsl","ls", "-l","/home/ck");
            Process process = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            System.out.println("Archivos en directorio actual");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
