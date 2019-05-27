package bean;

import java.util.Set;

public class Item {
    private int Item_ID; //物品ID
    private String Item_Name; //物品名字
    private int Value; //物品价格

    public String toString(){
        return Item_ID+" "+Item_Name+"  "+Value;
    }

    public void setItem_ID(int item_ID) {
        Item_ID = item_ID;
    }

    public void setItem_Name(String item_Name) {
        Item_Name = item_Name;
    }

    public void setValue(int value) {
        Value = value;
    }

    public void setItem_State(int item_State) {
        Item_State = item_State;
    }

    public void setClub_ID(int club_ID) {
        Club_ID = club_ID;
    }

    public int getItem_ID() {
        return Item_ID;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public int getValue() {
        return Value;
    }

    public int getItem_State() {
        return Item_State;
    }

    public int getClub_ID() {
        return Club_ID;
    }

    private int Item_State; //物品状态 是否借用
    private int Club_ID; //所属社团

    public Item() {
    }



}
