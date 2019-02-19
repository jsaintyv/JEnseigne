package org.jenseigne.dictionnaire;

import java.text.Collator;
import java.util.Locale;

public class MangeMotModel {

	public interface ChangeListener {
		public void onMangeMotModelChange(MangeMotModel model);
	}

	private MotIllustration mot = Dictionnaire.instance().getRandom();

	private long chronoStart = System.currentTimeMillis();

	private int index = 0;

	private int fautesDeFrappe = 0;

	private long score = 0;

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public boolean incScore() {
		this.score += 1;
		if (score == 0)
			return false;
		return (this.score % 5) == 0;
	}

	public boolean decScore() {
		if (score == 0)
			return false;
		this.score -= 2;
		return (this.score % 5) == 0;
	}

	private ChangeListener listener;

	public ChangeListener getListener() {
		return listener;
	}

	public void setListener(ChangeListener listener) {
		this.listener = listener;
	}

	public MotIllustration getMotIllustration() {
		return mot;
	}

	public void setMotIllustration(MotIllustration mot) {
		this.mot = mot;
		this.index = 0;
		listener.onMangeMotModelChange(this);
	}

	public int getFautesDeFrappe() {
		return fautesDeFrappe;
	}

	public void setFautesDeFrappe(int fautesDeFrappe) {
		this.fautesDeFrappe = fautesDeFrappe;
	}

	public boolean incFautes() {
		decScore();
		this.fautesDeFrappe += 1;

		return (fautesDeFrappe == 3);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isNext(String c) {
		if (fin())
			return false;
		Collator compareOperator = Collator.getInstance(Locale.FRENCH);
		compareOperator.setStrength(Collator.PRIMARY);
		if (compareOperator.compare(mot.getMot().substring(index, index + 1)
				.toUpperCase(), c.toUpperCase()) == 0) {
			index += 1;
			return true;
		} else {
			return false;
		}
	}

	public boolean fin() {
		if (index >= mot.getMot().length())
			return true;
		return false;
	}

	public String getMotRestant() {
		return mot.getMot().substring(index, mot.getMot().length());
	}

	public String getMotTape() {
		return mot.getMot().substring(0, index);
	}

	public void start() {
		chronoStart = System.currentTimeMillis();
	}

	public long getTime() {
		return (long) (System.currentTimeMillis() - chronoStart) / 1000;
	}
}
