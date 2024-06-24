

### Endpoints

1. **Create Expense**
   - **Endpoint**: `POST /expense/create`
   - **Description**: Creates a new expense.
   - **Request Body**: Expense object with expense details.
   - **Responses**: 
     - `200 OK`: Expense created successfully.

2. **Get User Expenses**
   - **Endpoint**: `GET /expense/{userId}`
   - **Description**: Retrieves expenses for a specific user.
   - **Parameters**: 
     - `userId` (path, required): The unique identifier of the user.
   - **Responses**: 
     - `200 OK`: List of expenses retrieved.

### Components

1. **Expense**
   - **Properties**: 
     - `id` (integer): Expense ID.
     - `userId` (integer): User ID.
     - `categoryId` (integer): Category ID.
     - `amount` (number): Expense amount.
     - `description` (string): Expense description.
     - `createdAt` (string): Creation timestamp.
   - **Required**: `amount`, `categoryId`, `description`, `userId`
  
     
![expenseMS](https://github.com/MentalCoder91/myfinex-expense-service/assets/97496417/81400150-2705-4a33-91e9-7e2d4c12bf1b)

