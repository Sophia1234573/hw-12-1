import java.util.concurrent.*;

public class Process {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println(" reaction done");
        });

        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(2);
        WaterGenerator waterGenerator = new WaterGenerator(cyclicBarrier, semaphore1, semaphore2);

        while (!Thread.interrupted()) {
            new Thread(() -> waterGenerator.releaseHydrogen()).start();
            new Thread(() -> waterGenerator.releaseOxygen()).start();
        }
    }
}
