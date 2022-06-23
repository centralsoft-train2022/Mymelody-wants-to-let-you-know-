package input;

public class EditInput {

	private String taskid;
	private String taskname;
	private String taskdetail;
	private String tasktime;
	private String maildate;
	private String month;
	private String day;
	private String hour;
	private String minutes;
	private String needmail;
	private String regular;

	public EditInput(String taskid, String taskname, String taskdetail, String tasktime, String maildate, String month,
			String day, String hour, String minutes, String needmail, String regular) {
		super();
		this.taskid = taskid;
		this.taskname = taskname;
		this.taskdetail = taskdetail;
		this.tasktime = tasktime;
		this.maildate = maildate;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minutes = minutes;
		this.needmail = needmail;
		this.regular = regular;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getTaskdetail() {
		return taskdetail;
	}

	public void setTaskdetail(String taskdetail) {
		this.taskdetail = taskdetail;
	}

	public String getTasktime() {
		return tasktime;
	}

	public void setTasktime(String tasktime) {
		this.tasktime = tasktime;
	}

	public String getMaildate() {
		return maildate;
	}

	public void setMaildate(String maildate) {
		this.maildate = maildate;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getNeedmail() {
		return needmail;
	}

	public void setNeedmail(String needmail) {
		this.needmail = needmail;
	}

	public String getRegular() {
		return regular;
	}

	public void setRegular(String regular) {
		this.regular = regular;
	}

	@Override
	public String toString() {
		return "EditInput [taskid=" + taskid + ", taskname=" + taskname + ", taskdetail=" + taskdetail + ", tasktime="
				+ tasktime + ", maildate=" + maildate + ", month=" + month + ", day=" + day + ", hour=" + hour
				+ ", minutes=" + minutes + ", regular=" + regular + "]";
	}

}
