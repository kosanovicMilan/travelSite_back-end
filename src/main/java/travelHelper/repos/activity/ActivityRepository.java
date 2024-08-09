package travelHelper.repos.activity;

import travelHelper.entities.Activity;

import java.util.List;

public interface ActivityRepository {

    public List<Activity> getAllActivities();
    public Activity getOneActivity(Integer activity_id);
    public Activity addActivity(Activity activity);
    public void deleteActivity(Integer activity_id);
}
