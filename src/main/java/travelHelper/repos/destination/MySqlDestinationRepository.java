package travelHelper.repos.destination;

import travelHelper.entities.Destination;
import travelHelper.repos.SQL_AbstractRepo;

import javax.swing.text.html.HTMLDocument;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlDestinationRepository extends SQL_AbstractRepo implements DestinationRepository {


    @Override
    public Destination getOneDestination(int destination_id) {

        Destination destination = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();

            statement = connection.prepareStatement("SELECT * FROM destination WHERE destination_id = ?");
            statement.setInt(1, destination_id);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt("destination_id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                String description = resultSet.getString("description");

                destination = new Destination(id, name, country, description);
            }


            resultSet.close();
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return destination;
    }

    @Override
    public List<Destination> getAllDestinations() {
        List<Destination> destinations = new ArrayList<Destination>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Destination");
            while(resultSet.next()){
                destinations.add(new Destination(resultSet.getInt("destination_id")
                        ,resultSet.getString("name"),
                        resultSet.getString("country"),
                        resultSet.getString("description")
                        )
                );
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return destinations;
    }

    @Override
    public void deleteDestination(int destination_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM destination WHERE destination_id = ?");
            preparedStatement.setInt(1, destination_id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);

        }
    }

    @Override
    public Destination addDestination(Destination destination) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{

            connection = this.newConnection();

            String[] generatedColumns = {"destination_id"};

            preparedStatement = connection.prepareStatement("INSERT INTO destination(name,country,description) VALUES(?, ?, ?)",generatedColumns);
            preparedStatement.setString(1, destination.getName());
            preparedStatement.setString(2, destination.getCountry());
            preparedStatement.setString(3, destination.getDescription());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                destination.setDestination_id(resultSet.getInt(1));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }


        return destination;
    }
}
