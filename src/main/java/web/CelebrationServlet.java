package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBUtil;
import dao.TasksDao;

@WebServlet("/CelebrationServlet")
public class CelebrationServlet extends HttpServlet {

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException
	{
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//押されたボタンのidを取得、Stringからintへの変換
		String s = request.getParameter("id");
		int num = Integer.parseInt(s);
		Taskachievement(num);

		
		//JSPに遷移する
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/Celebration.jsp");
		disp.forward(request, response);
	}
	
	private static void Taskachievement(int id)
	{
		
		DBUtil db = new DBUtil( );
		
		try( Connection c = db.getConnection( ); )
		{

			TasksDao tdao = new TasksDao( c );

			tdao.TaskAchievement(id);
		}
		catch( SQLException e )
		{
			throw new RuntimeException( e );
		}
	}

}
