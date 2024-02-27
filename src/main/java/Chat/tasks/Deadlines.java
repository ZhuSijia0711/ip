package Chat.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadlines extends Task{
    //private String by;
    private LocalDateTime byDateTime;
    public Deadlines(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
        //this.time = by;
        this.type = TaskType.DEADLINE;
        shortType = this.type.name().substring(0, 1);
    }
    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String formattedDateTime = byDateTime.format(formatter);
        return "[" + shortType +"]" + super.toString() + "(by: "+ formattedDateTime +")";
    }
    public String getBy(){
        return by;
    }
    public LocalDateTime getByDate(){
        return byDate;
    }

    private boolean isValidDate(String by){
        return by.matches("\\d{2}/\\d{2}/\\d{4}");
    }


    public static LocalDateTime parseDateTime(String dateTimeString) {
            // Define the pattern to match the input format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

            // Parse the input string into a LocalDateTime object
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

        return dateTime;
        }
    }
}
