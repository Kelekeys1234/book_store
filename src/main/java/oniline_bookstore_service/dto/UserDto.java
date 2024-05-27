package oniline_bookstore_service.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@lombok.Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	 @JsonProperty("id")
	  @SerializedName("id")
	  private String id;

	  @JsonProperty("first_name")
	  @SerializedName("first_name")
	  private String firstName;

	  @JsonProperty("middle_name")
	  @SerializedName("middle_name")
	  private String middleName;

	  @JsonProperty("last_name")
	  @SerializedName("last_name")
	  private String lastName;

	  @JsonProperty("title")
	  @SerializedName("title")
	  private String title;

	  @JsonProperty("email_address")
	  @SerializedName("email_address")
	  private String emailAddress;

	  @JsonProperty("country_code")
	  @SerializedName("country_code")
	  private String countryCode;

	  @JsonProperty("phone_number")
	  @SerializedName("phone_number")
	  private BigDecimal phoneNumber;

	  @JsonProperty("user_name")
	  @SerializedName("user_name")
	  private String userName;

	  public UserDto id(String id) {
	    this.id = id;
	    return this;
	  }

}
