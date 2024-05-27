package oniline_bookstore_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {

	  @JsonProperty("data")
	  @SerializedName("data")
	  private TokenDto data;

	  public TokenResponse data(TokenDto data) {
	    this.data = data;
	    return this;
	  }
}
