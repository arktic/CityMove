import java.util.ArrayList;
import java.util.Random;


/**
 * Classe générant des véhicules standards, urgents ainsi que des piétons.
 *
 */
public class ElementMobileGenerateur extends Thread {

	protected static double frequence_vehicule = 1;
	protected static double frequence_vehicule_urgent = 1/1000;
	protected static double frequence_pieton = 1/1000;
	private static double default_coef = frequence_vehicule;
	
	public ArrayList<Coordonnee> tabCoord;
	public ArrayList<Direction> tabDirection;
	private ArrayList<Double> tabCoef;
	
	
	
	
	public ElementMobileGenerateur() {
		tabCoord= new ArrayList<Coordonnee>();
		tabDirection= new ArrayList<Direction>();
		tabCoef= new ArrayList<Double>();
		ajouterToutPointEntree();
	}
	
	
	/**
	 * Permet d'ajouter un point d'entree au générateur
	 * @param coo les coordonnees de ce point d'entree
	 * @param dir la direction de ce point d'entree
	 * @param coef : le coefficient de génération de voiture à cet endroit
	 */
	private void ajouterPointEntree(Coordonnee coo, Direction dir,double coef) {
		System.out.println("Ajout de "+coo+" en direction de "+dir);
		tabCoord.add(coo);
		tabDirection.add(dir);
		tabCoef.add(coef);
	}
	
	
	/**
	 * Permet d'ajouter un point d'entree au générateur
	 * @param coo les coordonnees de ce point d'entree
	 * @param dir la direction de ce point d'entree
	 */
	private void ajouterPointEntree(Coordonnee coo, Direction dir) {
		System.out.println("Ajout de "+coo+" en direction de "+dir);
		tabCoord.add(coo);
		tabDirection.add(dir);
		tabCoef.add(default_coef);
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
		System.out.println("Lancement du generateur...");
		while(true) {
			/* Pour chacuns des points d'entrée */
			for(int i = 0 ; i < tabCoord.size() ; i++) {
				if(generator.nextDouble()<tabCoef.get(i)) {
					CityMove.map.addElementMobile(new Voiture(tabCoord.get(i).getX(),tabCoord.get(i).getY(),tabDirection.get(i))); 
				}
			}
			
		}
	}
	
	
	
	
	
	
	
}
