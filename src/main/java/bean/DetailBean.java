package bean;

import java.util.ArrayList;
import java.util.List;

import vo.TasksVo;

public class DetailBean {

	private String userName;
	private TasksVo task;
	private boolean isTaskNameExists;
	private boolean isTaskKigenExists;
	private boolean isMailtimeExists;
	private boolean isRegulartimeExists;
	private List<String> PicturePaths = new ArrayList<String>();

	public DetailBean() {
		super();

		this.isTaskNameExists = true;
		this.isTaskKigenExists = true;
		this.isMailtimeExists = true;
		this.isRegulartimeExists = true;
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

	public void setTask(TasksVo task) {
		this.task = task;
	}

	public TasksVo getTask() {
		return task;
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

	public boolean isMailtimeExists() {
		return isMailtimeExists;
	}

	public void setMailtimeExists(boolean isMailtimeExists) {
		this.isMailtimeExists = isMailtimeExists;
	}

	public boolean isRegulartimeExists() {
		return isRegulartimeExists;
	}

	public void setRegulartimeExists(boolean isRegulartimeExists) {
		this.isRegulartimeExists = isRegulartimeExists;
	}

}
