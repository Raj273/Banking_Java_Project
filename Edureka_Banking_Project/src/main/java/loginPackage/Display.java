package loginPackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.DaoClass;

/**
 * Servlet implementation class Display
 */
@WebServlet("/Display")
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoClass dao = new DaoClass();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			String From=request.getParameter("from_date");
			String To=request.getParameter("to_date");
			String acno=request.getParameter("accountNum");

				if(dao.checkIfAccExist(acno)) {
					Class.forName("com.mysql.jdbc.Driver");

					Connection connection = DriverManager
							.getConnection("jdbc:mysql://localhost:3306/project","root","Abhith@123");

							PreparedStatement preparedStatement = connection
									.prepareStatement("select Date, Debit, Credit, Balance from transaction_history where Date between '" + From + "' and '" + To + "' and accountNum = " + acno);
						ResultSet rs = preparedStatement.executeQuery();
						 out.println("<!DOCTYPE html>");
						  out.println("<html>");
						  out.println("<head>");
						  out.println("<meta charset=\"utf-8\">");  // escape the quote marks
						  out.println("<title>Bank statements</title>");
						  out.println("<style>");  
						  out.println("table,th,td {");
						  //out.println("color:blue;");
						  //out.println("background-color:yellow;");
						  out.println("border: 1px solid black;");
						  out.println("border-collapse: collapse");
						  out.println("}");  
						  out.println("</style>");  // terminate style
						  out.println("</head>");
						  out.println("<body>");
						out.print("<table>");
					out.print("<tr><th>Date</th><th>Debit</th><th>Credit</th><th>Balance</th></tr>");
					while(rs.next()) {
						
					out.println("<tr><td>"+rs.getDate("Date")+"</td><td>"+rs.getInt("Debit")+"</td><td>"+rs.getInt("Credit")+"</td><td>"+rs.getDouble("Balance")+"</td></tr>");
					}
					out.print("</table>");
					out.println("</body>");
					  out.println("</html>");
				}
			
		} catch (ClassNotFoundException  | SQLException e) {
			
			e.printStackTrace();
		}
	}

}
