package travelHelper.repos.destination;

import travelHelper.entities.Destination;

import java.util.List;

public interface DestinationRepository {
    public Destination getOneDestination(int destination_id);
    public List<Destination> getAllDestinations();
    public void deleteDestination(int destination_id);
    public Destination addDestination(Destination destination);
}
