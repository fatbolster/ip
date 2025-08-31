import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    public static String[] split(String input) throws KingsleyException {
        if (input == null) {
            throw new KingsleyException("Empty Command :(");
        }

        String[] parts = input.split("\\s+", 2 );
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";
        return new String[] { commandWord, arguments };
    }

    public static void parseMark (String input, TaskList tasks, Storage storage, Ui ui) throws KingsleyException {
        if (input.trim().isEmpty()) {
            throw new KingsleyException("Need a number to indicate what task to mark");
        }
        int taskNumber = Integer.parseInt(input.trim()) - 1;
        if (taskNumber < 0) {
            throw new KingsleyException("We only use positive task numbers here :(");
        }
        if (taskNumber >= tasks.size()) {
            throw new KingsleyException("Task number given is bigger than your total number of tasks!");
        }
        Task taskOfInterest = tasks.get(taskNumber);
        taskOfInterest.markAsDone();
        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.showMark(taskOfInterest);
    }

    public static void parseUnmark(String input, TaskList tasks, Storage storage, Ui ui) throws KingsleyException {
        if (input.trim().isEmpty()) {
            throw new KingsleyException("Need a number to indicate what task to mark");
        }
        int taskNumber = Integer.parseInt(input.trim()) - 1;
        if (taskNumber < 0) {
            throw new KingsleyException("We only use positive task numbers here :(");
        }
        if (taskNumber >= tasks.size()) {
            throw new KingsleyException("Task number given is bigger than your total number of tasks!");
        }
        Task taskOfInterest = tasks.get(taskNumber);
        taskOfInterest.markAsUndone();

        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.showUnmark(taskOfInterest);
    }


    public static void parseDeadline(String input, TaskList tasks, Storage storage, Ui ui) throws KingsleyException {
        int byPos = input.indexOf("/by");
        if (byPos == -1) {
            throw new KingsleyException("/by missing for separation of description and deadline");
        }
        String taskDescription = input.substring(0, byPos).trim();
        if (taskDescription.isEmpty()) {
            throw new KingsleyException("Missing name for deadline task");
        }
        String dueDate = input.substring(byPos + 3).trim();
        if (dueDate.isEmpty()) {
            throw new KingsleyException("Deadline task must have a deadline :3");
        }
        Deadline deadlineTask;
        try {
            LocalDateTime dt = DateParser.processDateAndTime(dueDate);
            deadlineTask = new Deadline(taskDescription, dt);
        } catch (DateTimeParseException e) {
            throw new KingsleyException("Invalid format for date and time for deadline (dd/mm/yyyy HHmm)");
        }

        tasks.add(deadlineTask);
        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.showDeadline(deadlineTask, tasks.size());
    }

    public static void parseToDo(String input, TaskList tasks, Storage storage, Ui ui)  throws KingsleyException {
        String taskDescription = input.trim();
        if (taskDescription.isEmpty()) {
            throw new KingsleyException("Please write a description for your todo");
        }
        Todo toDoTask = new Todo(taskDescription);
        tasks.add(toDoTask);
        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.showToDo(toDoTask, tasks.size());
    }

    public static void parseEvent(String input, TaskList tasks, Storage storage, Ui ui) throws KingsleyException {
        int fromPos = input.indexOf("/from");
        if (fromPos == -1) {
            throw new KingsleyException("/from missing for separation of task description and deadline");
        }
        int toPos = input.indexOf("/to");
        if (toPos == -1) {
            throw new KingsleyException("/to missing for separation of start date and end date");
        }
        String taskDescription = input.substring(0, fromPos).trim();
        if (taskDescription.isEmpty()) {
            throw new KingsleyException("Event must have a description");
        }
        String startTime = input.substring(fromPos + 5, toPos).trim();
        if (startTime.isEmpty()) {
            throw new KingsleyException("Event must have a start time");
        }
        String endTime = input.substring(toPos + 3).trim();
        if (endTime.isEmpty()) {
            throw new KingsleyException("Event must have an end time");
        }

        LocalDateTime formattedStartTime;
        LocalDateTime formattedEndTime;

        try {
            formattedStartTime = DateParser.processDateAndTime(startTime);
        } catch (DateTimeParseException e) {
            throw new KingsleyException("Invalid format for start date and time for event (dd/mm/yyyy HHmm)");
        }

        try {
            formattedEndTime = DateParser.processDateAndTime(endTime);
        } catch (DateTimeParseException e) {
            throw new KingsleyException("Invalid format for end date and time for event (dd/mm/yyyy HHmm)");
        }

        if (formattedEndTime.isBefore(formattedStartTime)) {
            throw new KingsleyException("End time must be after start time :D");
        }

        Event eventTask = new Event(taskDescription, formattedStartTime, formattedEndTime);
        tasks.add(eventTask);
        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.showEvent(eventTask, tasks.size());
    }

    public static void parseDelete(String input, TaskList tasks, Storage storage, Ui ui) throws KingsleyException {
        if (input.trim().isEmpty()) {
            throw new KingsleyException("Need a number to indicate what task to mark");
        }
        int taskNumber = Integer.parseInt(input.trim()) - 1;
        if (taskNumber < 0) {
            throw new KingsleyException("We only use positive task numbers here :(");
        }
        if (taskNumber >= tasks.size()) {
            throw new KingsleyException("Task number given is bigger than your total number of tasks!");
        }
        Task deletedTask = tasks.remove(taskNumber);
        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        ui.showDelete(deletedTask, tasks.size());
    }

    public static void parseList(TaskList tasks, Ui ui) throws KingsleyException {
        if (tasks.size() == 0) {
            throw new KingsleyException("No tasks to show :D");
        }
        ui.showList(tasks.getTaskList());
    }


}
