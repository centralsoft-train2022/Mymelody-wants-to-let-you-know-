package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet
{
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException
	{

	

			//JSPに遷移する
			RequestDispatcher disp = request.getRequestDispatcher("/Detail.jsp");
			disp.forward(request, response);
	}

}
