package org.jenseigne.lettre;

public class LetterSUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'S';
	}

	@Override
	public void initListGraphic() {
		append(new Arc(ASymbol.MIDDLE_UPPER_MIDDLE, (float) 0.2f, -3.14f / 4f,
				-3f * 3.14f / 2f));
		append(new Arc(ASymbol.MIDDLE_BOTTOM_MIDDLE, (float) 0.2f, -3.14f / 2f,
				3f * 3.14f / 4f));

	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
