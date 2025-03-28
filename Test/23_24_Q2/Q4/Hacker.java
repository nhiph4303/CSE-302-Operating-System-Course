package Q4;

public class Hacker extends Thread {
    private RiverCrossing crossing;

    public Hacker(RiverCrossing crossing) {
        this.crossing = crossing;
    }

    @Override
    public void run() {
        try {
            crossing.hackerArrives();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

