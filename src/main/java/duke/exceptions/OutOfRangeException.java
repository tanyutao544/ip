package duke.exceptions;

/**
 * Exception class when command detail is out of range of list
 */
public class OutOfRangeException extends DukeException {
    public OutOfRangeException() {

    }

    /**
     * returns error message from exception
     *
     * @return String containing details of error
     */
    @Override
    public String getMessage() {
        return "OOPS!!! The value input is not in the list";
    }
}
