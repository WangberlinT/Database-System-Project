package bean;

import java.util.Set;

public class Item {
    private int itemId; //物品ID
    private String itemName; //物品名字
    private int value; //物品价格
    private int itemState; //物品状态 是否借用
    private int clubs; //所属社团

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
