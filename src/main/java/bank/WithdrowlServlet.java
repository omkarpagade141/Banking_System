package bank;
import bank.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/WithdrowlServlet")
public class WithdrowlServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String amount = req.getParameter("amount");
	System.out.println();
     PrintWriter out = resp.getWriter();
     if (Account.getAccountBalance()>=Double.parseDouble(amount)) {
    	 resp.setContentType("text/html");
         String jdbcURL = "jdbc:mysql://localhost:3306/banking_system";
         String dbUser = "root";
         String dbPassword = "Omkar@123";
         try {
             // Load the JDBC driver
             Class.forName("com.mysql.cj.jdbc.Driver");

             // Connect to the database
             Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
             
            	 String sql = "UPDATE customer SET balance = balance - ? WHERE account_number = ?";
                 PreparedStatement statement = connection.prepareStatement(sql);
                 statement.setString(1, amount);
                 statement.setString(2, Account.getAccountNumber());
                 
                
     
                 // Execute the query
               int row=  statement.executeUpdate();
                
                 statement.close();
                  
                 out.println("<html>"
                 		+ "<body>"
                 		+ "<h3>Successfully Withdrowl..</h3>"
                 		+ "</body>"
                 		+ "</html>");   
             
                
             
             connection.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
		
	}
     else {
    	 out.println("<html>"
          		+ "<body>"
          		+ "<h3>low account balance</h3>"
          		+ "</body>"
          		+ "</html>");
    	 
     }
     
}

 
	
	 
}
