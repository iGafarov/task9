package buildings.net.client;

import buildings.*;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BinaryClient {
    public static void main(String[] args) throws IOException {

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

            String result;

            while(scanner.hasNext()){

                type = scanner.nextLine();
                System.out.println("\t" + type);
                dos.writeUTF(type);

                building = Buildings.readBuilding(reader);
                Buildings.outputBuilding(building, dos);

                result = dis.readUTF();
                System.out.println("=================\nResponse: " + result + "\n=================");

                writer.write(result + "\n");
            }
            writer.close();

            dos.writeUTF("Exit");
            System.out.println("Exit");
            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
