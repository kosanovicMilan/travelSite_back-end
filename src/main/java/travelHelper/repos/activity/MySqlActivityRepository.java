package travelHelper.repos.activity;

import travelHelper.entities.Activity;
import travelHelper.repos.SQL_AbstractRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlActivityRepository extends SQL_AbstractRepo implements ActivityRepository {

    @Override
    public List<Activity> getAllActivities() {
        List<Activity> activities = new ArrayList<Activity>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT *,(select name from Destination D where D.destination_id = A.destination_id ) as destinationName FROM activity A");

            while(resultSet.next()) {
                activities.add(new Activity(
                        resultSet.getInt("activity_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("destination_id"),
                        resultSet.getString("destinationName")
                ));
            }



        }catch (Exception e) {
            System.out.println("Puko u mysqlrepo za All()");
            e.printStackTrace();
        }finally {
            this.closeConnection(connection);
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
        }

        return activities;
    }

    @Override
    public Activity getOneActivity(Integer activity_id) {

        Activity activity = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT *,(select name from Destination D where D.destination_id = A.destination_id ) as destinationName FROM activity A WHERE activity_id = ?");
            preparedStatement.setInt(1, activity_id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int activityId = resultSet.getInt("activity_id");
                String activityName = resultSet.getString("name");
                int destinationId = resultSet.getInt("destination_id");
                String destinationName = resultSet.getString("destinationName");
                activity = new Activity(activityId, activityName, destinationId, destinationName);
            }


        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return activity;
    }

    @Override
    public Activity addActivity(Activity activity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();
            String[] generatedColumns = {"activity_id"};

            preparedStatement = connection.prepareStatement("INSERT into activity(name,destination_id) VALUES (? , ?)",generatedColumns);
            preparedStatement.setString(1, activity.getName());
            preparedStatement.setInt(2,activity.getDestination_id());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()) {
                activity.setActivity_id(resultSet.getInt(1));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
        }

        return activity;
    }

    @Override
    public void deleteActivity(Integer activity_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("delete from Activity where activity_id = ?");
            preparedStatement.setInt(1,activity_id);
            preparedStatement.executeUpdate();



        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }
    }
}
