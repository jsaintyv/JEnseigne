package org.jenseigne.lettre;

public class LetterCUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'C';
	}

	@Override
	public void initListGraphic() {
		append(new Arc(ASymbol.MIDDLE, (float) 0.4f, -3.14f / 4f,
				-3.14f * 7 / 4f));

	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
