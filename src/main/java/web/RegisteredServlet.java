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
import bean.RegisterBean;
import dao.DBUtil;
import dao.PicturesDao;
import dao.TasksDao;
import vo.PicturesVo;
import vo.TasksVo;
import vo.UsersVo;

@WebServlet("/RegisteredServlet")
public class RegisteredServlet extends HttpServlet {
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		RegisterBean rbean = new RegisterBean();
		CheerBean cbean = new CheerBean();
		
		//usernameをBeanに渡す
		HttpSession session = request.getSession();
		UsersVo usersVo = (UsersVo) session.getAttribute("UsersVo");
		rbean.setUserName(usersVo.getUsername());
		cbean.setUserName(usersVo.getUsername());
		
		TasksVo inputData = receiveInput(request, usersVo.getUserid());
		
		if (inputData.getTaskname().isEmpty() && inputData.getKigen().equals(" ")) {

			rbean.setTaskNameExists(false);
			rbean.setTaskKigenExists(false);

			request.setAttribute("bean", rbean);

			RequestDispatcher disp = request.getRequestDispatcher("/jsp/Register.jsp");
			disp.forward(request, response);

		} else if (inputData.getTaskname().isEmpty()) {
			rbean.setTaskNameExists(false);
			rbean.setTaskKigenExists(true);

			request.setAttribute("bean", rbean);

			RequestDispatcher disp = request.getRequestDispatcher("/jsp/Register.jsp");
			disp.forward(request, response);

		} else if (inputData.getKigen().equals(" ")) {
			rbean.setTaskNameExists(true);
			rbean.setTaskKigenExists(false);

			request.setAttribute("bean", rbean);

			RequestDispatcher disp = request.getRequestDispatcher("/jsp/Register.jsp");
			disp.forward(request, response);

		} else {
			sendDB(inputData);
			
			PicturesVo pv = getPicture(inputData.getPictures_pictureid());

			cbean.addPicturePath(pv.getPath());
			cbean.setMessage(randomMessage());
			
			request.setAttribute("bean", cbean);

			RequestDispatcher disp = request.getRequestDispatcher("/jsp/Cheer.jsp");
			disp.forward(request, response);
		}
	}

	private void sendDB(TasksVo inputData) {
		DBUtil db = new DBUtil();

		try (Connection c = db.getConnection()) {

			TasksDao dao = new TasksDao(c);

			dao.insert(inputData);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private TasksVo receiveInput(HttpServletRequest request, int userId) {
		//入力を受け取る
		String name = request.getParameter("name");
		String detail = request.getParameter("detail");
		String dateKigen = request.getParameter("datekigen");
		String timeKigen = request.getParameter("timekigen");
		String needmail = request.getParameter("mailbutton");
		String dateMailtime = request.getParameter("datemailtime");
		String timeMailtime = request.getParameter("timemailtime");
		String regular = request.getParameter("regular");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String hour = request.getParameter("hour");
		String min = request.getParameter("min");

		boolean boolNeedmail = Boolean.valueOf(needmail);
		boolean boolRegular = Boolean.valueOf(regular);

		//"0000-00-00 00:00:00"の形に変換
		String kigen = dateKigen + " " + timeKigen;
		String mailtime = dateMailtime + " " + timeMailtime;
		String interval = "0000-" + month + "-" + day + " " + hour + ":" + min;

		if (boolRegular == false) {
			interval = null;
		}

		if (boolNeedmail == false) {
			mailtime = null;
		}

		return new TasksVo(
				name,
				detail,
				kigen,
				boolNeedmail,
				mailtime,
				boolRegular,
				interval,
				userId,
				getRandomMajorCharacter().getPictureId());

	}

	private PicturesVo getRandomMajorCharacter() {

		List<PicturesVo> pictureList = getMajorCharacters();
		Random r = new Random();

		return pictureList.get(r.nextInt(0, pictureList.size() - 1));

	}

	private List<PicturesVo> getMajorCharacters() {
		List<PicturesVo> pictureList = new ArrayList<PicturesVo>();

		DBUtil db = new DBUtil();

		try (Connection c = db.getConnection();) {

			PicturesDao dao = new PicturesDao(c);

			pictureList = dao.getMajorCharacters();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return pictureList;
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
	
	private String randomMessage() {
		
		Random r = new Random();
		int random = r.nextInt(3);
		
		if(random == 0) {return "がんばれ！";}
		if(random == 1) {return "ファイト！";}
		if(random == 2) {return "やったれ！";}
		
		return null;
	}

}
