<%@ page import="java.util.List" %>
<%@ page import="library.model.borrowrecord" %>

<!DOCTYPE html>

<html>

<head>
    <title>My Borrowed Books</title>
</head>

<body>

<h1>My Borrowed Books</h1>

<%
    List<borrowrecord> records =
            (List<borrowrecord>)
                    request.getAttribute(
                            "borrowRecords"
                    );
%>

<%
    if (records == null ||
            records.isEmpty()) {
%>

<p>You have no borrowed books.</p>

<%
} else {
%>

<table border="1">

    <tr>
        <th>Borrow ID</th>
        <th>Book ID</th>
        <th>Issue Date</th>
        <th>Due Date</th>
        <th>Action</th>
    </tr>

    <%
        for (borrowrecord r : records) {
    %>

    <tr>

        <td>
            <%= r.getId() %>
        </td>

        <td>
            <%= r.getbookid() %>
        </td>

        <td>
            <%= r.getissuedate() %>
        </td>

        <td>
            <%= r.getduedate() %>
        </td>

        <td>

            <form action="../returnbook"
                  method="post">

                <input type="hidden"
                       name="borrowId"
                       value="<%= r.getId() %>">

                <button type="submit">
                    Return
                </button>

            </form>

        </td>

    </tr>

    <%
        }
    %>

</table>

<%
    }
%>

<br>

<a href="../member/dashboard.jsp">
    Dashboard
</a>

</body>

</html>