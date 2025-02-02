
### **1. Project Overview**
- **Purpose**: This project is a console-based banking management system developed in Java, integrated with MySQL as the backend database. It offers essential banking functionalities such as account creation, balance inquiry, money transfer, and secure transactions.  
- **Key Features**:
  - User Registration and Login
  - Account Management (open, access, secure via PIN)
  - Financial Transactions (debit, credit, transfer)
  - Balance Inquiry
  - Security Measures (PIN authentication for transactions)

### **2. Technology Stack**
- **Programming Language**: Java  
- **Database**: MySQL  
- **Database Connectivity**: JDBC  
- **Tools**: Scanner for input, Prepared Statements for SQL queries, and exception handling for robust operations.


### **3. Functional Breakdown**

#### **a) User Module**  
- **Register**: 
  - Allows users to sign up with a full name, email, and password.
  - Ensures no duplicate accounts by checking email uniqueness using `user_exist()` function.  
  - Stores user data in the `User` table in the MySQL database.
- **Login**:
  - Authenticates users using their email and password.
  - Once authenticated, users can access their accounts or create a new bank account if not already linked.

#### **b) Account Management Module**  
- **Open Account**:  
  - If a user doesn’t have an account, they can create one by providing an initial deposit, a security PIN, and their details.
  - A unique `account_number` is generated, either incrementally based on existing accounts or starting from a base value.
  - Account details are stored in the `Accounts` table.

#### **c) Transaction Module**
- **Credit Money**:
  - Securely adds funds to a user’s account after verifying their PIN.
  - Uses SQL `UPDATE` to modify the balance in the `Accounts` table.
- **Debit Money**:
  - Deducts a specified amount from the user’s account.
  - Verifies sufficient balance and PIN authentication before proceeding.
  - Ensures atomicity of transactions using `connection.setAutoCommit(false)` to handle failures.
- **Transfer Money**:
  - Enables fund transfer between two accounts.
  - Ensures both debit (from sender) and credit (to receiver) operations are successful before committing the transaction.
  - Follows ACID properties for transactional integrity.
- **Check Balance**:
  - Retrieves the current balance of the account after verifying the security PIN.

#### **d) Security Measures**
- PIN-based authentication ensures that only authorized users can perform transactions on an account.
- Uses prepared statements to prevent SQL injection attacks.
- Incorporates transactional control to maintain data consistency in case of errors.


### **4. Database Schema**
- **User Table**:
  - Columns: `full_name`, `email`, `password`
- **Accounts Table**:
  - Columns: `account_number`, `full_name`, `email`, `balance`, `security_pin`


### **5. Implementation Highlights**
- **Error Handling**: The application gracefully handles SQL exceptions and invalid user inputs.  
- **Atomic Transactions**:
  - Implemented `commit` and `rollback` mechanisms to ensure consistency during operations like debit and transfer.  
- **Dynamic Account Number Generation**:
  - Auto-increments the last account number or starts from a predefined base value (e.g., 10000100).  
- **Reusable Code**:
  - Modules like `AccountManager` and `Accounts` follow Single Responsibility Principle, encapsulating specific functionalities.

### **6. Challenges and Solutions**
- **Challenge**: Maintaining transactional integrity for operations involving multiple queries.  
  **Solution**: Used JDBC transactions (`setAutoCommit(false)`, `commit()`, `rollback()`) to ensure operations are atomic.  
- **Challenge**: Handling simultaneous access to the database.  
  **Solution**: Adopted SQL-level locking mechanisms by using `SELECT ... FOR UPDATE` for sensitive operations if needed.  

### **7. Future Enhancements**
- Implement a graphical user interface (GUI) for a more user-friendly experience.
- Add support for additional banking services like account statements or fixed deposits.
- Incorporate encryption mechanisms for storing sensitive data like passwords and PINs.
