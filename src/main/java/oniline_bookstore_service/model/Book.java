package oniline_bookstore_service.model;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "book_store")
public class Book {

	@Id
	@Column(name = "id", unique = true, nullable = false, length=36)
	private String id;
	@Column(name = "title")
	private String title;
	@Column(name = "author")
	private String author;
	@Column(name = "isbn")
	private String isbn;
	@Column(name = "available")
	private Boolean available;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "update_by")
	private String updatedBy;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "updated_date")
	private Date UpdatedDate;

	// Getters and Setters
}
