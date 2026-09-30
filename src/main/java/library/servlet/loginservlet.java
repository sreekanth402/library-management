package library.servlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.admin.admin;
import library.userdao.memberdao;
import java.io.IOException;
import java.sql.SQLException;
import library.model.*;
@WebServlet("/login")
public class loginservlet extends HttpServlet {
    private memberdao memberdao;
    @Override
    public void init(){
        memberdao=new memberdao();
    }
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response) throws IOException{
      String email = request.getParameter("email");
      String password = request.getParameter("password");
   member member;
   try{
       member=memberdao.login(email,password);
   } catch (SQLException e) {
       throw new RuntimeException(e);
   }
    if(admin.s(email,password)){
        HttpSession session=request.getSession();
        session.setAttribute("role","admin");
        response.sendRedirect("admindashboard.jsp");
        return ;

    }
   if(member!=null){
       HttpSession session=request.getSession();
       session.setAttribute("member",member);
       session.setAttribute("memberId", member.getId());
       response.sendRedirect("dashboard.jsp");
   }
   else{
       response.getWriter().println("Invalid email or password");
   }
    }
}
