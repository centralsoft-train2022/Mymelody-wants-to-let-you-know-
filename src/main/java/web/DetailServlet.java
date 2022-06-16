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

import bean.DetailBean;
import dao.DBUtil;
import dao.PicturesDao;
import dao.TasksDao;
import vo.PicturesVo;
import vo.TasksVo;

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
		

		List<TasksVo>  taskList = getTasksVo(num);
		DetailBean bean = new DetailBean();		
		bean.setTaskList(taskList);
<<<<<<< HEAD
		bean.setTaskid(num);

=======
		
		List<PicturesVo> pictureList = getMajorCharacters();
		for(PicturesVo pv:pictureList) {
			bean.addPicturePath(pv.getPath());}
		
>>>>>>> d2500339caeb33f09b0e6aeabd0cd2e13c9646b9
		request.setAttribute("bean", bean);

		//JSPに遷移する
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/Detail.jsp");
		disp.forward(request, response);
	}
	
	private List<PicturesVo> getMajorCharacters() {
		List<PicturesVo> pictureList = new ArrayList<PicturesVo>();
		
		DBUtil db = new DBUtil();

		try (Connection c = db.getConnection();) {

			PicturesDao dao = new PicturesDao(c);

			pictureList = dao.getMajorCharacters();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return pictureList;
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
