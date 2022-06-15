package bean;

public class GetDetailBean {
	private String taskname;
	private String mailtime;
	private boolean needmail;
	private String taskinterval;

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getMailtime() {
		return mailtime;
	}

	public void setMailtime(String mailtime) {
		this.mailtime = mailtime;
	}

	public boolean isNeedmail() {
		return needmail;
	}

	public void setNeedmail(boolean needmail) {
		this.needmail = needmail;
	}

	public String getTaskinterval() {
		return taskinterval;
	}

	public void setTaskinterval(String taskinterval) {
		this.taskinterval = taskinterval;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[TasksVo:");

		buffer.append("\n taskname: ");
		buffer.append(taskname);

		buffer.append("\n needmail: ");
		buffer.append(needmail);

		if (mailtime != null) {
			buffer.append("\n mailtime: ");
			buffer.append(mailtime);
		}

		if (taskinterval != null) {
			buffer.append("\n taskinterval: ");
			buffer.append(taskinterval);
		}
		buffer.append("\n]");
		return buffer.toString();
	}
}