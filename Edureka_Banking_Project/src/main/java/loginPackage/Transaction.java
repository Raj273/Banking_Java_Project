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
 * Servlet implementation class Transaction
 */
@WebServlet("/Transaction")
public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoClass dao=new DaoClass();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String trans=request.getParameter("submit");
		String acntno=request.getParameter("accountNum");
		int amnt=Integer.parseInt(request.getParameter("Amount"));
		
		try {
			if(dao.checkIfAccExist(acntno)) {
				Double Balance=dao.getAmount(acntno);
				Double updateAmnt=Balance-amnt;
				if(trans.equals("Debit") && updateAmnt>=0){
					dao.updateAmount(acntno, updateAmnt, "Debit", amnt);
					PrintWriter out = response.getWriter();
					out.print("Amount debited successfully");
				}
				else if(trans.equals("Credit")){
					dao.updateAmount(acntno, (Balance+amnt), "Credit", amnt);
					PrintWriter out = response.getWriter();
					out.print("Amount credited successfully");
				}
				else {
					PrintWriter out = response.getWriter();
					out.print("Low Balance");
				}
			}
			else {
				PrintWriter out = response.getWriter();
				out.print("Account number does not exist");
			}
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
	}

	

}
