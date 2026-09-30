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

@WebServlet("/borrowhistory")
public class borrowhistoryservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        borrowdao dao = new borrowdao();

        try {

            // Get all borrow records
            List<borrowrecord> records =
                    dao.getborrowrecords();

            // Put them into request
            request.setAttribute(
                    "borrowRecords",
                    records
            );

            // Send them to JSP
            request.getRequestDispatcher(
                    "borrow/borrow-history.jsp"
            ).forward(
                    request,
                    response
            );

        } catch (SQLException e) {

            throw new ServletException(e);
        }
    }
}