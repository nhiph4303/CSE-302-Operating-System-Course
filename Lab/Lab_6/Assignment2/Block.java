package Assignment2;
public class Block {
    private String name;
    private int address;
    private int size;

    public Block(String name, int address, int size) {
        this.name = name;
        this.address = address;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getAddress() {
        return address;
    }

    public int getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
