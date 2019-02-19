package org.jenseigne.lettre;

public class LetterOUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'O';
	}

	@Override
	public void initListGraphic() {
		append(new Arc(MIDDLE, MIDDLE.x - LEFT_MIDDLE.x, 3.14f / 2f,
				-3.14f * 3f / 2f));
	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
