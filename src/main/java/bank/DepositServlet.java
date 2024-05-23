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
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String amount = req.getParameter("amount");
	 
     PrintWriter out = resp.getWriter();
      
    	 resp.setContentType("text/html");
         String jdbcURL = "jdbc:mysql://localhost:3306/banking_system";
         String dbUser = "root";
         String dbPassword = "Omkar@123";
         try {
          
             Class.forName("com.mysql.cj.jdbc.Driver");

             
             Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
             
            	 String sql = "UPDATE customer SET balance = balance + ? WHERE account_number = ?";
                 PreparedStatement statement = connection.prepareStatement(sql);
                 statement.setString(1, amount);
                 statement.setString(2, Account.getAccountNumber());
                 
                
     
                 
               int row=  statement.executeUpdate();
                
                 statement.close();
                  
                 out.println("<html>"
                 		+ "<body>"
                 		+ "<h3>Successfully deposit</h3>"
                 		+ "</body>"
                 		+ "</html>");   
             
                
             
             connection.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
}
}
     
 
