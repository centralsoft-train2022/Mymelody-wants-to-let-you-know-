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
import javax.servlet.http.HttpSession;

import bean.TaskListBean;
import dao.DBUtil;
import dao.PicturesDao;
import dao.TasksDao;
import vo.PicturesVo;
import vo.TasksVo;
import vo.UsersVo;

@WebServlet("/TaskListServlet")
public class TaskListServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションからログインユーザーを取得
		HttpSession session = request.getSession();
		UsersVo user = (UsersVo) session.getAttribute("UsersVo");

		TaskListBean bean = new TaskListBean();

		int uid = user.getUserid();
		List<TasksVo> taskList = getAllTasks(uid);
		bean.setTaskList(taskList);

//		updateTasks(taskList);

		// 期日過ぎてて達せしてないやつを確認して格納
		boolean acievementFlag = checkAchievementFlag(uid);
		bean.setAchievementFlag(acievementFlag);

		// taskListからキャラクターの画像ファイルを取得しBeanへ
		for (TasksVo task : taskList) {
			PicturesVo pic = getPicture(task.getPictures_pictureid());
			bean.addPicturePath(pic.getPath());
		}

		bean.setUserName(user.getUsername());

		request.setAttribute("bean", bean);

		// JSPに遷移する
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/TaskList.jsp");
		disp.forward(request, response);
	}

	private void updateTasks(List<TasksVo> taskList) {

		for (TasksVo task : taskList) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date kigen = null;

			try {
				kigen = formatter.parse(task.getKigen());
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			Date date = new Date();

			if (kigen.before(date)) {// 現在日時が期限の日付を超えてるか
				if (task.isTaskvisible()) {// 削除されていないか
					if (task.isCompleted()) {// 繰り返しタスクかどうか
						
						String newKigen = task.getKigen();
						
						TasksVo newTask = new TasksVo(task.getTaskid(), task.getTaskbody(), task.getTaskbody(),
								task.getKigen(), task.getMailtime(), task.getTaskinterval(), task.isNeedmail(),
								task.isRegular());

						updateTask(newTask);
					}
				}
			}
		}
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

	//最後はflag(Boolean)を返す
	private Boolean checkAchievementFlag(int uid) {
		boolean acievementFlag = false;
		List<TasksVo> tskList = getAllTasks(uid);

		for (TasksVo tsk : tskList) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date kigen = null;

			try {
				kigen = formatter.parse(tsk.getKigen());
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			Date date = new Date();
			if (kigen.before(date)) {// 現在日時が期限の日付を超えてるか
				if (tsk.isTaskvisible()) {// 削除されていないか
					if (!tsk.isCompleted()) {// 達成しているか
						acievementFlag = true;
					}
				}
			}
		}
		return acievementFlag;

	}

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

	private void updateTask(TasksVo inputData) {

		DBUtil db = new DBUtil();
		try (Connection c = db.getConnection();) {

			TasksDao tsksdao = new TasksDao(c);

			tsksdao.update(inputData);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
