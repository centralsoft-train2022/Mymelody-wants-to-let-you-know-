package Vo;

public class PicturesVo {

	private int pictureId;
	private String path;
	private String characterName;
	private String fileName;
	private int charactertype;

	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

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

	public int getCharactertype() {
		return charactertype;
	}

	public void setCharactertype(int charactertype) {
		this.charactertype = charactertype;
	}

}
