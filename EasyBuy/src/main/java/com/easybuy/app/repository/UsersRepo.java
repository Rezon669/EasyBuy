package com.easybuy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.easybuy.app.entity.Users;

public interface UsersRepo extends JpaRepository<Users, String> {

	// void getDetails(String mobilenumber, String password);

	// @Query("SELECT u.name FROM Users u WHERE" + " u.mobilenummber LIKE %?1% AND
	// "+ " u.password LIKE %?2%")
	// public String getDetailsfromDB( String mobilenummber, String password);

//	public String getDetailsfromDB(String mobilenumber, String password);

	public Users findByUsername(String username);

	public Object findByEmailid(String emailid);

	@Modifying
	@Query("UPDATE Users u SET u.password = :password WHERE u.emailid = :email")
	void updatePasswordByEmailid(@Param("password") String password, @Param("email") String email);

	// public void getDe(String mobilenumber, String password);

	// @Query("SELECT u.uid FROM Users u ")
//	public List<Object> findalluid(String uid);

	// Users findByUsername(String username);

}
