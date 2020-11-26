package buildings.threads;

import buildings.Floor;

public class Cleaner extends Thread{
    private Floor _floor;

    public Cleaner(Floor floor) {
        _floor = floor;
    }

    @Override
    public void run() {
        for (int i = 0; i < _floor.getSpacesNum(); i++) {
            System.out.println(Thread.currentThread().getName() + " Cleaning room number " + i
                    + " with total area " + _floor.getSpace(i).getSquare() + " square meters");
        }
        System.out.println("End of Cleaner");
    }
}
