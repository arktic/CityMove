import java.util.ArrayList;
import java.util.Random;

public abstract class ElementMobiles extends Elements {

	protected Coordonnee vitesse;

	
	public int seDeplacer() {
		return 0;
	}

	public int stoper() {
		vitesse.set(0, 0);
		return 0;
	}

	public abstract int verifierDeplacement(Direction d);



	/**
	 * Renvoie la position en TUILES de la prochaine position d'un element mobile
	 * @param dir : la direction courante 
	 * @return
	 */
	public Coordonnee getNextTilesPosition(Direction dir) {
		/* On recupere notre position courante, en tuiles, sur la map */
		Coordonnee maMapPosition = Application.map.getPositionInTiles(this.position);

		
		/* On renvoie la tuile suivante, qui depend de notre position et de notre diretion */
		switch (dir){
		case NORD :
			return new Coordonnee(maMapPosition.getX(), maMapPosition.getY()-1);
		case SUD :
			return new Coordonnee(maMapPosition.getX(), maMapPosition.getY()+1);
		case EST :
			return new Coordonnee(maMapPosition.getX()+1, maMapPosition.getY());
		case OUEST :
			return new Coordonnee(maMapPosition.getX()-1, maMapPosition.getY());
		default : // Si ce n'est aucun de ceux la, on renvoie la position courante (immobile)
			return new Coordonnee(maMapPosition.getX(), maMapPosition.getY());
		}
	}
	
	/**
	 * Choisit aleatoirement une direction parmis celles qui lui sont proposees
	 * @return
	 */
	public Direction choixDeplacement() {
		Direction maDirection = Direction.AUCUNE;
		
		/* Je recupere mon mapElement correspondant a mon ElementMobile */
		Coordonnee posInTiles = Application.map.getPositionInTiles(position);
		MapElement monMapElement = Application.map.getMapElement(posInTiles);
		
		/* Je recupere le tableau de possibilites de ce mapElement puis je stock la taille du tableau */
		ArrayList<Direction> tab = monMapElement.getPossibilities();
		int taille = tab.size();
		
		/* Je genere un nommbre aleatoire compris entre 0 et la taile de mon tableau -> c'est la que ce fait le choix */
		Random generator = new Random();
		int choix = generator.nextInt(taille);
		
		maDirection = tab.get(choix);
		
		return maDirection;
	}

}