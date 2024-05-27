package oniline_bookstore_service.DaoImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import oniline_bookstore_service.Dao.BookstoreDao;
import oniline_bookstore_service.model.Book;
import oniline_bookstore_service.repository.BookstoreRepository;

@Component
@Slf4j
public class BookstoreDaoImpl implements BookstoreDao {

	@Autowired
	private BookstoreRepository repository;

	@Override
	public Book getBookById(String bookId) {
		log.info("inside book DaoImp");
		return repository.findById(bookId).orElse(null);
	}

	@Override
	public Book saveBook(Book book) {
		log.info("inside saveBook DaoImp");
		return repository.save(book);
	}

	@Override
	public List<Book> getAllBook() {
		log.info("get all book store");
		return repository.findByAvailableTrue();
	}

	@Override
	public void deleteBookStore(String id) {
		log.info("delete by id");
		repository.deleteById(id);

	}

}
