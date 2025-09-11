package kingsley;

/**
 * A Todo is a type of task with only a description.
 */
public class Todo extends Task {
    /**
     * Creates a todo event with the input description
     *
     * @param description description of todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + ( isDone ? 1 : 0 ) + " | "  + description;
    }

}
