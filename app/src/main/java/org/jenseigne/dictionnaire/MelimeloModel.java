package org.jenseigne.dictionnaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import android.graphics.Color;
import android.graphics.Point;

public class MelimeloModel {
	public static final char CHAR_FREE = ' ';
	public static final int DIRECTION_GAUCHE_DROITE = 0;
	public static final char DIRECTION_HAUT_BAS = 1;

	public static final int[] listColor = new int[] { Color.BLUE, Color.RED,
			Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA };

	private int largeur;
	private int hauteur;

	private int score = 0;

	private long chronoStart = System.currentTimeMillis();

	private char[] grid;

	public class MotPosition {
		private MotIllustration mot;
		private Point position;
		private int direction;
		private boolean found = false;
		private int color;

		public MotIllustration getMotIllustre() {
			return mot;
		}

		public void setMot(MotIllustration mot) {
			this.mot = mot;
		}

		public Point getPosition() {
			return position;
		}

		public void setPosition(Point position) {
			this.position = position;
		}

		public int getDirection() {
			return direction;
		}

		public void setDirection(int direction) {
			this.direction = direction;
		}

		public boolean isFound() {
			return found;
		}

		public void setFound(boolean found) {
			this.found = found;
		}

		public int getColor() {
			return color;
		}

		public void setColor(int color) {
			this.color = color;
		}

		public Point getEnd() {
			Point end = new Point(position);
			if (direction == DIRECTION_GAUCHE_DROITE) {
				end.x += (mot.getMot().length() - 1);
			} else {
				end.y += (mot.getMot().length() - 1);
			}
			return end;
		}
	}

	private List<MotPosition> listeDeMots;

	public List<MotPosition> getListeDeMots() {
		return listeDeMots;
	}

	public void setListeDeMots(List<MotPosition> listeDeMots) {
		this.listeDeMots = listeDeMots;
	}

	public MelimeloModel() {
		this.largeur = 9;
		this.hauteur = 9;
		init();
	}

	public MelimeloModel(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		init();
	}

	public void init() {

		score = 0;
		this.grid = new char[largeur * hauteur];
		for (int x = 0; x < grid.length; x++) {
			grid[x] = CHAR_FREE;
		}

		listeDeMots = new ArrayList<MotPosition>();

		Iterator<MotIllustration> itMot = Dictionnaire.instance().iterator();
		while (itMot.hasNext()) {

			MotIllustration motIllustration = itMot.next();
			boolean found = foundPosition(motIllustration, 6);
			if (found == false)
				found = foundPosition(motIllustration, 3);
			if (found == false)
				found = foundPosition(motIllustration, 0);
		}

		Collections.shuffle(listeDeMots);
		
		debug();
		chronoStart = System.currentTimeMillis();
	}

	private boolean foundPosition(MotIllustration motIllustration,
			int targetScore) {
		int beginIndex = 0;
		char premiereLettre = motIllustration.getMot().charAt(0);
		Point p = null;
		boolean found = false;
		while (found == false
				&& (p = foundNextCharOrFree(beginIndex, premiereLettre)) != null) {
			found = testOrientations(p, motIllustration, targetScore);

			if (found == false) {
				beginIndex = p.x + p.y * largeur + 1;
			}
		}
		return found;
	}

	private void debug() {
		for (int x = 0; x < largeur * hauteur; x++) {
			System.out.print("" + grid[x]);
			if (x % largeur == 0)
				System.out.println("");
		}
		System.out.println("");

		for (MotPosition s : listeDeMots) {
			System.out.println("mot:" + s.mot.getMot());
		}
	}

	private boolean testOrientations(Point p, MotIllustration motIllustration,
			int targetScore) {
		boolean placeOk;
		// Gauche -> Droite
		if ((p.x + motIllustration.getMot().length()) < largeur) {
			int cIndex = 0;
			placeOk = true;
			int score = 0;
			while (placeOk && cIndex < motIllustration.getMot().length()) {
				char cFound = getCharAt(p.x + cIndex, p.y);
				if (cFound == motIllustration.getMot().charAt(cIndex)
						|| cFound == CHAR_FREE) {
					if (cFound != CHAR_FREE)
						score += 3;
					placeOk = true;
				} else {
					placeOk = false;
				}
				cIndex += 1;
			}

			if (placeOk == true && (score >= targetScore)) {
				writeGaucheDroite(p, motIllustration);
				return true;
			}
		}

		// Haut -> Bas
		if ((p.y + motIllustration.getMot().length()) < hauteur) {
			int cIndex = 0;
			placeOk = true;
			int score = 0;
			while (placeOk && cIndex < motIllustration.getMot().length()) {
				char cFound = getCharAt(p.x, p.y + cIndex);
				if (cFound == motIllustration.getMot().charAt(cIndex)
						|| cFound == CHAR_FREE) {
					if (cFound != CHAR_FREE)
						score += 3;
					placeOk = true;
				} else {
					placeOk = false;
				}
				cIndex += 1;
			}

			if (placeOk == true && (score >= targetScore)) {
				writeHautBas(p, motIllustration);
				return true;
			}
		}

		return false;
	}

	private void writeHautBas(Point p, MotIllustration motIllustration) {
		int cIndex = 0;
		boolean placeOk = true;
		while (placeOk && cIndex < motIllustration.getMot().length()) {
			setCharAt(p.x, p.y + cIndex, motIllustration.getMot()
					.charAt(cIndex));
			cIndex += 1;
		}

		addMot(p, motIllustration, DIRECTION_HAUT_BAS);

	}

	private void setCharAt(int x, int y, char c) {
		grid[x + y * largeur] = c;

	}

	private void writeGaucheDroite(Point p, MotIllustration motIllustration) {
		int cIndex = 0;
		boolean placeOk = true;
		while (placeOk && cIndex < motIllustration.getMot().length()) {
			setCharAt(p.x + cIndex, p.y, motIllustration.getMot()
					.charAt(cIndex));
			cIndex += 1;
		}

		addMot(p, motIllustration, DIRECTION_GAUCHE_DROITE);
	}

	private void addMot(Point p, MotIllustration motIllustration, int direction) {
		MotPosition motPosition = new MotPosition();
		motPosition.mot = motIllustration;
		motPosition.position = new Point(p);
		motPosition.direction = direction;
		try {
			motPosition.color = listColor[(int) (listColor.length * Math
					.random())];
		} catch (Throwable th) {
			motPosition.color = Color.BLACK;
		}

		listeDeMots.add(motPosition);
	}

	public Point foundNextCharOrFree(int beginIndex, char c) {

		for (int x = beginIndex; x < grid.length; x++) {
			if (grid[x] == c || grid[x] == CHAR_FREE)
				return new Point(x % largeur, x / largeur);
		}
		return null;
	}

	public char getCharAt(int x, int y) {
		if (y > hauteur) {
			throw new Error("hors de la grille(" + x + "," + y + ")");
		}

		if (x > hauteur) {
			throw new Error("hors de la grille(" + x + "," + y + ")");
		}
		return grid[x + y * largeur];
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public MotPosition getNext() {

		Iterator<MotPosition> itMots = listeDeMots.iterator();
		while (itMots.hasNext()) {
			MelimeloModel.MotPosition motPosition = (MelimeloModel.MotPosition) itMots
					.next();
			if (motPosition.isFound() == false) {
				return motPosition;
			}
		}

		// Partie terminé

		score = (int) (listeDeMots.size() * 30 - getTime());
		return null;
	}

	public String getWord(Point begin, Point end) {
		StringBuffer sb = new StringBuffer();
		if (begin.x == end.x) {
			for (int y = begin.y; y <= end.y; y++) {
				sb.append(getCharAt(begin.x, y));
			}
		}

		if (begin.y == end.y) {
			for (int x = begin.x; x <= end.x; x++) {
				sb.append(getCharAt(x, begin.y));
			}
		}
		return sb.toString();
	}

	public long getScore() {
		return score;
	}

	public long getTime() {
		return (long) (System.currentTimeMillis() - chronoStart) / 1000;
	}
}
