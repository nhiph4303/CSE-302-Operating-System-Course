package Q2;
public class Runner implements Runnable {
    private static int value = 10; // Biến toàn cục, chia sẻ giữa các luồng

    @Override
    public void run() {
        value += 20;
        System.out.println("A. Value = " + value); // Line A
    }

    public static int getValue() {
        return value;
    }

    public static void setValue(int newValue) {
        value = newValue;
    }
}