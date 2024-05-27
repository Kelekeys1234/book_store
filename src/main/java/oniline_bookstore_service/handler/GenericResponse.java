package oniline_bookstore_service.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenericResponse<T> {
    private static final String DATA_CONST = "data";
    private static final String STATUS_CODE_CONST = "status";
    private static final String MESSAGE_CONST = "message";
    private final T data;
    private final HttpStatus status;
    private final String message;

    private GenericResponse(final Builder<T> builder) {
        this.data = builder.data;
        this.message = builder.message;
        this.status = builder.status;
    }

    public T getData() {
        return data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public static class Builder<T> {
        private T data;
        private HttpStatus status;
        private String message;

        public Builder<T> setData(final T data) {
            this.data = data;
            return this;
        }

        public Builder<T> setStatus(final HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder<T> setMessage(final String message) {
            this.message = message;
            return this;
        }

        public GenericResponse<T> build() {
            return new GenericResponse<>(this);
        }
        
        public Object create() {
    		final GenericResponse handler = new GenericResponse(this);
    		final Map<String, Object> responseMap = new HashMap<>(4);
    		responseMap.put(STATUS_CODE_CONST, handler.status.value());
    		responseMap.put(MESSAGE_CONST, handler.message);
    		if (handler.data != null) {
    			responseMap.put(DATA_CONST, handler.data);
    		}

    		return new ResponseEntity<>(responseMap, status);
    	}
    }
    
   
}
