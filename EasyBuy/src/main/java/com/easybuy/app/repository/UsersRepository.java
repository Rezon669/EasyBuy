package com.easybuy.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.easybuy.app.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

	Optional<Users> findByUsername(String username);

	public Optional<Users> findByEmailid(String emailid);

	@Query("SELECT u.role from Users u WHERE u.emailid = :emailid")
	public String findEmailid(String emailid);

	@Modifying
	@Query("UPDATE Users u SET u.password = :password WHERE u.emailid = :email")
	void updatePasswordByEmailid(@Param("password") String password, @Param("email") String email);

	@Query("SELECT username from Users u WHERE u.emailid = :emailid")
	public String findByUser(String emailid);

}
