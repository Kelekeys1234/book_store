package oniline_bookstore_service.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

public class SignUpResponse {
	
	 private static final long serialVersionUID = 1L;

	  @JsonProperty("data")
	  @SerializedName("data")
	  private UserDto data;

	  public SignUpResponse data(UserDto data) {
	    this.data = data;
	    return this;
	  }


	  public UserDto getData() {
	    return data;
	  }

	  public void setData(UserDto data) {
	    this.data = data;
	  }


	  @Override
	  public boolean equals(Object o) {
	    if (this == o) {
	      return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }
	    SignUpResponse signUpResponse = (SignUpResponse) o;
	    return Objects.equals(this.data, signUpResponse.data);
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(data);
	  }

	  @Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("class SignUpResponse {\n");
	    
	    sb.append("    data: ").append(toIndentedString(data)).append("\n");
	    sb.append("}");
	    return sb.toString();
	  }

	  /**
	   * Convert the given object to string with each line indented by 4 spaces
	   * (except the first line).
	   */
	  private String toIndentedString(Object o) {
	    if (o == null) {
	      return "null";
	    }
	    return o.toString().replace("\n", "\n    ");
	  }
	}




