package Chat.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Events(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        this.time = from;
        this.type = TaskType.EVENT;
        shortType = this.type.name().substring(0, 1);
    }
    @Override
    public String toString() {
        return "[" + shortType + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
    public static LocalDateTime parseDateTime(String dateTimeString) {
        // Define the pattern to match the input format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        // Parse the input string into a LocalDateTime object
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

        return dateTime;

    }
    public LocalDateTime getFrom(){
        return from;
    }
    public LocalDateTime getTo() {
        return to;
    }
}
