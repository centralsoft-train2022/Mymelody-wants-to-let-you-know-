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

import bean.CheerBean;
import dao.DBUtil;
import dao.PicturesDao;
import vo.PicturesVo;

@WebServlet("/CheerServlet")
public class CheerServlet extends HttpServlet {

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CheerBean bean = getCheerBean();

		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		//JSPに遷移する
		request.setAttribute("bean", bean);
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/Cheer.jsp");
		disp.forward(request, response);

	}

	private CheerBean getCheerBean() {
		CheerBean bean = new CheerBean();

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
