package library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.model.member;
import library.userdao.memberdao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updatemember")
public class updatememberservlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     int id = Integer.parseInt(request.getParameter("id"));
     String email = request.getParameter("email");
     String name=request.getParameter("name");
     String pass =request.getParameter("pass");
     member m =new member();
     m.setEmail(email);
     m.setName(name);
     m.setUserpassword(pass);
     m.setId(id);
        try {
            memberdao.updatemember(m);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
