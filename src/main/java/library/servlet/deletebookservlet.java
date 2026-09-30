package library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.userdao.bookdao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deletebook")
public class deletebookservlet extends HttpServlet {
    private bookdao bookdao;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int bookid= Integer.parseInt(request.getParameter("id"));
        boolean f= false;
        try {
            f = bookdao.deletebook(bookid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(f){
           response.getWriter().println("book deleted successfully");
       }
       else{
           response.getWriter().println("book not found");
       }
    }
}
