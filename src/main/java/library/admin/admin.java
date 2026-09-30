package library.admin;

public class admin {
    private static final String useremail ="admin";
    private static final String adminpassword ="admin@1234";
    public static boolean s(String username, String password){
        return  useremail.equals(username) && adminpassword.equals(password);
    }
}
