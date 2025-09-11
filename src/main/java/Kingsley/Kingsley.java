package Kingsley;

import java.util.Scanner;

/**
 * Represents the Kingsley Chatbot application
 *
 * Initializes UI, storage and task list
 * It will respond to user according to his/her input commands until he/she exits the program
 */
public class Kingsley {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a new instance of the Kingsley Chatbot
     *
     * @param filePath file path where task data is stored
     */
    public Kingsley(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (KingsleyException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application
     *
     * The chatbot greets the user greeting first. It then repeatedly takes in user commands until the user types "bye"
     *
     * Chatbot will send a goodbye message to user upon exiting loop
     *
     **/
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showGreeting();

        while (true) {
            String input = sc.nextLine();
            if (input.startsWith("bye")) {
                break;
            }
            try {
                String[] parts = Parser.split(input);
                String command = parts[0];
                String arguments = parts[1];
                if (command.equals("mark")) {
                    Parser.parseMark(arguments, tasks, storage, ui);
                } else if (command.equals("unmark")) {
                    Parser.parseUnmark(arguments, tasks, storage, ui);
                } else if (command.equals("deadline")) {
                    Parser.parseDeadline(arguments, tasks, storage, ui);
                } else if (command.equals("delete")) {
                    Parser.parseDelete(arguments, tasks, storage, ui);
                } else if (command.equals("event")) {
                    Parser.parseEvent(arguments, tasks, storage, ui);
                } else if (command.equals("todo")) {
                    Parser.parseToDo(arguments, tasks, storage, ui);
                } else if (command.equals("list")) {
                    Parser.parseList(tasks, ui);
                } else if (command.equals("find")) {
                    Parser.parseFind(arguments, tasks, ui);
                } else {
                    throw new KingsleyException("No such command exists :(");
                }
            } catch (KingsleyException e) {
                ui.showError(e);
            }

        }

        ui.showBye();
    }

    /**
     * Entry point of the application
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new Kingsley("./data/kingsley.txt").run();
    }












}