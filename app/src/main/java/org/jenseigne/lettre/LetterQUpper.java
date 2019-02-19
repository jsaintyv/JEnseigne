package org.jenseigne.lettre;

public class LetterQUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'Q';
	}

	@Override
	public void initListGraphic() {
		append(new Arc(MIDDLE, MIDDLE.x - LEFT_MIDDLE.x, 3.14f / 2f,
				-3.14f * 3f / 2f));
		append(new DrawJump());
		append(new Line(MIDDLE, BOTTOM_LEFT_CORNER));
	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
