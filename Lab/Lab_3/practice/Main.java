
public class Main {
    public static final String TASK_FILE = "schedule.txt";

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Main.TASK_FILE));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }
}