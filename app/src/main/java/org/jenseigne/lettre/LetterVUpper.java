package org.jenseigne.lettre;


public class LetterVUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'V';
	}

	@Override
	public void initListGraphic() {
		append(new Line(UPPER_RIGHT_CORNER, BOTTOM_MIDDLE));
		append(new DrawJump());
		append(new Line(UPPER_LEFT_CORNER, BOTTOM_MIDDLE));
	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
