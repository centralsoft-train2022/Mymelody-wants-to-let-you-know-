package bean;

import java.util.List;

import Vo.TasksVo;

public class TaskListBean
{
	private String TaskName;
	private List<TasksVo> taskList;

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
