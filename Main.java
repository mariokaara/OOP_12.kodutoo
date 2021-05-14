import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    private static BlockingQueue<String> failiGeneraator(int failideArv) throws IOException {
        BlockingQueue<String> list = new ArrayBlockingQueue<>(failideArv);
        for (int i = 0; i < failideArv; i++) {
            String failinimi = "fail" + (i + 1) + ".txt";
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(failinimi)))) {
                for (int j = 0; j < 100; j++) {
                    int minimum = 1;
                    int maximum = 10;
                    int randomNum = minimum + (int) (Math.random() * maximum);
                    bw.write(randomNum + " ");
                }
                list.add(failinimi);
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Mitu algfaili soovid genereerida? ");
        Integer failideArv = sc.nextInt();
        BlockingQueue<String> failid = failiGeneraator(failideArv);
        BlockingQueue<Integer> summad = new ArrayBlockingQueue<>(failideArv);

        Thread t1 = new Thread(new SummaArvutaja(failid, summad));
        Thread t2 = new Thread(new SummaArvutaja(failid, summad));
        Thread t3 = new Thread(new SummaArvutaja(failid, summad));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        for (int i = 0; i < failideArv; i++) {
            System.out.println(summad.poll());
        }
    }
}
