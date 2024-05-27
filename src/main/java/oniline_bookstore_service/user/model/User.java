package oniline_bookstore_service.user.model;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = false, exclude = "otps")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_info")
@ToString
public class User extends Auditable implements Serializable, UserDetails {

	private static final long serialVersionUID = -8071438426412490566L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false, nullable = false, unique = true)
	private UUID id;

	@Column(name = "title")
	private String title;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "nationality_code")
	private String nationalityCode;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "password")
	private String password;

	@Column(name = "salt")
	private String salt;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private UserStatusEnum status;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hashing_algorithm_id", unique = false)
	private HashingAlgorithm hashingAlgorithm;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private final Set<OTP> otps = new HashSet<>();;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
	}

	@Override
	public String getUsername() {

		return this.emailAddress;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	public void addOtp(OTP otp) {

		this.otps.add(otp);
		otp.setUser(this);
	}
}