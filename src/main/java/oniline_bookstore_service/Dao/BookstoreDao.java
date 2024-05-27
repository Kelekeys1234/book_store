package oniline_bookstore_service.Dao;
import java.util.List;

import oniline_bookstore_service.model.Book;

public interface BookstoreDao {

	public Book getBookById(String bookId);
	
	public Book saveBook(Book book);
	
	public List<Book> getAllBook();
	
	public void deleteBookStore(String id);
}
