package library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.model.book;
import library.userdao.bookdao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/getallbooks")
public class bookservlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     bookdao bookdao = new bookdao();
     try{
         List<book> books=bookdao.getallbooks();
          request.setAttribute("books",books);
          request.getRequestDispatcher("book.jsp").forward(request,response);

     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
    }
}
