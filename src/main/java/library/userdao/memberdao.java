package library.userdao;

import library.model.member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class memberdao {

    private static final String url="jdbc:mysql://localhost:3306/libray";
    private static final String user="root";
    private static final String password="00000";


    public boolean addmember(member m) throws SQLException {
        String sql = "Insert into memeber(name,email,userpassword,balance) values(?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, m.getName());
            preparedStatement.setString(2, m.getEmail());
            preparedStatement.setString(3, m.getUserpassword());
            preparedStatement.setDouble(4, m.getBalance());
           int rows=  preparedStatement.executeUpdate();
           return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
        public member getmemberid(int id) throws SQLException {
             String sql ="select * from memeber where id=?";
             try(Connection connection =DriverManager.getConnection(url,user,password)){
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 preparedStatement.setInt(1,id);
                 ResultSet resultSet = preparedStatement.executeQuery();
                 if(resultSet.next()){
                     member m =new member(
                       resultSet.getString("name"),
                             resultSet.getString("email"),
                             resultSet.getString("userpassword"),
                             resultSet.getDouble("balance")
                     );
                      return m;

                 }
             } catch (ClassNotFoundException e) {
                 throw new RuntimeException(e);
             }
            return null;
        }
        public static List<member> getAllmember() throws SQLException {
        String sql ="select * from memeber";
            List<member> members = new ArrayList<>();
        try(Connection connection =DriverManager.getConnection(url,user,password)){
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                member m1= new member(
                        resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("userpassword"),
                resultSet.getDouble("balance")
                );
                members.add(m1);
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
            return members;
        }

public static void updatemember(member m) throws SQLException {
        String sql ="update memeber set name=?,email=?,userpassword=? where id=?";
        try(Connection connection =DriverManager.getConnection(url,user,password)){
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, m.getName());
            preparedStatement.setString(2,m.getEmail());
            preparedStatement.setString(3,m.getUserpassword());
            preparedStatement.setInt(4,m.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

}
public boolean deletemember(int id) throws SQLException {
        String sql ="delete from memeber where id=?";
        try(Connection connection =DriverManager.getConnection(url,user,password)){
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
      int row=            preparedStatement.executeUpdate();
 return row>0;
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    return false;
}
public member findbyemail(String email) throws SQLException {
        String sql ="select * from memeber where email=?";
        try(Connection connection =DriverManager.getConnection(url,user,password)){
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                member m=new member(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),resultSet.getString("email"),resultSet.getString("userpassword"),resultSet.getDouble("balance"));
                        return m;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    return null;
}
public member login(String email,String passw) throws SQLException {
        String sql ="select * from memeber where email=? and userpassword=?";
        try(Connection connection =DriverManager.getConnection(url,user,password)){
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
             preparedStatement.setString(1, email);
             preparedStatement.setString(2, passw);
             ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new member(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("userpassword"),
                        resultSet.getDouble("balance"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    return null;
}

}
