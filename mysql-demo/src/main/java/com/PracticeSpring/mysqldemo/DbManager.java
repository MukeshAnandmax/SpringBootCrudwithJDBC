package com.PracticeSpring.mysqldemo;


import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class DbManager {

    public DbManager() {
        try {
            creteTable();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static Connection connection;
    public static void  getDbConnection()  {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jbdl", "root", "#321Mysql");

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void creteTable() throws SQLException {

        getDbConnection();

        Statement statement = connection.createStatement();
       /* private int UserId;
        private  String name;
        private int age;
        private  String country;
        private Date timeOfInsertion;*/

        statement.execute("create table if not exists user( UserId int primary key auto_increment, name varchar(30)," +
                "age int, country varchar(20), timeOfInsertion date)");

    }


    public  void  deleteUser(int userId) throws SQLException {
        getDbConnection();

        String sql = "delete from user where userId="+userId;
        Statement statement =  connection.createStatement();
        statement.execute(sql);


    }

    public void updateUser(User user) throws SQLException {

        getDbConnection();
        User oldUser = getUser(user.getUserId());

       if(user.getName()==null){
           user.setName(oldUser.getName());
       }
       if(user.getAge()==0){
           user.setAge(oldUser.getAge());
       }
       if(user.getCountry()==null){
           user.setCountry(oldUser.getCountry());
       }

       user.setTimeOfInsertion(oldUser.getTimeOfInsertion());

        String sql = "update user set userId=?,name=?,age=?,country=?,timeOfInsertion=? where userId="+user.getUserId();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,user.getUserId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setInt(3,user.getAge());
        preparedStatement.setString(4,user.getCountry());
        if(user.getTimeOfInsertion()==null){
            preparedStatement.setDate(5,new Date(System.currentTimeMillis()));
        }else {
            preparedStatement.setDate(5,new Date(user.getTimeOfInsertion().getTime()));
        }


        int row =  preparedStatement.executeUpdate();

        if(row>0){
            System.out.println(row+"Rows affected");
        }else{
            System.out.println("No Row Affected");
        }
    }

    public void  insertUser(User user) throws SQLException {

        getDbConnection();

        String sql="insert into user (userId,name,age,country,timeOfInsertion) values(null,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, user.getName());
        preparedStatement.setInt(2,user.getAge());
        preparedStatement.setString(3,user.getCountry());
        if(user.getTimeOfInsertion()==null){
            preparedStatement.setDate(4,new Date(System.currentTimeMillis()));
        }else {
            preparedStatement.setDate(4,new Date(user.getTimeOfInsertion().getTime()));
        }


        int row =  preparedStatement.executeUpdate();

        if(row>0){
            System.out.println(row+"Rows affected");
        }else{
            System.out.println("No Row Affected");
        }

    }

   public List<User> getUsers() throws SQLException {

        getDbConnection();
       List<User> userList = new ArrayList<>();

        String sql = "select * from user";

        Statement statement =  connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){

           User user = new User();

           user.setUserId(resultSet.getInt(1));
           user.setAge(resultSet.getInt(3));
           user.setName(resultSet.getString(2));
           user.setCountry(resultSet.getString(4));



           user.setTimeOfInsertion(new java.util.Date(resultSet.getDate(5).getTime()));

            userList.add(user);
        }
       return  userList;
   }





    public User getUser( int id ) throws SQLException {

        getDbConnection();
       // List<User> userList = new ArrayList<>();

        String sql = "select * from user where userId="+id;

        Statement statement =  connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){

            User user = new User();

            user.setUserId(resultSet.getInt(1));
            user.setAge(resultSet.getInt(3));
            user.setName(resultSet.getString(2));
            user.setCountry(resultSet.getString(4));
            user.setTimeOfInsertion(new java.util.Date(resultSet.getDate(5).getTime()));

           // userList.add(user);
            return user;
        }
        return  null;
    }






}
