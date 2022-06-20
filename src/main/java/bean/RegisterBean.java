package bean;

import java.util.ArrayList;
import java.util.List;

public class RegisterBean {
	
	private String userName;
	private List<String> PicturePaths = new ArrayList<String>();
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPicturePath(int index) {
		return PicturePaths.get(index);
	}

	public void addPicturePath(String picturePath) {
		PicturePaths.add(picturePath);}

}
