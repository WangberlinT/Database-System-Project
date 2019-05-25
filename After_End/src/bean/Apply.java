package bean;

public class Apply {

    private int Apply_ID;
    private int Apply_State;
    private String Apply_Type;
    private String Apply_Description;
    private int Apply_To;
    private int Apply_From;
    private String name;
    private int phone;

    Apply() {
    }

    public String toString() {
        return Apply_ID + "： 来自" + name + "的" + Apply_Type + ": " + Apply_Description + "\n";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getApply_ID() {
        return Apply_ID;
    }

    public void setApply_ID(int apply_ID) {
        Apply_ID = apply_ID;
    }

    public int getApply_State() {
        return Apply_State;
    }

    public void setApply_State(int apply_State) {
        Apply_State = apply_State;
    }

    public String getApply_Type() {
        return Apply_Type;
    }

    public void setApply_Type(String apply_Type) {
        Apply_Type = apply_Type;
    }

    public String getApply_Description() {
        return Apply_Description;
    }

    public void setApply_Description(String apply_Description) {
        Apply_Description = apply_Description;
    }

    public int getApply_To() {
        return Apply_To;
    }

    public void setApply_To(int apply_To) {
        Apply_To = apply_To;
    }

    public int getApply_From() {
        return Apply_From;
    }

    public void setApply_From(int apply_From) {
        Apply_From = apply_From;
    }

}
