import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;


public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
