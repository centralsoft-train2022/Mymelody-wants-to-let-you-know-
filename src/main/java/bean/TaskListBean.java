package bean;

import java.util.ArrayList;
import java.util.List;

import vo.TasksVo;

public class TaskListBean
{
	
	private String userName;
	private List<TasksVo> taskList = new ArrayList<TasksVo>();
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

	public List<TasksVo> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TasksVo> taskList) {
		this.taskList = taskList;
	}
	
	

}
