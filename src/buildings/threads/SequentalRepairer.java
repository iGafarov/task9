package buildings.threads;

import buildings.Floor;
import buildings.Semaphore;

public class SequentalRepairer implements Runnable{

    private Floor _floor;
    private Semaphore _semaphore;

    public SequentalRepairer(Semaphore semaphore, Floor floor){
        _semaphore = semaphore;
        _floor = floor;
    }

    @Override
    public synchronized void run() {
        try {
            _semaphore.acquire();
            for (int i = 0; i < _floor.getSpacesNum(); ++i) {
                System.out.println(Thread.currentThread().getName() +" : Repairing space number " + i + " with total area "
                        + _floor.getSpace(i).getSquare() + " square meters");
                Thread.sleep(1000);
            }
            System.out.println("End of Repair");
            _semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
