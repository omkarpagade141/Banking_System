package bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetAccountDetailsServlet")
public class GetAccountDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        Account account = null;

        // Database connection parameters
        String jdbcURL = "jdbc:mysql://localhost:3306/banking_system";
        String dbUser = "root";
        String dbPassword = "Omkar@123";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Prepare the SQL query
            String sql = "SELECT * FROM customer WHERE account_number = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, accountNumber);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Process the result set
            if (resultSet.next()) {
                String accountName = resultSet.getString("name");
                double accountBalance = resultSet.getDouble("balance");
                account= new Account(accountNumber, accountName, accountBalance);
            }

            // Close the connections
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the account details as a request attribute
        request.setAttribute("account", account);

        // Forward the request to the JSP page
        request.getRequestDispatcher("accountDetails.jsp").forward(request, response);
    }
}

 


