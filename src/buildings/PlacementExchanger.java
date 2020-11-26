package buildings;

import buildings.exceptions.InexchangeableFloorsException;
import buildings.exceptions.InexchangeableSpacesException;

public class PlacementExchanger {


    public static boolean changeableSpaces(Space first, Space second){
        if(first.getSquare() == second.getSquare() && first.getRooms() == second.getRooms())
            return true;
        else return false;
    }

    public static boolean changeableFloors(Floor first, Floor second){
        if(first.getFloorSquare() == second.getFloorSquare() && first.getFloorRooms() == second.getFloorRooms())
            return true;
        else return false;
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException {
        if(changeableSpaces(floor1.getSpace(index1), floor2.getSpace(index2)) == false) throw new InexchangeableSpacesException();
        Space tmp;
        tmp = floor1.getSpacesArr()[index1];
        floor1.changeSpace(index1, floor2.getSpace(index2));
        floor2.changeSpace(index2, tmp);
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException {
        if(changeableFloors(building1.getFloor(index1),building2.getFloor(index2)) == false) throw new InexchangeableFloorsException();
        Floor tmp;
        tmp = building1.getFloor(index1);
        building1.changeFloor(index1, building2.getFloor(index2));
        building2.changeFloor(index2, tmp);
    }

}
