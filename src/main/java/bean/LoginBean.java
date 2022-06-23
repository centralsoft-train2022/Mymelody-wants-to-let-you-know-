package bean;

import java.util.ArrayList;
import java.util.List;

public class LoginBean {
	private boolean wrongUserName;
	private boolean wrongPassword;
	private List<String> PicturePaths = new ArrayList<String>();

	public boolean isWrongUserName() {
		return wrongUserName;
	}

	public void setWrongUserName(boolean wrongUserName) {
		this.wrongUserName = wrongUserName;
	}

	public boolean isWrongPassword() {
		return wrongPassword;
	}

	public void setWrongPassword(boolean wrongPassword) {
		this.wrongPassword = wrongPassword;
	}

	public String getPicturePath(int index) {
		return PicturePaths.get(index);
	}

	public void addPicturePath(String picturePath) {
		PicturePaths.add(picturePath);
	}
}
