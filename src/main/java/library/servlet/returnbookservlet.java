package library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import library.model.borrowrecord;
import library.userdao.bookdao;
import library.userdao.borrowdao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/returnbook")
public class returnbookservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int borrowId = Integer.parseInt(
                request.getParameter("borrowId")
        );

        borrowdao borrowDao = new borrowdao();
        bookdao bookDao = new bookdao();

        try {

            // Find the borrow record
            borrowrecord record =
                    borrowDao.getborrowrecord(
                            borrowId
                    );

            if (record == null) {

                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Borrow record not found"
                );

                return;
            }

            // Get the book ID
            int bookId =
                    record.getbookid();

            // Set return_date = today
            borrowDao.return_book(
                    borrowId
            );

            // Make book available again
            bookDao.updateBookStatus(
                    bookId,
                    true
            );

            // Go back to borrowed books
            response.sendRedirect(
                    "borrowedbooks"
            );

        } catch (SQLException e) {

            throw new ServletException(e);
        }
    }
}