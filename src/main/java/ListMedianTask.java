import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
public class ListMedianTask {
    private final ArrayDeque<ArrayList<Integer>> queue = new ArrayDeque<>();
    private int produced_k = 1;
    private int consumed_k = 1;

    public void produce(int producerNumber, int sleep) throws InterruptedException {
        while(true) {
            synchronized (this) {
                queue.add(getRandomList());
                System.out.printf("Producer %d produced - %d%n", producerNumber, produced_k++);
                notify();
            }
            Thread.sleep(sleep * 1000L);
        }
    }

    public void consume(int consumerNumber) throws InterruptedException {
        while(true) {
            synchronized (this) {
                while(queue.isEmpty())
                    wait();
                var list = queue.poll();
                double med = median(list);
                System.out.printf("Consumer %d consumed - %d (for list %s median = %.1f)%n", consumerNumber, consumed_k++, list, med);
            }
        }
    }

    private ArrayList<Integer> getRandomList() {
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();
        int size = rand.nextInt(10) + 1;
        for(int i = 0; i < size; i++)
            list.add(rand.nextInt(20));
        return list;
    }

    private double median(ArrayList<Integer> list) {
        list.sort(Comparator.naturalOrder());
        if (list.size() %  2 == 1)
            return list.get(list.size() / 2);
        return (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2.0;
    }
}
