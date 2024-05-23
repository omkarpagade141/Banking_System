package bank;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@WebServlet("/bankclerk")
public class ClerkDashboard extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        if ("open".equals(action)) {
            accountOpen(request, response, out);
        } else if ("update".equals(action)) {
            accountUpdate(request, response, out);
        }
        else if ("statement".equals(action)) {
        generateStatement(request, response, out);
        }
        else {
            out.println("<h3>Invalid action!</h3>");
        }
       
       
    }
    private void accountOpen(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException{
    	 String name = request.getParameter("name");
         String address = request.getParameter("address");
         String username = request.getParameter("username");
         String password = request.getParameter("password");
         
         
         // Here, you would typically save these details to a database.
         // For now, we'll simulate this with a simple message.
         
         response.setContentType("text/html");
          
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root", "Omkar@123");
             String sql = "INSERT INTO customer (name, address, username, password) VALUES (?, ?, ?, ?)";
             PreparedStatement pst = conn.prepareStatement(sql);
             pst.setString(1, name);
             pst.setString(2, address);
             
             pst.setString(3, username);
             pst.setString(4, password);
             


             int row = pst.executeUpdate();

             if (row > 0) {
                 out.println("<html><body><h3>Registration successful! please </h3><a href=\"adminLogin.html\">login</a></body></html>");
                 
             } else {
                 out.println("<html><body><h3>Registration failed. Please try again.</h3></body></html>");
             }

             conn.close();
         }
        
         catch (Exception e) {
             out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
         }
    }
    private void accountUpdate(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException{
    	 
    	 String name = request.getParameter("name");
         String address = request.getParameter("newAddress");
         String accountNumber = request.getParameter("updateAccountNumber");
         String password = request.getParameter("newPassword");
         
         
         
         
          
          
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root", "Omkar@123");
              
              
             String sql = "UPDATE customer SET name = ?, address = ?, password = ? WHERE account_number = ?";
             PreparedStatement pst = conn.prepareStatement(sql);
             pst.setString(1, name);
             pst.setString(2, address);
             pst.setString(3, password); 
             pst.setString(4, accountNumber);
             


             int row = pst.executeUpdate();

             if (row > 0) {
                 out.println("<html><body><h3>Data update successful!</body></html>");
                 
             } else {
                 out.println("<html><body><h3>Data update failed.... Please try again.</h3></body></html>");
             }

             conn.close();
         }
        
         catch (Exception e) {
             out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
         }
    }
    private void generateStatement(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException{
 	
    }
}
