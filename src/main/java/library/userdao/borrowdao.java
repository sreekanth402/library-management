package library.userdao;

import library.model.borrowrecord;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class borrowdao {
    private static final String url="jdbc:mysql://localhost:3306/libray";
    private static final String user="root";
    private static final String password=System.getenv("password");

public void issuebook(borrowrecord record){
    String sql ="insert into borrow(book_id,member_id,issue_date,return_date,pending_bal) values(?,?,?,?,?)";
    try(Connection connection = DriverManager.getConnection(url,user,password)) {
        Class.forName("com.mysql.cj.jdbc.Driver");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,record.getbookid());
        preparedStatement.setInt(2,record.getMemberid());
        preparedStatement.setDate(3, Date.valueOf(record.getissuedate()));
        preparedStatement.setNull(4,Types.DATE);
        preparedStatement.setInt(5,record.getPendingbal());
        preparedStatement.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
}

public borrowrecord getborrowrecord(int id) throws SQLException {
    String sql ="select *from  borrow where id=?";
    try(Connection connection =DriverManager.getConnection(url,user,password)){
        Class.forName("com.mysql.cj.jdbc.Driver");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            borrowrecord b =new borrowrecord(
                    resultSet.getInt("id"),
                    resultSet.getInt("book_id"),
                    resultSet.getInt("member_id"),
                    resultSet.getDate("issue_date"),
                    resultSet.getDate("return_date"),
                    resultSet.getInt("pending_balance")
            );
            return b;
        }
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    return null;
}
public List<borrowrecord> getborrowrecords() throws SQLException {
    String sql ="select *from  borrow";
    List<borrowrecord> b =new ArrayList<>();
    try(Connection connection =DriverManager.getConnection(url,user,password)){
        Class.forName("com.mysql.cj.jdbc.Driver");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            borrowrecord m1 =new borrowrecord(
                    resultSet.getInt("id"),
                    resultSet.getInt("book_id"),
                    resultSet.getInt("member_id"),
                    resultSet.getDate("issue_date"),
                    resultSet.getDate("return_date"),
                    resultSet.getInt("pending_balance")
            );
            b.add(m1);
        }
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    return b;
}
public List<borrowrecord> getborrowrecordsbyid(int id) throws SQLException {
    String sql ="select *from  borrow where id=?";
    List<borrowrecord> b =new ArrayList<>();
    try(Connection connection =DriverManager.getConnection(url,user,password)){
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            borrowrecord m =new borrowrecord(
                    resultSet.getInt("id"),
                    resultSet.getInt("book_id"),
                    resultSet.getInt("member_id"),
                    resultSet.getDate("issue_date"),
                    resultSet.getDate("return_date"),
                    resultSet.getInt("pending_balance")
            );
            b.add(m);
        }
    }
    return b;
}
public List<borrowrecord> getactiverecords() throws SQLException {
    String sql ="select *from  borrow where return_date is null";
    List<borrowrecord> b =new ArrayList<>();
    try(Connection connection =DriverManager.getConnection(url,user,password)){
        Class.forName("com.mysql.cj.jdbc.Driver");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
             borrowrecord m =new borrowrecord(
                     resultSet.getInt("id"),
                     resultSet.getInt("book_id"),
                     resultSet.getInt("member_id"),
                     resultSet.getDate("issue_date"),
                     resultSet.getDate("return_date"),
                     resultSet.getInt("pending_balance")
             );
             b.add(m);
        }
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    return b;

}
public void return_book(int id) throws SQLException {
    String sql = "UPDATE borrow" +
                 "SET return_date=?\n" +
                 "WHERE id=?\n";

    try (
            Connection connection =
                    DriverManager.getConnection(url, user, password);

            PreparedStatement ps =
                    connection.prepareStatement(sql)
    ) {
        Class.forName("com.mysql.cj.jdbc.Driver");
        LocalDate returnDate = LocalDate.now();
        ps.setDate(1, Date.valueOf(returnDate));
        ps.setInt(2, id);
        ps.executeUpdate();
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
}
    }

