package commands;

import exceptions.OutOfRangeException;
import storage.Storage;
import tasks.*;
import ui.Ui;

/**
 * Class which handles deletion of tasks in list
 */
public class DeleteCommand extends Command {
	private TaskList tasks;
	private int deleteIndex;
	private Task deleted;

	public DeleteCommand(int deleteIndex) {
		this.deleteIndex = deleteIndex - 1;

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
	 * Method to execute the delete command
	 * Deletes the task from the input index
	 * @param tasks tasks list to be modified
	 * @param ui to help with printing of messages
	 * @param storage To deal with saving of tasklist
	 */
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException {
		this.tasks = tasks;
		if (deleteIndex >= tasks.size()) {
			throw new OutOfRangeException();
		}
		deleted = tasks.get(deleteIndex);
		this.tasks.remove(deleteIndex);

		ui.printFormatted(new String[]{
				"Noted. I've removed this task:",
				"  " + deleted,
				"Now you have " + this.tasks.size() + " tasks in the list"});
	}
}
