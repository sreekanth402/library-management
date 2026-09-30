package library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import library.model.book;
import library.model.borrowrecord;
import library.userdao.bookdao;
import library.userdao.borrowdao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/issuebook")
public class issusebookservlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get book ID from books.jsp
        int bookId = Integer.parseInt(
                request.getParameter("bookId")
        );

        // 2. Get logged-in member
        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("memberId") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        int memberId =
                (Integer) session.getAttribute("memberId");

        try {

            bookdao bookDao = new bookdao();

            // 3. Find the book
            book b =
                    bookDao.getbookbyid(bookId);

            if (b == null) {

                response.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Book not found"
                );

                return;
            }

            // 4. Check availability
            if (!b.bookStatus()) {

                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Book is already borrowed"
                );

                return;
            }

            // 5. Create borrow record
            borrowrecord record =
                    new borrowrecord();

            // Put actual Book object
            record.setBookid(b);

            // Put member ID
            record.setmemberid(memberId);

            // Today's date
            record.setissuedate();

            // Issue date + 10 days
            record.setduedate();

            /*
             * Put your existing balance logic here.
             * Do NOT take balance from request.
             */

            // 6. Insert borrowing record
            borrowdao borrowDao =
                    new borrowdao();

            borrowDao.issuebook(record);

            // 7. Mark book as borrowed
            bookDao.updateBookStatus(
                    bookId,
                    false
            );

            // 8. Go back to books
            response.sendRedirect(
                    "books/books.jsp"
            );

        } catch (SQLException e) {

            throw new ServletException(e);
        }
    }
}