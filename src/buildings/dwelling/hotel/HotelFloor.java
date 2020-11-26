package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.DwellingFloor;

import java.util.Iterator;

public class HotelFloor extends DwellingFloor {

    private int _starsNum;

    final int STARS_NUMBER = 1;


    public HotelFloor(int flatsNum) {
        super(flatsNum);
        _starsNum = STARS_NUMBER;
    }


    public HotelFloor(Space[] arr) {
        super(arr);
        _starsNum = STARS_NUMBER;
    }


    public int getStarsNum(){
        return _starsNum;
    }


    public void setStarsNum(int set){
        _starsNum = set;
    }


    public String toString(){
        String res = "\n\nHotelFloor (Stars number: " + getStarsNum() + "\n            Spaces: " + getSpacesNum() + ")";
        for(int i = 0; i < getSpacesNum(); ++i){
            res += "\n\t" + getSpace(i).toString();
        }
        return res;
    }


    public boolean equals(Object object){
        if(object == this) return true;
        if(object == null || object.getClass() != getClass()) return false;
        HotelFloor floor = (HotelFloor) object;
        boolean flag = false;
        if(floor.getSpacesNum() == getSpacesNum() && floor.getStarsNum() == getStarsNum()){
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
            result += getStarsNum() ^ getSpacesNum() ^ getSpace(i).hashCode();
        }
        return result;
    }


    public void print(int floor_index ,int start_space_index){
        System.out.println(" ======= Hotel Floor # " + floor_index + " ======= \n");
        System.out.println("Stars number: " + getStarsNum() + "\n");
        for(int i = 0; i < getSpacesNum(); ++i) {
            System.out.print("== '" + start_space_index + "' ");
            getSpace(i).print();
            ++start_space_index;
        }
    }


    public void print(){
        System.out.println(" ======= Hotel Floor ======= \n");
        System.out.println("\nStars number: " + getStarsNum() + "\n");
        for(int i = 0; i < getSpacesNum(); ++i) {
            System.out.print("== '" + i + "' ");
            getSpace(i).print();
        }
    }

}
