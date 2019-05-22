package bean;

import java.util.Set;

public class Item {
    private int itemId; //璧勪骇ID
    private String itemName; //璧勪骇鍚�
    private int value; //璧勪骇浠峰��
    private int itemState; //璧勪骇鏄惁琚�熷嚭
    private int clubs; //浠庡睘鐨勭ぞ鍥�

    public Item() {
    }

    //getter and setter
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getClubs() {
        return clubs;
    }

    public void setClubs(int clubs) {
        this.clubs = clubs;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getItemState() {
        return itemState;
    }

    public void setItemState(int itemState) {
        this.itemState = itemState;
    }
}
