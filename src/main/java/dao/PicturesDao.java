package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.PicturesVo;

public class PicturesDao {

	private Connection con;

	public PicturesDao(Connection con) {
		super();
		this.con = con;
	}

	private static final String SELECT_ALL_SQL = ""
			+ "SELECT `pictures`.`pictureid`,\n"
			+ "    `pictures`.`charactername`,\n"
			+ "    `pictures`.`filename`,\n"
			+ "    `pictures`.`charactertype`\n"
			+ "FROM `mymelody`.`pictures`;\n"
			+ ";";

	public List<PicturesVo> getAllPictures() {

		List<PicturesVo> list = new ArrayList<PicturesVo>();

		try (PreparedStatement stmt = this.con.prepareStatement(SELECT_ALL_SQL)) {

			try (ResultSet rset = stmt.executeQuery()) {

				while (rset.next()) {
					PicturesVo pic = new PicturesVo();
					pic.setPictureId(rset.getInt(1));
					pic.setCharacterName(rset.getString(2));
					pic.setFileName(rset.getString(3));
					pic.setCharactertype(rset.getInt(4));
					list.add(pic);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}
	
	private static final String SELECT_PICTURE_SQL = ""
			+ " select *"
			+ " from"
			+ " pictures"
			+ " where"
			+ " pictureid=? ";

	public PicturesVo getPicture(int picureid) {
		PicturesVo pic = null;

		try (PreparedStatement stmt = this.con.prepareStatement(SELECT_PICTURE_SQL)) {
			stmt.setInt(1, picureid);
			// +"EMPLOYEEID="+i);//これはつかわない SQLインジェクション対策、高速化対策

			/* ｓｑｌ実行 */
			try (ResultSet rset = stmt.executeQuery();) {
				pic = new PicturesVo();

				/* 取得したデータをEmployeesVoのインスタンスにまとめます */
				while (rset.next()) {
					// em.setEmployeeid( rset.getInt("EMPLOYEEID") );
					pic.setPictureId(rset.getInt(1));
					pic.setCharacterName(rset.getString(2));
					pic.setFileName(rset.getString(3));
					pic.setCharactertype(rset.getInt(4));
					
					if(pic.getCharactertype() == 0) {
						pic.setPath("mymelody/"+pic.getFileName());
					};
					if(pic.getCharactertype() == 1) {
						pic.setPath("majorCharacter/"+pic.getFileName());
					};
					if(pic.getCharactertype() == 2) {
						pic.setPath("minorCharacter/"+pic.getFileName());
					};
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return pic;
	}
	
	public List<PicturesVo> getMymelodies() {
		List<PicturesVo> list = new ArrayList<PicturesVo>();
		list = getAllPictures();
		list.removeIf(picture -> picture.getCharactertype() != 0);
		for(PicturesVo pv:list) {
		pv.setPath("mymelody/"+pv.getFileName());}
		return list;
	}

	public List<PicturesVo> getMajorCharacters() {
		List<PicturesVo> list = new ArrayList<PicturesVo>();
		list = getAllPictures();
		list.removeIf(picture -> picture.getCharactertype() != 1);
		for(PicturesVo pv:list) {
			pv.setPath("majorCharacter/"+pv.getFileName());}
		return list;
	}
	
	public List<PicturesVo> getMinorCharacters() {
		List<PicturesVo> list = new ArrayList<PicturesVo>();
		list = getAllPictures();
		list.removeIf(picture -> picture.getCharactertype() != 2);
		for(PicturesVo pv:list) {
			pv.setPath("minorCharacter/"+pv.getFileName());}
		return list;
	}
	
	public List<PicturesVo> getComposite() {
		List<PicturesVo> list = new ArrayList<PicturesVo>();
		list = getAllPictures();
		list.removeIf(picture -> picture.getCharactertype() != 3);
		for(PicturesVo pv:list) {
			pv.setPath("composite/"+pv.getFileName());}
		return list;
	}
	
	public List<PicturesVo> getAngry() {
		List<PicturesVo> list = new ArrayList<PicturesVo>();
		list = getAllPictures();
		list.removeIf(picture -> picture.getCharactertype() != 4);
		for(PicturesVo pv:list) {
			pv.setPath("angry/"+pv.getFileName());}
		return list;
	}

}
