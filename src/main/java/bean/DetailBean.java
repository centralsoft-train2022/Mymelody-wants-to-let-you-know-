package bean;

import java.util.List;

import Vo.TasksVo;

public class DetailBean {
	
	
	private List<TasksVo> taskList;
	private int Taskid;

	
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
