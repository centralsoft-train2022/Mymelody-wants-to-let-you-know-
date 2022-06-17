package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBUtil;
import dao.TasksDao;
import vo.TasksVo;

@WebServlet("/RegisteredServlet")
public class RegisteredServlet extends HttpServlet {
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

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

		//入力データが取得できているかの確認表示
		System.out.println(name);
		System.out.println(detail);
		System.out.println(kigen);
		System.out.println(needmail);
		System.out.println(mailtime);
		System.out.println(regular);
		System.out.println(interval);
		System.out.println(month);
		System.out.println(day);
		System.out.println(hour);
		System.out.println(min);

		if (boolRegular == false) {
			interval = null;
		}

		DBUtil db = new DBUtil();

		try (Connection c = db.getConnection()) {

			TasksDao dao = new TasksDao(c);

			TasksVo data = new TasksVo(
					name,
					detail,
					false,
					kigen,
					boolNeedmail,
					mailtime,
					boolRegular,
					interval,
					false,
					1,
					1);

			dao.insert(data);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		//JSPに遷移する
		RequestDispatcher disp = request.getRequestDispatcher("/html/Cheer.html");
		disp.forward(request, response);
	}

}
