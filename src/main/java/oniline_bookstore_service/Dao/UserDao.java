package oniline_bookstore_service.Dao;

import java.util.Optional;

import java.util.UUID;

import oniline_bookstore_service.user.model.User;

public interface UserDao {

	User addUpdateUser (User user);

	Optional<User> getUserById(UUID id);
	
	User getUserByEmailOrPhoneNumber(String email, String phoneNumber);
	
	Optional<User> findByUserName(String name);
	
	User findByEmail(String email);
}