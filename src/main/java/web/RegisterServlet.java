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

import bean.RegisterBean;
import dao.DBUtil;
import dao.PicturesDao;
import vo.PicturesVo;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet
{
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException
	{
		RegisterBean bean = new RegisterBean();

		List<PicturesVo> pictureList = getMinorCharacters();
		for(PicturesVo pv:pictureList) {
			bean.addPicturePath(pv.getPath());}
		
		request.setAttribute("bean", bean);

			//JSPに遷移する
			RequestDispatcher disp = request.getRequestDispatcher("/jsp/Register.jsp");
			disp.forward(request, response);
	}
	
	private List<PicturesVo> getMinorCharacters() {
		List<PicturesVo> pictureList = new ArrayList<PicturesVo>();
		
		DBUtil db = new DBUtil();

		try (Connection c = db.getConnection();) {

			PicturesDao dao = new PicturesDao(c);

			pictureList = dao.getMinorCharacters();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return pictureList;
	}

}
