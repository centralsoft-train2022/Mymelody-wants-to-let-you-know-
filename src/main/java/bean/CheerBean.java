package bean;

import java.util.ArrayList;
import java.util.List;

public class CheerBean {
	private List<String> PicturePaths = new ArrayList<String>();

	public String getPicturePath(int index) {
		return PicturePaths.get(index);
	}

	public void addPicturePath(String picturePath) {
		PicturePaths.add(picturePath);
	}
}
