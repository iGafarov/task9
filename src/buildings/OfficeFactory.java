package buildings;

import buildings.office.*;


public class OfficeFactory implements BuildingFactory{
    public Space createSpace(double area){
        Space create = new Office(area);
        return create;
    }

    public Space createSpace(double area, int roomsCount){
        Space create = new Office(area, roomsCount);
        return create;
    }

    public Floor createFloor(int spaceCount){
        Floor create = new OfficeFloor(spaceCount);
        return create;
    }

    public Floor createFloor(Space[] spaces){
        Floor create = new OfficeFloor(spaces);
        return create;
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts){
        Building create = new OfficeBuilding(floorsCount, spacesCounts);
        return create;
    }

    public Building createBuilding(Floor[] floors){
        Building create = new OfficeBuilding(floors);
        return create;
    }
}
