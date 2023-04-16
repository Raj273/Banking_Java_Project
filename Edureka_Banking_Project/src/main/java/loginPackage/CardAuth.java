package loginPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoClass;

/**
 * Servlet implementation class CardAuth
 */
@WebServlet("/CardAuth")
public class CardAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoClass dao=new DaoClass();
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("Name");
		String card=request.getParameter("card");
		String cvv=request.getParameter("CVV");
		double amt=(double) Integer.parseInt(request.getParameter("amount"));
		
		
		try {
			if(dao.authorizeCard(name, card, cvv) && amt<=100000) {
				
				PrintWriter out = response.getWriter();
				out.print("Authorization Successful");
			
			}
			else {
				PrintWriter out = response.getWriter();
				out.print("Authorization failed");
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	

}
