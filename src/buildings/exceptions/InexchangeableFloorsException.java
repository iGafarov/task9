package buildings.exceptions;

public class InexchangeableFloorsException extends Error{
    public InexchangeableFloorsException(){
        String msg = "Inexchangeable Floors!\n Choose another indexes.";
        throw new Error(msg);
    }
}
