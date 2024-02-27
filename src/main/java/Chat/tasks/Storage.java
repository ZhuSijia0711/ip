package Chat.tasks;

import Chat.tasks.TaskList;
import Chat.tasks.Task;
import Chat.exceptions.RepeatMark;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Storage {
    private static final String FILE_PATH = "tasks.txt";

    public Storage() {
        // Constructor if needed for any initialization
    }

    public TaskList loadTasks() throws FileNotFoundException {
        TaskList taskList = new TaskList();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String taskString = scanner.nextLine();
                    if(taskString != null) {
                        Task task = Task.fromString(taskString);
                        taskList.addTask(task);
                    }
                }
                scanner.close();
            } catch (IOException | RepeatMark e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }
        }
        return taskList;
    }

    public void saveTasks(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : taskList.getAllTasks()) {
                writer.write(task.shortType + " | " + task.numisDone() +
                        " | " + task.getDescription() + " | " + task.time + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
