package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Vo.PicturesVo;
import bean.LoginBean;
import dao.DBUtil;
import dao.PicturesDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		display(request, response);
	}

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		display(request, response);

	}

	private void display(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fromjsp = request.getParameter("fromjsp");

		if (fromjsp == null) {
			LoginBean bean = getLoginBean();

			request.setAttribute("bean", bean);
			RequestDispatcher disp = request.getRequestDispatcher("jsp/Login.jsp");
			disp.forward(request, response);
		} else {
			RequestDispatcher disp = request.getRequestDispatcher("TaskListServlet");
			disp.forward(request, response);
		}
	}

	private LoginBean getLoginBean() {

		LoginBean bean = new LoginBean();

		DBUtil db = new DBUtil();

		List<PicturesVo> pictureList = new ArrayList<PicturesVo>();

		try (Connection c = db.getConnection();) {

			PicturesDao dao = new PicturesDao(c);

			pictureList = dao.getAllPictures();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		Random r = new Random();

		bean.setCharcterMainImageFileName(pictureList.get(r.nextInt(0, 69)).getFileName());
		return bean;

	}
}
