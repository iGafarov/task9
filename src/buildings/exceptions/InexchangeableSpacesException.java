package buildings.exceptions;

public class InexchangeableSpacesException extends Error{
    public InexchangeableSpacesException(){
        String msg = "Inexchangeable Spaces!\n Choose another indexes: ";
        throw new Error(msg);
    }
}
