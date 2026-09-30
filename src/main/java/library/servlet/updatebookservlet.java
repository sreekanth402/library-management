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

@WebServlet("/updatebook")
public class updatebookservlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Double price = Double.parseDouble(request.getParameter("price"));
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String publisher = request.getParameter("publisher");
        int id = Integer.parseInt(request.getParameter("id"));
        boolean status =Boolean.parseBoolean(request.getParameter("status"));
        book b =new book();
        b.setTitle(title);
        b.setAuthor(author);
        b.setPublisher(publisher);
        b.setId(id);
        b.setStatus(status);
        b.setPrice(price);
        try{
            bookdao.updatebook(b);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
