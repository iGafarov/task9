package buildings;

import buildings.*;

public interface Space extends Comparable<Space> {

    double getSquare();
    void setSquare(double set);
    int getRooms();
    void setRooms(int set);
    Object clone();
    void print();
}
