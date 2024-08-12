package travelHelper.services;

import travelHelper.entities.Destination;
import travelHelper.repos.destination.DestinationRepository;

import javax.inject.Inject;
import java.util.List;

public class DestinationsService {

    public DestinationsService() {
        System.out.println(this);
    }

    @Inject
    private DestinationRepository destinationRepository;

    public List<Destination> getAllDestinations() {
        return destinationRepository.getAllDestinations();
    }

    public Destination getOneDestination(int id){
        return destinationRepository.getOneDestination(id);
    }

    public Destination addDestination(Destination destination) {
        return destinationRepository.addDestination(destination);
    }

    public void deleteDestination(int id) {
        this.destinationRepository.deleteDestination(id);
    }
}
