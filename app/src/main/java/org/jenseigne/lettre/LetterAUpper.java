package org.jenseigne.lettre;

public class LetterAUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'A';
	}

	@Override
	public void initListGraphic() {
		append(new Line(UPPER_MIDDLE, BOTTOM_RIGHT_CORNER));
		append(new DrawJump());
		append(new Line(UPPER_MIDDLE, BOTTOM_LEFT_CORNER));
		append(new DrawJump());
		append(new Line(MIDDLE_RIGHT_MIDDLE, MIDDLE_LEFT_MIDDLE));

	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
