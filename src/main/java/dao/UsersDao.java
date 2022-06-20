package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.UsersVo;

public class UsersDao {
	private Connection con;

	public UsersDao(Connection con) {
		super();
		this.con = con;
	}

	private static final String SELECT_USER_SQL = "select "
			+ " userid"
			+ ",username"
			+ ",password"
			+ ",mailaddress"
			+ " from"
			+ " users"
			+ " where"
			+ " mailaddress=? ";

	public UsersVo getUser(String address) {
		UsersVo user = null;

		try (PreparedStatement stmt = this.con.prepareStatement(SELECT_USER_SQL)) {
			stmt.setString(1, address);
			// +"EMPLOYEEID="+i);//これはつかわない SQLインジェクション対策、高速化対策

			/* ｓｑｌ実行 */
			try (ResultSet rset = stmt.executeQuery();) {
				user = new UsersVo();

				/* 取得したデータをEmployeesVoのインスタンスにまとめます */
				while (rset.next()) {
					// em.setEmployeeid( rset.getInt("EMPLOYEEID") );
					user.setUserid(rset.getInt(1));
					user.setUsername(rset.getString(2));
					user.setPassword(rset.getString(3));
					user.setMailaddress(rset.getString(4));
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return user;

	}

}
