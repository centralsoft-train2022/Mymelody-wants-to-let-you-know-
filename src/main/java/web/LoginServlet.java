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
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import dao.DBUtil;
import dao.PicturesDao;
import dao.UsersDao;
import vo.PicturesVo;
import vo.UsersVo;

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

		String mailaddress = request.getParameter("Mailaddress");
		String password = request.getParameter("Password");

		DBUtil db = new DBUtil();

		try (Connection c = db.getConnection()) {

			UsersDao dao = new UsersDao(c);
			UsersVo user = dao.getUser(mailaddress);

			HttpSession session = request.getSession();
			session.setAttribute("UsersVo", user);

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

		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private LoginBean getLoginBean() {

		LoginBean bean = new LoginBean();

		List<PicturesVo> pictureList = getPictures();

		Random r = new Random();

		bean.addPicturePath(pictureList.get(r.nextInt(0, pictureList.size() - 1)).getPath());
		return bean;

	}

	private List<PicturesVo> getPictures() {

		DBUtil db = new DBUtil();

		List<PicturesVo> pictureList = new ArrayList<PicturesVo>();

		try (Connection c = db.getConnection();) {

			PicturesDao dao = new PicturesDao(c);

			pictureList = dao.getMymelodies();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return pictureList;
	}
}
