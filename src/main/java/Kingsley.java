import java.util.Scanner;

public class Kingsley {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("    ------------------------------------------");
        System.out.println("    Hello! I'm Kingsley");
        System.out.println("    What can I do for you?");
        System.out.println("    ------------------------------------------");

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println("    ------------------------------------------");
            System.out.println("    " + input);
            System.out.println("    ------------------------------------------");


        }


        System.out.println("    ------------------------------------------");
        System.out.println("    Bye! Hope to see you again soon!");
        System.out.println("    ------------------------------------------");
    }
}
