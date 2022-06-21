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

import bean.CheerBean;
import dao.DBUtil;
import dao.PicturesDao;
import vo.PicturesVo;
import vo.UsersVo;

@WebServlet("/CheerServlet")
public class CheerServlet extends HttpServlet {

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CheerBean bean = getCheerBean();

		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		//usernameをBeanに渡す
		HttpSession session = request.getSession();
		UsersVo user = (UsersVo) session.getAttribute("UsersVo");
		bean.setUserName(user.getUsername());

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
		
		bean.setMessage(randomMessage());
		
		return bean;
	}

	private String randomMessage() {
		
		Random r = new Random();
		int random = r.nextInt(3);
		
		if(random == 0) {return "がんばれ！";}
		if(random == 1) {return "ファイト！";}
		if(random == 2) {return "やったれ！";}
		
		return null;
	}

	private List<PicturesVo> getPictures() {

		DBUtil db = new DBUtil();

		List<PicturesVo> pictureList = new ArrayList<PicturesVo>();

		try (Connection c = db.getConnection();) {

			PicturesDao dao = new PicturesDao(c);

			pictureList = dao.getMajorCharacters();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return pictureList;
	}

}
