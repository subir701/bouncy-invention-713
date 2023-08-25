package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

	@Query(value = "SELECT a FROM Activity a WHERE a.price <= :charge")
	List<Activity> findByCharge(Double charge);
}
