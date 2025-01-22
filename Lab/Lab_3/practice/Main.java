import java.io.*;

public class Main {
    public static final String TASK_FILE = "schedule.txt";

    public static void main(String[] args) {
        try {
            BufferedReader rd = new BufferedReader(new FileReader(Main.TASK_FILE));
            String line;
            line = rd.readLine();
            while (line!=null){
                String [] components = line.split(",");
                System.out.println(components[0]);
                System.out.println(components[1]);
                System.out.println(components[2]);
                System.out.println("------------------------------------");
                line = rd.readLine();
            }
        } catch (FileNotFoundException e) {
            // System.out.println("File not found " + e.getMessage());
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}