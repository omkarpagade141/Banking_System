<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bank.Account" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .mainDiv{
        	display: flex;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .account-details {
            margin-top: 20px;
        }
        .account-details p {
            font-size: 16px;
            color: #333;
        }
        input[type="text"] {
            width: 98%;
            padding: 10px;
            margin: 10px 0;
            font-size: 16px;
            box-sizing: border-box;
        }
        .button-container {
            display: flex;
            
        }
        input[type="submit"] {
 			width:98%;
 			margin:1%;
            padding: 10px;
            font-size: 16px;
            color: white;
            border: none;
            cursor: pointer;
        }
        .withdraw {
            background-color: #d9534f;
        }
        .withdraw:hover {
            background-color: #c9302c;
        }
        .deposit {
            background-color: #5cb85c;
        }
        .deposit:hover {
            background-color: #4cae4c;
        }
        .formDiv{
        	width: 50%;
        }
    </style>
</head>
<body>
	<div class="mainDiv">
    <div class="container">
        <h1>Account Details</h1>
        <div class="account-details">
            <%
                Account account = (Account) request.getAttribute("account");
                if (account != null) {
                    out.println("<p>Account Number: " + Account.getAccountNumber() + "</p>");
                    out.println("<p>Account Name: " + Account.getAccountName() + "</p>");
                    out.println("<p>Account Balance: " + Account.getAccountBalance() + "  Cr</p>");
                } else {
                    out.println("<p>No account found with the provided account number.</p>");
                }
            %>
        </div>
    </div>
    
     <div class="container">
        <h1>Transaction</h1>
      
        
        <div class="button-container">
        <form method="post" action="WithdrowlServlet" class="formDiv"> 
          <input type="text" name="amount" id="amount" placeholder="Enter withdraw Amount" required>
                <input type="submit"  class="withdraw" value="Withdrawal">
                
        </form>
        <form method="post" action="DepositServlet" class="formDiv"> 
                <input type="text" name="amount" id="amount" placeholder="Enter deposit Amount" required>  
                <input type="submit" class="deposit" value="Deposit">
        </form>
        </div>
       
    </div>
    
    </div>
</body>
</html>
