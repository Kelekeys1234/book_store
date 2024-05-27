package oniline_bookstore_service.service;

import java.util.Date;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;
import oniline_bookstore_service.dto.BookDto;
import oniline_bookstore_service.exception.BookNotFoundException;
import oniline_bookstore_service.model.Book;
import oniline_bookstore_service.Dao.BookstoreDao;

@Service
@Slf4j
public class BookstoreService {
	@Autowired
	private BookstoreDao bookstoreDao;

	public List<BookDto> getAllAvailableBooks() {
		log.info("inside get all book store");
		List<Book> books = bookstoreDao.getAllBook();
		if (!ObjectUtils.isEmpty(books)) {
			return books.stream().map(this::convertToDto).collect(Collectors.toList());
		}
		return null;
	}

	public BookDto addBook(BookDto bookDto) {
		log.info("inside adding of book service");
		Book savedBook = new Book();
		if (!ObjectUtils.isEmpty(bookDto)) {
			Book book = convertToEntity(bookDto);
			book.setId(UUID.randomUUID().toString());
			book.setCreatedDate(new Date());
			savedBook = bookstoreDao.saveBook(book);
		}
		return convertToDto(savedBook);
	}

	public BookDto updateBook(String bookId, BookDto bookDto) throws BadRequestException {

		log.info("inside updation of book service");
		
			Book bookFromDb = validationForBook(bookId);
			bookFromDb = convertToEntity(bookDto);
			bookFromDb.setId(bookId);
			bookFromDb.setUpdatedDate(new Date());
			Book updateBook = bookstoreDao.saveBook(bookFromDb);
			return convertToDto(updateBook);
	}

	public void deleteBook(String bookId) throws BadRequestException {
		log.info("inside delete book from store");
		validationForBook(bookId);
		bookstoreDao.deleteBookStore(bookId);
	}

	private BookDto convertToDto(Book book) {
		BookDto bookDTO = new BookDto();
		bookDTO.setId(book.getId());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setAuthor(book.getAuthor());
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setAvailable(book.getAvailable());
		return bookDTO;
	}

	private Book convertToEntity(BookDto bookDTO) {
		Book book = new Book();
		book.setId(bookDTO.getId());
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setIsbn(bookDTO.getIsbn());
		book.setAvailable(bookDTO.getAvailable());
		return book;
	}

	private Book validationForBook(String bookId) throws BadRequestException {
		Book bookFromDb = bookstoreDao.getBookById(bookId);
		if (ObjectUtils.isEmpty(bookFromDb)) {
			log.info("invalid book id.");
			throw new BookNotFoundException("invalid book id.");

		}
		return bookFromDb;
	}

}
