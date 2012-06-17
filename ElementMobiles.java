import java.util.ArrayList;
import java.util.Random;

public abstract class ElementMobiles extends Elements {

	protected int vitesse;
	protected Direction direction;

	
	public int seDeplacer() {
		return 0;
	}

	public int stoper() {
		vitesse = 0;
		return 0;
	}

	public abstract int verifierDeplacement(Direction d);
	
	/**
	 * 
	 * @param nbTailes
	 * @param direction
	 * @return La case situé plusieurs cases en avance de la case actuelle
	 */
	public Coordonnee getNextTilesPosition(int nbTailes, Direction direction) {
		/* On recupere notre position courante, en tuiles, sur la map */
		Coordonnee maMapPosition = Application.map.getPositionInTiles(this.position);

		
		/* On renvoie la tuile suivante, qui depend de notre position et de notre diretion */
		switch (direction){
		case NORD :
			return new Coordonnee(maMapPosition.getX(), maMapPosition.getY()-nbTailes);
		case SUD :
			return new Coordonnee(maMapPosition.getX(), maMapPosition.getY()+nbTailes);
		case EST :
			return new Coordonnee(maMapPosition.getX()+nbTailes, maMapPosition.getY());
		case OUEST :
			return new Coordonnee(maMapPosition.getX()-nbTailes, maMapPosition.getY());
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