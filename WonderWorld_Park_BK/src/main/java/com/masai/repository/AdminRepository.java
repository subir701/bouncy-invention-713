package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Optional<Admin> findByAdminEmail(String email);
	Optional<Admin> findByAdminUsername(String username);
}
