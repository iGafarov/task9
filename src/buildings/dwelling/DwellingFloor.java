package buildings.dwelling;

import buildings.Floor;
import buildings.Space;
import buildings.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Iterator;

public class DwellingFloor implements Floor, Serializable, Cloneable {

    private int _spacesNum;
    private Space _arr[];



    public DwellingFloor(int flatsNum){
        _arr = new Space[flatsNum];
        _spacesNum = flatsNum;
        for(int i = 0; i < flatsNum; ++i){
            _arr[i] = new Flat();
        }

    }

    public DwellingFloor(Space arr[]){
        _arr = new Space[arr.length];
        for(int i = 0; i < arr.length; ++i){
            _arr[i] = arr[i];
        }
        _spacesNum = arr.length;

    }

    public double getFloorSquare(){
        double sum = 0;
        for(int i = 0; i < _spacesNum; ++i){
            sum += _arr[i].getSquare();
        }
        return sum;
    }

    public int getFloorRooms(){
        int sum = 0;
        for(int i = 0; i < _spacesNum; ++i){
            sum += _arr[i].getRooms();
        }
        return sum;
    }

    public int getSpacesNum(){
        return _spacesNum;
    }

    public Space[] getSpacesArr(){
        return _arr;
    }

    public Space getSpace(int num) throws SpaceIndexOutOfBoundsException {
        if(num < 0 || num >= _arr.length){
            throw new SpaceIndexOutOfBoundsException(num,_arr.length);
        }
        return _arr[num];
    }

    public void changeSpace(int num, Space space) throws SpaceIndexOutOfBoundsException{
        if(num < 0 || num >= _arr.length){
            throw new SpaceIndexOutOfBoundsException(num,_arr.length);
        }
        _arr[num] = space;
    }

    public void addSpace(int num, Space space) throws SpaceIndexOutOfBoundsException{
        if(num < 0 || num > _arr.length){
            throw new SpaceIndexOutOfBoundsException(num,_arr.length + 1);
        }

        Space tmp[] = new Space[_spacesNum + 1];;
        for(int i = 0; i < num; ++i){
            tmp[i] = _arr[i];
        }
        tmp[num] = space;
        for(int i = num; i < _spacesNum; ++i){
            tmp[i+1] = _arr[i];
        }
        _arr = new Space[_spacesNum + 1];
        for(int i = 0; i < _spacesNum + 1; ++i){
            _arr[i] = tmp[i];
        }
        _spacesNum += 1;
    }

    public void removeSpace(int num) throws SpaceIndexOutOfBoundsException{
        if(num < 0 || num >= _arr.length){
            throw new SpaceIndexOutOfBoundsException(num,_arr.length);
        }
        Space tmp[] = new Space[_spacesNum - 1];

        for(int i = 0; i < num; ++i){
            tmp[i] = _arr[i];
        }
        for(int i = num + 1; i < _spacesNum; ++i){
            tmp[i-1] = _arr[i];
        }
        _arr = new Space[_spacesNum - 1];
        for(int i = 0; i < _spacesNum - 1; ++i){
            _arr[i] = tmp[i];
        }
        _spacesNum -= 1;
    }


    public Space getBestSpace(){
        Space best = _arr[0];
        for(int i = 1; i < _spacesNum; ++i){
            if(best.getSquare() < _arr[i].getSquare()) {
                best = _arr[i];
            }
        }
        return best;
    }


    public String toString(){
        String res = "\nDwellingFloor (Spaces: " + _spacesNum + ")";
        for(int i = 0; i < _spacesNum; ++i){
            res += "\n\t" + _arr[i].toString();
        }
        return res;
    }


    public boolean equals(Object object){
        if(object == this) return true;
        if(object == null || object.getClass() != getClass()) return false;
        DwellingFloor floor = (DwellingFloor)object;
        boolean flag = false;
        if(floor.getSpacesNum() == getSpacesNum()){
            for(int i = 0; i < getSpacesNum(); ++i){
                if(getSpace(i).equals(floor.getSpace(i))){
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
        for(int i = 0; i < getSpacesNum(); ++i){
            result += getSpacesNum() ^ getSpace(i).hashCode();
        }
        return result;
    }


    public Object clone(){
        Object result = null;
        try {
            result = super.clone();

            ((DwellingFloor)result)._arr = _arr.clone();
            for (int i = 0; i < _arr.length; ++i) {
                ((DwellingFloor)result)._arr[i] = (Space)_arr[i].clone();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }


    public Iterator<Space> iterator(){
        Iterator<Space> it = new Iterator<Space>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                if(index < getSpacesNum()){
                    return true;
                }
                else return false;
            }

            @Override
            public Space next() {
                return getSpace(index++);
            }
        };
        return it;
    }

    @Override
    public int compareTo(Floor floor) {
        if(getSpacesNum() < floor.getSpacesNum())return -1;
        if(getSpacesNum() > floor.getSpacesNum())return 1;
        return 0;
    }


    public void print(int floor_index ,int start_space_index){
        System.out.println(" ======= Dwelling Floor # " + floor_index + " ======= \n");
        for(int i = 0; i < getSpacesNum(); ++i) {
            System.out.print("== '" + start_space_index + "' ");
            getSpace(i).print();
            ++start_space_index;
        }
    }

    public void print(){
        System.out.println(" ======= Dwelling Floor ======= \n");
        for(int i = 0; i < getSpacesNum(); ++i) {
            System.out.print("== '" + i + "' ");
            getSpace(i).print();
        }
    }
}
