package buildings;

import buildings.dwelling.*;


public class DwellingFactory implements BuildingFactory{
    public Space createSpace(double area){
        Space create = new Flat(area);
        return create;
    }

    public Space createSpace(double area, int roomsCount){
        Space create = new Flat(area, roomsCount);
        return create;
    }

    public Floor createFloor(int spaceCount){
        Floor create = new DwellingFloor(spaceCount);
        return create;
    }

    public Floor createFloor(Space[] spaces){
        Floor create = new DwellingFloor(spaces);
        return create;
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts){
        Building create = new Dwelling(floorsCount, spacesCounts);
        return create;
    }

    public Building createBuilding(Floor[] floors){
        Building create = new Dwelling(floors);
        return create;
    }
}
