package org.jenseigne.lettre;


public class LetterLUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'L';
	}

	@Override
	public void initListGraphic() {
		append(new Line(UPPER_RIGHT_CORNER, BOTTOM_RIGHT_CORNER));
		append(new Line(BOTTOM_RIGHT_CORNER, BOTTOM_LEFT_CORNER));
	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
