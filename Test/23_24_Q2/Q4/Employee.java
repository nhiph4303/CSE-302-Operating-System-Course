package Q4;

public class Employee extends Thread {
    private RiverCrossing crossing;

    public Employee(RiverCrossing crossing) {
        this.crossing = crossing;
    }

    @Override
    public void run() {
        try {
            crossing.employeeArrives();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

