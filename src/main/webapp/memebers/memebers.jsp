<%@ page import="java.util.List" %>
<%@ page import="library.model.member" %>
<!Doctype html>
<html>
<head>
    <title>memeber -details</title>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>email</th>
        <th>password</th>
        <th>balance</th>
    </tr>
    <%List<member> members =(List<member>) request.getAttribute("member");
      for(member m:members){
    %>
    <tr>
        <td><%= m.getId() %></td>
        <td><%= m.getName() %></td>
        <td><%= m.getEmail() %></td>
        <td><%= m.getUserpassword()%></td>
        <td><%= m.getBalance() %></td>
    </tr>
    <%}%>
</table>
</body>
</html>