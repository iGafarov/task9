package buildings.net.server.sequential;


import buildings.*;
import buildings.dwelling.hotel.Hotel;
import buildings.exceptions.BuildingUnderArrestException;
import buildings.office.OfficeBuilding;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class SerialServer {
    public static double calculatePrice(Building building) throws BuildingUnderArrestException {
        if(arrestedBuilding(building)) throw new BuildingUnderArrestException();
        double squarePrice;
        double square = building.getGenSquare();
        double result;
        if (building instanceof Hotel){
            squarePrice = 2000;
        }
        else if (building instanceof OfficeBuilding){
            squarePrice = 1500;
        }else{
            squarePrice = 1000;
        }
        result = squarePrice*square;
        return result;
    }

    public static boolean arrestedBuilding(Building building){
        int chance = (int)(Math.random()*10);
        if(chance > 8){
            return true;
        }else return false;
    }

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8000)){

            System.out.println("Sequential serial server started!");

            while(true){
                try(
                        Socket socket = server.accept();
                        DataOutputStream dos =
                                new DataOutputStream(
                                        socket.getOutputStream());

                        DataInputStream dis =
                                new DataInputStream(
                                        socket.getInputStream());

                ) {
                    System.out.println("\n------------------------------------------------------------------------------------");

                    Building building;
                    Double price;
                    ObjectOutputStream oos = new ObjectOutputStream(dos);
                    while (!(dis.readUTF()).equals("Exit")) {

                        building = Buildings.deserializeBuilding(dis);
                        try {
                            System.out.println("\n===============================\n" +
                                    "\tRequest Building:" +
                                    "\n===============================\n"
                                    + building);

                            price = calculatePrice(building);
                            System.out.println("Price of Building: " + price);

                            String result = (price.toString());

                            oos.writeObject(result);

                        } catch (BuildingUnderArrestException e){
                            BuildingUnderArrestException result = e;
                            System.out.println("Building is arrested!");

                            oos.writeObject(result);
                        }
                        dos.flush();
                        System.out.println("\n------------------------------------------------------------------------------------");

                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
