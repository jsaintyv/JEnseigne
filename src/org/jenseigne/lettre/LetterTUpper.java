package org.jenseigne.lettre;

public class LetterTUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'T';
	}

	@Override
	public void initListGraphic() {

		append(new Line(UPPER_RIGHT_CORNER, UPPER_LEFT_CORNER));
		append(new DrawJump());
		append(new Line(UPPER_MIDDLE, BOTTOM_MIDDLE));

	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
