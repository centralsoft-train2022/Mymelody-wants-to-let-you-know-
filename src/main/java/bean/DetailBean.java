package bean;

import java.util.ArrayList;
import java.util.List;

import vo.TasksVo;

public class DetailBean {
	private String TaskName;
	private String TaskBody;
	private boolean completed;
	private String kigen;
	private boolean needmail;
	private String mailtime;
	
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
	
	public void setTaskBody(String TaskBody) {
		this.TaskBody = TaskBody;
	}
	
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getKigen() {
		return kigen;
	}

	public void setKigen(String kigen) {
		this.kigen = kigen;
	}

	public boolean isNeedmail() {
		return needmail;
	}

	public void setNeedmail(boolean needmail) {
		this.needmail = needmail;
	}

	public String getMailtime() {
		return mailtime;
	}

	public void setMailtime(String mailtime) {
		this.mailtime = mailtime;
	}

	public String getTaskBody() {
		return this.TaskBody;
	}

}
