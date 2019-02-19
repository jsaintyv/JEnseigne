package org.jenseigne.lettre;


public class LetterYUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'Y';
	}

	@Override
	public void initListGraphic() {
		append(new Line(UPPER_RIGHT_CORNER, MIDDLE));
		append(new Line(MIDDLE, BOTTOM_MIDDLE));
		append(new DrawJump());
		append(new Line(UPPER_LEFT_CORNER, MIDDLE));
	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
