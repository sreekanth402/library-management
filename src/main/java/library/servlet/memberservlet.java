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
import java.util.List;

@WebServlet("/getallmember")
public class memberservlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<member> members= memberdao.getAllmember();
            request.setAttribute("member",members);
            request.getRequestDispatcher("dashboard.jsp").forward(request,response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
