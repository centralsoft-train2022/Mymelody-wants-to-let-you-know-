package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Vo.EmployeesVo;
import bean.TaskListBean;

@WebServlet("/TaskListServlet")
public class TaskListServlet extends HttpServlet {

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException
	{
		//社員リストwoDBから取得　課題
		EmployeesVo  emp = getEmployeesVo("aaaaa");

		TaskListBean bean = new TaskListBean();


//		//セッションからログインユーザーを取得
//		HttpSession session = request.getSession();
//	    EmployeesVo emp  = (EmployeesVo)session.getAttribute("EmployeesVo");
	    //String peStr  = (String)session.getAttribute("password");

	    bean.setTaskName(emp.getEmployeename());

		request.setAttribute("bean", bean);

		//JSPに遷移する
		RequestDispatcher disp = request.getRequestDispatcher("/TaskList.jsp");
		disp.forward(request, response);
	}

	//DBから従業員を取得する
	private EmployeesVo getEmployeesVo( String TaskName)
	{
		
		//DBから従業員を取得 仮実装
//		DBUtil db = new DBUtil( );
//		try( Connection c = db.getConnection( ); )
//		{
//			EmployeesDao	dao	= new EmployeesDao( c );
//			empList = dao.getAllEmployees();
//		}
//		catch( SQLException e )
//		{
//			throw new RuntimeException( e );
//		}
		
		
		
		
		//テストデータ
		EmployeesVo emp = new EmployeesVo();
		emp.setEmployeename(TaskName);
		
		return emp;
	}

}
