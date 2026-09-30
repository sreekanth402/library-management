<%@ page  import="library.model.book" %>
<%@ page import="library.model.member" %>
<%@ page import="library.model.borrowrecord" %>
<%@ page import="java.util.List" %>
<% book book = (book) session.getAttribute("user");
    if(book==null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<@ page import ="library.model.member%>
<% member member =(member) session.getAttribute("member");
if(member==null){
    response.sendRedirect("login.jsp");
    return;
}
%>
<@ page import ="library.model.borrowrecord%>
<% borrowrecord borrowrecord =(borrowrecord) session.getAttribute("member");
    if(borrowrecord==null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title> dashboard</title>
</head>
<body>
<h1>member details</h1>
<p>member_name:
   <% member.getName();%>
</p>
<p>member_email:
    <% member.getEmail();%>
</p>
<p>member_password:
    <% member.getUserpassword();%>
</p>
<p>member_balance:
    <% member.getBalance();%>
</p>
<h1>book_details</h1>
<p>book_name:
    <% book.getTitle();%>
</p>
<p>book_id:
    <% book.getId();%>
</p>
<p>book_author:<% book.getAuthor();%>
</p>
<p>book_publisher:
    <% book.getPublisher();%>
</p>
<p>
    book_price:
    <% book.getPrice();%>
</p>
<h1>brorrow record</h1>
<p>borrow_id:
    <% borrowrecord.getId();%>
</p>
<p>borrow_bookid:
    <% borrowrecord.getbookid();%>
</p>
<p>borrow_memberid:-
    <% borrowrecord.getMemberid();%>
</p>
<p>
    borrow_issuedate:
    <% borrowrecord.getissuedate();%>
</p>
<p>
    borrow_returndate:
    <%borrowrecord.getreturndate();%>
</p>
</body>
</html>


