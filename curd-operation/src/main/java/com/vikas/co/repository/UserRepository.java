package com.vikas.co.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vikas.co.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByName(String name);
    List<User> findByEmail(String email);
	Optional<User> findById(Integer id);
	void deleteById(Integer id);
	boolean existsByEmail(String email);
}
