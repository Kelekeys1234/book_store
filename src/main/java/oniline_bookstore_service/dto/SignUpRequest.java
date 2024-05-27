package oniline_bookstore_service.dto;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;


public class SignUpRequest {
	private static final long serialVersionUID = 1L;

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

	  @JsonProperty("password")
	  @SerializedName("password")
	  private String password;

	  public SignUpRequest firstName(String firstName) {
	    this.firstName = firstName;
	    return this;
	  }

	  public String getFirstName() {
	    return firstName;
	  }

	  public void setFirstName(String firstName) {
	    this.firstName = firstName;
	  }

	  public SignUpRequest middleName(String middleName) {
	    this.middleName = middleName;
	    return this;
	  }

	  public String getMiddleName() {
	    return middleName;
	  }

	  public void setMiddleName(String middleName) {
	    this.middleName = middleName;
	  }

	  public SignUpRequest lastName(String lastName) {
	    this.lastName = lastName;
	    return this;
	  }


	  public String getLastName() {
	    return lastName;
	  }

	  public void setLastName(String lastName) {
	    this.lastName = lastName;
	  }

	  public SignUpRequest title(String title) {
	    this.title = title;
	    return this;
	  }

	

	  public String getTitle() {
	    return title;
	  }

	  public void setTitle(String title) {
	    this.title = title;
	  }

	  public SignUpRequest emailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
	    return this;
	  }

	

//	@javax.validation.constraints.Email
	  public String getEmailAddress() {
	    return emailAddress;
	  }

	  public void setEmailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
	  }

	  public SignUpRequest countryCode(String countryCode) {
	    this.countryCode = countryCode;
	    return this;
	  }


	  public String getCountryCode() {
	    return countryCode;
	  }

	  public void setCountryCode(String countryCode) {
	    this.countryCode = countryCode;
	  }

	  public SignUpRequest phoneNumber(BigDecimal phoneNumber) {
	    this.phoneNumber = phoneNumber;
	    return this;
	  }

	  public BigDecimal getPhoneNumber() {
	    return phoneNumber;
	  }

	  public void setPhoneNumber(BigDecimal phoneNumber) {
	    this.phoneNumber = phoneNumber;
	  }

	  public SignUpRequest userName(String userName) {
	    this.userName = userName;
	    return this;
	  }



	  public String getUserName() {
	    return userName;
	  }

	  public void setUserName(String userName) {
	    this.userName = userName;
	  }

	  public SignUpRequest password(String password) {
	    this.password = password;
	    return this;
	  }

	 

	  public String getPassword() {
	    return password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }


	  @Override
	  public boolean equals(Object o) {
	    if (this == o) {
	      return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }
	    SignUpRequest signUpRequest = (SignUpRequest) o;
	    return Objects.equals(this.firstName, signUpRequest.firstName) &&
	        Objects.equals(this.middleName, signUpRequest.middleName) &&
	        Objects.equals(this.lastName, signUpRequest.lastName) &&
	        Objects.equals(this.title, signUpRequest.title) &&
	        Objects.equals(this.emailAddress, signUpRequest.emailAddress) &&
	        Objects.equals(this.countryCode, signUpRequest.countryCode) &&
	        Objects.equals(this.phoneNumber, signUpRequest.phoneNumber) &&
	        Objects.equals(this.userName, signUpRequest.userName) &&
	        Objects.equals(this.password, signUpRequest.password);
	  }

	  @Override
	  public int hashCode() {
	    return Objects.hash(firstName, middleName, lastName, title, emailAddress, countryCode, phoneNumber, userName, password);
	  }

	  @Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("class SignUpRequest {\n");
	    
	    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
	    sb.append("    middleName: ").append(toIndentedString(middleName)).append("\n");
	    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
	    sb.append("    title: ").append(toIndentedString(title)).append("\n");
	    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
	    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
	    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
	    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
	    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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
