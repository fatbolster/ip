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
            if (input.equals("bye")) {
                break;
            }
            if (input.startsWith("mark")) {
                String[] parts = input.split(" ");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                Task taskOfInterest = tasks[taskNumber];
                taskOfInterest.markAsDone();
                System.out.println("    ___________________________________________");
                System.out.println("    Nice! I've marked this task as done: ");
                System.out.println("       " + taskOfInterest.toString());
                System.out.println("    ___________________________________________");
            } else if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                Task taskOfInterest = tasks[taskNumber];
                taskOfInterest.markAsUndone();
                System.out.println("    ___________________________________________");
                System.out.println("    OK, I've marked this task as not done yet: ");
                System.out.println("       " + taskOfInterest.toString());
                System.out.println("    ___________________________________________");
            } else if (input.startsWith("deadline")) {
                int byPos = input.indexOf("/by");
                String taskDescription = input.substring("deadline".length(), byPos).trim();
                String dueDate = input.substring(byPos + 3).trim();
                Deadline deadlineTask = new Deadline(taskDescription, dueDate);
                tasks[taskCount] = deadlineTask;
                taskCount++;
                System.out.println("    ___________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + deadlineTask.toString());
                System.out.println("    Now you have " + taskCount + " tasks in the list.");
                System.out.println("    ___________________________________________");
            } else if (input.startsWith("event")) {
                int fromPos = input.indexOf("/from");
                int toPos = input.indexOf("/to");
                String taskDescription = input.substring("event".length(), fromPos).trim();
                String startTime = input.substring(fromPos + 5, toPos).trim();
                String endTime = input.substring(toPos + 3).trim();
                Event eventTask = new Event(taskDescription, startTime, endTime);
                tasks[taskCount] = eventTask;
                taskCount++;
                System.out.println("    ___________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + eventTask.toString());
                System.out.println("    Now you have " + taskCount + " tasks in the list.");
                System.out.println("    ___________________________________________");
            } else if (input.startsWith("todo")) {
                String taskDescription = input.substring("todo".length()).trim();
                Todo toDoTask = new Todo(taskDescription);
                tasks[taskCount] = toDoTask;
                taskCount++;
                System.out.println("    ___________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + toDoTask.toString());
                System.out.println("    Now you have " + taskCount + " tasks in the list.");
                System.out.println("    ___________________________________________");
            } else if (input.equals("list")) {
                System.out.println("    ___________________________________________");
                System.out.println("    Here are the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) {
                    int taskNumber = i + 1;
                    System.out.println("     " + taskNumber + ". " + tasks[i].toString());
                }
                System.out.println("    ___________________________________________");
            } else {
                System.out.println("Invalid Input");
            }

        }


        System.out.println("    ___________________________________________");
        System.out.println("    Bye! Hope to see you again soon!");
        System.out.println("    ___________________________________________");
    }
}
