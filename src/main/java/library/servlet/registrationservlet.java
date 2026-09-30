package library.servlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.model.member;
import library.userdao.memberdao;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/registraion")
public class registrationservlet extends HttpServlet {
    private memberdao memberdao =new memberdao();
    private member member;
    private boolean statu;
    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException{
        String name =request.getParameter("name");
        String  email =request.getParameter("email");
        String  password =request.getParameter("password");
        Double  amount = Double.valueOf(request.getParameter("amount"));
        member =new member(name,email,password,amount);

        try {
            statu =memberdao.addmember(member);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        if(statu){
            response.sendRedirect("login.jsp");
        }
        else {
            response.getWriter().println("registration failed");
        }
    }
}
