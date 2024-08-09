package travelHelper.repos.destination;

import travelHelper.entities.Destination;
import travelHelper.repos.SQL_AbstractRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlDestinationRepository extends SQL_AbstractRepo implements DestinationRepository {


    @Override
    public Destination getOneDestination(int destination_id) {
        return null;
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

    }

    @Override
    public Destination addDestination(Destination destination) {
        return null;
    }
}
