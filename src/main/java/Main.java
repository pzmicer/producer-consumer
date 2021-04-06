import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ListMedianTask task = new ListMedianTask();

        Thread p1 = new Thread(new Producer(task, 3));
        Thread p2 = new Thread(new Producer(task, 1));
        Thread c1 = new Thread(new Consumer(task));

        p1.start();
        p2.start();
        c1.start();

        p1.join();
        p2.join();
        c1.join();
    }
}
