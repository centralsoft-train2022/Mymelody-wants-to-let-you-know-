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
import input.RegisterInput;
import vo.PicturesVo;
import vo.TasksVo;
import vo.UsersVo;

@WebServlet("/RegisteredServlet")
public class RegisteredServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("RegisteredServletが呼び出されたよ");

		request.setCharacterEncoding("UTF-8");

		RegisterBean rbean = new RegisterBean();
		CheerBean cbean = new CheerBean();

		// usernameをBeanに渡す
		HttpSession session = request.getSession();
		UsersVo usersVo = (UsersVo) session.getAttribute("UsersVo");
		rbean.setUserName(usersVo.getUsername());
		cbean.setUserName(usersVo.getUsername());

		// 入力を受け取る
		RegisterInput input = receiveInput(request);
		System.out.println(input);

		// 以前の入力をセッションから取得
		RegisterInput preinput = (RegisterInput) session.getAttribute("preinput");

		// 入力をセッションに保存
		session.setAttribute("preinput", input);

		// 登録画面に表示するキャラクターをBeanに渡す
		List<PicturesVo> pictureList = getMinorCharacters();
		for (PicturesVo pv : pictureList) {
			rbean.addPicturePath(pv.getPath());
		}

		if (input.getName().isEmpty() && input.getDateKigen().equals(" ")) {

			rbean.setTaskNameExists(false);
			rbean.setTaskKigenExists(false);

			request.setAttribute("bean", rbean);

			RequestDispatcher disp = request.getRequestDispatcher("/jsp/Register.jsp");
			disp.forward(request, response);

		} else if (input.getName().isEmpty()) {
			rbean.setTaskNameExists(false);
			rbean.setTaskKigenExists(true);

			request.setAttribute("bean", rbean);

			RequestDispatcher disp = request.getRequestDispatcher("/jsp/Register.jsp");
			disp.forward(request, response);

		} else if (input.getDateKigen().equals(" ")) {
			rbean.setTaskNameExists(true);
			rbean.setTaskKigenExists(false);

			request.setAttribute("bean", rbean);

			RequestDispatcher disp = request.getRequestDispatcher("/jsp/Register.jsp");
			disp.forward(request, response);

		} else if (input.equals(preinput)) {
			
			// セッションからタスクを取得
			TasksVo preTask = (TasksVo) session.getAttribute("preTask");
			
			PicturesVo pv = getPicture(preTask.getPictures_pictureid());

			cbean.addPicturePath(pv.getPath());
			cbean.setMessage(randomMessage());

			request.setAttribute("bean", cbean);

			RequestDispatcher disp = request.getRequestDispatcher("/jsp/Cheer.jsp");
			disp.forward(request, response);
			
		} else {

			// 入力からタスクを生成
			TasksVo newTask = convertNewTask(input, usersVo.getUserid());
			sendDB(newTask);
			
			session.setAttribute("preTask", newTask);
			
			PicturesVo pv = getPicture(newTask.getPictures_pictureid());

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

	private TasksVo convertNewTask(RegisterInput ri, int userId) {

		boolean boolNeedmail = Boolean.valueOf(ri.getNeedmail());
		boolean boolRegular = Boolean.valueOf(ri.getRegular());

		// "0000-00-00 00:00:00"の形に変換
		String kigen = ri.getDateKigen() + " " + ri.getTimeKigen();
		String mailtime = ri.getDateMailtime() + " " + ri.getTimeMailtime();
		String interval = "0000-" + ri.getMonth() + "-" + ri.getDay() + " " + ri.getHour() + ":" + ri.getMin();

		if (boolRegular == false) {
			interval = null;
		}

		if (boolNeedmail == false) {
			mailtime = null;
		}

		return new TasksVo(ri.getName(), ri.getDetail(), kigen, boolNeedmail, mailtime, boolRegular, interval, userId,
				getRandomMajorCharacter().getPictureId());

	}

	private RegisterInput receiveInput(HttpServletRequest request) {

		return new RegisterInput(request.getParameter("name"), request.getParameter("detail"),
				request.getParameter("datekigen"), request.getParameter("timekigen"),
				request.getParameter("mailbutton"), request.getParameter("datemailtime"),
				request.getParameter("timemailtime"), request.getParameter("regular"), request.getParameter("month"),
				request.getParameter("day"), request.getParameter("hour"), request.getParameter("min"));
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

		if (random == 0) {
			return "がんばれ！";
		}
		if (random == 1) {
			return "ファイト！";
		}
		if (random == 2) {
			return "やったれ！";
		}

		return null;
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
