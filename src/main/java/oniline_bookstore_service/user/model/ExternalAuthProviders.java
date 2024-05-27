package oniline_bookstore_service.user.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "external_auth_providers")
public class ExternalAuthProviders extends Auditable implements Serializable {

	private static final long serialVersionUID = -2358485743235120982L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "client_id")
	private String clientId;
	
	@Column(name = "client_secret")
	private String clientSecret;
	
	@Column(name = "authorization_uri")
	private String authorizationUri;
	
	@Column(name = "token_uri")
	private String tokenUri;
	
	@Column(name = "user_info_uri")
	private String userInfoUri;

	

}