import buildings.*;
import buildings.threads.Cleaner;
import buildings.threads.Repairer;
import buildings.dwelling.DwellingFloor;
import buildings.threads.SequentalCleaner;
import buildings.threads.SequentalRepairer;

public class Runner {

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {

        /*Floor f0 = new OfficeFloor(3);
        f0.changeSpace(0,new Office(50,2));

        Floor f1 = new DwellingFloor(2);


        Space[] arr = new Space[3];
        Space o1 = new Flat(60,4);
        Space o2 = new Office(150.99,3);
        Space o3 = new Office(200,5);
        arr[0] = o1;
        arr[1] = o2;
        arr[2] = o3;
        Floor f2 = new OfficeFloor(arr);

        Floor f3 = new DwellingFloor(2);
        Floor f4 = new OfficeFloor(2);
        f4.changeSpace(0,new Office(50,2));
        f4.changeSpace(1,new Office(50,2));
        //f4.changeSpace(0,new Flat(250,1));
        Floor[] Arr = new Floor[5];
        Arr[0] = f0;
        Arr[1] = f1;
        Arr[2] = f2;
        Arr[3] = f3;
        Arr[4] = f4;
        Building B = new OfficeBuilding(Arr);
        */
        /*Floor[] Arr1 = new Floor[4];
        for(int i = 1; i < Arr1.length; ++i){
            Arr1[i] = new HotelFloor(2);
        }
        Space[] arr1 = new Space[2];
        for (int i = 0; i < arr1.length-1; i++) {
            arr1[i] = new Flat(100,5);
        }
        arr1[1] = new Flat(150,5);
        Arr1[0] = new HotelFloor(arr1);


        //Floor floor1 = new DwellingFloor(3);
        //Floor floor2 = new HotelFloor(3);
        //((HotelFloor)floor2).setStarsNum(2);

        Building B = new Hotel(Arr1);

        Floor floor = new HotelFloor(2);
        //System.out.println(floor.toString());
        ((HotelFloor)floor).setStarsNum(5);
        //System.out.println(floor.toString());
        int arr[] = new int[2];
        arr[0] = 2;
        arr[1] = 3;
        Building B1 = new Hotel(2,arr);
        //B1.print();
        Floor floor1 = new DwellingFloor(3);
        *//*floor1.print();
        Iterator<Space> it = ((HotelFloor) floor).iterator();
        Iterator<Space> it1 = ((DwellingFloor)floor1).iterator();*//*
        Iterator<Floor> it2 = ((Hotel)B1).iterator();
        B1.print();
        while(it2.hasNext()){
            it2.next().print();
        }*/
/*
        Buildings.setBuildingFactory(new DwellingFactory());

        Floor floor = new DwellingFloor(3);
        //floor.changeSpace(0, new Flat(300,3));
        floor.changeSpace(1, new Flat(30,5));
        floor.changeSpace(2, new Office(60,3));
        //System.out.println(floor);
        //Space[] spaces = floor.getSpacesArr();*/



        ///////// check sortSpaceSquare


        /*Space[] spaces = new Space[4];
        spaces[0] = new Flat();
        spaces[1] = new Flat(30,5);
        spaces[2] = new Office(60,3);
        spaces[3] = new Flat();
        Floor floor1 = new DwellingFloor(spaces);
        System.out.println(floor1);
        Buildings.sortSpacesSquare(floor1.getSpacesArr());
        System.out.println(floor1);*/


        ///////// sortFloorRooms & sortArrays

        /*Floor[] floors = new Floor[3];
        floors[0] = new DwellingFloor(5);
        floors[1] = new OfficeFloor(2);
        floors[2] = new HotelFloor(3);
        Building B = new Dwelling(floors);
        System.out.println(B);
        Buildings.sortArrays(B.getFloorsArr());
        System.out.println(B);*/


        //////// sortSpacesRooms


        /*Space[] spaces = new Space[4];
        spaces[0] = new Flat();
        spaces[1] = new Flat(30,5);
        spaces[2] = new Office(60,3);
        spaces[3] = new Flat();
        Floor floor1 = new DwellingFloor(spaces);
        System.out.println(floor1);
        Buildings.sortSpacesRooms(floor1.getSpacesArr());
        System.out.println(floor1);*/


        ///////// sortFloorsSquare


        /*Floor[] floors = new Floor[3];
        floors[0] = new DwellingFloor(5);
        floors[1] = new OfficeFloor(2);
        floors[2] = new HotelFloor(3);
        Building B = new Dwelling(floors);
        System.out.println(B);
        Buildings.sortFloorsSquare(B.getFloorsArr());
        System.out.println(B);*/


        //////// sortArraysComparator


        /*Floor[] floors = new Floor[3];
        floors[0] = new DwellingFloor(5);
        floors[1] = new OfficeFloor(2);
        floors[2] = new HotelFloor(3);
        Building B = new Dwelling(floors);
        System.out.println(B);
        Buildings.sortArraysComparator(B.getFloorsArr(), new CriterionFloor());
        System.out.println(B);


        Space[] spaces = new Space[4];
        spaces[0] = new Flat();
        spaces[1] = new Flat(30,5);
        spaces[2] = new Office(60,3);
        spaces[3] = new Flat();
        Floor floor1 = new DwellingFloor(spaces);
        System.out.println(floor1);
        Buildings.sortArraysComparator(floor1.getSpacesArr(), new CriterionSpace());
        System.out.println(floor1);*/


        /*Floor floor = new DwellingFloor(10);

        Runnable sal1 = new SequentalRepairer(floor);
        Runnable sal2 = new SequentalCleaner(floor);

        Thread myThread1 = new Thread(sal1);
        Thread myThread2 = new Thread(sal2);

        myThread1.start();
        myThread2.start();*/

        Floor floor = new DwellingFloor(5);

        Semaphore semaphore = new Semaphore(2);
        SequentalRepairer repairer = new SequentalRepairer(semaphore, floor);
        SequentalCleaner cleaner = new SequentalCleaner(semaphore, floor);
        new Thread(repairer).start();
        new Thread(cleaner).start();



    }


}
