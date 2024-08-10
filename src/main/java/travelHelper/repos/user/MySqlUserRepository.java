package travelHelper.repos.user;

import travelHelper.entities.User;
import travelHelper.repos.SQL_AbstractRepo;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserRepository extends SQL_AbstractRepo implements UserRepository {

    @Override
    public User getOneUser(int user_id) {
        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_id=?");
            preparedStatement.setInt(1, user_id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String surrname = resultSet.getString("surrname");
                String type = resultSet.getString("type");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String status = resultSet.getString("status");

                user = new User(id, name, surrname, type, password, email,status);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user");

            while(resultSet.next()){
                users.add(new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("surrname"),
                        resultSet.getString("type"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("status")

                        ));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeConnection(connection);
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
        }


        return users;
    }

    @Override
    public void deleteUser(int user_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE user_id=?");
            preparedStatement.setInt(1, user_id);
            preparedStatement.executeUpdate();


            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }

    }

    @Override
    public User addUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            String[] generatedColumns = {"user_id"};

            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO User VALUES (?, ?, ?, ?, ?, ?)",generatedColumns);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurrname());
            preparedStatement.setString(3, user.getType());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6,user.getStatus());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                user.setUser_id(resultSet.getInt("user_id"));
            }

            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {

        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email=?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String surrname = resultSet.getString("surrname");
                String type = resultSet.getString("type");
                String password = resultSet.getString("password");
                String status = resultSet.getString("status");

                user = new User(id, name, surrname, type, password, email,status);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return user;
    }
}
