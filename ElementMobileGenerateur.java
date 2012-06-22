import java.util.ArrayList;
import java.util.Random;


/**
 * Classe générant des véhicules standards, urgents ainsi que des piétons.
 *
 */
public class ElementMobileGenerateur extends Thread {

	protected static double frequence_vehicule = 1;
	protected static double frequence_vehicule_urgent = 0.02;
	protected static double frequence_pieton = 0.0001;
	
	public ArrayList<Coordonnee> tabCoord;
	public ArrayList<Direction> tabDirection;
	



	public ElementMobileGenerateur() {
		tabCoord= new ArrayList<Coordonnee>();
		tabDirection= new ArrayList<Direction>();
		ajouterToutPointEntree();
	}



	/**
	 * Permet d'ajouter un point d'entree au générateur
	 * @param coo les coordonnees de ce point d'entree
	 * @param dir la direction de ce point d'entree
	 */
	private void ajouterPointEntree(Coordonnee coo, Direction dir) {
		tabCoord.add(coo);
		tabDirection.add(dir);
	}


	private void ajouterToutPointEntree() {

		int l=0,c=0;
		Map map = CityMove.map;
		/* Parcours du coté haut */
		for( c = 0 ; c < map.nbColonnes ; c++) {
			if(map.tabMapElement[0][c].myBackgroundElement==BackgroundElement.ROUTE_SUD) {
				ajouterPointEntree(new Coordonnee(c,l),Direction.SUD);
			}
		}


		/* Parcours bord droit et gauche */
		int cg = 0, cd = map.nbColonnes-1;
		for(l=1;l<map.nbLignes-1;l++) {
			if(map.tabMapElement[l][cg].myBackgroundElement==BackgroundElement.ROUTE_EST) {
				ajouterPointEntree(new Coordonnee(cg,l),Direction.EST);
			}
			if(map.tabMapElement[l][cd].myBackgroundElement==BackgroundElement.ROUTE_OUEST) {
				ajouterPointEntree(new Coordonnee(cd,l),Direction.OUEST);
			}
		}

		/* Parcours bord bas */
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
			/* Pour chacuns des points d'entrée */
			for(int i = 0 ; i < tabCoord.size() ; i++) {
				Direction dir = tabDirection.get(i);
				if(generator.nextDouble()<frequence_vehicule) {
					//TODO: test pour ne pas ajouter de voiure si il y ena  deja une à cet endroit..
					//TODO : modif pour faire apparaitre les voituures une case avant (pour effet d'arriver et pas teleportation ;))
					
					CityMove.map.addElementMobile(new Voiture(tabCoord.get(i).getX()*CityMove.map.sizeElement,tabCoord.get(i).getY()*CityMove.map.sizeElement,dir));	

				}
				
						
				if(generator.nextDouble()<frequence_vehicule_urgent)
				{
				
					//System.out.println("Ajout d'une voiture urgente en "+tabCoord.get(i).getX()+":"+tabCoord.get(i).getY());
					CityMove.map.addElementMobile(new VoitureUrgent(tabCoord.get(i).getX()*CityMove.map.sizeElement,tabCoord.get(i).getY()*CityMove.map.sizeElement,dir));
				}
					
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}



