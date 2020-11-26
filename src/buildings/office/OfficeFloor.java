package buildings.office;
import buildings.Floor;
import buildings.Space;
import buildings.exceptions.SpaceIndexOutOfBoundsException;
import buildings.lists.ArrList;
import buildings.lists.ArrayListNode;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeFloor implements Floor, Serializable, Cloneable {

    private ArrList _spaces;


    private ArrayListNode getSpaceNode(int num) throws SpaceIndexOutOfBoundsException {
        if(num < 0 || num >= _spaces.getSize()){
            throw new SpaceIndexOutOfBoundsException(num, _spaces.getSize());
        }
        return _spaces.getArrayListNode(num);
    }

    private void addSpaceNode(Space space, int num) throws SpaceIndexOutOfBoundsException{
        if(num < 0 || num > _spaces.getSize()){
            throw new SpaceIndexOutOfBoundsException(num, _spaces.getSize() + 1);
        }
        _spaces.insert(space,num);
    }

    private void removeSpaceNode(int num) throws SpaceIndexOutOfBoundsException{
        if(num < 0 || num >= _spaces.getSize()){
            throw new SpaceIndexOutOfBoundsException(num, _spaces.getSize());
        }
        _spaces.removeAt(num);
    }

    public OfficeFloor(int officesNum){
        _spaces = new ArrList(officesNum);
    }

    public OfficeFloor(Space[] space){
        _spaces = new ArrList();
        for(int i = 0; i < space.length; ++i) {
            _spaces.push_back(space[i]);
        }
    }

    public int getSpacesNum(){
        return _spaces.getSize();
    }

    public double getFloorSquare(){
        double genSquare = 0;
        for(int i = 0; i < _spaces.getSize(); ++i){
            genSquare += _spaces.getArrayListNode(i)._space.getSquare();
        }
        return genSquare;
    }

    public int getFloorRooms(){
        int genRooms = 0;
        for(int i = 0; i < _spaces.getSize(); ++i){
            genRooms += _spaces.getArrayListNode(i)._space.getRooms();
        }
        return genRooms;
    }

    public Space[] getSpacesArr(){
        Space[] arr = new Space[getSpacesNum()];
        for(int i = 0; i < arr.length; ++i){
            arr[i] = _spaces.getArrayListNode(i)._space;
        }
        return arr;
    }

    public Space getSpace(int num){
        return getSpaceNode(num)._space;
    }

    public void changeSpace(int num, Space space) throws SpaceIndexOutOfBoundsException {
        if(num < 0 || num >= _spaces.getSize()){
            throw new SpaceIndexOutOfBoundsException(num, _spaces.getSize());
        }
        for(int i = 0; i < _spaces.getSize(); ++i){
            if(i == num) _spaces.getArrayListNode(i)._space = space;
        }
    }

    public void addSpace(int num, Space space){
        addSpaceNode(space,num);
    }

    public void removeSpace(int num){
        removeSpaceNode(num);
    }

    public Space getBestSpace(){
        Space best = getSpace(0);
        for(int i = 0; i < _spaces.getSize()-1; ++i){
            if(getSpacesArr()[i].getSquare() < getSpacesArr()[i + 1].getSquare()) {
                best = getSpacesArr()[i + 1];
            }
        }
        return best;
    }

    public String toString(){
        String res = "\nOfficeFloor (Spaces: " + _spaces.getSize() + ")";
        for(int i = 0; i < _spaces.getSize(); ++i){
            res += "\n\t" + getSpace(i).toString();
        }
        return res;
    }

    public boolean equals(Object object){
        if(object == this) return true;
        if(object == null || object.getClass() != getClass()) return false;
        OfficeFloor floor = (OfficeFloor)object;
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
            ((OfficeFloor)result)._spaces = (ArrList)this._spaces.clone();
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
        System.out.println(" ======= Office Floor # " + floor_index + " ======= \n");
        for(int i = 0; i < getSpacesNum(); ++i) {
            System.out.print("== '" + start_space_index + "' ");
            getSpace(i).print();
            ++start_space_index;
        }
    }

    public void print(){
        System.out.println(" ======= Office Floor ======= \n");
        for(int i = 0; i < getSpacesNum(); ++i) {
            System.out.print("== '" + i + "' ");
            getSpace(i).print();
        }
    }

}
