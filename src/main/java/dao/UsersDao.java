//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UsersDao {
//	private Connection con;
//	
//	private static final String SELECT_USER_SQL = "select "
//			+ " userid"
//			+ ",username"
//			+ ",password"
//			+ ",useraddress"
//			+ " from"
//			+ " EMPLOYEES"
//			+ " where"
//			+ " EMPLOYEEID=?";
//	
//	public EmployeesVo getEmployee(int eid) {
//		EmployeesVo em = null;
//
//		try (PreparedStatement stmt = this.con.prepareStatement(SELECT_EMPLOYEE_SQL)) {
//			stmt.setInt(1, eid);
//			// +"EMPLOYEEID="+i);//これはつかわない SQLインジェクション対策、高速化対策
//
//			/* ｓｑｌ実行 */
//			try (ResultSet rset = stmt.executeQuery();) {
//				em = new EmployeesVo();
//
//				/* 取得したデータをEmployeesVoのインスタンスにまとめます */
//				while (rset.next()) {
//					// em.setEmployeeid( rset.getInt("EMPLOYEEID") );
//					em.setEmployeeid(rset.getInt(1));
//					em.setEmployeename(rset.getString(2));
//					em.setHeight(rset.getBigDecimal(3));
//					em.setEmail(rset.getString(4));
//					em.setWeight(rset.getBigDecimal(5));
//					em.setHirefiscalyear(rset.getInt(6));
//					em.setBirthday(rset.getDate(7));
//					em.setBloodtype(rset.getString(8));
//				}
//
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//
//		return em;
//
//	}
//
//}
