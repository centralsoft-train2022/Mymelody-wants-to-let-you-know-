package bean;

import java.util.ArrayList;
import java.util.List;

import vo.TasksVo;

public class DetailBean {
	
	private List<TasksVo> taskList = new ArrayList<TasksVo>();
	private List<String> PicturePaths = new ArrayList<String>();
	
	private int Taskid;

	public String getPicturePath(int index) {
		return PicturePaths.get(index);
	}

	public void addPicturePath(String picturePath) {
		PicturePaths.add(picturePath);
	}

	
	public int getTaskid() {
		return this.Taskid;
	}

	public void setTaskid(int Taskid) {
		this.Taskid = Taskid;
	}

	public List<TasksVo> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TasksVo> taskList) {
		this.taskList = taskList;
	}
	
	
}
