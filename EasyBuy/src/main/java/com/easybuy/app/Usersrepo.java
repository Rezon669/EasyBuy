package com.easybuy.app;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Usersrepo extends JpaRepository<Users, String> {

	@Query("SELECT u.name FROM Users u WHERE" + " u.mobilenummber LIKE %?1% AND "+ " u.password LIKE %?2%")
    public String finduser(@Param("mobilenummber") String mobilenummber,@Param("password")  String password);

	//@Query("SELECT u.uid FROM Users u ")
//	public List<Object> findalluid(String uid);



	
}
