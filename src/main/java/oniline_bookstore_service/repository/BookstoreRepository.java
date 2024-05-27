package oniline_bookstore_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oniline_bookstore_service.model.Book;

@Repository
public interface BookstoreRepository extends JpaRepository<Book, String> {
	  // Custom query method to find all available books
    List<Book> findByAvailableTrue();
}
