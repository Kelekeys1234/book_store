package oniline_bookstore_service.DaoImp;

import java.util.Optional;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oniline_bookstore_service.Dao.HashingAlgorithmDao;
import oniline_bookstore_service.user.model.HashingAlgorithm;
import oniline_bookstore_service.repository.HashingAlgorithmRepository;


@Service
public class HashingAlgorithmDaoImpl implements HashingAlgorithmDao{
	@Autowired
	private HashingAlgorithmRepository HashingAlgorithmRepository;

	@Override
	public HashingAlgorithm addUpdateHashingAlgorithm(HashingAlgorithm hashingAlgorithm) {
		return HashingAlgorithmRepository.save(hashingAlgorithm);
	}

	@Override
	public Optional<HashingAlgorithm> getByName(String name) {
		return HashingAlgorithmRepository.findByName(name);
	}

	@Override
	public Optional<HashingAlgorithm> findById(UUID id) {
		return HashingAlgorithmRepository.findById(id);
	}

}
