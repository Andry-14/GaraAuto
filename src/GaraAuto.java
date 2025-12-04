import java.util.ArrayList;
import java.util.List;

public class GaraAuto {
    public static double distanzaGara = 10.0; // 10 km
    private List<Auto> autoList = new ArrayList<>();
    private Giudice giudice = new Giudice();
    private List<Thread> threadList = new ArrayList<>();

    
    public GaraAuto() {
        
        aggiungiAuto(new Auto(1, 180.0, giudice));
        aggiungiAuto(new Auto(2, 200.0, giudice));
        aggiungiAuto(new Auto(3, 190.0, giudice));
        aggiungiAuto(new Auto(4, 210.0, giudice));
        aggiungiAuto(new Auto(5, 170.0, giudice));
        aggiungiAuto(new Auto(6, 205.0, giudice));
        aggiungiAuto(new Auto(7, 175.0, giudice));
    }

    public void avviaGara() {
        System.out.println("GARA INIZIATA! Distanza: " + distanzaGara + " km");
        System.out.println("Partecipanti: " + autoList.size() + " auto");

        for (Auto auto : autoList) {
            Thread t = new Thread(auto);
            threadList.add(t);
            t.start();
        }

        
        Thread monitor = new Thread(() -> {
            while (true) {
                boolean tutteFinite = true;
                System.out.println("\n--- CLASSIFICA PARZIALE ---");
                for (Auto auto : autoList) {
                    double dist = auto.getDistanzaPercorsa();
                    String stato = auto.getNumero() + ": " + String.format("%.2f", dist) + " km";
                    if (dist < distanzaGara) {
                        tutteFinite = false;
                        stato += " [IN CORSO]";
                    } else {
                        stato += " [FINITA]";
                    }
                    System.out.println(stato);
                }
                System.out.println("-----------------------------");

                if (tutteFinite) break;

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        monitor.start();

        
        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n GARA TERMINATA");
    }

    public void mostraPodio() {
        giudice.verifica();
    }

    public void aggiungiAuto(Auto auto) {
        autoList.add(auto);
    }

    public Giudice getGiudice() {
        return giudice;
    }

    
    public static void main(String[] args) {
        GaraAuto gara = new GaraAuto();  
        gara.avviaGara();
        gara.mostraPodio();

       
        GestioneFile.salvaPodio(gara.getGiudice().getPodio());
        System.out.println("\n📌 Podio letto dal file:");
        GestioneFile.leggiPodio().forEach(System.out::println);
    }
}