package bean;

import java.util.ArrayList;
import java.util.List;

import vo.TasksVo;

public class DetailBean {
	
	private String userName;
	private TasksVo task;
	private List<String> PicturePaths = new ArrayList<String>();
	private String month;
	private String day;
	private String hour;
	private String minutes;
	

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

	public TasksVo getTask() {
		return task;
	}

	
	public String getMonth() {
		return month;
	}

	public String getDay() {
		return day;
	}

	public String getHour() {
		return hour;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setTask(TasksVo task) {
		this.task = task;
		if (task.getTaskinterval() != null) {
			String [] stringList= task.getTaskinterval().split("[- :]");
			
			this.month = String.valueOf(Integer.parseInt(stringList[1]));
			this.day = String.valueOf(Integer.parseInt(stringList[2]));
			this.hour = String.valueOf(Integer.parseInt(stringList[3]));
			this.minutes = String.valueOf(Integer.parseInt(stringList[4]));	
		}else {
			this.month = "0";
			this.day = "0";
			this.hour = "0";
			this.minutes = "0";
		}

	}

}
