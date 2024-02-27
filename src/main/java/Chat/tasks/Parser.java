package Chat.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Chat.exceptions.InvalidIndex;
public class Parser {
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    private static TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public static void parse(String input) throws InvalidIndex {
        String[] inputs = input.trim().split("\\s+", 2);
        String commandWord = inputs[0];
        String arguments = inputs.length > 1 ? inputs[1] : "";

        switch (commandWord) {
        case COMMAND_EXIT:
            //System.out.println("Bye. Hope to see you again soon!");
            break;
        case COMMAND_LIST:
            printList();
            printLine();
            break;
        case COMMAND_MARK:
            markTask(arguments);
            printLine();
            break;
        case COMMAND_UNMARK:
            unmarkTask(arguments);
            printLine();
            break;
        case COMMAND_TODO:
            addTodoTask(arguments);
            printLine();
            break;
        case COMMAND_DEADLINE:
            addDeadlineTask(arguments);
            printLine();
            break;
        case COMMAND_EVENT:
            addEventTask(arguments);
            printLine();
            break;
        case COMMAND_DELETE:
            deleteTask(arguments);
            printLine();
            break;
        default:
            System.out.println("Invalid command. Please try again.");
            printLine();
        }
    }

    private static void printList() {
        taskList.printList();
    }

    private static void markTask(String arguments) {
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            taskList.markTask(index);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + taskList.getTask(index).getStatusIcon() + " "
                    + taskList.getTask(index).description);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task index, please try again!");
        } catch (NumberFormatException e){
            System.out.println("Please key in a number after 'mark'");
        }
    }

    private static void unmarkTask(String arguments) {
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            taskList.unmarkTask(index);
            System.out.println("Nice! I've marked this task as not done:");
            System.out.println("  " + taskList.getTask(index).getStatusIcon() + " "
                    + taskList.getTask(index).description);
        } catch ( IndexOutOfBoundsException e) {
            System.out.println("Invalid task index, please try again!");
        } catch ( NumberFormatException e){
            System.out.println("Please key in a number after 'unmark'");
        }
    }

    private static void addTodoTask(String description) {
        if (!description.isEmpty()) {
            taskList.addTask(new Todos(description));
            System.out.println("Got it. I've added this task: " + description);
            System.out.println("Now you have " + (taskList.getSize()) + " tasks in the list.");
        } else {
            System.out.println("Please enter a description for the TODO task.");
        }
    }

    private static void addDeadlineTask(String arguments) {
        String[] parts = arguments.split("/by");
        if (parts.length != 2) {
            System.out.println("Invalid format for deadline command. Please use 'deadline <description> /by <date>'");
            return;
        }
        String description = parts[0].trim();
        String by = parts[1].trim();

        taskList.addTask(new Deadlines(description, by));
        System.out.println("Got it. I've added this task: " + description);
        System.out.println("Now you have " + (taskList.getSize()) + " tasks in the list.");
    }

    private static void addEventTask(String arguments) {
        String[] parts = arguments.split("/from");
        if (parts.length != 2) {
            System.out.println("Invalid format for event command. Please use 'event <description> /from <start> /to <end>'");
            return;
        }
        String description = parts[0].trim();
        String[] dates = parts[1].split("/to");
        if (dates.length != 2) {
            System.out.println("Invalid format for event command. Please use 'event <description> /from <start> /to <end>'");
            return;
        }
        String start = dates[0].trim();
        String end = dates[1].trim();
        taskList.addTask(new Events(description, start, end));
        System.out.println("Got it. I've added this task: " + description);
        System.out.println("Now you have " + (taskList.getSize()) + " tasks in the list.");
    }

    private static void deleteTask(String arguments) throws InvalidIndex {
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            taskList.deleteTask(index);
            System.out.println("Ok! Task " + (index + 1) + " is removed from the list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task index, please try again!");
        } catch (NumberFormatException e){
            System.out.println("Please key in a number after 'delete'");
        }
    }
    private static void printLine() {
        System.out.print("____________________________________________________________\n");
    }
}
