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

		List<PicturesVo> pictureList = getMajorCharacters();
		for (PicturesVo pv : pictureList) {
			bean.addPicturePath(pv.getPath());
		}

		bean.setUserName(user.getUsername());

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
