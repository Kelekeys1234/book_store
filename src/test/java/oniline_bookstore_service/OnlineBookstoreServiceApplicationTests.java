package oniline_bookstore_service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import oniline_bookstore_service.Dao.BookstoreDao;
import oniline_bookstore_service.exception.BookNotFoundException;
import oniline_bookstore_service.model.Book;
import oniline_bookstore_service.service.BookstoreService;

@SpringBootTest
class OnlineBookstoreServiceApplicationTests {
	@Mock
	private BookstoreDao bookStoreDao;

	@InjectMocks
	private BookstoreService bookService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddBook() {
		Book book = new Book();
		book.setId(UUID.randomUUID().toString());
		book.setTitle("Sample Book");
		book.setAuthor("Sample Author");
		book.setIsbn("1234567890");
		book.setAvailable(true);
		Mockito.when(bookStoreDao.saveBook(book)).thenReturn(book);

	}

	@Test
	void testUpdateBook() throws BookNotFoundException {
		String id = UUID.randomUUID().toString();
		Book book = new Book();
		book.setId(id.toString());
		book.setTitle("Old Title");
		book.setAuthor("Old Author");
		book.setIsbn("1234567890");
		book.setAvailable(true);

		Mockito.when(bookStoreDao.getBookById(id.toString())).thenReturn(Optional.of(book).orElse(null));
		Mockito.when(bookStoreDao.saveBook(book)).thenReturn(book);
	}

	@Test
	void testDeleteBook() throws BookNotFoundException, BadRequestException {
		String id = UUID.randomUUID().toString();
		Book book = new Book();
		book.setId(id.toString());

		Mockito.when(bookStoreDao.getBookById(id.toString())).thenReturn(Optional.of(book).orElse(null));

		bookService.deleteBook(id);

		verify(bookStoreDao, times(1)).getBookById(id);
	}

}
