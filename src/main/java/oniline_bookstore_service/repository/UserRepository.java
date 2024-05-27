package oniline_bookstore_service.repository;

import java.util.Optional;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oniline_bookstore_service.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  User findByEmailAddressOrMobileNumber(String email, String phoneNumber);
  Optional<User> findByEmailAddress(String emailAddress);
}
