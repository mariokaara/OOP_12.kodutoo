import java.util.ArrayList;
import java.util.List;


class Võidujooks implements Runnable {

    private List<Integer> list;
    private int arv;

    public Võidujooks(List<Integer> list, int arv) {
        this.list=list;
        this.arv=arv;
    }

    public void run() {
        synchronized (list) {
            for (int i = 0; i < arv; i++) {
                list.add(i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        Thread t1 = new Thread(new Võidujooks(list, 1000000));
        Thread t2 = new Thread(new Võidujooks(list, 1000000));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(list.size());

    }
}