package duke.exceptions;

/**
 * Exception class when command detail is of incorrect value
 */
public class IncorrectValueException extends DukeException {
    public IncorrectValueException() {}

    /**
     * returns error message from exception
     *
     * @return String containing details of error
     */
    @Override
    public String getMessage() {
        return "OOPS!!! The value input is incorrect";
    }
}
