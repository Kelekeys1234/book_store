package oniline_bookstore_service.user.service;

import oniline_bookstore_service.user.model.HashingAlgorithm;
import oniline_bookstore_service.user.model.OTP;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import oniline_bookstore_service.Dao.OTPDao;
import oniline_bookstore_service.Dao.RoleDao;
import oniline_bookstore_service.Dao.UserDao;
import oniline_bookstore_service.dto.SignInRequest;
import oniline_bookstore_service.dto.SignUpRequest;
import oniline_bookstore_service.dto.TokenDto;
import oniline_bookstore_service.dto.TokenResponse;
import oniline_bookstore_service.dto.VerifyContact;
import oniline_bookstore_service.dto.VerifyContact.TypeEnum;
import oniline_bookstore_service.user.model.Role;
import oniline_bookstore_service.user.model.User;
import oniline_bookstore_service.utils.HashingAlgorithmUtils;
import oniline_bookstore_service.utils.UserMapper;

@Service
@Slf4j
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private OTPDao otpDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private OTPService otpService;

	@Value("${hashing_algorithm_name}")
	private String HASHING_ALGORITHM_NAME;

	@Autowired
	private HashingAlgorithmUtils hashingAlgorithmUtils;

	@Value("${admin_role}")
	private String ADMIN_ROLE;

	@Transactional
	public String signUp(SignUpRequest signUpRequest) throws BadRequestException {
		log.info("inside signUp service");

		// Validate email and phone number
		if (!isValidEmail(signUpRequest.getEmailAddress())) {
			throw new BadRequestException("Invalid email address.");
		}

		// Check if user already exists in the database
		User user = userDao.getUserByEmailOrPhoneNumber(signUpRequest.getEmailAddress(),
				signUpRequest.getPhoneNumber().toString());
		if (user != null) {
			log.info("Error occurred while processing your request. Please try again later.");
			throw new BadRequestException("User with this email or phone number already exists.");
		}

		// Populate user details
		User userFromUtils = UserMapper.populateUser(signUpRequest);

		// Ensure a new HashingAlgorithm instance for each user
		HashingAlgorithm hashingAlgorithm = hashingAlgorithmUtils.saveHashingAlgorithm(HASHING_ALGORITHM_NAME);
		userFromUtils.setHashingAlgorithm(hashingAlgorithm);

		// Set role
		Role role = new Role();
		role.setName(ADMIN_ROLE);
		role.setIsActive(true);
		Role roleFrmDb = roleDao.saveRole(role);
		userFromUtils.setRoles(Collections.singleton(roleFrmDb));

		// Create OTP
		OTP createOtp = otpService.createOtp(userFromUtils);
		userFromUtils.addOtp(createOtp);

		// Save User (which will cascade and save the OTP)
		User userFrmDb = userDao.addUpdateUser(userFromUtils);

		log.info("User saved successfully with ID: {}", userFrmDb.getId());
		log.info("OTP generated and linked to user: {}", createOtp.getOtp());

		return "User registration successful. Please copy your token and verify " + createOtp.getOtp();
	}

	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pat = Pattern.compile(emailRegex);
		return email != null && pat.matcher(email).matches();
	}


	public TokenResponse signIn(SignInRequest signInRequest) {
		log.info("inside sign in service..");

		TokenResponse tokenResponse = new TokenResponse();
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword()));
		if (!authentication.isAuthenticated()) {
			log.info("invalid user request !");
			throw new UsernameNotFoundException("invalid user request !");
		}
		TokenDto tokenDto = new TokenDto();
		tokenDto.setAccessToken(tokenGenerator.createAccessToken(authentication));
		tokenDto.setRefreshToken(tokenGenerator.createRefreshToken(authentication));
		tokenDto.setExpiresIn(tokenGenerator.calculateAccessTokenExpiration(Instant.now()));
		tokenDto.setRefreshExpiresIn((tokenGenerator.calculateRefreshTokenExpiration(Instant.now())));
		tokenResponse.setData(tokenDto);
		return tokenResponse;

	}

	public String verifyOtp(VerifyContact verifyContact) throws BadRequestException {
		log.info("otp verification");
		// find OTP by otp ...
		if (!ObjectUtils.isEmpty(verifyContact)
				&& verifyContact.getType().name().equalsIgnoreCase(TypeEnum.EMAIL.name())) {
			OTP otp = otpDao.getByOtp(verifyContact.getOtp());
			if (!ObjectUtils.isEmpty(otp) && !LocalDateTime.now().isAfter(otp.getExpiryTime())) {
				otp.setIsConsumed(true);
				otp.setUpdatedAt(LocalDateTime.now());
				otpDao.createUpdateOtp(otp);
			} else if (!ObjectUtils.isEmpty(otp) && LocalDateTime.now().isAfter(otp.getExpiryTime())) {
				log.warn("The OTP has expired!");
				throw new BadRequestException("The OTP has expired!.");
			} else {
				// Handle the case where no OTP is found
				log.warn("No valid OTP found !");
				throw new BadRequestException("No valid OTP found!.");
			}

		}
		return "verification successfull";
	}

}
