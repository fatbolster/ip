import java.util.Scanner;

public class Kingsley {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
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
            if (input.equals("list")) {
                System.out.println("    ___________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    int taskNumber = i + 1;
                    System.out.println("    " + taskNumber + ". " + tasks[i]);
                }
                System.out.println("    ___________________________________________");
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("    ___________________________________________");
                System.out.println("     added: " + input);
                System.out.println("    ___________________________________________");

            }



        }


        System.out.println("    ___________________________________________");
        System.out.println("    Bye! Hope to see you again soon!");
        System.out.println("    ___________________________________________");
    }
}
