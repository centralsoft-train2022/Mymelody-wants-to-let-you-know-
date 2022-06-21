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
import javax.servlet.http.HttpSession;

import bean.TaskListBean;
import dao.DBUtil;
import dao.PicturesDao;
import dao.TasksDao;
import vo.PicturesVo;
import vo.TasksVo;
import vo.UsersVo;

@WebServlet("/TaskListServlet")
public class TaskListServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 社員リストwoDBから取得 課題
		//		EmployeesVo  emp = getEmployeesVo("aaaaa");//テストデータ（１つの場合）

		//セッションからログインユーザーを取得
		HttpSession session = request.getSession();
		UsersVo user = (UsersVo) session.getAttribute("UsersVo");

		int uid = user.getUserid();
		List<TasksVo> taskList = getAllTasks(uid);

		TaskListBean bean = new TaskListBean();

		bean.setTaskList(taskList);
		
		//taskListからキャラクターの画像ファイルを取得しBeanへ
		for(TasksVo task:taskList) {
		PicturesVo pic = getPicture(task.getPictures_pictureid());
		bean.addPicturePath(pic.getPath());
		}

		bean.setUserName(user.getUsername());

		request.setAttribute("bean", bean);

		// JSPに遷移する
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/TaskList.jsp");
		disp.forward(request, response);
	}
	
	private PicturesVo getPicture(int pictureid) {

		PicturesVo pic;

		DBUtil db = new DBUtil();

		try (Connection c = db.getConnection();) {

			PicturesDao dao = new PicturesDao(c);

			pic = dao.getPicture(pictureid);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return pic;
	}

	private static List<TasksVo> getAllTasks(int uid) {

		List<TasksVo> tskList = new ArrayList<TasksVo>();

		DBUtil db = new DBUtil();

		try (Connection c = db.getConnection();) {

			TasksDao tdao = new TasksDao(c);

			tskList = tdao.getAllTasks(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return tskList;
	}

}
