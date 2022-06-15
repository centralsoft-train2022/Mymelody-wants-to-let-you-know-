package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	/* ユーザ名 */
	// String user = "train2022";
	private static final String DB_USER = "root";

	/* パスワード */
	// String pass = "train2022";
	private static final String DB_PASS = "rootroot";

	/* サーバ名 */
	private static final String DB_SERVER = "localhost:3306";

	// String servername = "172.20.10.251:3306";

	/* DB名 */
	private static final String dbname = "mymelody";

	private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

	private static final String DB_CONECT_STR = "jdbc:mysql:";

	private Connection con;

	public Connection getConnection() {

		// DBに接続する
		// ドライバーのロード
		try {
			Class.forName(DRIVER_CLASS_NAME);

			// 接続の実行とコネクションオブジェクトの取得
			this.con = DriverManager.getConnection(
					DB_CONECT_STR + "//" + DB_SERVER + "/" + dbname, DB_USER, DB_PASS);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

			// このエラーはドライバーがないかDBに不具合か、接続できないエラーなので
			// 例外をランタイム例外に載せ替えてスローしシステムを終了させる
			RuntimeException re = new RuntimeException(e);
			throw re;
		}

		return this.con;
	}
}