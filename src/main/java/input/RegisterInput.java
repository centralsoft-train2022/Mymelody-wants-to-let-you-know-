package input;

public class RegisterInput {

	private String name;
	private String detail;
	private String dateKigen;
	private String timeKigen;
	private String needmail;
	private String dateMailtime;
	private String timeMailtime;
	private String regular;
	private String month;
	private String day;
	private String hour;
	private String min;

	public boolean equals(RegisterInput alt) {
		if (alt == null) {
			return false;
		}
		if (this.name.equals(alt.getName()) && this.detail.equals(alt.getDetail())
				&& this.dateKigen.equals(alt.getDateKigen()) && this.timeKigen.equals(alt.getTimeKigen())
				&& this.needmail.equals(alt.getNeedmail()) && this.dateMailtime.equals(alt.getDateMailtime())
				&& this.timeMailtime.equals(alt.getTimeMailtime()) && this.regular.equals(alt.getRegular())
				&& this.month.equals(alt.getMonth()) && this.day.equals(alt.getDay()) && this.hour.equals(alt.getHour())
				&& this.min.equals(alt.getMin())) {
			return true;
		}
		return false;
	}

	public RegisterInput(String name, String detail, String dateKigen, String timeKigen, String needmail,
			String dateMailtime, String timeMailtime, String regular, String month, String day, String hour,
			String min) {
		super();
		this.name = nulltoEmpty(name);
		this.detail = nulltoEmpty(detail);
		this.dateKigen = nulltoEmpty(dateKigen);
		this.timeKigen = nulltoEmpty(timeKigen);
		this.needmail = nulltoEmpty(needmail);
		this.dateMailtime = nulltoEmpty(dateMailtime);
		this.timeMailtime = nulltoEmpty(timeMailtime);
		this.regular = nulltoEmpty(regular);
		this.month = nulltoEmpty(month);
		this.day = nulltoEmpty(day);
		this.hour = nulltoEmpty(hour);
		this.min = nulltoEmpty(min);
	}

	public String nulltoEmpty(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDateKigen() {
		return dateKigen;
	}

	public void setDateKigen(String dateKigen) {
		this.dateKigen = dateKigen;
	}

	public String getTimeKigen() {
		return timeKigen;
	}

	public void setTimeKigen(String timeKigen) {
		this.timeKigen = timeKigen;
	}

	public String getNeedmail() {
		return needmail;
	}

	public void setNeedmail(String needmail) {
		this.needmail = needmail;
	}

	public String getDateMailtime() {
		return dateMailtime;
	}

	public void setDateMailtime(String dateMailtime) {
		this.dateMailtime = dateMailtime;
	}

	public String getTimeMailtime() {
		return timeMailtime;
	}

	public void setTimeMailtime(String timeMailtime) {
		this.timeMailtime = timeMailtime;
	}

	public String getRegular() {
		return regular;
	}

	public void setRegular(String regular) {
		this.regular = regular;
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

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	@Override
	public String toString() {
		return "RegisterInput [name=" + name + ", detail=" + detail + ", dateKigen=" + dateKigen + ", timeKigen="
				+ timeKigen + ", needmail=" + needmail + ", dateMailtime=" + dateMailtime + ", timeMailtime="
				+ timeMailtime + ", regular=" + regular + ", month=" + month + ", day=" + day + ", hour=" + hour
				+ ", min=" + min + "]";
	}
}
