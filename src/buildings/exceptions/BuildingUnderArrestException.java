package buildings.exceptions;

import java.io.IOException;
import java.io.Serializable;

public class BuildingUnderArrestException extends IOException implements Serializable {
    public BuildingUnderArrestException() {
        String message = "Building is arrested!";
        try {
            throw new Exception(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
