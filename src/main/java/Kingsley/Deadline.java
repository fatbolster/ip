package Kingsley;

import java.time.LocalDateTime;

/**
 * A deadline is a task with a deadline (a date and time in which the task has to be finished by)
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a deadline task with the given description and deadline
     * @param description description of the task
     * @param by deadline of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.processDateTimeToString(by) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSaveFormat() {
        return "D | " + ( isDone ? 1 : 0 ) + " | "  + description + " | " + DateParser.processDateTimeToStorageString(by);
    }


}