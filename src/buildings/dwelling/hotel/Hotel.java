package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.exceptions.FloorIndexOutOfBoundsException;
import buildings.Space;
import buildings.dwelling.Dwelling;
import buildings.dwelling.Flat;

public class Hotel extends Dwelling {

    private int _starsNum;
    final int STARS_NUMBER = 1;


    public Hotel(Floor[] arr) {
        super(arr);
        int maxStars = STARS_NUMBER;
        for(Floor floor: arr){
            if(!(floor instanceof HotelFloor)){
                continue;
            }
            else{
                if(maxStars < ((HotelFloor) floor).getStarsNum())
                    maxStars = ((HotelFloor) floor).getStarsNum();
            }
        }
        _starsNum = maxStars;
    }


    public Hotel(int floorsNum, int[] arr_num_of_flats) {
        super(floorsNum, arr_num_of_flats);
        for(int i = 0; i < floorsNum; ++i){
            changeFloor(i, new HotelFloor(arr_num_of_flats[i]));
        }
        _starsNum = STARS_NUMBER;
    }


    public int getStarsNum(){
        return _starsNum;
    }

    public void setStarsNum(int set){
        _starsNum = set;
    }


    @Override
    public Space getBestSpace(){
        Space best = new Flat();
        double coeff = 0.25;
        double area;
        double bestAreaCoeff = 0;
        for(int i = 0; i < getFloorsNum(); ++i){
            if(!(getFloor(i) instanceof HotelFloor)) continue;
            switch(((HotelFloor)getFloor(i)).getStarsNum()){
                case 1:
                    coeff = 0.25;
                    break;
                case 2:
                    coeff = 0.5;
                    break;
                case 3:
                    coeff = 1;
                    break;
                case 4:
                    coeff = 1.25;
                    break;
                case 5:
                    coeff = 1.5;
                    break;
            }
            area = getFloor(i).getBestSpace().getSquare();

            if(bestAreaCoeff < area * coeff) {
                bestAreaCoeff = area * coeff;
                best = getFloor(i).getBestSpace();
            }

        }
        return best;
    }


    public String toString(){
        String res = "\nHotel (Stars number: " + getStarsNum() + "\n       Floors: " + getFloorsNum() + ")\n";
        for(int i = 0; i < getFloorsNum(); ++i){
            res += getFloor(i).toString();
        }
        return res;
    }


    public boolean equals(Object object){
        if(object == this) return true;
        if(object == null || object.getClass() != getClass()) return false;
        Hotel building = (Hotel) object;
        boolean flag = false;
        if(building.getFloorsNum() == getFloorsNum() && building.getStarsNum() == getStarsNum()){
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
            result += getStarsNum() ^ getFloorsNum() ^ getFloor(i).hashCode();
        }
        return result;
    }


    @Override
    public void changeFloor(int num, Floor floor) throws FloorIndexOutOfBoundsException {
        if(num < 0 || num >= getGenSpacesNum()) throw new FloorIndexOutOfBoundsException(num,getGenSpacesNum());
        super.changeFloor(num,floor);
        if(floor instanceof HotelFloor){
            if(getStarsNum() < ((HotelFloor) floor).getStarsNum())
                setStarsNum(((HotelFloor) floor).getStarsNum());
        }
    }


    @Override
    public void print(){
        System.out.println("\n\n=============== HOTEL ===============\n");
        System.out.println("Number of Floors: " + getFloorsNum());
        System.out.println("Stars number: " + _starsNum);
        System.out.println();
        int flat_number = 0;
        for(int i = 0; i < getFloorsNum(); ++i){
            getFloor(i).print(i,flat_number);
            flat_number += getFloor(i).getSpacesNum();
        }
        System.out.println("========================================");
    }


}
