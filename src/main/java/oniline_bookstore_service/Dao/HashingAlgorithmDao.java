package oniline_bookstore_service.Dao;

import java.util.Optional;

import java.util.UUID;

import oniline_bookstore_service.user.model.HashingAlgorithm;

public interface HashingAlgorithmDao {
	HashingAlgorithm addUpdateHashingAlgorithm(HashingAlgorithm hashingAlgorithm);
	Optional<HashingAlgorithm> findById(UUID id);
	Optional<HashingAlgorithm> getByName(String name);

}
