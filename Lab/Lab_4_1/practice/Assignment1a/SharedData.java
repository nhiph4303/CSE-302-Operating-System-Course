class SharedData {
    private int data = 0;

    public synchronized void increase() {
        data++;
    }

    public synchronized void decrease() {
        data--;
    }

    public int getData() {
        return data;
    }
}