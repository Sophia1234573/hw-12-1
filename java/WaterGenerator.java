import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class WaterGenerator {

    private CyclicBarrier cyclicBarrier;
    private Semaphore semaphore1;
    private Semaphore semaphore2;

    public WaterGenerator(CyclicBarrier cyclicBarrier, Semaphore semaphore1, Semaphore semaphore2) {
        this.cyclicBarrier = cyclicBarrier;
        this.semaphore1 = semaphore1;
        this.semaphore2 = semaphore2;
    }

    public void releaseHydrogen() {
        try {
            semaphore2.acquire();
            Thread.sleep(2000);
            if (semaphore1.availablePermits() == 0 && semaphore2.availablePermits() == 0) {
                System.out.print("H");
                cyclicBarrier.await();
            }
            semaphore2.release();

            } catch(InterruptedException | BrokenBarrierException e){
                e.printStackTrace();
            }
        }

    public void releaseOxygen() {
        try {
            semaphore1.acquire();
            Thread.sleep(2000);
            if (semaphore1.availablePermits() == 0 && semaphore2.availablePermits() == 0) {
                System.out.print("O");
                cyclicBarrier.await();
            }
            semaphore1.release();

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}


