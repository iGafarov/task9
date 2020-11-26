package buildings.net.server.parallel;

import buildings.*;
import buildings.dwelling.Dwelling;
import buildings.dwelling.hotel.Hotel;
import buildings.exceptions.BuildingUnderArrestException;
import buildings.office.OfficeBuilding;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SerialServer {
    public static double calculatePrice(Building building) throws BuildingUnderArrestException {
        if(arrestedBuilding(building)) throw new BuildingUnderArrestException();
        double squarePrice = 0;
        double square = building.getGenSquare();
        double result;
        if (building instanceof Hotel){
            squarePrice = 2000;
        }
        else if (building instanceof OfficeBuilding){
            squarePrice = 1500;
        }else if(building instanceof Dwelling){
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

            System.out.println("Parallel serial server started!");

            while(true) {
                try {
                    Socket socket = server.accept();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try (
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

                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            finally{
                                try {
                                    socket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }).start();

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}