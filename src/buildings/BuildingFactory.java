package buildings;

public interface BuildingFactory {
    public Space createSpace(double area);
    public Space createSpace(double area, int roomsCount);
    public Floor createFloor(int spacesCount);
    public Floor createFloor(Space[] spaces);
    public Building createBuilding(int floorsCount, int[] spaceCounts);
    public Building createBuilding(Floor[] floors);
}
