package oniline_bookstore_service.DaoImp;

import java.util.Optional;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oniline_bookstore_service.Dao.UserDao;
import oniline_bookstore_service.repository.UserRepository;
import oniline_bookstore_service.user.model.User;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUpdateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(UUID id) {
		return userRepository.findById(id);
	}

	@Override
	public User getUserByEmailOrPhoneNumber(String email, String phoneNumber) {
		return userRepository.findByEmailAddressOrMobileNumber(email, phoneNumber);
	}

	@Override
	public Optional<User> findByUserName(String name) {
		Optional<User> user = userRepository.findByEmailAddress(name);
		return user;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmailAddress(email).orElse(null);
	}
}
