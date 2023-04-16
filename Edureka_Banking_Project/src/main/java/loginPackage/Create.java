package loginPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoClass;

/**
 * Servlet implementation class Create
 */
@WebServlet("/Create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoClass dao=new DaoClass();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("Name");
		String DOB=request.getParameter("DOB");
		String Address=request.getParameter("Address");
		String email=request.getParameter("email");
		String Account=request.getParameter("Account");
		try {
			if(dao.Insert(name,DOB,Address,email,Account)) {
				PrintWriter out = response.getWriter();
				out.print("Account Created Successfully");
			}
			else {
				PrintWriter out = response.getWriter();
				out.print("Account creation failed");
				RequestDispatcher rd=request.getRequestDispatcher("create.jsp");
				rd.include(request, response);
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	

}
