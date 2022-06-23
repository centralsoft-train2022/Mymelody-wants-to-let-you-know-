package bean;

public class PopTestBean {

	private Boolean achievementFlag;
	//期限超えてて達成してないものがあるかのフラグを格納、取得
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getAchievementFlag() {
		return achievementFlag;
	}

	public void setAchievementFlag(Boolean achievementFlag) {
		this.achievementFlag = achievementFlag;
	}

}
