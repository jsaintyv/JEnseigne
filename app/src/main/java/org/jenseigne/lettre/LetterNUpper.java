package org.jenseigne.lettre;

public class LetterNUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'M';
	}

	@Override
	public void initListGraphic() {
		append(new Line(UPPER_RIGHT_CORNER, BOTTOM_RIGHT_CORNER));
		append(new DrawJump());
		append(new Line(UPPER_LEFT_CORNER, BOTTOM_LEFT_CORNER));
		append(new DrawJump());
		append(new Line(UPPER_RIGHT_CORNER, BOTTOM_LEFT_CORNER));

	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
