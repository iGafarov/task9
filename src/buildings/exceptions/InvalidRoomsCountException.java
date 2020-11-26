package buildings.exceptions;

public class InvalidRoomsCountException extends IllegalArgumentException{
    public InvalidRoomsCountException(int num) {
        String msg = "InvalidRoomsCountException\n" + "Invalid Argument: " + num + "\n Enter number between 1 and 10";
        throw new IllegalArgumentException(msg);
    }
}
