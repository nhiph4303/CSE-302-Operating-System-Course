class IncreaseTask implements Runnable {
    private final SharedData data;
    private final int n;

    public IncreaseTask(SharedData data, int n) {
        this.data = data;
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            data.increase();
        }
    }
}