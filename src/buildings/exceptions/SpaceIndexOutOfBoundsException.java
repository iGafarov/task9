package buildings.exceptions;

public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException{
    public SpaceIndexOutOfBoundsException(int num, int size){
        String string = "SpaceIndexOutOfBoundsException\nInvalid argument: " + num + "\nEnter number between" + " 0 and " + (size-1);
        throw new IndexOutOfBoundsException(string);
    }
}
