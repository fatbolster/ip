import java.util.Scanner;

public class Kingsley {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        System.out.println("    ------------------------------------------");
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
            } else if (input.equals("list")) {
                System.out.println("    ___________________________________________");
                System.out.println("    Here are the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) {
                    int taskNumber = i + 1;
                    System.out.println("     " + taskNumber + ". " + tasks[i].toString());
                }
                System.out.println("    ___________________________________________");
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("    ___________________________________________");
                System.out.println("    added: " + input);
                System.out.println("    ___________________________________________");

            }



        }


        System.out.println("    ___________________________________________");
        System.out.println("    Bye! Hope to see you again soon!");
        System.out.println("    ___________________________________________");
    }
}
