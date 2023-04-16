package Dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DaoClass {
	public boolean validate(String UserName,String Password) throws ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/project","root","Abhith@123");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from user where username = ? and password = ? ")) {
			preparedStatement.setString(1, UserName);
			preparedStatement.setString(2, Password);

			ResultSet rs = preparedStatement.executeQuery();
		if(rs.next())
			return true;

		} catch (SQLException e) {
			// process sql exception
			System.out.println(e);
		}
		return false;
	}
	public static String generateRandomNumber(int n) {
		String AlphaNumericString = "0123456789";

		StringBuilder sb = new StringBuilder(n);
		
		for (int i = 0; i < n; i++) {
		int index = (int)(AlphaNumericString.length()* Math.random());
		sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	public boolean Insert(String name, String dob, String address,String email,String account) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String accountNum=generateRandomNumber(16);
		String CreditCard=generateRandomNumber(16);
		String Cvv=generateRandomNumber(3);

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/project","root","Abhith@123");

				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into account(accountNum,name,DOB,Address,email,type,Credit,cvv,balance) values (?,?,?,?,?,?,?,?,?)") ){
			preparedStatement.setString(1, accountNum);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3,dob);
			preparedStatement.setString(4, address);
			preparedStatement.setString(5, email);
			preparedStatement.setString(6, account);
			preparedStatement.setString(7, CreditCard);
			preparedStatement.setString(8,Cvv);
			preparedStatement.setDouble(9, 1000);
			

			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			// process sql exception
			System.out.println(e);
		}
		return false;
		
	}
	
	public boolean checkIfAccExist(String acntno) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/project","root","Abhith@123");

				PreparedStatement preparedStatement = connection
						.prepareStatement("select accountNum from account where accountNum = " + acntno)) {

			ResultSet rs = preparedStatement.executeQuery();
			rs.last();
			if(rs.getRow() > 0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage()+" "+e.getSQLState()+" "+e.getErrorCode());
		}
		
		return false;
	}
	
	public Double getAmount(String acno) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/project","root","Abhith@123");

				PreparedStatement preparedStatement = connection
						.prepareStatement("select balance from account where accountNum = " + acno)) {
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			return  rs.getDouble("balance");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage()+" "+e.getSQLState()+" "+e.getErrorCode());
		}
		return null ;
	}
	
	public boolean updateAmount(String acno, Double amt, String type, int amountToUpdate ) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/project","root","Abhith@123");

				PreparedStatement preparedStatement = connection
						.prepareStatement("update account set balance = " + amt + " where accountNum = " + acno)) {
			 preparedStatement.executeUpdate();
				
				long now = System.currentTimeMillis();
		        Date sqlDate = new Date(now);
				switch(type) {
				case "Credit":
					PreparedStatement preparedStatement2 = connection.prepareStatement("insert into transaction_history(accountNum,Date,Credit,Balance) values(?,?,?,?)");
					preparedStatement2.setString(1,acno);
					preparedStatement2.setDate(2,sqlDate);
					preparedStatement2.setInt(3,amountToUpdate);
					preparedStatement2.setDouble(4,amt);
					preparedStatement2.executeUpdate();
					break;
				case "Debit":
					PreparedStatement preparedStatement3 = connection.prepareStatement("insert into transaction_history(accountNum,Date,Debit,Balance) values(?,?,?,?)");
					preparedStatement3.setString(1,acno);
					preparedStatement3.setDate(2,sqlDate);
					preparedStatement3.setInt(3,amountToUpdate);
					
					preparedStatement3.setDouble(4,amt);
					preparedStatement3.executeUpdate();
					break;
				}
				
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage()+" "+e.getSQLState()+" "+e.getErrorCode());
		}
		return false;
	}

	public boolean authorizeCard(String name, String card, String cvv) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","Abhith@123");
				Statement st = connection.createStatement();

				ResultSet rs = st.executeQuery("select accountNum from account where Name = '" + name + "' and Credit = '" + card + "' and CVV = '" + cvv + "'");) {
		
			int a=rs.getRow();
			System.out.print(a);
			//rs.last();
			if(rs.getRow() >0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
}
