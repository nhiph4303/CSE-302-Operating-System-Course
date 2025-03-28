package Q2;
public class Main {
    public static void main(String[] args) {
        // Tạo luồng mới
        Thread thread = new Thread(new Runner());
        thread.start();

        // Đợi luồng mới kết thúc (tương ứng với pthread_join)
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Cập nhật giá trị và in tại Line B
        Runner.setValue(Runner.getValue() + 15);
        System.out.println("B. Value = " + Runner.getValue()); // Line B
    }
}