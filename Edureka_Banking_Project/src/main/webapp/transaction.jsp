<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color:#66BB55;">
<form action="Transaction">
		<label>Account Number</label> <input type="text" name="accountNum" /><br> 
		<label>Amount</label><input type="text" name="Amount" /><br>
		<input type="submit" name="submit" value="Debit" />
		<input type="submit" name="submit" value="Credit" />
		
		</form>
</body>
</html>