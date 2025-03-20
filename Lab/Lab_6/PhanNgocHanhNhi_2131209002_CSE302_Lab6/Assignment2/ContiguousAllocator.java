package Assignment2;
import java.util.*;

public class ContiguousAllocator {
    private int memorySize; // Tổng bộ nhớ
    private List<Block> blocksList = new ArrayList<Block>(); 

    // Constructor khởi tạo bộ nhớ
    public ContiguousAllocator(int memorySize) {
        this.memorySize = memorySize;
        this.blocksList.clear();
        Block block = new Block(null, 0, this.memorySize); 
        this.blocksList.add(block);
    }

    public void changeMemorySize(int memorySize) {
        this.memorySize = memorySize;
        this.blocksList.clear();
        Block block = new Block(null, 0, this.memorySize); 
        this.blocksList.add(block);
    }

    public boolean requestFirstFit(String name, int size) {
        for (Block block : blocksList) {
            if (block.getName() == null && block.getSize() >= size) { 
                block.setName(name);
                if (block.getSize() > size) {
                    blocksList.add(new Block(null, block.getAddress() + size, block.getSize() - size));
                    block.setSize(size);
                }
                return true;
            }
        }
        return false; 
    }

    public boolean requestBestFit(String name, int size) {
        Block bestBlock = null;
        for (Block block : blocksList) {
            if (block.getName() == null && block.getSize() >= size) { 
                if (bestBlock == null || block.getSize() < bestBlock.getSize()) {
                    bestBlock = block;
                }
            }
        }
        if (bestBlock != null) {
            bestBlock.setName(name);
            if (bestBlock.getSize() > size) {
                blocksList.add(new Block(null, bestBlock.getAddress() + size, bestBlock.getSize() - size));
                bestBlock.setSize(size);
            }
            return true;
        }
        return false; 
    }
    public boolean requestWorstFit(String name, int size) {
        Block worstBlock = null;
        for (Block block : blocksList) {
            if (block.getName() == null && block.getSize() >= size) {
                if (worstBlock == null || block.getSize() > worstBlock.getSize()) {
                    worstBlock = block;
                }
            }
        }
        if (worstBlock != null) {
            worstBlock.setName(name);
            if (worstBlock.getSize() > size) {
                blocksList.add(new Block(null, worstBlock.getAddress() + size, worstBlock.getSize() - size));
                worstBlock.setSize(size);
            }
            return true;
        }
        return false; 
    }

    public void releaseMemory(String name) {
        for (Block block : blocksList) {
            if (block.getName() != null && block.getName().equals(name)) {
                block.setName(null);
                mergeFreeBlocks();  
                return;
            }
        }
    }

    private void mergeFreeBlocks() {
        Collections.sort(blocksList, Comparator.comparingInt(Block::getAddress)); 
        for (int i = 0; i < blocksList.size() - 1; i++) {
            Block current = blocksList.get(i);
            Block next = blocksList.get(i + 1);
            if (current.getName() == null && next.getName() == null) {
                current.setSize(current.getSize() + next.getSize()); 
                blocksList.remove(i + 1); 
                i--;  
            }
        }
    }

    
    public void compactMemory() {
        mergeFreeBlocks();  
        int address = 0;
        for (Block block : blocksList) {
            if (block.getName() != null) {  
                block.setAddress(address);
                address += block.getSize();
            }
        }
    }

    // Print current memory status
    public void stat() {
        for (Block block : blocksList) {
            System.out.println("Addresses [" + block.getAddress() + ":" + (block.getAddress() + block.getSize() - 1)
                    + "] " + (block.getName() == null ? "Unused" : "Process " + block.getName()));
        }
    }
}
