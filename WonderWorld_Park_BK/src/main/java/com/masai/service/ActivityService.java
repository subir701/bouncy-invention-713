package com.masai.service;

import java.util.List;

import com.masai.Exception.ActivityException;
import com.masai.model.Activity;

public interface ActivityService {

	 Activity insertActivity(Activity  activiy)throws ActivityException;
	 Activity updateActivity(Activity activity,Integer activityId)throws ActivityException;
	 Activity deletedActivity(Integer activityId)throws ActivityException;
	 List<Activity> viewAllActivities()throws ActivityException;
	 Activity getActivityById(Integer activityId)throws ActivityException;
	 
}
