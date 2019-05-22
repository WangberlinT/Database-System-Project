package bean;

public class itemshow {

	private int num;
	private int value;
	private String name;
	public itemshow(){
		
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name+" "+value+" "+num;
	}
}
