package kingsley;

import java.util.List;

public class Ui {
    public String showGreeting() {
        return "Hello! I'm Kingsley. What can I do for you?";
    }

    public String showBye() {
        return "Bye! Hope to see you again soon!";
    }
    public String showMark(Task t) {
        return "Nice! I've marked this task as done:"
                + "\n" + "       " + t.toString();
    }

    public String showUnmark(Task t) {
        return "Nice! I've marked this task as not done yet:"
                + "\n" + "       " + t.toString();
    }

    public String showDeadline(Deadline d, int taskCount) {
        return "Got it. I've added this task:"
                + "        " + d.toString()
                + "\n"
                + "Now you have " + taskCount + " "
                + pluralize("task", taskCount) + " in the list.";
    }

    public String showToDo(Todo t, int taskCount) {
        return "Got it. I've added this task:"
                + "\n" + "        " + t.toString()
                + "\n"
                + "Now you have " + taskCount + " " + pluralize("task", taskCount)
                + " in the list.";
    }

    public String showEvent(Event e, int taskCount) {
        return "Got it. I've added this task:"
                + "\n" + "        " + e.toString()
                + "\n"
                + "Now you have " + taskCount + " " + pluralize("task", taskCount)
                + " in the list.";
    }

    public String showDelete(Task t, int taskCount) {
        return "Noted. I've removed this task."
                + "\n" + "        " + t.toString()
                + "\n"
                + "Now you have " + taskCount + " " + pluralize("task", taskCount)
                + " in the list.";
    }

    public String showList(List<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:").append("\n");

        for (int i = 0; i < taskList.size() ; i++) {
            int taskNumber = i + 1;
            Task currentTask = taskList.get(i);
            sb.append("     ").append(taskNumber).append(". ")
              .append(currentTask.toString()).append("\n");
        }

        return sb.toString();
    }

    public String showError(KingsleyException e) {
        return e.getMessage();
    }


    public String showFind(List<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:").append("\n");
        for (int i = 0; i < taskList.size() ; i++) {
            int taskNumber = i + 1;
            Task currentTask = taskList.get(i);
            sb.append("     ").append(taskNumber).append(". ")
              .append(currentTask.toString()).append("\n");
        }
        return sb.toString();
    }


    public String pluralize(String word, int n) {
        return n <= 1 ? word : word + "s";
    }



}
