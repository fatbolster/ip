package kingsley;

import java.time.LocalDateTime;

public final class Scheduler {
    public static boolean overlap(Event a, Event b) {
        LocalDateTime aStart = a.getStartTime();
        LocalDateTime aEnd = a.getEndTime();
        LocalDateTime bStart = b.getStartTime();
        LocalDateTime bEnd = b.getEndTime();

        return aEnd.isAfter(bStart) && bEnd.isAfter(aStart);

    }
}
