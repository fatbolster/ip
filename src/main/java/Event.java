import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateParser.processDateTimeToString(startTime)
                + " to: " + DateParser.processDateTimeToString(endTime) + ")";
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E | " + ( isDone ? 1 : 0 ) + " | "  + description + " | " +
                DateParser.processDateTimeToStorageString(startTime)
                + "-" + DateParser.processDateTimeToStorageString(endTime);
    }



}
