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
	 * Renvoie la position en TUILES de la prochaine position d'un élément mobile
	 * @param dir : la direction courante 
	 * @return
	 */
	public Coordonnee getNextTilesPosition(Direction dir) {
		/* On recupere notre position courante, en tuiles, sur la map */
		Coordonnee maMapPosition = map.getPositionInTiles();

		/* On renvoie la tuile suivante, qui depend de notre position et de notre diretion */
		switch (dir){
		case NORD :
			return new Coordonnee(maMapPosition.getX(), maMapPosition.getY()-1);
			break;
		case SUD :
			return new Coordonnee(maMapPosition.getX(), maMapPosition.getY()+1);
			break;
		case EST :
			return new Coordonnee(maMapPosition.getX()+1, maMapPosition.getY());
			break;
		case OUEST :
			return new Coordonnee(maMapPosition.getX()-1, maMapPosition.getY());
			break;
		default : // Si ce n'est aucun de ceux la, on renvoie la position courante (immobile)
			return new Coordonnee(maMapPosition.getX(), maMapPosition.getY());
		}
	}


}