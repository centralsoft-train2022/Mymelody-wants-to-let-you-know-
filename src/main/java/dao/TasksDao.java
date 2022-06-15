package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Vo.TasksVo;

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
			+ " tasks";

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
	
	public List<TasksVo> getAllTasks() {
		List<TasksVo> list = new ArrayList<TasksVo>();

		try (PreparedStatement stmt = this.con.prepareStatement(SELECT_AllTASKS_SQL)) {

			// +"EMPLOYEEID="+i);//これはつかわない SQLインジェクション対策、高速化対策

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
	
	public List<TasksVo> getExtractTasks(int num) {
		List<TasksVo> list = new ArrayList<TasksVo>();

		try (PreparedStatement stmt = this.con.prepareStatement(Extract_AllTASKS_SQL)) {

			// +"EMPLOYEEID="+i);//これはつかわない SQLインジェクション対策、高速化対策
			stmt.setInt( 1, num );
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

}