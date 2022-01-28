package commands;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;
import storage.Storage;

/**
 * Class which handles the adding of commands to tasklist
 */
public class AddCommand extends Command {
	private String input;
	private String type;
	private TaskList tasks;
	private Task added;


	public AddCommand(String commandType, String details) {
		this.type = commandType;
		this.input = details;
	}

	/**
	 * Method to get the modified tasklist after command execution
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
	 * @param storage To deal with saving of tasklist
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		this.tasks = tasks;
		String[] processedInput;
		switch (type) {
		case "todo":
			added = new ToDo(input);
			break;
		case "event":
			processedInput = input.split("/at", 2);
			added = new Event(processedInput[0], processedInput[1]);
			break;
		case "deadline":
			processedInput = input.split("/by", 2);
			added = new Deadline(processedInput[0], processedInput[1]);
			break;
		}
		this.tasks.add(added);

		ui.printFormatted(new String[]{
				"Got it. I've added this task:",
				"  " + added,
				"Now you have " + tasks.size() + " tasks in the list"});
	}
}
