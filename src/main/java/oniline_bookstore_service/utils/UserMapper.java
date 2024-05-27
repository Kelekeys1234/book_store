package oniline_bookstore_service.utils;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;
import oniline_bookstore_service.dto.SignUpRequest;
import oniline_bookstore_service.dto.SignUpResponse;
import oniline_bookstore_service.dto.UserDto;
import oniline_bookstore_service.user.model.User;

@UtilityClass
public class UserMapper {

	public static User populateUser(SignUpRequest signUpRequest) {

		String salt = PasswordMapper.generateSalt();
		String hashedPassword = PasswordMapper.hashPassword(signUpRequest.getPassword(), salt);
		return User.builder().firstName(signUpRequest.getFirstName()).middleName(signUpRequest.getMiddleName())
				.lastName(signUpRequest.getLastName()).title(signUpRequest.getTitle())
				.emailAddress(signUpRequest.getEmailAddress()).countryCode(signUpRequest.getCountryCode())
				.mobileNumber(signUpRequest.getPhoneNumber().toString()).salt(salt).password(hashedPassword)
				.createdAt(LocalDateTime.now()).createdBy(signUpRequest.getEmailAddress())
				.build();
	}

	public static SignUpResponse populateUserDto(User user) {
		SignUpResponse SignUpResponse = new SignUpResponse();
		UserDto userDto = UserDto.builder().id(user.getId().toString()).firstName(user.getFirstName())
				.middleName(user.getMiddleName()).countryCode(user.getCountryCode())
				.phoneNumber(new BigDecimal(user.getMobileNumber())).lastName(user.getLastName())
				.emailAddress(user.getEmailAddress()).title(user.getTitle()).userName(user.getFirstName()).build();
		SignUpResponse.data(userDto);
		return SignUpResponse;

	}

}
