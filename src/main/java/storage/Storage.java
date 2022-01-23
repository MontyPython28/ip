package storage;

import exception.DukeException;
import task.TaskList;
import ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    static String PATH = "data";
    static String FILENAME = "todolist.txt";
    File file;

    /**
     * Creates .txt file according to PATH and FILENAME where todolist will be recorded.
     * @throws DukeException If there is a problem with creating the file.
     */
    public Storage() throws DukeException{
        File directory = new File(PATH + "/");
        if (! directory.exists()){
            boolean created = directory.mkdir();
        }

        this.file = new File(PATH, FILENAME);
        try {
            boolean isNotMadeYet = file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Sorry, storage file could not be created.");
        }

    }

    /**
     * Rewrites file with the new TaskList
     * @param taskList TaskList that will now be recorded in the file.
     * @throws DukeException If there is a problem with writing to the file.
     */
    public void writeToFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.file, false);
            for (int i=0; i < taskList.size(); i++) {
                fileWriter.write(taskList.get(i).fileFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Sorry, couldn't write to the file this time!");
        }
    }
}
