package Assignment2;

public interface ReplacementPolicy {
    public Result refer(int value);
    public void remove(int value);

    public class Result {
        private boolean found;
        private int value;
        private int position;
        private int replacedValue;

        public Result(boolean found, int value, int position, int replacedValue) {
            this.found = found;
            this.value = value;
            this.position = position;
            this.replacedValue = replacedValue;
        }

        public boolean isFound() {
            return found;
        }

        public int getValue() {
            return value;
        }

        public int getPosition() {
            return position;
        }

        public int getReplacedValue() {
            return replacedValue;
        }
    }
}
