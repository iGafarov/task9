package buildings.exceptions;

public class InvalidSpaceAreaException extends IllegalArgumentException {
    public InvalidSpaceAreaException(double num) {
        String msg = "InvalidSpaceAreaException\n" + "Invalid Argument: " + num + "\n Enter number between 20.0 and 500.0";
        throw new IllegalArgumentException(msg);
    }
}
