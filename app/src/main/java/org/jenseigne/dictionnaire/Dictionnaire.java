package org.jenseigne.dictionnaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Dictionnaire {
	private List<MotIllustration> listMots;

	private Dictionnaire() {
		listMots = new ArrayList<MotIllustration>();
		listMots.add(create("zèbre", "zebre.png"));
		listMots.add(create("yaourt", "yaourt.jpeg"));
		listMots.add(create("xylophone", "xylophone.png"));
		listMots.add(create("wagon", "wagon.jpg"));
		listMots.add(create("vis", "vis.png"));
		listMots.add(create("vache", "vache.png"));
		listMots.add(create("usine", "usine.png"));
		listMots.add(create("table", "table.jpeg"));
		listMots.add(create("télévision", "television.png"));
		listMots.add(create("serpent", "serpent.png"));
		listMots.add(create("rose", "rose.png"));
		listMots.add(create("requin", "requin.png"));
		listMots.add(create("rhinocéros", "rhino.png"));
		listMots.add(create("raisin", "raisin.png"));
		listMots.add(create("quille", "quille.png"));
		listMots.add(create("pingouin", "pingouin.png"));
		listMots.add(create("pomme", "pomme.png"));
		listMots.add(create("papillon", "pappillon.png"));
		listMots.add(create("poisson", "poisson.png"));
		listMots.add(create("porte", "porte.png"));
		listMots.add(create("poire", "poire.png"));
		listMots.add(create("ours", "ours.png"));
		listMots.add(create("oiseau", "oiseau.png"));
		listMots.add(create("nid", "nid.jpg"));
		listMots.add(create("meduse", "meduse.png"));
		listMots.add(create("marteau", "marteau.png"));
		listMots.add(create("lune", "lune.png"));
		listMots.add(create("lion", "lion.png"));
		listMots.add(create("kangourou", "kangourou.png"));
		listMots.add(create("jambon", "jambon.png"));
		listMots.add(create("île", "ile.png"));
		listMots.add(create("hippopotame", "hippopotame.png"));
		listMots.add(create("gorille", "gorille.png"));
		listMots.add(create("grenouille", "grenouille.png"));
		listMots.add(create("fraise", "fraise.png"));
		listMots.add(create("fourchette", "fourchette.png"));
		listMots.add(create("fourmi", "fourmie.png"));
		listMots.add(create("fleur", "fleur.png"));
		listMots.add(create("etoile", "etoile.png"));
		listMots.add(create("éléphant", "elephant.png"));
		listMots.add(create("dauphin", "dauphin.png"));
		listMots.add(create("drapeau", "drapeau.png"));
		listMots.add(create("coeur", "coeur.png"));
		listMots.add(create("coccinelle", "coccinelle.png"));
		listMots.add(create("cle", "cle.png"));
		listMots.add(create("citron", "citron.png"));
		listMots.add(create("chien", "chien.png"));
		listMots.add(create("chaise", "chaise.png"));
		listMots.add(create("chat", "chat.png"));
		listMots.add(create("cerise", "cerise.png"));
		listMots.add(create("bateau", "bateau.png"));
		listMots.add(create("balai", "balai.png"));
		listMots.add(create("arbre", "arbre.png"));
		listMots.add(create("arc", "arc.png"));
		listMots.add(create("un", "un.png"));
		listMots.add(create("deux", "deux.png"));
		listMots.add(create("trois", "trois.png"));
		listMots.add(create("quatre", "quatre.png"));
		listMots.add(create("cinq", "cinq.png"));
		listMots.add(create("six", "six.png"));
		listMots.add(create("sept", "sept.png"));
		listMots.add(create("huit", "huit.png"));
		listMots.add(create("neuf", "neuf.png"));
		listMots.add(create("dix", "dix.png"));
		listMots.add(create("onze", "onze.png"));
		listMots.add(create("douze", "douze.png"));
		listMots.add(create("treize", "treize.png"));
		listMots.add(create("quatorze", "quatorze.png"));
		listMots.add(create("quinze", "quinze.png"));
		listMots.add(create("seize", "seize.png"));
		listMots.add(create("vingt", "vingt.png"));
		listMots.add(create("trente", "trente.png"));
		listMots.add(create("quarante", "quarante.png"));
		listMots.add(create("cinquante", "cinquante.png"));
		listMots.add(create("soixante", "soixante.png"));
		listMots.add(create("cent", "cent.png"));
		listMots.add(create("mille", "mille.png"));
		listMots.add(create("million", "million.png"));

	}

	public static Dictionnaire theInstance = new Dictionnaire();

	public static Dictionnaire instance() {
		return theInstance;
	}

	public MotIllustration create(String mot, String image) {
		return new MotIllustration(mot, image);
	}

	public MotIllustration getRandom() {

		return listMots.get((int) (Math.random() * listMots.size()));
	}

	public Iterator<MotIllustration> iterator() {
		List<MotIllustration> list = new ArrayList<MotIllustration>(listMots);
		Collections.shuffle(list);
		return list.iterator();

	}
}
