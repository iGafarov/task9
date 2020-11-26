package buildings;

import java.util.Iterator;
import java.util.concurrent.Semaphore;

public class SynchronizedFloor implements Floor{

    private Floor floorDecorator;

    SynchronizedFloor(Floor floor){
        floorDecorator = floor;
    }

    @Override
    public synchronized int getSpacesNum() {
        return floorDecorator.getSpacesNum();
    }

    @Override
    public synchronized double getFloorSquare() {
        return floorDecorator.getFloorSquare();
    }

    @Override
    public synchronized int getFloorRooms() {
        return floorDecorator.getFloorRooms();
    }

    @Override
    public synchronized Space[] getSpacesArr() {
        return floorDecorator.getSpacesArr();
    }

    @Override
    public synchronized Space getSpace(int num) {
        return floorDecorator.getSpace(num);
    }

    @Override
    public synchronized void changeSpace(int num, Space space) {
        floorDecorator.changeSpace(num,space);
    }

    @Override
    public synchronized void addSpace(int num, Space space) {
        floorDecorator.addSpace(num, space);
    }

    @Override
    public synchronized void removeSpace(int num) {
        floorDecorator.removeSpace(num);
    }

    @Override
    public synchronized Space getBestSpace() {
        return floorDecorator.getBestSpace();
    }

    @Override
    public synchronized Object clone() {
        return floorDecorator.clone();
    }

    @Override
    public synchronized void print() {
        floorDecorator.print();
    }
    public synchronized void print(int floor_index, int start_space_index){
        floorDecorator.print(floor_index, start_space_index);
    }

    @Override
    public synchronized int compareTo(Floor o) {
        return floorDecorator.compareTo(o);
    }

    @Override
    public Iterator<Space> iterator() {
        return floorDecorator.iterator();
    }
}
