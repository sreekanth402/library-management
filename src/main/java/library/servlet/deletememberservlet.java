package library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.model.member;

import java.io.IOException;

@WebServlet("/deletemember")
public class deletememberservlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean b= Boolean.parseBoolean(request.getParameter("id"));
         if(b){
             response.getWriter().println("memeber is deleted sucessfully");
         }
         else{
             response.getWriter().println("memeber is not found");
         }

    }
}
