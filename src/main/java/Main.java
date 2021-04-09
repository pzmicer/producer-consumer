import java.util.ArrayList;

public class Main {

    private static final ArrayList<Thread> producers = new ArrayList<>();
    private static final ArrayList<Thread> consumers = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        ListMedianTask task = new ListMedianTask(4);

        createProducers(task, 3, new int[]{1, 1, 1});
        createConsumers(task, 2);

        startProducers();
        startConsumers();

        joinProducers();
        joinConsumers();
    }

    public static void createProducers(ListMedianTask task, int number, int[] delays) {
        for (int i = 0; i < number; i++) {
            producers.add(new Thread(new Producer(task, delays[i])));
        }
    }
    public static void createConsumers(ListMedianTask task, int number) {
        for (int i = 0; i < number; i++) {
            consumers.add(new Thread(new Consumer(task)));
        }
    }

    public static void startProducers() {
        for(var producer : producers)
            producer.start();
    }

    public static void startConsumers() {
        for(var consumer : consumers)
            consumer.start();
    }

    public static void joinProducers() throws InterruptedException {
        for(var producer : producers)
            producer.join();
    }

    public static void joinConsumers() throws InterruptedException {
        for(var consumer : consumers)
            consumer.join();
    }
}
