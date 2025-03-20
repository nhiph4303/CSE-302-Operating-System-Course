package Assignment1;

public class SharedData {
    private int value = 0; // Biến dùng chung (dễ bị xung đột)

    public SharedData(int v){
        this.value = v;
    }


    // Tăng giá trị (không đồng bộ)
    public void increment() {
        value++;
    }


    // Giảm giá trị (không đồng bộ)
    public void decrement() {
        value--;
    }

    // Đặt lại giá trị về 0
    public void reset() {
        value = 0;
    }


    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
