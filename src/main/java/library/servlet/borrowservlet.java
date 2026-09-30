package library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import library.model.borrowrecord;
import library.userdao.borrowdao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/borrowedbooks")
public class borrowservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        borrowdao dao = new borrowdao();

        try {

            // Get all records where return_date is NULL
            List<borrowrecord> records =
                    dao.getactiverecords();

            // Send records to JSP
            request.setAttribute(
                    "borrowRecords",
                    records
            );

            // Open JSP
            request.getRequestDispatcher(
                    "borrow/borrowed-books.jsp"
            ).forward(
                    request,
                    response
            );

        } catch (SQLException e) {

            throw new ServletException(e);
        }
    }
}