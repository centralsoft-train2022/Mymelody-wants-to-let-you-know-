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
import javax.servlet.http.HttpSession;

import bean.DetailBean;
import dao.DBUtil;
import dao.PicturesDao;
import dao.TasksDao;
import input.EditInput;
import vo.PicturesVo;
import vo.TasksVo;
import vo.UsersVo;

@WebServlet("/DetailDataServlet")
public class DetailDataServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		DetailBean bean = new DetailBean();

		//セッションからユーザーを取得
		HttpSession session = request.getSession();
		UsersVo user = (UsersVo) session.getAttribute("UsersVo");
		bean.setUserName(user.getUsername());

		//入力を受け取る
		EditInput input = receiveInput(request);

		if (!correct(input)) {

			bean.setTask(getTask(Integer.parseInt(input.getTaskid())));

			bean.addPicturePath(
					getPicture(getTask(Integer.parseInt(input.getTaskid())).getPictures_pictureid()).getPath());

			request.setAttribute("bean", bean);

			RequestDispatcher disp = request.getRequestDispatcher("/jsp/Detail.jsp");
			disp.forward(request, response);
		} else {

			TasksVo task = convertNewTask(input, user.getUserid());

			sendDB(task);

			RequestDispatcher disp = request.getRequestDispatcher("TaskListServlet");
			disp.forward(request, response);

			PicturesVo pic = getPicture(task.getPictures_pictureid());
			bean.addPicturePath(pic.getPath());

		}
	}

	private boolean correct(EditInput input) {
		if (input.getTaskname().isBlank()) {
			return false;
		}
		if (input.getTasktime().isBlank()) {
			return false;
		}
		return true;
	}

	private void sendDB(TasksVo inputData) {

		DBUtil db = new DBUtil();
		try (Connection c = db.getConnection();) {

			TasksDao tsksdao = new TasksDao(c);

			tsksdao.update(inputData);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private TasksVo convertNewTask(EditInput ei, int userId) {

		int taskid = Integer.parseInt(ei.getTaskid());

		String interval = "0000-" + ei.getMonth() + "-" + ei.getDay() + " " + ei.getHour() + ":" + ei.getMinutes();

		boolean boolNeedmail = Boolean.valueOf(ei.getNeedmail());
		boolean boolRegular = Boolean.valueOf(ei.getRegular());

		if (boolNeedmail == false) {
			ei.setMaildate(null);
		}

		if (boolRegular == false) {
			interval = null;
		}

		return new TasksVo(
				taskid,
				ei.getTaskname(),
				ei.getTaskdetail(),
				ei.getTasktime(),
				ei.getMaildate(),
				interval,
				boolNeedmail,
				boolRegular);
	}

	private EditInput receiveInput(HttpServletRequest request) {

		return new EditInput(
				request.getParameter("taskid"),
				request.getParameter("taskname"),
				request.getParameter("taskdetail"),
				request.getParameter("tasktime"),
				request.getParameter("maildate"),
				request.getParameter("month"),
				request.getParameter("day"),
				request.getParameter("hour"),
				request.getParameter("minutes"),
				request.getParameter("needmail"),
				request.getParameter("regular"));

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

	private static TasksVo getTask(int num) {

		DBUtil db = new DBUtil();
		TasksVo task;

		try (Connection c = db.getConnection();) {
			TasksDao tdao = new TasksDao(c);
			task = tdao.getTask(num);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return task;
	}

}
