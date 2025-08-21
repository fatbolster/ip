import java.util.Scanner;

public class Kingsley {
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("    __________________________________________");
        System.out.println("    Hello! I'm Kingsley");
        System.out.println("    What can I do for you?");
        System.out.println("    __________________________________________");

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
        Task taskOfInterest = tasks[taskNumber];
        taskOfInterest.markAsDone();
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
        Task taskOfInterest = Kingsley.tasks[taskNumber];
        taskOfInterest.markAsUndone();
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
        Deadline deadlineTask = new Deadline(taskDescription, dueDate);
        tasks[taskCount] = deadlineTask;
        taskCount++;
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
        tasks[taskCount] = toDoTask;
        taskCount++;
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
        Event eventTask = new Event(taskDescription, startTime, endTime);
        tasks[taskCount] = eventTask;
        taskCount++;
        System.out.println("    ___________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + eventTask.toString());
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
        System.out.println("    ___________________________________________");
    }

    public static void handleList(String input) throws KingsleyException{
        if (taskCount == 0) {
            throw new KingsleyException("No tasks to show :D");
        }
        System.out.println("    ___________________________________________");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            int taskNumber = i + 1;
            System.out.println("     " + taskNumber + ". " + tasks[i].toString());
        }
        System.out.println("    ___________________________________________");
    }


}