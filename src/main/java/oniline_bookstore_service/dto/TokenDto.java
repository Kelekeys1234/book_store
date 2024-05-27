package oniline_bookstore_service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto 

	
	implements Serializable {
		  private static final long serialVersionUID = 1L;

		  @JsonProperty("access_token")
		  @SerializedName("access_token")
		  private String accessToken;

		  @JsonProperty("refresh_token")
		  @SerializedName("refresh_token")
		  private String refreshToken;

		  @JsonProperty("token_type")
		  @SerializedName("token_type")
		  private String tokenType;

		  @JsonProperty("expires_in")
		  @SerializedName("expires_in")
		  private BigDecimal expiresIn;

		  @JsonProperty("refresh_expires_in")
		  @SerializedName("refresh_expires_in")
		  private BigDecimal refreshExpiresIn;

}
