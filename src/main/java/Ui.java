import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private static String NEW_LINE = "    __________________________________________";

    public void showGreeting() {
        printLine();
        System.out.println("    Hello! I'm Kingsley");
        System.out.println("    What can I do for you?");
        printLine();
    }

    public void showBye() {
        printLine();
        System.out.println("    Bye! Hope to see you again soon!");
        printLine();
    }
    public void showMark(Task t) {
        printLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("       " + t.toString());
        printLine();
    }

    public void showUnmark(Task t) {
        printLine();
        System.out.println("    Nice! I've marked this task as not done yet:");
        System.out.println("       " + t.toString());
        printLine();
    }

    public void showDeadline(Deadline d, int taskCount) {
        printLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + d.toString());
        System.out.println("    Now you have " + taskCount + " " + pluralize("task", taskCount) + " in the list.");
        printLine();
    }

    public void showToDo(Todo t, int taskCount) {
        printLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + t.toString());
        System.out.println("    Now you have " + taskCount + " " + pluralize("task", taskCount) + " in the list.");
        printLine();
    }

    public void showEvent(Event e, int taskCount) {
        printLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + e.toString());
        System.out.println("    Now you have " + taskCount + " " + pluralize("task", taskCount) + " in the list.");
        printLine();
    }

    public void showDelete(Task t, int taskCount) {
        printLine();
        System.out.println("    Noted. I've rempved this task.");
        System.out.println("       " + t.toString());
        System.out.println("    Now you have " + taskCount + " " + pluralize("task", taskCount) + " in the list.");;
        printLine();
    }

    public void showList(List<Task> taskList) {
        printLine();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskList.size() ; i++) {
            int taskNumber = i + 1;
            Task currentTask = taskList.get(i);
            System.out.println("     " + taskNumber + ". " + currentTask.toString());
        }
        printLine();
    }

    public void showError(KingsleyException e) {
        printLine();
        System.out.println("    " + e.getMessage());
        printLine();
    }

    public void printLine() {
        System.out.println(NEW_LINE);
    }

    public String pluralize(String word, int n) {
        return n <= 1 ? word : word + "s";
    }






}
