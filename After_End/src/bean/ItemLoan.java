package bean;

import java.util.Date;

public class ItemLoan {
    private int loanId; //借出记录ID
    private Item item; //被借出的资产
    private User user; //借出的学生
    private Date start; //借出时间
    private Date end; //借出截止时间
    private Date returnDate; //归还时间

    public ItemLoan(){}

    //getter and setter
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getstart() {
        return start;
    }

    public void setstart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
