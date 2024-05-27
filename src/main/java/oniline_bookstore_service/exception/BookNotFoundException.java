package oniline_bookstore_service.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookNotFoundException  extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String message) {
        super(message);
    }
}