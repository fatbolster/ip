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
                String[] parts = Parser.split(input);
                String command = parts[0];
                String arguments = parts[1];
                if (command.equals("mark")) {
                    Parser.parseMark(arguments, tasks, storage, ui);
                } else if (command.equals("unmark")) {
                    Parser.parseUnmark(arguments, tasks, storage, ui);
                } else if (command.equals("deadline")) {
                    Parser.parseDeadline(arguments, tasks, storage, ui);
                } else if (input.startsWith("delete")) {
                    Parser.parseDelete(arguments, tasks, storage, ui);
                } else if (input.startsWith("event")) {
                    Parser.parseEvent(arguments, tasks, storage, ui);
                } else if (input.startsWith("todo")) {
                    Parser.parseToDo(arguments, tasks, storage, ui);
                } else if (input.equals("list")) {
                    Parser.parseList(tasks, ui);
                } else {
                    throw new KingsleyException("No such command exists :(");
                }
            } catch (KingsleyException e) {
                ui.showError(e);
            }

        }

        ui.showBye();
    }

    public static void main(String[] args) {
        new Kingsley("./data/kingsley.txt").run();
    }












}