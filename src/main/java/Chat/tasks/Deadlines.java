package Chat.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task{
    //private String by;
    private LocalDateTime byDateTime;
    public Deadlines(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
        this.time = byDateTime;
        this.type = TaskType.DEADLINE;
        shortType = this.type.name().substring(0, 1);
    }
    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        //String formattedDateTime = byDateTime.format(formatter);
        return "[" + shortType +"]" + super.toString() + "(by: "+ byDateTime +")";
    }

    private boolean isValidDate(String by){
        return by.matches("\\d{2}/\\d{2}/\\d{4}");
    }


    public static LocalDateTime parseDateTime(String dateTimeString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            // If parsing fails, try parsing with ISO-8601 format
            DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            return LocalDateTime.parse(dateTimeString, isoFormatter);
        }

    }
    public LocalDateTime getBy() {
        return byDateTime;
    }
}
