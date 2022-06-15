package Vo;

public class PicturesVo {

	private int pictureId;
	private String characterName;
	private String fileName;
	private boolean isPopular;

	public int getPictureId() {
		return pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isPopular() {
		return isPopular;
	}

	public void setPopular(boolean isPopular) {
		this.isPopular = isPopular;
	}

}
