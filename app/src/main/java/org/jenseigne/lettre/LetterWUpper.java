package org.jenseigne.lettre;


public class LetterWUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'W';
	}

	@Override
	public void initListGraphic() {
		append(new Line(UPPER_RIGHT_CORNER, BOTTOM_MIDDLE_RIGHT));
		append(new DrawJump());
		append(new Line(UPPER_MIDDLE, BOTTOM_MIDDLE_RIGHT));
		append(new DrawJump());
		append(new Line(UPPER_MIDDLE, BOTTOM_MIDDLE_LEFT));
		append(new DrawJump());
		append(new Line(UPPER_LEFT_CORNER, BOTTOM_MIDDLE_LEFT));
	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
