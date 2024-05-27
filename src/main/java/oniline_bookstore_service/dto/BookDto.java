package oniline_bookstore_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BookDto {
	@JsonProperty("id")
	private String id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("author")
	private String author;

	@JsonProperty("isbn")
	private String isbn;

	@JsonProperty("available")
	private Boolean available;
	

}
