package buildings.threads;

import buildings.Floor;
import buildings.Semaphore;

public class SequentalCleaner implements Runnable{

    private Floor _floor;
    private Semaphore _semaphore;

    public SequentalCleaner(Semaphore semaphore, Floor floor){
        _semaphore = semaphore;
        _floor = floor;
    }

    @Override
    public synchronized void run() {
        try {
            _semaphore.acquire();
            for (int i = 0; i < _floor.getSpacesNum(); ++i) {
                System.out.println(Thread.currentThread().getName() +" : Cleaning room number " + i + " with total area "
                        + _floor.getSpace(i).getSquare() + " square meters");
                Thread.sleep(1000);
            }
            System.out.println("End of Cleaner");
            _semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
