package buildings;

import java.util.Iterator;

public interface Floor extends Comparable<Floor>, Iterable<Space> {
    int getSpacesNum();
    double getFloorSquare();
    int getFloorRooms();
    Space[] getSpacesArr();
    Space getSpace(int num);
    void changeSpace(int num, Space space);
    void addSpace(int num, Space space);
    void removeSpace(int num);
    Space getBestSpace();
    Object clone();
    void print(int floor_index, int start_space_index);
    void print();
}
