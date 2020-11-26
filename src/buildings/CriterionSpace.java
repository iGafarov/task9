package buildings;

import java.util.Comparator;

public class CriterionSpace implements Comparator<Space> {

    @Override
    public int compare(Space o1, Space o2) {
        if(o1.getRooms() < o2.getRooms()) return 1;
        if(o1.getRooms() > o2.getRooms()) return -1;
        return 0;
    }

}
