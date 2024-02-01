import java.util.Scanner;
public class Evelyn {
    public  static void echo(){
        String line;
        System.out.println("type your command: ");
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        printLine();
        if(line.equals("bye")){
            return;
        }
        else {
            System.out.println("this is your command: " + line);
            printLine();
            echo(); //create a loop
        }
    }
    public static void printLine(){
        System.out.print("____________________________________________________________\n");
    }
    public static void greeting(){
        printLine();
        System.out.println("Hello! I'm Evelyn");
        System.out.println("What can I do for you?");
        printLine();
    }
    public static void end(){
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public static void main(String[] args) {
        greeting();
        echo();
        end();
    }

}
