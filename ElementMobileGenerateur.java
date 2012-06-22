import java.util.ArrayList;
import java.util.Random;


/**
 * Classe generant des vehicules standards, urgents ainsi que des pietons.
 *
 */
public class ElementMobileGenerateur extends Thread {

	/* frequence de creation des vehicules */
	protected static double frequence_vehicule = 0.6;
	/* frequence de creation des vehicules urgents */
	protected static double frequence_vehicule_urgent = 0.03;
	/* frequence de creation des pietons */
	protected static double frequence_pieton = 0.0001;
	
	public ArrayList<Coordonnee> tabCoord;
	public ArrayList<Direction> tabDirection;
	



	public ElementMobileGenerateur() {
		tabCoord= new ArrayList<Coordonnee>();
		tabDirection= new ArrayList<Direction>();
		/* On ajoute tout les routes qui entrent dans la map comme des points d'entree */
		ajouterToutPointEntree();
	}



	/**
	 * Permet d'ajouter un point d'entree au generateur
	 * @param coo les coordonnees de ce point d'entree
	 * @param dir la direction de ce point d'entree
	 */
	private void ajouterPointEntree(Coordonnee coo, Direction dir) {
		tabCoord.add(coo);
		tabDirection.add(dir);
	}


	private void ajouterToutPointEntree() {

		int l=0,c=0;
		
		/* alias */
		Map map = CityMove.map;
		
		/* Parcours du cote haut */
		for( c = 0 ; c < map.nbColonnes ; c++) {
			/* Si on tombe sur une route sud, c'est une route qui entre (comme on est en haut de la map)
			 * => on l'ajoute */
			if(map.tabMapElement[0][c].myBackgroundElement==BackgroundElement.ROUTE_SUD) {
				ajouterPointEntree(new Coordonnee(c,l),Direction.SUD);
			}
		}


		/* Parcours bord droit et gauche */
		/* cg : cote gauche, cd : cote droit */
		int cg = 0, cd = map.nbColonnes-1;
		/* On commence a un, les 0 sont deja traites dans la boucle precedente */
		for(l=1;l<map.nbLignes-1;l++) {
			/* si on trouve des entrees, on les ajoute */
			if(map.tabMapElement[l][cg].myBackgroundElement==BackgroundElement.ROUTE_EST) {
				ajouterPointEntree(new Coordonnee(cg,l),Direction.EST);
			}
			if(map.tabMapElement[l][cd].myBackgroundElement==BackgroundElement.ROUTE_OUEST) {
				ajouterPointEntree(new Coordonnee(cd,l),Direction.OUEST);
			}
		}

		/* Parcours bord bas: de meme */
		l=map.nbLignes-1;
		for( c = 0 ; c < map.nbColonnes ; c++) {
			if(map.tabMapElement[l][c].myBackgroundElement==BackgroundElement.ROUTE_NORD) {
				ajouterPointEntree(new Coordonnee(c,l),Direction.NORD);
			}
		}
	}



	public void run() {
		Random generator = new Random();
		while(true) {
			/* Pour chacuns des points d'entrees qu'on a stocke en parcourant la map */
			for(int i = 0 ; i < tabCoord.size() ; i++) {
				Direction dir = tabDirection.get(i);
				/* Permet de ne pas ajouter deux elements sur la meme case, dans la meme iteration */
				boolean elemDejaAjoute=false;
				
				/* Si pas de element mobile a cet endroit */
				if(!tabCoord.get(i).verifierElementMobile()) {
					/* On tire au hasard si on ajoute un vehicule urgent */
					if( generator.nextDouble()<frequence_vehicule_urgent)
					{
						CityMove.map.addElementMobile(new VoitureUrgent(tabCoord.get(i).getX()*CityMove.map.sizeElement,tabCoord.get(i).getY()*CityMove.map.sizeElement,dir));
						elemDejaAjoute = true;
					}
				}
				
				/* Si rien n'a ete ajoute et qu'il y a la place de dispo: */
				if(!elemDejaAjoute && !tabCoord.get(i).verifierElementMobile())  {			
					if(generator.nextDouble()<frequence_vehicule) {
						CityMove.map.addElementMobile(new Voiture(tabCoord.get(i).getX()*CityMove.map.sizeElement,tabCoord.get(i).getY()*CityMove.map.sizeElement,dir));	
						
					}
				}
					
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}
}



