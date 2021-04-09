import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Consumer implements Runnable {
    private final ListMedianTask task;
    private static int ID = 1;
    private final int id = ID++;

    @Override
    public void run() {
        try {
            task.consume(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}