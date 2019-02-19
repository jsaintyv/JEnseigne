package org.jenseigne.lettre;

public class LetterDUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'D';
	}

	@Override
	public void initListGraphic() {

		append(new Line(UPPER_RIGHT_CORNER, BOTTOM_RIGHT_CORNER));
		append(new DrawJump());
		append(new Line(UPPER_RIGHT_CORNER, UPPER_MIDDLE));
		append(new Arc(ASymbol.MIDDLE, (float) (MIDDLE.y - UPPER_MIDDLE.y),
				-3.14f / 2f, 3.14f / 2f));
		append(new Line(BOTTOM_MIDDLE, BOTTOM_RIGHT_CORNER));

	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
