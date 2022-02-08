package duke.commands;

import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * Class which handles the adding of commands to task list
 */
public class AddCommand extends Command {
    private final String input;
    private final String type;
    private TaskList tasks;
    private Task added;

    /**
     * Constructor for Add Command class
     * Creates correct Task based on user input and adds to task list
     * @param commandType
     * @param details
     */
    public AddCommand(String commandType, String details) {
        this.type = commandType;
        this.input = details;
    }

    /**
     * Method to get the modified task list after command execution
     * @return TaskList
     */
    @Override
    public TaskList getList() {
        return tasks;
    }

    /**
     * Method to see if command ends the main program loop
     * @return true if it ends main program
     */
    @Override
    public boolean endsProgram() {
        return false;
    }

    /**
     * Method to execute the add command
     * Adds respective task type depending on input
     * @param tasks tasks list to be modified
     * @param ui to help with printing of messages
     * @param storage To deal with saving of task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        this.tasks = tasks;
        String[] processedInput;
        switch (type) {
        case "todo":
            added = new ToDo(input);
            break;
        case "event":
            processedInput = input.split("/at", 2);
            assert processedInput[1] != null;
            added = new Event(processedInput[0], processedInput[1]);
            break;
        case "deadline":
            processedInput = input.split("/by", 2);
            assert processedInput[1] != null;
            added = new Deadline(processedInput[0], processedInput[1]);
            break;
        default:
            break;
        }
        this.tasks.add(added);
        storage.saveFile(tasks);

        return ui.addMessage(added, tasks.size());
    }
}
