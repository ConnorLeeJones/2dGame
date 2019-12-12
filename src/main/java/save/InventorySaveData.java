package save;

import java.io.Serializable;

public class InventorySaveData implements Serializable {

    private int id;
    private int count;
    private static final long serialVersionUID = 1L;


    public InventorySaveData(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "InventorySaveData{" +
                "id=" + id +
                ", count=" + count +
                '}';
    }
}
