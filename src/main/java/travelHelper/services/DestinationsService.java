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
}
