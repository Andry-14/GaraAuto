import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestioneFile {
    private static final String FILE_NAME = "podio.txt";

    public static void salvaPodio(List<String> podio) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String posizione : podio) {
                writer.println(posizione);
            }
            System.out.println("Podio salvato su '" + FILE_NAME + "'");
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    public static List<String> leggiPodio() {
        List<String> podio = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                podio.add(line.trim());
            }
            System.out.println("Podio letto da '" + FILE_NAME + "'");
        } catch (IOException e) {
            System.err.println("Errore durante la lettura: " + e.getMessage());
        }
        return podio;
    }
}