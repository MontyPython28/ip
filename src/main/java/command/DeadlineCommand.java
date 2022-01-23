package command;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    String message;
    String time;

    public DeadlineCommand(String message, String time) {
        this.message = message;
        this.time = time;
    }

    /**
     * Add Deadline Task to TaskList.
     * Also overwrite Storage.
     *
     * @param ui Ui for outputting message.
     * @param storage Storage for rewriting TaskList.
     * @param taskList TaskList that stores Tasks.
     * @throws DukeException If time is in wrong format or problems with writing to Storage.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException { //throw exception if necessary
        try {
            taskList.add(new Deadline(this.message, false, LocalDate.parse(this.time)));
        } catch (DateTimeParseException e) {
            throw new DukeException("Have you entered the date in yyyy-mm-dd format?");
        }
        storage.writeToFile(taskList);
        ui.outputMessage("Got it. I've added this task: \n" +
                taskList.get(taskList.size() - 1) +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
