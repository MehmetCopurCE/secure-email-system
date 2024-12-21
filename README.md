# Confidential and Authentic Email System with User Registration

## **Project Overview**
This project implements a secure email system ensuring:
1. **User Authentication**: Users register and authenticate using a hashed password.
2. **Message Confidentiality**: Emails are encrypted with symmetric encryption, and the symmetric key is encrypted using the recipient's public key.
3. **Message Integrity and Authentication**: Email integrity is ensured using a hash function, and the sender's identity is authenticated using digital signatures.

The system is built using Spring Boot and tested via API endpoints.

---

## **Features**
1. **User Registration and Login**
    - Users register with a username and password.
    - Passwords are hashed using a secure hashing algorithm (e.g., BCrypt) before being stored in the database.
    - Authentication verifies the username and hashed password.

2. **Message Confidentiality**
    - Email content is encrypted using a symmetric encryption algorithm (e.g., AES).
    - The symmetric key is encrypted with the recipient's public key using RSA.

3. **Message Integrity and Sender Authentication**
    - The email content's hash is generated using a secure hash function (e.g., SHA-256).
    - The hash is digitally signed using the sender's private key.

4. **Message Decryption and Verification**
    - The recipient decrypts the symmetric key using their private key.
    - Email content is decrypted using the symmetric key.
    - Message integrity is verified by comparing the hashes.
    - Sender's identity is verified using the sender's public key.

---

## **Setup Instructions**

### **Requirements**
- Java 17+
- Maven
- Postman (for API testing)
- MySQL (or any other relational database)

### **Steps to Run**
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <project-directory>
   ```

2. Configure the database:
    - Update `application.properties` with your database credentials:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/email_system
      spring.datasource.username=<your-db-username>
      spring.datasource.password=<your-db-password>
      ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the API via Postman using `http://localhost:8080/api`.

---

## **API Endpoints**

### **1. User Registration**
- **Endpoint**: `/api/auth/register`
- **Method**: POST
- **Parameters**:
    - `username`: User's username
    - `password`: User's password
- **Description**: Registers a new user and stores their hashed password and public key.

### **2. User Login**
- **Endpoint**: `/api/auth/login`
- **Method**: POST
- **Parameters**:
    - `username`: User's username
    - `password`: User's password
- **Description**: Authenticates the user by verifying the password against the stored hash.

### **3. Send Email**
- **Endpoint**: `/api/email/send`
- **Method**: POST
- **Body** (JSON):
  ```json
  {
    "recipient": "<username>",
    "message": "<message content>",
    "senderPrivateKey": "<private key>"
  }
  ```
- **Description**: Encrypts the message, generates a digital signature, and stores the email.

### **4. Receive Email**
- **Endpoint**: `/api/email/receive`
- **Method**: GET
- **Parameters**:
    - `username`: Recipient's username
    - `privateKey`: Recipient's private key
- **Description**: Retrieves and decrypts the emails sent to the user and verifies their integrity and authenticity.

---

## **Cryptographic Techniques**
- **Password Hashing**: BCrypt is used to securely hash passwords.
- **Symmetric Encryption**: AES is used for encrypting email content.
- **Public-Key Encryption**: RSA is used for encrypting the symmetric key.
- **Hashing**: SHA-256 ensures the integrity of the email content.
- **Digital Signatures**: RSA is used for signing the email hash.

---

## **Screenshots**
1. **User Registration**
   ![User Registration](#)

2. **Send Email**
   ![Send Email](#)

3. **Receive and Verify Email**
   ![Receive Email](#)

---

## **Error Handling**
- Invalid login attempts return `401 Unauthorized`.
- Message verification failures return `400 Bad Request` with detailed error messages.

---

## **Future Improvements**
- Add frontend for a more user-friendly experience.
- Implement token-based authentication for session management.
- Enhance key management for better usability.

---

## **Contact**
For any questions or issues, feel free to contact: [Your Email Address]

