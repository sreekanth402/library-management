package library.model;

public class member {
    private int memberid;
    private String name;
    private String email;
    private String userpassword;
    private double balance ;
    public member(int id, String name, String email) {
        this.memberid = id;
        this.name = name;
        this.email = email;
    }
    public member(String name, String email, String userpassword, double balance) {
        this.name = name;
        this.email = email;
        this.userpassword = userpassword;
        this.balance = balance;
    }
    public member(int id, String name, String email, String password, double balance) {
            this.memberid = id;
            this.name = name;
            this.email = email;
            this.userpassword = password;
            this.balance = balance;
    }

    public member() {

    }

    public int getId() {
        return memberid;
    }

    public void setId(int id) {
        this.memberid = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
    public double getBalance(){
        return balance;
    }
    public void updateBalance(double amount){
            this.balance+=amount;
    }
}

