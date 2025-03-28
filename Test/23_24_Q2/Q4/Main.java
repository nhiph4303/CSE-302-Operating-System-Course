package Q4;

public class Main {

    public static void main(String[] args) {
        // Khởi tạo đối tượng RiverCrossing
        RiverCrossing crossing = new RiverCrossing();
        
        // Tạo các thread cho hacker và employee
        Thread hacker1 = new Hacker(crossing);
        Thread hacker2 = new Hacker(crossing);
        Thread employee1 = new Employee(crossing);
        Thread employee2 = new Employee(crossing);
        
        // Bắt đầu các tiến trình (threads)
        hacker1.start();
        hacker2.start();
        employee1.start();
        employee2.start();
    }
}

