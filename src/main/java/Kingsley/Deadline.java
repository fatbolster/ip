package Kingsley;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;


    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.processDateTimeToString(by) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + ( isDone ? 1 : 0 ) + " | "  + description + " | " + DateParser.processDateTimeToStorageString(by);
    }


}