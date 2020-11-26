package buildings.net.client;

import buildings.*;
import buildings.exceptions.BuildingUnderArrestException;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SerialClient {
    public static void main(String[] args) {

        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                DataOutputStream dos =
                        new DataOutputStream(
                                socket.getOutputStream());

                DataInputStream dis =
                        new DataInputStream(
                                socket.getInputStream());
        ){

            Scanner scanner = new Scanner(new FileReader("E:/Kreker/#8/BuildingTypes.txt"));
            String type;

            BufferedReader reader = new BufferedReader(new FileReader("E:/Kreker/#8/BuildingInfo.txt"));
            Building building;

            File file = new File("E:/Kreker/#8/BuildingPrices.txt");
            Writer writer = new FileWriter(file);

            Object response;

            String result = "";

            ObjectInputStream ois = new ObjectInputStream(dis);
            while(scanner.hasNext()){

                type = scanner.nextLine();
                System.out.println("\t" + type);
                dos.writeUTF(type);

                switch (type) {
                    case "Dwelling":
                        Buildings.setBuildingFactory(new DwellingFactory());
                        break;
                    case "OfficeBuilding":
                        Buildings.setBuildingFactory(new OfficeFactory());
                        break;
                    case "Hotel":
                        Buildings.setBuildingFactory(new HotelFactory());
                        break;
                }

                building = Buildings.readBuilding(reader);
                Buildings.serializeBuilding(building, dos);

                response = ois.readObject();

                if (response instanceof String){
                    result = (String)response;
                }
                else if (response instanceof BuildingUnderArrestException){
                    result = "Building is Arrested!";
                }

                System.out.println("=================\nResponse: " + result + "\n=================");

                writer.write(result + "\n");
            }
            writer.close();

            dos.writeUTF("Exit");
            System.out.println("Exit");
            dos.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
