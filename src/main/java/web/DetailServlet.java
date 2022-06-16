package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Vo.TasksVo;
import bean.DetailBean;
import dao.DBUtil;
import dao.TasksDao;

@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet
{
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException
	{
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//押されたボタンのidを取得、Stringからintへの変換
		String s = request.getParameter("edit");
		int num = Integer.parseInt(s);
		System.out.println(num);
		

		List<TasksVo>  taskList = getTasksVo(num);
		DetailBean bean = new DetailBean();		
		bean.setTaskList(taskList);

		request.setAttribute("bean", bean);

		//JSPに遷移する
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/Detail.jsp");
		disp.forward(request, response);
	}
	
	
	private static List<TasksVo> getTasksVo(int num)
	{

		//ここにDBアクセス処理を作ってみましょう。

		List<TasksVo> tskList = new ArrayList<TasksVo>();
		

		DBUtil db = new DBUtil( );
		
		try( Connection c = db.getConnection( ); )
		{

			TasksDao tdao = new TasksDao( c );

			tskList = tdao.getExtractTasks(num);
		}
		catch( SQLException e )
		{
			throw new RuntimeException( e );
		}

		return tskList;
	}

}
