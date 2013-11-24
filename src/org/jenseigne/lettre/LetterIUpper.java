package org.jenseigne.lettre;

public class LetterIUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'I';
	}

	@Override
	public void initListGraphic() {
		append(new Line(UPPER_MIDDLE, BOTTOM_MIDDLE));

	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
