package oniline_bookstore_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import oniline_bookstore_service.dto.BookDto;
import oniline_bookstore_service.exception.BadRequestException;
import oniline_bookstore_service.handler.GenericResponse;
import oniline_bookstore_service.service.BookstoreService;

@RestController
@RequestMapping("/api/v1/book")
@Slf4j
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookstoreController {

	@Autowired
	private BookstoreService bookstoreService;

	@GetMapping
	public ResponseEntity<GenericResponse<List<BookDto>>> getAllAvailableBooks() {
		log.info("Fetching all available books.");
		List<BookDto> allBooks = new ArrayList<>();
		try {
			allBooks = bookstoreService.getAllAvailableBooks();
		} catch (Exception e) {
			log.error("Error while fetching books", e);
			return buildErrorResponse("Error fetching books", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		GenericResponse<List<BookDto>> genericResponse = new GenericResponse.Builder<List<BookDto>>().setData(allBooks)
				.setStatus(HttpStatus.OK).setMessage("Fetched all books successfully").build();
		return new ResponseEntity<>(genericResponse, HttpStatus.OK);
	}

	
	@PostMapping
	public ResponseEntity<GenericResponse<BookDto>> addBook(@RequestBody BookDto bookDto) {
		log.info("Adding a new book.");
		BookDto savedBook;
		try {
			savedBook = bookstoreService.addBook(bookDto);
		} catch (BadRequestException e) {
			log.error("Error while saving book: {}", e.getMessage());
			return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error while saving book", e);
			return buildErrorResponse("Error saving book", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		GenericResponse<BookDto> genericResponse = new GenericResponse.Builder<BookDto>().setData(savedBook)
				.setStatus(HttpStatus.CREATED).setMessage("Book added successfully").build();
		return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
	}

	@PutMapping("/{bookId}")
	public ResponseEntity<GenericResponse<BookDto>> updateBook(@PathVariable("bookId") String bookId,
			@RequestBody BookDto bookDto) {
		log.info("Updating book with ID: {}", bookId);
		BookDto updatedBook;
		try {
			updatedBook = bookstoreService.updateBook(bookId, bookDto);
		} catch (BadRequestException e) {
			log.error("Error while updating book: {}", e.getMessage());
			return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error while updating book", e);
			return buildErrorResponse("Error updating book", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		GenericResponse<BookDto> genericResponse = new GenericResponse.Builder<BookDto>().setData(updatedBook)
				.setStatus(HttpStatus.OK).setMessage("Book updated successfully").build();
		return new ResponseEntity<>(genericResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<GenericResponse<Void>> deleteBook(@PathVariable("bookId") String bookId) {
		log.info("Deleting book with ID: {}", bookId);
		try {
			bookstoreService.deleteBook(bookId);
		} catch (BadRequestException e) {
			log.error("Error while deleting book: {}", e.getMessage());
			return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error while deleting book", e);
			return buildErrorResponse("Error deleting book", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		GenericResponse<Void> genericResponse = new GenericResponse.Builder<Void>().setStatus(HttpStatus.NO_CONTENT)
				.setMessage("Book deleted successfully").build();
		return new ResponseEntity<>(genericResponse, HttpStatus.NO_CONTENT);
	}

	private <T> ResponseEntity<GenericResponse<T>> buildErrorResponse(String message, HttpStatus status) {
		GenericResponse<T> response = new GenericResponse.Builder<T>().setMessage(message).setStatus(status).build();
		return new ResponseEntity<>(response, status);
	}

}
