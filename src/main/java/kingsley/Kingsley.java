package kingsley;

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

    private boolean isExit = false;

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
     * Communicates with the Parser of the application to understand user inputs
     *
     * Function will take in one user input at a time and respond accordingly with a corresponding String
     *
     **/
    public String getResponse(String input) {
            try {
                String[] parts = Parser.split(input);
                String command = parts[0];
                String arguments = parts[1];
                if (command.equals("mark")) {
                    return Parser.parseMark(arguments, tasks, storage, ui);
                } else if (command.equals("unmark")) {
                    return Parser.parseUnmark(arguments, tasks, storage, ui);
                } else if (command.equals("deadline")) {
                    return Parser.parseDeadline(arguments, tasks, storage, ui);
                } else if (command.equals("delete")) {
                    return Parser.parseDelete(arguments, tasks, storage, ui);
                } else if (command.equals("event")) {
                    return Parser.parseEvent(arguments, tasks, storage, ui);
                } else if (command.equals("todo")) {
                    return Parser.parseToDo(arguments, tasks, storage, ui);
                } else if (command.equals("list")) {
                    return Parser.parseList(tasks, ui);
                } else if (command.equals("find")) {
                    return Parser.parseFind(arguments, tasks, ui);
                } else if (command.equals("bye")) {
                    this.isExit = true;
                    return Parser.parseBye(ui);
                } else {
                    throw new KingsleyException("No such command exists :(");
                }
            } catch (KingsleyException e) {
                return ui.showError(e);
            }

    }

    public boolean getExitStatus() {
        return this.isExit;
    }

    public String getGreeting() {
        return ui.showGreeting();
    }


    /**
     * Entry point of the application
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Hello!");
    }












}