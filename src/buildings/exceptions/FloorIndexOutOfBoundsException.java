package buildings.exceptions;

public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public FloorIndexOutOfBoundsException(int num, int size){
        String string = "FloorIndexOutOfBoundsException\nInvalid argument: " + num + "\nEnter number between" + " 0 and " + (size-1);
        throw new IndexOutOfBoundsException(string);
    }
}
