package org.jenseigne.dictionnaire;

public class MotIllustration {
	private String mot;
	private String imageName;

	public MotIllustration(String mot2, String image) {
		this.mot = mot2;
		this.imageName = image;
	}

	public String getMot() {
		return mot;
	}

	public void setMot(String mot) {
		this.mot = mot;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}
