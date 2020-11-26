package buildings.dwelling;

import buildings.*;
import buildings.exceptions.FloorIndexOutOfBoundsException;
import buildings.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Iterator;

public class Dwelling implements Building, Serializable, Cloneable {

    private Floor _arr[];
    private int _floorsNum;


    public Dwelling(int floorsNum, int arr_num_of_flats[]){
        _arr = new Floor[floorsNum];
        _floorsNum = floorsNum;
        for(int i = 0; i < floorsNum; ++i){
            _arr[i] = new DwellingFloor(arr_num_of_flats[i]);
        }
    }


    public Dwelling(Floor arr[]){
        _floorsNum = arr.length;
        _arr = new Floor[_floorsNum];
        for(int i = 0; i < _floorsNum; ++i){
            _arr[i] = arr[i];
        }
    }


    public int getFloorsNum(){
        return _floorsNum;
    }


    public int getGenSpacesNum(){
        int sum = 0;
        for(int i = 0; i < _arr.length; ++i){
            sum += _arr[i].getSpacesNum();
        }
        return sum;
    }


    public double getGenSquare(){
        double sum = 0;
        for(int i = 0; i < _arr.length; ++i){
            sum += _arr[i].getFloorSquare();
        }
        return sum;
    }


    public int getGenRooms(){
        int sum = 0;
        for(int i = 0; i < _arr.length; ++i){
            sum += _arr[i].getFloorRooms();
        }
        return sum;
    }


    public Floor[] getFloorsArr(){
        return _arr;
    }


    public Floor getFloor(int num) throws FloorIndexOutOfBoundsException {
        if(num < 0 || num >= getGenSpacesNum()) throw new FloorIndexOutOfBoundsException(num,getGenSpacesNum());

        return _arr[num];
    }


    public void changeFloor(int num, Floor iFloor) throws FloorIndexOutOfBoundsException {
        if(num < 0 || num >= getGenSpacesNum()) throw new FloorIndexOutOfBoundsException(num,getGenSpacesNum());
        Floor floor = iFloor;
        _arr[num] = floor;
    }


    public Space getSpace(int num) throws SpaceIndexOutOfBoundsException {
        if(num < 0 || num >= getGenSpacesNum()) throw new SpaceIndexOutOfBoundsException(num,getGenSpacesNum());

        Space space = new Flat();
        int space_number = 0;
        for(int i = 0; i < _floorsNum; ++i){
            for(int j = space_number; j < space_number + _arr[i].getSpacesNum(); ++j){
                if(j == num){
                    space = _arr[i].getSpace(j - space_number);
                }
            }
            space_number += _arr[i].getSpacesNum();
        }
        return space;
    }


    public void changeSpace(int num, Space space) throws SpaceIndexOutOfBoundsException {
        if(num < 0 || num > getGenSpacesNum()) {
            throw new SpaceIndexOutOfBoundsException(num,getGenSpacesNum() + 1);
        }
        int space_number = 0;
        for(int i = 0; i < _floorsNum; ++i){
            for(int j = space_number; j < space_number + _arr[i].getSpacesNum(); ++j){
                if(j == num){
                    _arr[i].changeSpace(j-space_number,space);
                }
            }
            space_number += _arr[i].getSpacesNum();
        }
    }


    public void addSpace(int num, Space space) throws SpaceIndexOutOfBoundsException {
        if(num < 0 || num > getGenSpacesNum()) throw new SpaceIndexOutOfBoundsException(num,getGenSpacesNum() + 1);

        int space_number = 0;
        for(int i = 0; i < _floorsNum; ++i){
            for(int j = space_number; j <= space_number + _arr[i].getSpacesNum(); ++j){
                if(j == num){
                    _arr[i].addSpace(j-space_number,space);
                }
            }
            space_number += _arr[i].getSpacesNum();
        }
    }


    public void removeSpace(int num) throws SpaceIndexOutOfBoundsException {
        if(num < 0 || num >= getGenSpacesNum()) throw new SpaceIndexOutOfBoundsException(num,getGenSpacesNum());

        int space_number = 0;
        for(int i = 0; i < _floorsNum; ++i){
            for(int j = space_number; j < space_number + _arr[i].getSpacesNum(); ++j){
                if(j == num){
                    _arr[i].removeSpace(j-space_number);
                }
            }
            space_number += _arr[i].getSpacesNum();
        }
    }


    public Space getBestSpace(){
        Space best = getFloor(0).getBestSpace();
        for(int i = 1; i < getFloorsArr().length; ++i){
            if(best.getSquare() < getFloor(i).getBestSpace().getSquare()) {
                best = getFloor(i).getBestSpace();
            }
        }
        return best;
    }


    public Space[] getSortSpaces(){

        Space[] arr = new Space[getGenSpacesNum()];
        int space_num = 0;
        for(int i = 0 ; i < getFloorsArr().length; ++i){
            for(int j = 0; j < getFloorsArr()[i].getSpacesNum(); ++j){
                arr[j + space_num] = getFloorsArr()[i].getSpacesArr()[j];
            }
            space_num += getFloorsArr()[i].getSpacesNum();
        }

        Space tmp;
        for(int i = 0 ; i < arr.length - 1;++i){
            for(int j = 0; j < arr.length - i - 1; ++j){
                if(arr[j].getSquare() < arr[j+1].getSquare()){
                    tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        return arr;
    }


    public String toString(){
        String res = "\nDwelling (Floors: " + getFloorsNum() + ")\n";
        for(int i = 0; i < getFloorsNum(); ++i){
            res += getFloor(i).toString();
        }
        return res;
    }


    public boolean equals(Object object){
        if(object == this) return true;
        if(object == null || object.getClass() != getClass()) return false;
        Dwelling building = (Dwelling)object;
        boolean flag = false;
        if(building.getFloorsNum() == getFloorsNum()){
            for(int i = 0; i < getFloorsNum(); ++i){
                if(getFloor(i).equals(building.getFloor(i))){
                    flag = true;
                }
                else{
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }


    public int hashCode(){
        int result = 0;
        for(int i = 0; i < getFloorsNum(); ++i){
            result += getFloorsNum() ^ getFloor(i).hashCode();
        }
        return result;
    }


    public Object clone(){
        Object result = null;
        try {
            result = super.clone();

            ((Dwelling)result)._arr = _arr.clone();
            for(int i = 0; i < _arr.length; ++i){
                ((Dwelling)result)._arr[i] = (Floor)_arr[i].clone();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }


    public Iterator<Floor> iterator(){
        Iterator<Floor> it = new Iterator<Floor>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                if(index < getFloorsNum()){
                    return true;
                }
                else return false;
            }

            @Override
            public Floor next() {
                return getFloor(index++);
            }
        };
        return it;
    }


    public void print(){
        System.out.println("\n\n=============== DWELLING ===============\n");
        System.out.println("Number of Floors: " + getFloorsNum());
        System.out.println();
        int flat_number = 0;
        for(int i = 0; i < getFloorsNum(); ++i){
            getFloor(i).print(i,flat_number);
            flat_number += getFloor(i).getSpacesNum();
        }
        System.out.println("========================================");
    }

}
