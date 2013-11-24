package org.jenseigne.lettre;

public class LetterRUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'R';
	}

	@Override
	public void initListGraphic() {
		append(new Line(UPPER_RIGHT_CORNER, BOTTOM_RIGHT_CORNER));
		append(new DrawJump());
		append(new Line(UPPER_RIGHT_CORNER, UPPER_MIDDLE));
		append(new Arc(ASymbol.MIDDLE_UPPER_MIDDLE, (float) 0.2f, -3.14f / 2f,
				3.14f / 2f));
		append(new Line(MIDDLE, RIGHT_MIDDLE));
		append(new DrawJump());
		append(new Line(MIDDLE, BOTTOM_LEFT_CORNER));
	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
