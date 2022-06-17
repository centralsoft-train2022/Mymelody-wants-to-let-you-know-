package bean;

import java.util.ArrayList;
import java.util.List;

import vo.TasksVo;

public class TaskListBean
{
	private String TaskName;
	private List<TasksVo> taskList = new ArrayList<TasksVo>();
	private List<String> PicturePaths = new ArrayList<String>();

	public String getPicturePath(int index) {
		return PicturePaths.get(index);
	}

	public void addPicturePath(String picturePath) {
		PicturePaths.add(picturePath);
	}

	public String getTaskName() {
		return TaskName;
	}

	public void setTaskName(String TaskName) {
		this.TaskName = TaskName;
	}

	public List<TasksVo> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TasksVo> taskList) {
		this.taskList = taskList;
	}
	
	

}
