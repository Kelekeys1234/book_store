package oniline_bookstore_service.repository;

import java.util.Optional;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oniline_bookstore_service.user.model.HashingAlgorithm;
@Repository
public interface HashingAlgorithmRepository extends JpaRepository<HashingAlgorithm,UUID>{
	  Optional<HashingAlgorithm> findByName(String name);
}
