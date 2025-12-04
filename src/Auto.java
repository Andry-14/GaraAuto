import java.util.Random;

public class Auto implements Runnable {
    private int numero;
    private double velocita;
    private double distanzaPercorsa;
    private boolean inBox;
    private Giudice giudice;
    private Random random = new Random();

    public Auto(int numero, double velocita, Giudice giudice) {
        this.numero = numero;
        this.velocita = velocita;
        this.distanzaPercorsa = 0.0;
        this.inBox = false;
        this.giudice = giudice;
    }

    @Override
    public void run() {
        while (distanzaPercorsa < GaraAuto.distanzaGara) {
            try {
                Thread.sleep(100); 

                
                if (random.nextDouble() < 0.002) {
                    System.out.println("🔧 Auto " + numero + " entra in pit stop!");
                    pitStop();
                    System.out.println("✅ Auto " + numero + " esce dal pit stop.");
                }

                if (!inBox) {
                    distanzaPercorsa += velocita / 3600.0 * 0.1; // km in 0.1s
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        giudice.aggiungiAlPodio("Auto " + numero);
    }

    public void pitStop() {
        inBox = true;
        int durata = 3000 + random.nextInt(5000);
        try {
            Thread.sleep(durata);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            inBox = false;
        }
    }

    public double getDistanzaPercorsa() {
        return distanzaPercorsa;
    }

    public int getNumero() {
        return numero;
    }
}