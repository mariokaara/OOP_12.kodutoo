import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;

public class SummaArvutaja implements Runnable {

    private BlockingQueue<String> failinimed;
    private BlockingQueue<Integer> summad;

    public SummaArvutaja(
            BlockingQueue<String> failinimed,
            BlockingQueue<Integer> summad) {
        this.failinimed = failinimed;
        this.summad = summad;
    }

    public int summaArvutaja(String failinimi) {
        int summa = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(failinimi)))) {
            String rida = br.readLine();
            while (rida != null) {
                String[] arvudSõneMassiivis = rida.split(" ");
                for (int i = 0; i < arvudSõneMassiivis.length; i++) {
                    int arvuna = Integer.parseInt(arvudSõneMassiivis[i]);
                    summa += arvuna;
                }
                rida = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return summa;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String failinimi = failinimed.poll();
                if (failinimi == null)
                    break;
                int summa = summaArvutaja(failinimi);
                summad.put(summa);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}