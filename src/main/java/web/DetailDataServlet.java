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

		//Beanにタスクを渡す
		bean.setTask(getTask(Integer.parseInt(input.getTaskid())));

		//Beanにタスクを渡す
		bean.addPicturePath(
				getPicture(getTask(Integer.parseInt(input.getTaskid())).getPictures_pictureid()).getPath());

		if (input.getTaskname().isBlank() || input.getTasktime().isBlank()) {

			bean.setTaskNameExists(false);
			bean.setTaskKigenExists(false);

			goDetailJsp(response, request, bean);

		} else if (input.getNeedmail().equals("true") && input.getMaildate().isBlank()) {

			bean.setMailtimeExists(false);

			goDetailJsp(response, request, bean);

		} else if (input.getRegular().equals("true") &&
				input.getMonth().equals("0") && input.getDay().equals("0") && input.getHour().equals("0")
				&& input.getMinutes().equals("0")) {

			bean.setRegulartimeExists(false);

			goDetailJsp(response, request, bean);

		} else {

			TasksVo task = convertNewTask(input, user.getUserid());

			sendDB(task);

			RequestDispatcher disp = request.getRequestDispatcher("TaskListServlet");
			disp.forward(request, response);

		}
	}

	private void goDetailJsp(HttpServletResponse response, HttpServletRequest request, DetailBean bean)
			throws ServletException, IOException {

		request.setAttribute("bean", bean);

		RequestDispatcher disp = request.getRequestDispatcher("/jsp/Detail.jsp");
		disp.forward(request, response);

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
