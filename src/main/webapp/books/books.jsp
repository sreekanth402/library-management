<%@ page import="java.util.List" %>
<%@ page import="library.model.book" %>

<!DOCTYPE html>

<html>

<head>
    <title>Books</title>
</head>

<body>

<h1>Library Books</h1>

<%
    List<book> books =
            (List<book>) request.getAttribute("books");
%>

<table border="1">

    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Publisher</th>
        <th>Price</th>
        <th>Status</th>
        <th>Action</th>
    </tr>

    <%
        if (books != null) {

            for (book b : books) {
    %>

    <tr>

        <td>
            <%= b.getId() %>
        </td>

        <td>
            <%= b.getTitle() %>
        </td>

        <td>
            <%= b.getAuthor() %>
        </td>

        <td>
            <%= b.getPublisher() %>
        </td>

        <td>
            <%= b.getPrice() %>
        </td>

        <td>

            <% if (b.bookStatus()) { %>

            Available

            <% } else { %>

            Borrowed

            <% } %>

        </td>

        <td>

            <% if (b.bookStatus()) { %>

            <form action="../issuebook"
                  method="post">

                <input type="hidden"
                       name="bookId"
                       value="<%= b.getId() %>">

                <button type="submit">
                    Borrow
                </button>

            </form>

            <% } else { %>

            Already Borrowed

            <% } %>

        </td>

    </tr>

    <%
            }
        }
    %>

</table>

<br>

<a href="../member/dashboard.jsp">
    Dashboard
</a>

</body>

</html>