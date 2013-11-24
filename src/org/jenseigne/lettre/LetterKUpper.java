package org.jenseigne.lettre;


public class LetterKUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'K';
	}

	@Override
	public void initListGraphic() {
		append(new Line(UPPER_RIGHT_CORNER, BOTTOM_RIGHT_CORNER));
		append(new DrawJump());
		append(new Line(UPPER_LEFT_CORNER, RIGHT_MIDDLE));
		append(new DrawJump());
		append(new Line(RIGHT_MIDDLE, BOTTOM_LEFT_CORNER));

	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
