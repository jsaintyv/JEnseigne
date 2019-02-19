package org.jenseigne.lettre;

public class LetterGUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'G';
	}

	@Override
	public void initListGraphic() {
		append(new Arc(ASymbol.MIDDLE, (float) 0.4f, -3.14f / 4f,
				-3.14f * 8 / 4f));
		append(new DrawJump());
		append(new Line(MIDDLE, LEFT_MIDDLE));

	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
