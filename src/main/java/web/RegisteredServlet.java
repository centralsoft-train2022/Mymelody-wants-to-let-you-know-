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

		//セッションからデータを取得
		HttpSession session = request.getSession();
		UsersVo usersVo = (UsersVo) session.getAttribute("UsersVo");

		TasksVo inputData = receiveInput(request, usersVo.getUserid());

		System.out.println(inputData.getKigen());

		if (inputData.getTaskname().isEmpty() || inputData.getKigen().equals(" ")) {
			RequestDispatcher disp = request.getRequestDispatcher("RegisterServlet");
			disp.forward(request, response);
		} else {
			sendDB(inputData);

			//JSPに遷移する(6/20現在サーブレット遷移してます)
			RequestDispatcher disp = request.getRequestDispatcher("CheerServlet");
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

}
