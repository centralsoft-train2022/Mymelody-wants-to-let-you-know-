package bean;

import java.util.ArrayList;
import java.util.List;

import vo.TasksVo;

public class TaskListBean {

	private String userName;
	private List<TasksVo> taskList = new ArrayList<TasksVo>();
	private List<String> PicturePaths = new ArrayList<String>();
	private Boolean achievementFlag;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getPicturePaths() {
		return this.PicturePaths;
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

	public Boolean getAchievementFlag() {
		return achievementFlag;
	}

	public void setAchievementFlag(Boolean achievementFlag) {
		this.achievementFlag = achievementFlag;
	}

}
