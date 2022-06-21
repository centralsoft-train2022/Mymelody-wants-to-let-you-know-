package bean;

import java.util.ArrayList;
import java.util.List;

public class RegisterBean {

	private String message;
	private String userName;
	private boolean isTaskNameExists;
	private boolean isTaskKigenExists;
	private List<String> PicturePaths = new ArrayList<String>();
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isTaskNameExists() {
		return isTaskNameExists;
	}

	public void setTaskNameExists(boolean isTaskNameExists) {
		this.isTaskNameExists = isTaskNameExists;
	}

	public boolean isTaskKigenExists() {
		return isTaskKigenExists;
	}

	public void setTaskKigenExists(boolean isTaskKigenExists) {
		this.isTaskKigenExists = isTaskKigenExists;
	}

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
		PicturePaths.add(picturePath);
	}

}
