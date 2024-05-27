package oniline_bookstore_service.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class VerifyContact {
	 public enum TypeEnum {
		    @SerializedName("MOBILE")
		    MOBILE("MOBILE"),
		    
		    @SerializedName("EMAIL")
		    EMAIL("EMAIL");
		    
		    private String value;

		    TypeEnum(String value) {
		      this.value = value;
		    }

		    @JsonValue
		    public String getValue() {
		      return value;
		    }

		    @Override
		    public String toString() {
		      return String.valueOf(value);
		    }

		    @JsonCreator
		    public static TypeEnum fromValue(String value) {
		      for (TypeEnum b : TypeEnum.values()) {
		        if (b.value.equals(value)) {
		          return b;
		        }
		      }
		      throw new IllegalArgumentException("Unexpected value '" + value + "'");
		    }
		  }

		  @JsonProperty("type")
		  @SerializedName("type")
		  private TypeEnum type;

		  @JsonProperty("otp")
		  @SerializedName("otp")
		  private String otp;

		  @JsonProperty("contact")
		  @SerializedName("contact")
		  private String contact;

		  public VerifyContact type(TypeEnum type) {
		    this.type = type;
		    return this;
		  }

		
		  public TypeEnum getType() {
		    return type;
		  }

		  public void setType(TypeEnum type) {
		    this.type = type;
		  }

		  public VerifyContact otp(String otp) {
		    this.otp = otp;
		    return this;
		  }

		 

		  public String getOtp() {
		    return otp;
		  }

		  public void setOtp(String otp) {
		    this.otp = otp;
		  }

		  public VerifyContact contact(String contact) {
		    this.contact = contact;
		    return this;
		  }

		  public String getContact() {
		    return contact;
		  }

		  public void setContact(String contact) {
		    this.contact = contact;
		  }


		  @Override
		  public boolean equals(Object o) {
		    if (this == o) {
		      return true;
		    }
		    if (o == null || getClass() != o.getClass()) {
		      return false;
		    }
		    VerifyContact verifyContact = (VerifyContact) o;
		    return Objects.equals(this.type, verifyContact.type) &&
		        Objects.equals(this.otp, verifyContact.otp) &&
		        Objects.equals(this.contact, verifyContact.contact);
		  }

		  @Override
		  public int hashCode() {
		    return Objects.hash(type, otp, contact);
		  }

		  @Override
		  public String toString() {
		    StringBuilder sb = new StringBuilder();
		    sb.append("class VerifyContact {\n");
		    
		    sb.append("    type: ").append(toIndentedString(type)).append("\n");
		    sb.append("    otp: ").append(toIndentedString(otp)).append("\n");
		    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
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



