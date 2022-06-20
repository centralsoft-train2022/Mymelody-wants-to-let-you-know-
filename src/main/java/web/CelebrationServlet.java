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

import bean.CelebrationBean;
import dao.DBUtil;
import dao.PicturesDao;
import dao.TasksDao;
import vo.PicturesVo;

@WebServlet("/CelebrationServlet")
public class CelebrationServlet extends HttpServlet {

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		CelebrationBean bean = getCelebrationBean();

		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		
		//押されたボタンのidを取得、Stringからintへの変換
		String s = request.getParameter("id");
		int num = Integer.parseInt(s);
		Taskachievement(num);

		//JSPに遷移する
		request.setAttribute("bean", bean);
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/Celebration.jsp");
		disp.forward(request, response);
		
	}


	private void display(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CelebrationBean bean = getCelebrationBean();

		request.setCharacterEncoding("UTF-8");
		//押されたボタンのidを取得、Stringからintへの変換
		String s = request.getParameter("id");
		int num = Integer.parseInt(s);
		Taskachievement(num);

		//JSPに遷移する
		request.setAttribute("bean", bean);
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/Celebration.jsp");
		disp.forward(request, response);
	}
  
	private static void Taskachievement(int id) {

		DBUtil db = new DBUtil();

		try (Connection c = db.getConnection();) {

			TasksDao tdao = new TasksDao(c);

			tdao.TaskAchievement(id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private CelebrationBean getCelebrationBean() {
		CelebrationBean bean = new CelebrationBean();

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
