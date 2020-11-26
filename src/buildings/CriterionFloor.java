package buildings;

import java.util.Comparator;

public class CriterionFloor implements Comparator<Floor> {

    @Override
    public int compare(Floor o1, Floor o2) {
        if(o1.getFloorSquare() < o2.getFloorSquare()) return 1;
        if(o1.getFloorSquare() > o2.getFloorSquare()) return -1;
        return 0;
    }

}
