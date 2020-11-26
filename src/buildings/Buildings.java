package buildings;


import java.io.*;
import java.util.Comparator;
import java.util.Scanner;

public class Buildings {

    public static BuildingFactory buildingFactory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory set){
        buildingFactory = set;
    }

    public static Space createSpace(double area){
        Space create = buildingFactory.createSpace(area);
        return create;
    }

    public static Space createSpace(double area, int roomsCount){
        Space create = buildingFactory.createSpace(area, roomsCount);
        return create;
    }

    public static Floor createFloor(int spaceCount){
        Floor create = buildingFactory.createFloor(spaceCount);
        return create;
    }

    public static Floor createFloor(Space[] spaces){
        Floor create = buildingFactory.createFloor(spaces);
        return create;
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts){
        Building create = buildingFactory.createBuilding(floorsCount, spacesCounts);
        return create;
    }

    public static Building createBuilding(Floor[] floors){
        Building create = buildingFactory.createBuilding(floors);
        return create;
    }


    public static void outputBuilding(Building building, OutputStream out){
        DataOutputStream out1 = new DataOutputStream(out);
        try {
            out1.writeInt(building.getFloorsNum());
            for (int i = 0; i < building.getFloorsNum(); ++i) {
                out1.writeInt(building.getFloor(i).getSpacesNum());
                for (int j = 0; j < building.getFloor(i).getSpacesNum(); ++j) {
                    out1.writeInt(building.getFloor(i).getSpace(j).getRooms());
                    out1.writeDouble(building.getFloor(i).getSpace(j).getSquare());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Building inputBuilding(InputStream in){
        DataInputStream in1 = new DataInputStream(in);
        Building building = null;
        try {
            int floors_num = in1.readInt();
            Floor[] floors = new Floor[floors_num];
            int spaces_number;
            for (int i = 0; i < floors_num; ++i) {
                spaces_number = in1.readInt();
                floors[i] = createFloor(spaces_number);
                int rooms;
                double square;
                for (int j = 0; j < spaces_number; ++j) {
                    rooms = in1.readInt();
                    square = in1.readDouble();
                    floors[i].changeSpace(j, createSpace(square, rooms));
                }
            }

            building = createBuilding(floors);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return building;
    }


    public static void writeBuilding (Building building, Writer out){
        try {

            out.write(building.getFloorsNum() + " ");

            for (int i = 0; i < building.getFloorsNum(); ++i) {

                out.write(building.getFloor(i).getSpacesNum() + " ");

                for (int j = 0; j < building.getFloor(i).getSpacesNum(); ++j) {

                    out.write(building.getFloor(i).getSpace(j).getRooms() + " ");
                    out.write("" + building.getFloor(i).getSpace(j).getSquare() + " ");

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static Building readBuilding (Reader in){
        StreamTokenizer token = new StreamTokenizer(in);

        try {
            token.nextToken();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int floors_num = (int)token.nval;
        Floor[] floors = new Floor[floors_num];
        int spaces_number;
        int rooms;
        double square;
        for(int i = 0; i < floors_num; ++i){   //Analyze Floors
            try {
                token.nextToken();
            } catch (IOException e) {
                e.printStackTrace();
            }
            spaces_number = (int)token.nval;

            floors[i] = createFloor(spaces_number);
            for(int space_index = 0; space_index < spaces_number; ++space_index) {

                try {
                    token.nextToken();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rooms = (int)token.nval;
                try {
                    token.nextToken();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                square = token.nval;
                floors[i].changeSpace(space_index,createSpace(square,rooms));
            }
        }

        Building building = createBuilding(floors);
        return building;

    }



    public static void serializeBuilding (Building building, OutputStream out){
        ObjectOutputStream out1;
        try {
            out1 = new ObjectOutputStream(out);
            out1.writeObject(building);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Building deserializeBuilding (InputStream in){
        Building B = null;
        ObjectInputStream in1;
        try {
            in1 = new ObjectInputStream(in);
            B = (Building)in1.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return B;
    }


    public static void writeBuildingFormat (Building building, Writer out){
        PrintWriter out1 = new PrintWriter(out);
        int floors_number = building.getFloorsNum();
        out1.printf(" || Floors number = " + floors_number);
        int spaces_number;
        int rooms;
        double square;
        for(int i = 0; i < floors_number; ++i){
            spaces_number = building.getFloor(i).getSpacesNum();
            out1.printf(" || Spaces number = " + spaces_number);
            for(int j = 0; j < building.getFloor(i).getSpacesNum(); ++j){

                rooms = building.getFloor(i).getSpace(j).getRooms();
                out1.printf(" | Rooms: " + rooms);

                square = building.getFloor(i).getSpace(j).getSquare();
                out1.printf(" | Square: " + square);
            }
        }
    }


    public static Building readBuildingScanner (Scanner scanner){
        int floors_num = scanner.nextInt();
        System.out.println(floors_num);
        Floor[] floors = new Floor[floors_num];
        int spaces_number;

        for(int i = 0; i < floors_num; ++i){
            spaces_number = scanner.nextInt();
            System.out.println(spaces_number);
            floors[i] = createFloor(spaces_number);
            for(int space_index = 0; space_index < spaces_number; ++space_index) {
                int rooms;
                double square;
                rooms = scanner.nextInt();
                System.out.println(rooms);

                square = scanner.nextDouble();
                System.out.println(square);

                floors[i].changeSpace(space_index, createSpace(square,rooms));
            }
        }
        Building building = createBuilding(floors);
        return building;
    }


    public static void sortSpacesSquare(Space[] arr){

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                Space tmp;
                if(arr[j].compareTo(arr[j+1]) > 0){
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }


    public static void sortFloorsRooms(Floor[] arr){

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                Floor tmp;
                if(arr[j].compareTo(arr[j+1]) > 0){
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }


    public static <T extends Comparable<T>> void sortArrays(T[] arr){

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                T tmp;
                if(arr[j].compareTo(arr[j+1]) > 0){
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }


    public static void sortSpacesRooms(Space[] arr){

        CriterionSpace criterion = new CriterionSpace();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                Space tmp;
                if((criterion.compare(arr[j], arr[j+1])) > 0){
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

    }


    public static void sortFloorsSquare(Floor[] arr){

        CriterionFloor criterion = new CriterionFloor();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                Floor tmp;
                if((criterion.compare(arr[j], arr[j+1])) > 0){
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

    }


    public static <T> void sortArraysComparator(T[] arr, Comparator<T> comparator){

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                T tmp;
                if((comparator.compare(arr[j], arr[j+1])) > 0){
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }


}
