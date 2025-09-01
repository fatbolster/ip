package Kingsley;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An event is a task with a time period, with a specified start time and end time.
 */
public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Creates an event with the input description, start time and end time.
     *
     * @param description the description of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateParser.processDateTimeToString(startTime)
                + " to: " + DateParser.processDateTimeToString(endTime) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E | " + ( isDone ? 1 : 0 ) + " | "  + description + " | " +
                DateParser.processDateTimeToStorageString(startTime)
                + "-" + DateParser.processDateTimeToStorageString(endTime);
    }



}
