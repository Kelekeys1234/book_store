package oniline_bookstore_service.utils;

import java.time.LocalDateTime;

import org.apache.coyote.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;
import oniline_bookstore_service.Dao.HashingAlgorithmDao;
import oniline_bookstore_service.user.model.HashingAlgorithm;

@Service
@Slf4j
public class HashingAlgorithmUtils {
	@Autowired
	private HashingAlgorithmDao hashingAlgorithmDao;

	public HashingAlgorithm saveHashingAlgorithm(String algorithmName) throws BadRequestException {
		
			HashingAlgorithm newAlgorithm = new HashingAlgorithm();
			newAlgorithm.setName(algorithmName);
			newAlgorithm.setCreatedAt(LocalDateTime.now());
			return hashingAlgorithmDao.addUpdateHashingAlgorithm(newAlgorithm);
		

		

	}

}
