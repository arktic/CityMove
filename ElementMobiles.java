import java.util.ArrayList;
import java.util.Random;


public abstract class ElementMobiles extends Elements {
/*----- Attributs -----*/
	protected int vitesse;
	protected Direction direction;
	
	
	
	public ElementMobiles(int posx, int posy,Direction dir) {
		super(posx,posy);
		vitesse=10;
		direction=dir;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public int seDeplacer() { 
		Direction maDirection = choixDeplacement();
		
		if(verifierDeplacement(maDirection)) {
			Coordonnee mapPosition = CityMove.map.getPositionInTiles(getPosition());
			
			
			Coordonnee newMapPosition;
			MapElement monMapElement = CityMove.map.getMapElement(mapPosition);
			MapElement monNewMapElement;
			TypeMobileElement myTypeMobileElement = monMapElement.getMyTypeMobileElement();
			
			setPosition(getNextPosition(maDirection));
			newMapPosition = CityMove.map.getPositionInTiles(getPosition());
			monNewMapElement = CityMove.map.getMapElement(newMapPosition);
			monNewMapElement.setMyTypeMobileElement(myTypeMobileElement);
			
			
						
			if(doitLibererCaseDerriere()) {
				monMapElement.setMyTypeMobileElement(TypeMobileElement.VIDE);
			}
		}
		else {
			stoper();
		}
		
		return 0;
	}

	
	
	
	/**
	 * renvoi vrai si nous avons atteint le bout de notre case courante, donnc qu'on vient de lib�rer la case d'avant
	 * Note: on suppose ici que tout nos �l�ments mobiles occupent une case
	 * @return
	 */
	private boolean doitLibererCaseDerriere() {
		
		/* On determine si on est encore sur la case derriere */
		
		switch (direction) {
		case NORD:
			return (position.getY()% (CityMove.map.sizeElement)==0);
		case SUD:
			return (position.getY()% (CityMove.map.sizeElement)==0);
		case EST:
			return (position.getX()% (CityMove.map.sizeElement)==0);
		case OUEST:
			return (position.getX()% (CityMove.map.sizeElement)==0);
		default:
			return false;
		}
	}





	public int stoper() {
		vitesse = 0;
		return 0;
	}
	
	/**
	 * 
	 * @param Direction vers laquelle l'element veut se deplacer
	 * @return Oui ou Non
	 */
	public abstract boolean verifierDeplacement(Direction d);
	
	/**
	 * 
	 * @param tme : Type d'element mobile a verifier
	 * @param d : direction dans laquele on veut regarder
	 * @return si il y a un element de ce type devant
	 */
	public boolean verifierElementMobile(TypeMobileElement tme, Direction d)
	{
		boolean ElementMobileVerifie = false;
		
		Coordonnee coordonneeVerifiee = getNextTilesPosition(1, d);
		if(coordonneeVerifiee.isOnMap()) {
			MapElement monMapElementVerifie = CityMove.map.getMapElement(coordonneeVerifiee);
			TypeMobileElement monMobileElementVerifie = monMapElementVerifie.getMyTypeMobileElement();
			
			if(monMobileElementVerifie!=null && tme == monMobileElementVerifie)
			{
				ElementMobileVerifie = true;
			}
		}
		
		return ElementMobileVerifie;
	}
	
	/**
	 * 
	 * @param d : Direction dans laquelle on veut verifier la presence d'un feu
	 * @return Vrai ou faux
	 */
	public boolean verifierFeu(Direction d)
	{
		boolean isRouge = false;

		Coordonnee coordonneeVerifiee = getNextTilesPosition(1, d);
		//System.out.println("coord verif: " + coordonneeVerifiee);
		if(coordonneeVerifiee.isOnMap()) {
			MapElement monMapElementVerifie = CityMove.map.getMapElement(coordonneeVerifiee);
			Feu monFeuVerifie = monMapElementVerifie.getMyFeu();
			
			if(monFeuVerifie != null) {
				EtatFeu etatFeu = monFeuVerifie.getEtat();
				if(etatFeu == EtatFeu.ROUGE) {
					isRouge = true;
				}
			//	return isRouge;
			}
			
		
		}
		return isRouge;
	}
	
	public Coordonnee getNextPosition(Direction direction) {
		/* On recupere notre position courante */
		Coordonnee maPosition = this.position;
		
		/* On renvoie les cordonnees suivantes, qui dependent de notre position et de notre diretion */
		switch (direction){
		case NORD :
			return new Coordonnee(maPosition.getX(), maPosition.getY()-1);
		case SUD :
			return new Coordonnee(maPosition.getX(), maPosition.getY()+1);
		case EST :
			return new Coordonnee(maPosition.getX()+1, maPosition.getY());
		case OUEST :
			return new Coordonnee(maPosition.getX()-1, maPosition.getY());
		default : // Si ce n'est aucun de ceux la, on renvoie la position courante (immobile)
			return new Coordonnee(maPosition.getX(), maPosition.getY());
		}
	}
	
	/**
	 * 
	 * @param nbTiles
	 * @param direction
	 * @return La case situ� plusieurs cases en avance de la case actuelle
	 */
	public Coordonnee getNextTilesPosition(int nbTiles, Direction direction) {
		/* On recupere notre position courante, en tuiles, sur la map */
		Coordonnee maMapPosition = CityMove.map.getPositionInTiles(this.position);
		//System.out.println("ma map position:" + maMapPosition);
		/* On renvoie la tuile suivante, qui depend de notre position et de notre diretion */
		switch (direction){
		case NORD :
			return new Coordonnee(maMapPosition.getX(), maMapPosition.getY()-nbTiles);
		case SUD :
			return new Coordonnee(maMapPosition.getX(), maMapPosition.getY()+nbTiles);
		case EST :
			return new Coordonnee(maMapPosition.getX()+nbTiles, maMapPosition.getY());
		case OUEST :
			return new Coordonnee(maMapPosition.getX()-nbTiles, maMapPosition.getY());
		default : // Si ce n'est aucun de ceux la, on renvoie la position courante (immobile)
			return new Coordonnee(maMapPosition.getX(), maMapPosition.getY());
		}
	}
	
	/**
	 * Choisit aleatoirement une direction parmis celles qui lui sont proposees
	 * @return La direction choisie
	 */
	public Direction choixDeplacement() {
		Direction maDirection = Direction.AUCUNE;
		
		/* Je recupere mon mapElement correspondant a mon ElementMobile */
		Coordonnee posInTiles = CityMove.map.getPositionInTiles(position);
		MapElement monMapElement = CityMove.map.getMapElement(posInTiles);
		//System.out.println("mapElem : "+monMapElement);
		
		//System.out.println("ChoixDeplacemnt : getpossi avec " +monMapElement);
		/* Je recupere le tableau de possibilites de ce mapElement puis je stock la taille du tableau */
		ArrayList<Direction> tab = monMapElement.getPossibilities();
		int taille = tab.size();
		
		/* Je genere un nommbre aleatoire compris entre 0 et la taile de mon tableau -> c'est la que ce fait le choix */
		Random generator = new Random();
		
		int choix = generator.nextInt(taille);
		
		maDirection = tab.get(choix);
		//System.out.println("ALEA : direction = "+maDirection);
		return maDirection;
	}

}