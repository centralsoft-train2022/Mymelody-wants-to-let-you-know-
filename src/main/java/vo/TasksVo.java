package vo;

/* Code Generator Information.
 * generator Version 1.0.0 release 2007/10/10
 * generated Date Thu May 18 17:00:22 JST 2017
 */
import java.io.Serializable;

public class TasksVo implements Serializable {

	public static final String TABLE = "tasks";

	private int taskid;
	private String taskname;
	private String taskbody;
	private boolean completed;
	private String kigen;
	private boolean needmail;
	private String mailtime;
	private boolean regular;
	private String taskinterval;
	private boolean taskvisible;
	private int users_userid;
	private int pictures_pictureid;
	private boolean alreadysend;

	/**
	* Constractor
	*/

	/**
	* Constractor
	* @param <code>employeeid</code>
	*/

	public TasksVo() {
		super();
	}

	public TasksVo(String taskname, String taskbody, String kigen, boolean needmail,
			String mailtime, boolean regular, String taskinterval, int users_userid,
			int pictures_pictureid) {
		super();
		this.taskname = taskname;
		this.taskbody = taskbody;
		this.completed = false;
		this.kigen = kigen;
		this.needmail = needmail;
		this.mailtime = mailtime;
		this.regular = regular;
		this.taskinterval = taskinterval;
		this.taskvisible = true;
		this.users_userid = users_userid;
		this.pictures_pictureid = pictures_pictureid;
		this.alreadysend = false;
	}



	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getTaskbody() {
		return taskbody;
	}

	public void setTaskbody(String taskbody) {
		this.taskbody = taskbody;
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

	public boolean isRegular() {
		return regular;
	}

	public void setRegular(boolean regular) {
		this.regular = regular;
	}

	public String getTaskinterval() {
		return taskinterval;
	}

	public void setTaskinterval(String taskinterval) {
		this.taskinterval = taskinterval;
	}

	public boolean isTaskvisible() {
		return taskvisible;
	}

	public void setTaskvisible(boolean taskvisible) {
		this.taskvisible = taskvisible;
	}

	public int getUsers_userid() {
		return users_userid;
	}

	public void setUsers_userid(int users_userid) {
		this.users_userid = users_userid;
	}

	public int getPictures_pictureid() {
		return pictures_pictureid;
	}

	public void setPictures_pictureid(int pictures_pictureid) {
		this.pictures_pictureid = pictures_pictureid;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[TasksVo:");

		buffer.append(" taskid: ");
		buffer.append(taskid);

		buffer.append("\n taskname: ");
		buffer.append(taskname);

		if (taskbody != null) {
			buffer.append("\n taskbody: ");
			buffer.append(taskbody);
		}

		buffer.append("\n completed: ");
		buffer.append(completed);
		buffer.append("\n kigen: ");
		buffer.append(kigen);
		buffer.append("\n needmail: ");
		buffer.append(needmail);

		if (mailtime != null) {
			buffer.append("\n mailtime: ");
			buffer.append(mailtime);
		}

		buffer.append("\n regular: ");
		buffer.append(regular);

		if (taskinterval != null) {
			buffer.append("\n taskinterval: ");
			buffer.append(taskinterval);
		}

		buffer.append("\n taskvisible: ");
		buffer.append(taskvisible);
		buffer.append("\n users_userid: ");
		buffer.append(users_userid);
		buffer.append("\n pictures_pictureid: ");
		buffer.append(pictures_pictureid);
		buffer.append("\n]");
		return buffer.toString();
	}

	public boolean isAlreadysend() {
		return alreadysend;
	}

	public void setAlreadysend(boolean alreadysend) {
		this.alreadysend = alreadysend;
	}

}