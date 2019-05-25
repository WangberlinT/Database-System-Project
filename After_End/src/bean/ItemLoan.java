package bean;

import java.sql.Timestamp;
import java.util.Date;

public class ItemLoan {
	private int Item_ID;
	private String Item_Name;
	private int Item_Value;
	private int User_ID;
	private String Name;
	private Timestamp Start_Time;
	private String Phone_Number;

    public int getItem_ID() {
		return Item_ID;
	}

	public void setItem_ID(int item_ID) {
		Item_ID = item_ID;
	}

	public String getItem_Name() {
		return Item_Name;
	}

	public void setItem_Name(String item_Name) {
		Item_Name = item_Name;
	}

	public int getItem_Value() {
		return Item_Value;
	}

	public void setItem_Value(int item_Value) {
		Item_Value = item_Value;
	}

	public int getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Timestamp getStart_Time() {
		return Start_Time;
	}

	public void setStart_Time(Timestamp start_Time) {
		Start_Time = start_Time;
	}

	public String getPhone_Number() {
		return Phone_Number;
	}

	public void setPhone_Number(String phone_Number) {
		Phone_Number = phone_Number;
	}

	public ItemLoan(){}

	public String toString() {
		return Name+"在"+Start_Time+"借走了价值"+Item_Value+"的"+Item_Name;
		
	}

}
