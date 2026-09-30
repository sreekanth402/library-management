package library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.model.book;
import library.userdao.bookdao;

import java.io.IOException;

@WebServlet("/addbook")
public class addbookservlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");

        double price = Double.parseDouble(
                request.getParameter("price")
        );

        boolean status = Boolean.parseBoolean(
                request.getParameter("status")
        );

        book book = new book(
                title,
                author,
                publisher,
                price,
                status
        );

        boolean result = bookdao.addbook(book);

        if (result) {
            response.sendRedirect("admindashboard.jsp");
        } else {
            response.getWriter().println(
                    "Book was not added. Check database/Tomcat console."
            );
        }
    }
}