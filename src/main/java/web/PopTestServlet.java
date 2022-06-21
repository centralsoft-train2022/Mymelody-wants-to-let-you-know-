package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PopTestBean;
import dao.DBUtil;
import dao.TasksDao;
import vo.TasksVo;

@WebServlet("/PopTestServlet")
public class PopTestServlet extends HttpServlet {

	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PopTestBean bean = getPopTestBean(1);

		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		//		//usernameをBeanに渡す
		//		HttpSession session = request.getSession();
		//		UsersVo user = (UsersVo) session.getAttribute("UsersVo");
		//		bean.setUserName(user.getUsername());

		//JSPに遷移する
		request.setAttribute("bean", bean);
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/PopTest.jsp");
		disp.forward(request, response);

	}

	private PopTestBean getPopTestBean(int uid) {
		PopTestBean bean = new PopTestBean();
		boolean acievementFlag = checkAchievementFlag(uid);

		bean.setAchievementFlag(acievementFlag);

		return bean;
	}

	//条件に合致しているデータがあるかを確認し、flagを立てるメソッド
	//最後はflag(Boolean)を返す
	private Boolean checkAchievementFlag(int uid) {
		boolean acievementFlag = false;
		List<TasksVo> tskList = getAllTasks(uid);

		for (TasksVo tsk : tskList) {

			System.out.println("aabc");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date kigen = null;

			try {
				kigen = formatter.parse(tsk.getKigen());
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			Date date = new Date();

			if (kigen.before(date)) {//現在日時が期限の日付を超えてるか
				if (tsk.isCompleted()) {//達成しているか
					acievementFlag = false;
					System.out.println("aad");
				}
			}
		}

		return acievementFlag;

	}

	//全データを取得してくる
	private static List<TasksVo> getAllTasks(int uid) {

		List<TasksVo> tskList = new ArrayList<TasksVo>();

		DBUtil db = new DBUtil();

		try (Connection c = db.getConnection();) {

			TasksDao tdao = new TasksDao(c);

			tskList = tdao.getAllTasks(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return tskList;
	}

}
