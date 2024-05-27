package oniline_bookstore_service.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PasswordMapper {
	public String generateSalt() {
		byte[] salt = new byte[16];
		new SecureRandom().nextBytes(salt);
		return bytesToHex(salt);
	}

	public String hashPassword(String password, String salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update((password + salt).getBytes());
			byte[] hashedPassword = md.digest();
			return bytesToHex(hashedPassword);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error hashing password.");
		}
	}

	private String bytesToHex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte b : bytes) {
			result.append(String.format("%02x", b));
		}
		return result.toString();
	}

}
