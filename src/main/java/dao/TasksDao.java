package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.TasksVo;

public class TasksDao {
	private Connection con;

	public TasksDao(Connection con) {
		super();
		this.con = con;
	}

	private static final String SELECT_AllTASKS_SQL = "select "
			+ " taskid"
			+ ",taskname"
			+ ",taskbody"
			+ ",completed"
			+ ",kigen"
			+ ",needmail"
			+ ",mailtime"
			+ ",regular"
			+ ",taskinterval"
			+ ",taskvisible"
			+ ",users_userid"
			+ ",pictures_pictureid"
			+ " from"
			+ " tasks"
			+ " Where"
			+ " users_userid = ?"
			+ " and taskvisible = 1";

	private static final String Extract_AllTASKS_SQL = "select "
			+ " taskid"
			+ ",taskname"
			+ ",taskbody"
			+ ",completed"
			+ ",kigen"
			+ ",needmail"
			+ ",mailtime"
			+ ",regular"
			+ ",taskinterval"
			+ ",taskvisible"
			+ ",users_userid"
			+ ",pictures_pictureid"
			+ " from"
			+ " tasks"
			+ " Where"
			+ " taskid = ?";

	private static final String UPDATE_TaskAchievement = ""
			+ "UPDATE \n"
			+ "	tasks\n"
			+ " set completed = True"
			+ " WHERE\n"
			+ " taskid = ?";

	private static final String INSERT_SQL = ""
			+ "insert\n"
			+ "into tasks\n"
			+ "(\n"
			+ " taskname\n"
			+ " ,taskbody\n"
			+ " ,completed\n"
			+ " ,kigen\n"
			+ " ,needmail\n"
			+ " ,mailtime\n"
			+ " ,regular\n"
			+ " ,taskinterval\n"
			+ " ,taskvisible\n"
			+ " ,users_userid\n"
			+ " ,pictures_pictureid\n"
			+ ")\n"
			+ "values\n"
			+ "(\n"
			+ " ?"
			+ ",?"
			+ ",?"
			+ ",?"
			+ ",?"
			+ ",?"
			+ ",?"
			+ ",?"
			+ ",?"
			+ ",?"
			+ ",?"
			+ ")";

	private static final String DELETE_Task = ""
			+ "UPDATE \n"
			+ "	tasks\n"
			+ " set taskvisible = 0"
			+ " WHERE\n"
			+ " taskid = ?";

	private static final String UPDATE_SQL = ""
			+ "UPDATE \n"
			+ "	`tasks`\n"
			+ " set taskname = ?"
			+ " ,taskbody = ?"
			+ " ,kigen = ?"
			+ " ,mailtime = ?"
			+ " ,taskinterval = ?"
			+ " ,needmail = ?"
			+ " ,regular = ?"
			+ " WHERE\n"
			+ " taskid = ?;";

	public List<TasksVo> getAllTasks(int uid) {
		List<TasksVo> list = new ArrayList<TasksVo>();

		try (PreparedStatement stmt = this.con.prepareStatement(SELECT_AllTASKS_SQL)) {

			// +"EMPLOYEEID="+i);//これはつかわない SQLインジェクション対策、高速化対策
			stmt.setInt(1, uid);
			/* ｓｑｌ実行 */
			try (ResultSet rset = stmt.executeQuery();) {

				/* 取得したデータをEmployeesVoのインスタンスにまとめます */
				while (rset.next()) {
					TasksVo task = new TasksVo();
					// em.setEmployeeid( rset.getInt("EMPLOYEEID") );
					task.setTaskid(rset.getInt(1));
					task.setTaskname(rset.getString(2));
					task.setTaskbody(rset.getString(3));
					task.setCompleted(rset.getBoolean(4));
					task.setKigen(rset.getString(5));
					task.setNeedmail(rset.getBoolean(6));
					task.setMailtime(rset.getString(7));
					task.setRegular(rset.getBoolean(8));
					task.setTaskinterval(rset.getString(9));
					task.setTaskvisible(rset.getBoolean(10));
					task.setUsers_userid(rset.getInt(11));
					task.setPictures_pictureid(rset.getInt(12));
					list.add(task);
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;

	}

	public TasksVo getExtractTasks(int num) {

		TasksVo task = new TasksVo();

		try (PreparedStatement stmt = this.con.prepareStatement(Extract_AllTASKS_SQL)) {

			// +"EMPLOYEEID="+i);//これはつかわない SQLインジェクション対策、高速化対策
			stmt.setInt(1, num);
			/* ｓｑｌ実行 */
			try (ResultSet rset = stmt.executeQuery();) {

				/* 取得したデータをEmployeesVoのインスタンスにまとめます */
				while (rset.next()) {

					// em.setEmployeeid( rset.getInt("EMPLOYEEID") );
					task.setTaskid(rset.getInt(1));
					task.setTaskname(rset.getString(2));
					task.setTaskbody(rset.getString(3));
					task.setCompleted(rset.getBoolean(4));
					task.setKigen(rset.getString(5));
					task.setNeedmail(rset.getBoolean(6));
					task.setMailtime(rset.getString(7));
					task.setRegular(rset.getBoolean(8));
					task.setTaskinterval(rset.getString(9));
					task.setTaskvisible(rset.getBoolean(10));
					task.setUsers_userid(rset.getInt(11));
					task.setPictures_pictureid(rset.getInt(12));
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return task;
	}

	public void TaskAchievement(int num) throws SQLException {

		PreparedStatement stmt = this.con.prepareStatement(UPDATE_TaskAchievement);

		stmt.setInt(1, num);
		/* ｓｑｌ実行 */
		stmt.execute();
	}

	public void DeleteTask(int num) throws SQLException {

		PreparedStatement stmt = this.con.prepareStatement(DELETE_Task);

		stmt.setInt(1, num);
		/* ｓｑｌ実行 */
		stmt.execute();
	}

	public void insert(TasksVo data) {
		try (PreparedStatement stmt = this.con.prepareStatement(INSERT_SQL)) {

			stmt.setString(1, data.getTaskname());
			stmt.setString(2, data.getTaskbody());
			stmt.setBoolean(3, data.isCompleted());
			stmt.setString(4, data.getKigen());
			stmt.setBoolean(5, data.isNeedmail());
			stmt.setString(6, data.getMailtime());
			stmt.setBoolean(7, data.isRegular());
			stmt.setString(8, data.getTaskinterval());
			stmt.setBoolean(9, data.isTaskvisible());
			stmt.setInt(10, data.getUsers_userid());
			stmt.setInt(11, data.getPictures_pictureid());

			/* ｓｑｌ実行 */
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void update(int id, String taskname, String taskdetail, String kigen, String maildate, String interval, int needmail, int regular) {

		try (PreparedStatement stmt = this.con.prepareStatement(UPDATE_SQL)) {
			stmt.setString(1, taskname);
			stmt.setString(2, taskdetail);
			stmt.setString(3, kigen);
			stmt.setString(4, maildate);
			stmt.setString(5, interval);
			stmt.setInt(6, needmail);
			stmt.setInt(7, regular);
			stmt.setInt(8, id);
			/* ｓｑｌ実行 */
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
