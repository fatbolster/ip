package Kingsley;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

/**
 * Handles saving and loading of tasks to and from a source file respectively.
 */
public class Storage {
    private final String filePath;

    /**
     * Create a storage object which reads and loads with the input filepath.
     *
     * @param filePath file path of the file the storage object will communicate with
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves task list from storage file.
     *
     * @return an arraylist of tasks from the file path
     * @throws KingsleyException if input file does not exist
     */
    public ArrayList<Task> load() throws KingsleyException {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(filePath);

        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new KingsleyException("No such file exists!");
        }

        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            switch (type) {
                case "T":
                    Todo todo = new Todo(description);
                    if (isDone) {
                        todo.markAsDone();
                    }
                    taskList.add(todo);
                    break;
                case "E":
                    String time = parts[3];
                    String[] timePeriods = time.split("-");
                    String startTime = timePeriods[0];
                    LocalDateTime parsedStartDate = DateParser.processDateAndTime(startTime);
                    String endTime = timePeriods[1];
                    LocalDateTime parsedEndDate = DateParser.processDateAndTime(endTime);
                    Event event = new Event(description, parsedStartDate, parsedEndDate);
                    if (isDone) {
                        event.markAsDone();
                    }
                    taskList.add(event);
                    break;
                case "D":
                    String dueDate = parts[3];
                    LocalDateTime parsedDate = DateParser.processDateAndTime(dueDate);
                    Deadline deadline = new Deadline(description, parsedDate);
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    taskList.add(deadline);


            }
        }
        return taskList;
    }



    /**
     * Saves a given arraylist of task into storage file
     *
     * @throws IOException if problems are encountered when writing to the file
     */
    public void save(ArrayList<Task> taskList) throws IOException {
        File f = new File(filePath);
        File parent = f.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        FileWriter fw = new FileWriter(filePath);

        for (Task task: taskList) {
            fw.write(task.toSaveFormat());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

}
