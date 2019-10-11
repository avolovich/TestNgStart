package app;

import java.time.DayOfWeek;

public class DuplicateUserException extends Exception {

    public DuplicateUserException(String message) {
        super(message);
    }


}
