package Chat.tasks;
import java.time.LocalDate;

import Chat.exceptions.RepeatMark;
import Chat.exceptions.RepeatUnmark;

import java.time.LocalDateTime;
import java.util.Objects;

import static Chat.tasks.Deadlines.parseDateTime;

public class Task {
    protected TaskType type;
    protected String shortType;
    protected String description;
    protected boolean isDone;
    public LocalDateTime time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = TaskType.TODO;
        this.time = null;
        shortType = this.type.name().substring(0, 1);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return  " " + getStatusIcon() + " " + getDescription();
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }
    public String numisDone(){
        return (isDone ? "1" : "0");
    }
    public void markAsDone() throws RepeatMark {
        if(isDone){
            throw new RepeatMark();
        }
        isDone = true;

    }

    public void markAsNotDone() throws RepeatUnmark {
        if(!isDone){
            throw new RepeatUnmark();
        }
        isDone = false;
    }
    public static Task fromString(String taskString) throws RepeatMark {
        String[] parts = taskString.split("\\|");
        String type = parts[0].trim();
        String description = parts[2].trim();
        String time = null;
        String from = null;
        String to = null;
        if(parts[3] != null) {
            time = parts[3].trim();
            if(time.contains("-")) {
                String[] splitTime = time.split("-");
                from = splitTime[0].trim();
                to = splitTime[1].trim();
            }
        }
        Task task = null;
        switch (type) {
        case "T":
            task = new Todos(description);
            if(Objects.equals(parts[1].trim(), "1")){
                task.isDone = true;
            }
            break;
        case "D":
            LocalDateTime deadlineTime = parseDateTime(time);
            // Assuming Deadlines constructor takes description and by as parameters
            task = new Deadlines(description, deadlineTime);
            if(Objects.equals(parts[1].trim(), "1")){
                task.isDone = true;
            }
            break;
        case "E":
            LocalDateTime eventFrom = parseDateTime(from);
            LocalDateTime eventTo = parseDateTime(to);
            // Assuming Events constructor takes description, from, and to as parameters
            task = new Events(description, eventFrom, eventTo);
            if(Objects.equals(parts[1].trim(), "1")){
                task.isDone = true;
            }
            break;
        // Add more cases for other task types if needed
        }
        return task;
    }
}
