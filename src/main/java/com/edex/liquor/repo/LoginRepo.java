package com.edex.liquor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edex.liquor.model.Login;


public interface LoginRepo  extends JpaRepository<Login, Integer> {
	@Query(value = "Select * from login Where username = :username AND password = :password", nativeQuery = true)
	Login findBy(String username, String password);

}
