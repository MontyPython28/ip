package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;

public class DeleteCommand extends Command {
    private int serialNumber;

    public DeleteCommand(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Deletes Task from TaskList that has the serialNumber given.
     * Also overwrite Storage.
     *
     * @param storage Storage for rewriting TaskList.
     * @param taskList TaskList that stores Tasks.
     * @throws DukeException If index number out of bounds or problems with writing to Storage.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        if (this.serialNumber < 1 || this.serialNumber > taskList.size()) {
            throw new DukeException("Have you entered the correct number?");
        }
        String taskDescription = taskList.get(this.serialNumber - 1).getDescription();
        taskList.delete(this.serialNumber);
        storage.writeToFile(taskList);
        return "Noted. I've removed this task:\n"
                + taskDescription
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }
}
