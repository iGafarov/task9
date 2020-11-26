package buildings.threads;

import buildings.Floor;

public class Repairer extends Thread{
    private Floor _floor;

    public Repairer(Floor floor) {
        _floor = floor;
    }

    @Override
    public void run() {
        for (int i = 0; i < _floor.getSpacesNum(); i++) {
            System.out.println(Thread.currentThread().getName() + " Repairing space number " + i
                    + " with total area " + _floor.getSpace(i).getSquare() + " square meters");
        }
        System.out.println("End of Repairer");
    }
}
