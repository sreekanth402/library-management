package library.model;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class borrowrecord {
    private book book;
    private member member;
    private int id;

    public borrowrecord(int id, int bookId, int memberId, Date issueDate, Date returnDate, int pendingBalance) {
        this.id = id;
        this.bookid = bookId;
        this.memberid = memberId;
        this.issuedate= issueDate.toLocalDate();
        this.returndate = returnDate.toLocalDate();
        this.pendingbal =pendingBalance;

    }

    public borrowrecord() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPendingbal() {
        return pendingbal;
    }

    public void setPendingbal(int pendingbal) {
        this.pendingbal = pendingbal;
    }
    public borrowrecord(book book, int id, int bookid, LocalDate issuedate, int memberid, LocalDate duedate, LocalDate returndate, int pendingbal) {
        this.book = book;
        this.id = id;
        this.bookid = bookid;
        this.issuedate = issuedate;
        this.memberid = memberid;
        this.duedate = duedate;
        this.returndate = returndate;
        this.pendingbal = pendingbal;
    }
    private List<book> booklist = new ArrayList<>();
    private int bookid;
    private int memberid;
    private LocalDate issuedate;
    private LocalDate duedate;
    private LocalDate returndate;
    private int pendingbal;
    public boolean getborrowstatus() {
        return !book.bookStatus();
    }
    public void setBookid(book  book) {
        this.book = book;
        this.bookid = book.getId();
    }
    public void setmemberid(int memberid) {
        this.member = member;
        this.memberid = memberid;
    }
    public int getMemberid() {

        return memberid;
    }
    public int getbookid() {
        return bookid;
    }
    public void setissuedate() {
        this.issuedate = LocalDate.now();
    }
    public LocalDate getissuedate() {
        return issuedate;
    }
    public void setduedate() {
        this.duedate = issuedate.plusDays(10);
    }
    public LocalDate getduedate() {
        return duedate;
    }
    public void setreturndate() {
        this.returndate = LocalDate.now();
    }
    public LocalDate getreturndate() {
        return returndate;
    }
    public void addBook(book book) {
        booklist.add(book);
    }
    public int getBookcount() {
        return booklist.size();
    }
    public double balance() {
        int count = getBookcount();
        double balance = member.getBalance();
        if (balance <= 100) {
            member.updateBalance(1000);
        } else {
            balance = balance - (10 * count);
        }
        return balance;
    }
}