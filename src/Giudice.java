import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Giudice {
    private List<String> podio = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();

    public synchronized void aggiungiAlPodio(String nomeAuto) {
        lock.lock();
        try {
            if (podio.size() < 3) {
                podio.add(nomeAuto);
            }
        } finally {
            lock.unlock();
        }
    }

    public List<String> getPodio() {
        return new ArrayList<>(podio);
    }

    public void verifica() {
        System.out.println("\n--- VERIFICA PODIO ---");
        if (podio.isEmpty()) {
            System.out.println("Nessuna auto ha completato la gara.");
        } else {
            for (int i = 0; i < podio.size(); i++) {
                System.out.println((i + 1) + "° posto: " + podio.get(i));
            }
        }
    }
}