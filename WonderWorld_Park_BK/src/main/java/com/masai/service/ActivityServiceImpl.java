package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.ActivityException;
import com.masai.model.Activity;
import com.masai.repository.ActivityRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepo;

	@Override
	public Activity insertActivity(Activity activiy) throws ActivityException {
		if(activiy!=null)throw new ActivityException("Activity is null");
		if(activiy.getPrice()<=0.0)throw new ActivityException("price needs to be greater than zero");
		log.debug("Calling save method from activityJpa Repository");
		Activity main=activityRepo.save(activiy);
		log.info("Activity added sucessfully");
		return main;
	}

	@Override
	public Activity updateActivity(Activity activity, Integer activityId) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity deletedActivity(Integer activityId) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> viewAllActivities() throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity getActivityById(Integer activityId) throws ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

}
