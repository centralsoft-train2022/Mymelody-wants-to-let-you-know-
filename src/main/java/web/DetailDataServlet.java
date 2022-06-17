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

@WebServlet("/DetailDataServlet")
public class DetailDataServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DetailDataServlet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DetailDataServlet(request, response);
	}

	private void DetailDataServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBUtil db = new DBUtil();
		try (Connection c = db.getConnection();) {

			TasksDao tsksdao = new TasksDao(c);

			request.setCharacterEncoding("UTF-8");
			String tskid = request.getParameter("taskid");
			int taskid = Integer.parseInt(tskid);
			String taskname = request.getParameter("taskname");

			String taskdetail = request.getParameter("taskdetail");
			tsksdao.update(taskid, taskname, taskdetail);//後でbeanにまとめてupdate関数に渡す

			RequestDispatcher disp = request.getRequestDispatcher("TaskListServlet");
			disp.forward(request, response);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
