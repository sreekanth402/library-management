<%@ page import="java.util.List" %>
<%@ page import="library.model.borrowrecord" %>

<!DOCTYPE html>

<html>

<head>
    <title>Borrow History</title>
</head>

<body>

<h1>Borrow History</h1>

<%
    List<borrowrecord> records =
            (List<borrowrecord>)
                    request.getAttribute("borrowRecords");
%>

<%
    if (records == null || records.isEmpty()) {
%>

<h3>No borrow history found.</h3>

<%
} else {
%>

<table border="1">

    <tr>
        <th>Borrow ID</th>
        <th>Book ID</th>
        <th>Member ID</th>
        <th>Issue Date</th>
        <th>Return Date</th>
        <th>Status</th>
    </tr>

    <%
        for (borrowrecord record : records) {
    %>

    <tr>

        <td>
            <%= record.getId() %>
        </td>

        <td>
            <%= record.getbookid() %>
        </td>

        <td>
            <%= record.getMemberid() %>
        </td>

        <td>
            <%= record.getissuedate() %>
        </td>

        <td>

            <%
                if (record.getreturndate() == null) {
            %>

            Not Returned

            <%
            } else {
            %>

            <%= record.getreturndate() %>

            <%
                }
            %>

        </td>

        <td>

            <%
                if (record.getreturndate() == null) {
            %>

            Borrowed

            <%
            } else {
            %>

            Returned

            <%
                }
            %>

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

<a href="../admin/dashboard.jsp">
    Admin Dashboard
</a>

</body>

</html>