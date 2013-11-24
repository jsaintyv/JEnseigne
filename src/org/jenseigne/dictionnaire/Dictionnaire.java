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
		listMots.add(create("clé", "cle.png"));
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
		
		listMots.add(create("gland","acorn.png"));		
		listMots.add(create("klaxon","air_horn.png"));
		listMots.add(create("réveil","alarmclock.png"));
		listMots.add(create("alligator","alligator.png"));		
		listMots.add(create("ancre","anchor.png"));
		listMots.add(create("ange","angel.png"));		
		listMots.add(create("la cheville","ankle.png"));
		listMots.add(create("fourmi","ant.png"));
		listMots.add(create("fourmilier","anteater.png"));
		listMots.add(create("antilope","antelope.png"));
		listMots.add(create("pomme","apple.png"));
		listMots.add(create("pommier","apple_tree.png"));
		listMots.add(create("appareil","appliance.png"));
		listMots.add(create("abricot","apricot.png"));
		listMots.add(create("bras","arm.png"));
		listMots.add(create("fauteuil","armchair.png"));
		listMots.add(create("artichaut","artichoke.png"));		
		listMots.add(create("asperge","asparagus.png"));
		listMots.add(create("astronaute","astronaut.png"));				
		listMots.add(create("hache","ax.png"));
		listMots.add(create("biberon","baby_bottle.png"));
		listMots.add(create("dos","back.png"));
		listMots.add(create("décoration","badge.png"));
		listMots.add(create("sac","bag.png"));		
		listMots.add(create("balance","balance.png"));		
		listMots.add(create("balle","ball.png"));
		listMots.add(create("pelote","ball_of_yarn.png"));
		listMots.add(create("ballon de foot","ball_soccer.png"));						
		listMots.add(create("grange","barn.png"));		
		listMots.add(create("bain","bath.png"));
		listMots.add(create("maillot","bathing_suit.png"));
		listMots.add(create("baie","bay.png"));
		listMots.add(create("plage","beach.png"));
		listMots.add(create("bulle","bead.png"));
		listMots.add(create("haricot","bean.png"));
		listMots.add(create("ours","bear.png"));
		listMots.add(create("barbe","beard.png"));		
		listMots.add(create("castor","beaver.png"));
		listMots.add(create("lit","bed.png"));
		listMots.add(create("chambre","bedroom.png"));
		listMots.add(create("abeille","bee.png"));		
		listMots.add(create("scarabée","beetle.png"));		
		listMots.add(create("cloche","bell.png"));
		listMots.add(create("ventre","belly.png"));
		listMots.add(create("banc","bench.png"));
		listMots.add(create("bavoir","bib.png"));
		listMots.add(create("grand","big.png"));
		listMots.add(create("chapiteau","big_top.png"));
		listMots.add(create("vélo","bike.png"));
		listMots.add(create("oiseau","bird.png"));
		listMots.add(create("morceau","bit.png"));		
		listMots.add(create("noir","black.png"));
		listMots.add(create("mûre","blackberry.png"));
		listMots.add(create("merle","blackbird.png"));
		listMots.add(create("lame","blade.png"));		
		listMots.add(create("un bloc","block.png"));
		listMots.add(create("blond","blond.png"));
		listMots.add(create("bleu","blue.png"));
		listMots.add(create("myrtille","blueberry.png"));
		listMots.add(create("rougir","blush.png"));
		listMots.add(create("planche","board.png"));
		listMots.add(create("barque","boat.png"));		
		listMots.add(create("boulon","bolt.png"));
		listMots.add(create("bombe","bomb.png"));
		listMots.add(create("os","bone.png"));
		listMots.add(create("livre","book.png"));
		listMots.add(create("bibliothèque","bookcase.png"));		
		listMots.add(create("boîte","box.png"));		
		listMots.add(create("garçon","boy.png"));		
		listMots.add(create("cerveau","brain.png"));
		listMots.add(create("branche","branch.png"));
		listMots.add(create("pain","bread.png"));				
		listMots.add(create("brique","brick.png"));		
		listMots.add(create("pont","bridge.png"));
		listMots.add(create("pinceau","brush.png"));
		listMots.add(create("bulle","bubble.png"));
		listMots.add(create("seau","bucket.png"));
		listMots.add(create("bourgeon","bud.png"));
		listMots.add(create("buffle","buffalo.png"));
		listMots.add(create("insecte","bug.png"));
		listMots.add(create("ampoule","bulb.png"));
		listMots.add(create("taureau","bull.png"));		
		listMots.add(create("brioche","bun.png"));
		listMots.add(create("bus","bus.png"));
		
		listMots.add(create("chaîne","chain.png"));
		listMots.add(create("chaise","chair.png"));
		listMots.add(create("craie","chalk.png"));
		listMots.add(create("caméléon","chameleon.png"));
				
		listMots.add(create("tissus","cloth.png"));
		listMots.add(create("cintre","clothes_hanger.png"));
		listMots.add(create("nuage","cloud.png"));
		listMots.add(create("nuageux","cloudy.png"));
		listMots.add(create("trèfle","clover.png"));
		listMots.add(create("cow-boy","cowboy.png"));
		listMots.add(create("clown","clown.png"));
		
		listMots.add(create("manteau","coat.png"));
		listMots.add(create("cobra","cobra.png"));
		
		listMots.add(create("maïs","corn.png"));
		listMots.add(create("canapé","couch.png"));
		
		listMots.add(create("vache","cow.png"));
		listMots.add(create("crabe","crab.png"));
		listMots.add(create("berceau","cradle.png"));
		
		listMots.add(create("tanière","den.png"));
		listMots.add(create("désert","desert.png"));
		listMots.add(create("bureau","desk.png"));
		listMots.add(create("dessert","dessert.png"));
		listMots.add(create("diamant","diamond.png"));
		
		
		listMots.add(create("tiroir","drawer.png"));
		listMots.add(create("rêve","dream.png"));
		listMots.add(create("robe","dress.png"));
		listMots.add(create("boisson","drink.png"));
		
		listMots.add(create("goutte","drip.png"));
		
		
		listMots.add(create("tambour","drum.png"));
		listMots.add(create("sécher","dry.png"));
		listMots.add(create("canard","duck.png"));
		listMots.add(create("canne","duck_mother.png"));
		listMots.add(create("dune","dune.png"));
		
		listMots.add(create("aigle","eagle.png"));
		listMots.add(create("oreille","ear.png"));
		listMots.add(create("oeuf","egg.png"));
		listMots.add(create("aubergine","eggplant.png"));

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
