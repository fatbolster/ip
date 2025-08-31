import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Kingsley {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Kingsley(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (KingsleyException e) {
            this.tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showGreeting();

        while (true) {
            String input = sc.nextLine();
            if (input.startsWith("bye")) {
                break;
            }
            try {
                if (input.startsWith("mark")) {
                    handleMark(input);
                } else if (input.startsWith("unmark")) {
                    handleUnmark(input);
                } else if (input.startsWith("deadline")) {
                    handleDeadline(input);
                } else if (input.startsWith("delete")) {
                    handleDelete(input);
                } else if (input.startsWith("event")) {
                    handleEvent(input);
                } else if (input.startsWith("todo")) {
                    handleToDo(input);
                } else if (input.equals("list")) {
                    handleList(input);
                } else {
                    throw new KingsleyException("No such command exists :(");
                }
            } catch (KingsleyException e) {
                System.out.println("    __________________________________________");
                System.out.println("    " + e.getMessage());
                System.out.println("    __________________________________________");
            }

        }

        System.out.println("    ___________________________________________");
        System.out.println("    Bye! Hope to see you again soon!");
        System.out.println("    ___________________________________________");
    }


    public static void handleMark(String input) throws KingsleyException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new KingsleyException("Need a number to indicate what task to mark");
        }
        int taskNumber = Integer.parseInt(parts[1]) - 1;
        if (taskNumber < 0) {
            throw new KingsleyException("We only use positive task numbers here :(");
        }
        if (taskNumber >= taskCount) {
            throw new KingsleyException("Task number given is bigger than your total number of tasks!");
        }
        Task taskOfInterest = tasks.get(taskNumber);
        taskOfInterest.markAsDone();
        try {
            LOCAL_STORAGE.save(new ArrayList<>(tasks));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("    ___________________________________________");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("       " + taskOfInterest.toString());
        System.out.println("    ___________________________________________");
    }

    public static void handleUnmark(String input) throws KingsleyException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new KingsleyException("Need a number to indicate what task to mark");
        }
        int taskNumber = Integer.parseInt(parts[1]) - 1;
        if (taskNumber < 0) {
            throw new KingsleyException("We only use positive task numbers here :(");
        }
        if (taskNumber >= taskCount) {
            throw new KingsleyException("Task number given is bigger than your total number of tasks!");
        }
        Task taskOfInterest = tasks.get(taskNumber);
        taskOfInterest.markAsUndone();
        Deadline deadlineTask;

        try {
            LOCAL_STORAGE.save(new ArrayList<>(tasks));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("    ___________________________________________");
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("       " + taskOfInterest.toString());
        System.out.println("    ___________________________________________");
}
    public static void handleDeadline(String input) throws KingsleyException {
        int byPos = input.indexOf("/by");
        if (byPos == -1) {
            throw new KingsleyException("/by missing for separation of description and deadline");
        }
        String taskDescription = input.substring("deadline".length(), byPos).trim();
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
        taskCount++;
        try {
            LOCAL_STORAGE.save(new ArrayList<>(tasks));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("    ___________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + deadlineTask.toString());
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
        System.out.println("    ___________________________________________");
    }

    public static void handleToDo(String input) throws KingsleyException {
        String taskDescription = input.substring("todo".length()).trim();
        if (taskDescription.isEmpty()) {
            throw new KingsleyException("Please write a description for your todo");
        }
        Todo toDoTask = new Todo(taskDescription);
        tasks.add(toDoTask);
        taskCount++;
        try {
            LOCAL_STORAGE.save(new ArrayList<>(tasks));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("    ___________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + toDoTask.toString());
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
        System.out.println("    ___________________________________________");
    }

    public static void handleEvent(String input) throws KingsleyException {
        int fromPos = input.indexOf("/from");
        if (fromPos == -1) {
            throw new KingsleyException("/from missing for separation of task description and deadline");
        }
        int toPos = input.indexOf("/to");
        if (toPos == -1) {
            throw new KingsleyException("/to missing for separation of start date and end date");
        }
        String taskDescription = input.substring("event".length(), fromPos).trim();
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

    public void handleList() throws KingsleyException {
        if (tasks.size() == 0) {
            throw new KingsleyException("No tasks to show :D");
        }
        ui.showList(tasks.getTaskList());
    }

    public void handleDelete(String input) throws KingsleyException {
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


}