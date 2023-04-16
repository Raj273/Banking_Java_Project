<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Banking Project</title>
</head>
<body style="background-color:#66BB55;">
	<form action="Create">
		<label>Name</label> <input type="text" name="Name" /><br> 
		<label>DOB</label><input type="Date" name="DOB" /><br>
		 <label>Address</label><textarea id="Address" name="Address" rows="4" cols="50"> </textarea>
		<br> <label>Email</label> <input type="email" name="email" /><br>
		<label for="cars">Type of Account</label>
		 <select id="Account" name="Account">
			<option value="SB">SB Account</option>
			<option value="Current">Current Account</option>

		</select><br>
		 <input type="submit" value="Submit" />
	</form>
</body>
</html>