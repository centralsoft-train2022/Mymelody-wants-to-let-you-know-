package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException
	{
			DBUtil db = new DBUtil( );
			String idStr = request.getParameter("ID");
			
			
			
			
			//TaskListServletに遷移する
			RequestDispatcher disp = request.getRequestDispatcher("/TaskListServlet");
			disp.forward(request, response);
	}

}
