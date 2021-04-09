import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Producer implements Runnable {
    private final ListMedianTask task;
    private static int ID = 1;
    private final int id = ID++;
    private final int n;

    @Override
    public void run() {
        try {
            task.produce(id, n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}