package buildings.dwelling;

import buildings.exceptions.InvalidRoomsCountException;
import buildings.exceptions.InvalidSpaceAreaException;
import buildings.Space;

import java.io.Serializable;

public class Flat implements Space, Serializable, Cloneable {

    private double _square;
    private int _rooms;

    final int SQUARE = 50;
    final int ROOMS = 2;

    public void throwSquare(double square) throws InvalidSpaceAreaException {
        if(square < 20.0 || square > 500.0) throw new InvalidSpaceAreaException(square);
    }

    public void throwRooms(int rooms) throws InvalidRoomsCountException {
        if(rooms < 1 || rooms > 10) throw new InvalidRoomsCountException(rooms);
    }

    public Flat(){
        _square = SQUARE;
        _rooms = ROOMS;
    }
    public Flat(double square){
        throwSquare(square);
        _square = square;
        _rooms = ROOMS;
    }

    public Flat(double square, int rooms){
        throwSquare(square);
        throwRooms(rooms);
        _square = square;
        _rooms = rooms;
    }
    public int getRooms(){
        return _rooms;
    }
    public double getSquare(){
        return _square;
    }
    public void setRooms(int set){
        throwRooms(set);
        _rooms = set;
    }
    public void setSquare(double set){
        throwSquare(set);
        _square = set;
    }

    public String toString(){
        String res = "Flat (Rooms: " + _rooms + ", Square: " + _square + ")";
        return res;
    }

    public boolean equals(Object object){
        if(object == this) return true;
        if(object == null || object.getClass() != getClass()) return false;
        Flat tmp = (Flat)object;
        boolean flag = false;
        if(tmp.getRooms() == getRooms() && tmp.getSquare() == getSquare())
            flag = true;
        return flag;
    }

    public int hashCode(){
        int result;
        long doubleAsLong = Double.doubleToLongBits(_square);
        long mask1 = 0b0000000000000000000000000000000011111111111111111111111111111111L;
        long mask2 = 0b1111111111111111111111111111111100000000000000000000000000000000L;
        long a = doubleAsLong & mask1;
        long b = doubleAsLong & mask2;
        result = (int)(_rooms ^ a ^ b);
        return result;
    }

    public Object clone(){
        Object result = null;
        try {
            result = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int compareTo(Space space) {
        if(_square < space.getSquare())return -1;
        if(_square > space.getSquare())return 1;
        return 0;
    }

    public void print(){
        System.out.println("Flat");
        System.out.println(" Rooms: " + _rooms);
        System.out.println(" Square: " + _square + "\n");
    }
}
