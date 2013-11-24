package org.jenseigne.lettre;

public class LetterHUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'H';
	}

	@Override
	public void initListGraphic() {
		append(new Line(UPPER_RIGHT_CORNER, BOTTOM_RIGHT_CORNER));
		append(new DrawJump());
		append(new Line(UPPER_LEFT_CORNER, BOTTOM_LEFT_CORNER));
		append(new DrawJump());
		append(new Line(RIGHT_MIDDLE, LEFT_MIDDLE));

	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
