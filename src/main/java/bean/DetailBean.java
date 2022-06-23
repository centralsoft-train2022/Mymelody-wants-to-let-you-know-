package bean;

import java.util.ArrayList;
import java.util.List;

import vo.TasksVo;

public class DetailBean {

	private String userName;
	private TasksVo task;
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
		PicturePaths.add(picturePath);
	}

	public void setTask(TasksVo task) {
		this.task = task;
	}

	public TasksVo getTask() {
		return task;
	}

}
