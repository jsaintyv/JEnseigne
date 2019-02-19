package org.jenseigne.lettre;

public class LetterEUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'E';
	}

	@Override
	public void initListGraphic() {

		append(new Line(UPPER_RIGHT_CORNER, BOTTOM_RIGHT_CORNER));
		append(new DrawJump());
		append(new Line(UPPER_RIGHT_CORNER, UPPER_LEFT_CORNER));
		append(new DrawJump());
		append(new Line(RIGHT_MIDDLE, LEFT_MIDDLE));
		append(new DrawJump());
		append(new Line(BOTTOM_RIGHT_CORNER, BOTTOM_LEFT_CORNER));

	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
