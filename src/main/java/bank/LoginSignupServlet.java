package bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 

@WebServlet("/LoginSignupServlet")
public class LoginSignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        if ("loginUser".equals(action)) {
            handleLoginUser(request, response, out);
        } else if ("loginEmployee".equals(action)) {
            handleLoginEmployee(request, response, out);
        } else {
            out.println("<h3>Invalid action!</h3>");
        }
    }

    private void handleLoginUser(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
       
    	// write logic for customer login after customer created by bank clerk 
        
        
    }

    private void handleLoginEmployee(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
    	 String username = request.getParameter("username");
         String password = request.getParameter("password");
         String role = request.getParameter("role");
         
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
         	
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root", "Omkar@123");
             System.out.println(conn);
             String sql = "SELECT * FROM employee WHERE username = ? AND password = ? AND role = ?";
             PreparedStatement pst = conn.prepareStatement(sql);
             pst.setString(1, username);
             pst.setString(2, password);
             pst.setString(3, role);

             ResultSet rs = pst.executeQuery();
             if (rs.next()) {
             	  
             	 if ("bank_accountant".equals(role) ) {
                      RequestDispatcher rd= request.getRequestDispatcher("BankAccountant.html");
                      rd.forward(request, response);
                  } 
             	 else if ("bank_clerk".equals(role) ) {
                      RequestDispatcher rd= request.getRequestDispatcher("BankClerk.html");
                      rd.forward(request, response);
                  } 
             	 else if ("bank_manager".equals(role) ) {
                      RequestDispatcher rd= request.getRequestDispatcher("BankManager.html");
                      rd.forward(request, response);
                  } 
                  else {
                  	 RequestDispatcher rd= request.getRequestDispatcher("index.html");
                  	 rd.forward(request, response);
                  }
 				
 			}
             else {
             	out.println("<html><body><h3>Login failed??? </h3><a href=\"index.html\">please signup</a></body></html>");
 			}

              
         } 
         catch (Exception e) {
             out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
         }

       
    }
}

