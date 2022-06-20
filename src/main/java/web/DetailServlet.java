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
import javax.servlet.http.HttpSession;

import bean.DetailBean;
import dao.DBUtil;
import dao.PicturesDao;
import dao.TasksDao;
import vo.PicturesVo;
import vo.TasksVo;
import vo.UsersVo;

@WebServlet("/DetailServlet")
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		// 押されたボタンのidを取得、Stringからintへの変換
		String s = request.getParameter("edit");
		int num = Integer.parseInt(s);

		bean.setTaskid(num);

		TasksVo task = getTasksVo(num);
		bean.setTask(task);
		
		PicturesVo pic = getPicture(task.getPictures_pictureid());
		bean.addPicturePath(pic.getPath());
		
		request.setAttribute("bean", bean);

		// JSPに遷移する
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/Detail.jsp");
		disp.forward(request, response);
	}

	private PicturesVo getPicture(int pictureid) {

		PicturesVo pic;

		try (Connection c = db.getConnection();) {

			PicturesDao dao = new PicturesDao(c);

			pic = dao.getPicture(pictureid);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return pic;
	}

	private static TasksVo getTasksVo(int num) {

		DBUtil db = new DBUtil();
		TasksVo task;

		DBUtil db = new DBUtil();

			TasksDao tdao = new TasksDao( c );
			task = tdao.getExtractTasks(num);
		}

		return task;
	}

}
