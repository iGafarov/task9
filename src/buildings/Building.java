package buildings;

public interface Building extends Iterable<Floor>{
    int getFloorsNum();
    int getGenSpacesNum();
    double getGenSquare();
    int getGenRooms();
    Floor[] getFloorsArr();
    Floor getFloor(int num);
    void changeFloor(int num, Floor floor);
    Space getSpace(int num);
    void changeSpace(int num, Space space);
    void addSpace(int num, Space space);
    void removeSpace(int num);
    Space getBestSpace();
    Space[] getSortSpaces();
    Object clone();
    void print();
}
