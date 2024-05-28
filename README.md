# Book Store Backend

This project provides the backend services for a book store application. It includes user authentication, account verification, and user registration functionalities.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
  - [User Login](#user-login)
  - [Verify Contact](#verify-contact)
  - [User Sign Up](#user-sign-up)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/book-store.git
    cd book-store
    ```

2. Build the project (if using Maven):
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    java -jar target/book-store-1.0.0.jar
    ```

## Usage

Start the application and use the following API endpoints to interact with the user functionalities.

## API Endpoints

### User Login

Authenticate a user with their username and password.

- **URL**: `localhost:8901/book-store/api/v1/user/login`
- **Method**: `POST`
- **Headers**:
  - `Authorization: Bearer <JWT_TOKEN>`
  - `Content-Type: application/json`
- **Request Body**:
    ```json
    {
      "user_name": "winner12@gmail.com",
      "password": "8888"
    }
    ```
- **Curl Command**:
    ```sh
    curl --location --request POST 'localhost:8901/book-store/api/v1/user/login' \
    --header 'Content-Type: application/json' \
    --data-raw '{
      "user_name": "winner12@gmail.com",
      "password": "8888"
    }'
    ```

### Verify Contact

Verify the user's contact information with an OTP.

- **URL**: `localhost:8901/book-store/api/v1/user/verify_contact`
- **Method**: `PUT`
- **Headers**:
  - `Content-Type: application/json`
- **Request Body**:
    ```json
    {
      "type": "EMAIL",
      "otp": "629027",
      "contact": "9012902655"
    }
    ```
- **Curl Command**:
    ```sh
    curl --location --request PUT 'localhost:8901/book-store/api/v1/user/verify_contact' \
    --header 'Content-Type: application/json' \
    --data-raw '{
      "type": "EMAIL",
      "otp": "629027",
      "contact": "9012902655"
    }'
    ```

### User Sign Up

Register a new user with the required details.

- **URL**: `localhost:8901/book-store/api/v1/user/signUp`
- **Method**: `POST`
- **Headers**:
  - `Content-Type: application/json`
- **Request Body**:
    ```json
    {
      "first_name": "kelly",
      "middle_name": "kelly",
      "last_name": "kelly",
      "title": "Mr",
      "email_address": "kelly1@gmail.com",
      "country_code": "string",
      "phone_number": 9078902655,
      "user_name": "string",
      "password": "string"
    }
    ```
- **Curl Command**:
    ```sh
    curl --location --request POST 'localhost:8901/book-store/api/v1/user/signUp' \
    --header 'Content-Type: application/json' \
    --data-raw '{
      "first_name": "kelly",
      "middle_name": "kelly",
      "last_name": "kelly",
      "title": "Mr",
      "email_address": "kelly1@gmail.com",
      "country_code": "string",
      "phone_number": 9078902655,
      "user_name": "string",
      "password": "string"
    }'
    ```

## Contributing

Contributions are welcome! Please submit a pull request or open an issue to discuss any changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
