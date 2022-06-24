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

import bean.OkorareBean;
import dao.DBUtil;
import dao.PicturesDao;
import vo.PicturesVo;

@WebServlet("/OkorareServlet")
public class OkorareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		OkorareBean bean = new OkorareBean();
		List<PicturesVo> pictureList = getAngryPictures();
		Random r = new Random();
		bean.addPicturePath(pictureList.get(r.nextInt(0, pictureList.size())).getPath());
		request.setAttribute("bean", bean);
		System.out.println("a");
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/Okorare.jsp");
		disp.forward(request, response);
		
		
	}
	private List<PicturesVo> getAngryPictures() {

		DBUtil db = new DBUtil();

		List<PicturesVo> pictureList = new ArrayList<PicturesVo>();

		try (Connection c = db.getConnection();) {

			PicturesDao dao = new PicturesDao(c);

			pictureList = dao.getAngry();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return pictureList;
	}

}
