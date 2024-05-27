package oniline_bookstore_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
	
	 @JsonProperty("user_name")
	  @SerializedName("user_name")
	  private String userName;

	  @JsonProperty("password")
	  @SerializedName("password")
	  private String password;


}
