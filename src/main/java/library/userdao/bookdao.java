package library.userdao;

import library.model.book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class bookdao {
    private static final String url = "jdbc:mysql://localhost:3306/libray";
    private static final String username = "root";
    private static final String password = System.getenv("password");

    public static boolean  addbook(book b) {
        String sql = "Insert into library(title,author,publisher,price,status) values(?,?,?,?,?)";
        try (
                Connection connection = DriverManager.getConnection(url, username, password)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, b.getTitle());
            preparedStatement.setString(2, b.getAuthor());
            preparedStatement.setString(3, b.getPublisher());
            preparedStatement.setDouble(4, b.getPrice());
            preparedStatement.setBoolean(5, b.bookStatus());
              int rows = preparedStatement.executeUpdate();
              return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public List<book> getallbooks() throws SQLException {
        List<book> books = new ArrayList<book>();
        String sql = "select * from library";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                book book = new book(
                        rs.getDouble("price"),
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher")
                );
                book.setStatus(rs.getBoolean("status"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

public book getbookbyid(int id) throws SQLException {
        String sql = "select *from library where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                book b =new book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getDouble("price")

                );
                b.setStatus(rs.getBoolean("status"));
                return b;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
}
public static void updatebook(book b) throws SQLException {
        String sql = " UPDATE library" +
                "            SET title = ?,\n" +
                "                author = ?,\n" +
                "                publisher = ?,\n" +
                "                price = ?,\n" +
                "                status = ?\n" +
                "            WHERE id = ?";
        try(Connection connection =DriverManager.getConnection(url,username,password)){
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, b.getTitle());
            preparedStatement.setString(2, b.getAuthor());
            preparedStatement.setString(3, b.getPublisher());
            preparedStatement.setDouble(4, b.getPrice());
            preparedStatement.setBoolean(5, b.bookStatus());
            preparedStatement.setInt(6, b.getId());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
}
public boolean deletebook(int id) throws SQLException {
        String sql="Delete from Library where id=?";
        try(Connection connection =DriverManager.getConnection(url,username,password)){
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rows =preparedStatement.executeUpdate();
            return rows >0;
        }catch (Exception e){
            e.printStackTrace();
        }
return false;
}
    public void updateBookStatus(int id, boolean status)
            throws SQLException {

        String sql =
                "UPDATE book SET status=? WHERE id=?";

        try (
                Connection connection =
                        DriverManager.getConnection(url, username, password);

                PreparedStatement ps =
                        connection.prepareStatement(sql);

        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            ps.setBoolean(1, status);
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
