package travelHelper.services;

import travelHelper.entities.Activity;
import travelHelper.repos.activity.ActivityRepository;

import javax.inject.Inject;
import java.util.List;

public class ActivityService {
    public ActivityService() {
        System.out.println(this);
    }

    @Inject
    private ActivityRepository activityRepository;

    public List<Activity> getActivities() {
        return this.activityRepository.getAllActivities();
    }

    public Activity getActivity(int id) {
        return this.activityRepository.getOneActivity(id);
    }

    public Activity addActivity(Activity activity) {
        return this.activityRepository.addActivity(activity);
    }

    public void deleteActivity(int id) {
        this.activityRepository.deleteActivity(id);
    }

}
