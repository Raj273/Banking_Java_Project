<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color:#66BB55;">
<form action="CardAuth">
		<label>Name</label> <input type="text" name="Name" /><br> 
		<label>Card NUmber</label><input type="text" name="card" /><br>
		 <label>CVV</label><input type="text" name="CVV" /><br>
		<label>Amount</label> <input type="text" name="amount" /><br>
		
		 <input type="submit" value="Authorize" />
		 
 </form>
</body>
</html>