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

import bean.TaskListBean;
import dao.DBUtil;
import dao.PicturesDao;
import dao.TasksDao;
import vo.PicturesVo;
import vo.TasksVo;

@WebServlet("/TaskListServlet")
public class TaskListServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 社員リストwoDBから取得 課題
//		EmployeesVo  emp = getEmployeesVo("aaaaa");//テストデータ（１つの場合）
		List<TasksVo> taskList = getAllTasks();

		

		TaskListBean bean = new TaskListBean();

		bean.setTaskList(taskList);
		
		List<PicturesVo> pictureList = getMajorCharacters();
		for(PicturesVo pv:pictureList) {
		bean.addPicturePath(pv.getPath());}

//		//セッションからログインユーザーを取得
//		HttpSession session = request.getSession();
//	    TasksVo tsk  = (TasksVo)session.getAttribute("TasksVo");
		// String peStr = (String)session.getAttribute("password");

		request.setAttribute("bean", bean);

		// JSPに遷移する
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/TaskList.jsp");
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
	/*
	 * //DBから従業員を取得する private EmployeesVo getEmployeesVo( String TaskName) {
	 * 
	 * //DBから従業員を取得 仮実装 // DBUtil db = new DBUtil( ); // try( Connection c =
	 * db.getConnection( ); ) // { // EmployeesDao dao = new EmployeesDao( c ); //
	 * empList = dao.getAllEmployees(); // } // catch( SQLException e ) // { //
	 * throw new RuntimeException( e ); // }
	 * 
	 * 
	 * 
	 * 
	 * //テストデータ EmployeesVo emp = new EmployeesVo(); emp.setEmployeename(TaskName);
	 * 
	 * return emp; }
	 */

	private static List<TasksVo> getAllTasks() {

//		List<TasksVo> tskList = null;

		// ここにDBアクセス処理を作ってみましょう。 課題

		// 仮実装 あとで消す
		List<TasksVo> tskList = new ArrayList<TasksVo>();

		// TasksVo t1 = new TasksVo(0, null, null, false, null, false, null, false,
		// null, false, 0, 0);
//		TasksVo t2 = new TasksVo(0, null, null, false, null, false, null, false, null, false, 0, 0);

		DBUtil db = new DBUtil();

		try (Connection c = db.getConnection();) {

			TasksDao tdao = new TasksDao(c);

			tskList = tdao.getAllTasks();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		/*
		 * t1.setTaskid(1); t2.setTaskid(2);
		 * 
		 * t1.setTaskname("課題1"); t2.setTaskname("課題2");
		 * 
		 * t1.setCompleted(false); t2.setCompleted(true);
		 * 
		 * tskList.add(t1); tskList.add(t2);
		 */
		// 仮実装終わり

		return tskList;
	}

}
