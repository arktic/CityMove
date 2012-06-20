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
	//TODO : PAS FINI!!!!!!!!!!!!!! au boulot trou du cul !!!!!!!!!!
	public int seDeplacer() { 
		Direction maDirection;
	
		maDirection = choixDeplacement();
		System.out.println("mon choix deplacement:" + maDirection);
		if(verifierDeplacement(maDirection)) {
			System.out.println("verif deplacement ok");
			Coordonnee mapPosition = CityMove.map.getPositionInTiles(getPosition());
			
			//System.out.println("maPosition : "+mapPosition);
			
			Coordonnee newMapPosition;
			MapElement monMapElement = CityMove.map.getMapElement(CityMove.map.getPositionInTiles(position));
			MapElement monNewMapElement;
			TypeMobileElement myTypeMobileElement = monMapElement.getMyTypeMobileElement();
			
			setPosition(getNextPosition(maDirection));
			System.out.println("voiture position: " + position);
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
		boolean ElementMobileVerifie = true;
		
		Coordonnee coordonneeVerifiee = getNextTilesPosition(1, d);
		MapElement monMapElementVerifie = CityMove.map.getMapElement(coordonneeVerifiee);
		TypeMobileElement monMobileElementVerifie = monMapElementVerifie.getMyTypeMobileElement();
		
		if(tme != monMobileElementVerifie)
		{
			ElementMobileVerifie = false;
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
		boolean feuRougeVerifie = true;

		Coordonnee coordonneeVerifiee = getNextTilesPosition(1, d);
		System.out.println("coord verif: " + coordonneeVerifiee);
		MapElement monMapElementVerifie = CityMove.map.getMapElement(coordonneeVerifiee);
		Feu monFeuVerifie = monMapElementVerifie.getMyFeu();
		
		if(monFeuVerifie == null) {
			feuRougeVerifie = false;
			
			return feuRougeVerifie;
		}
		
		EtatFeu monEtatFeuVerifie = monFeuVerifie.getEtat();
		
		if(monEtatFeuVerifie != EtatFeu.ROUGE) {
			feuRougeVerifie = false;
		}
		
		return feuRougeVerifie;
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
		System.out.println("ma map position:" + maMapPosition);
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
		
		//System.out.println("CHOIXDEPLACEMENT: "+ maDirection);
		/* Je recupere mon mapElement correspondant a mon ElementMobile */
		Coordonnee posInTiles = CityMove.map.getPositionInTiles(position);
		//System.out.println("posIntTiles : "+posInTiles);
		MapElement monMapElement = CityMove.map.getMapElement(posInTiles);
		System.out.println("mapElem : "+monMapElement);
		
		System.out.println("ChoixDeplacemnt : getpossi avec " +monMapElement);
		/* Je recupere le tableau de possibilites de ce mapElement puis je stock la taille du tableau */
		ArrayList<Direction> tab = monMapElement.getPossibilities();
		int taille = tab.size();
		
		/* Je genere un nommbre aleatoire compris entre 0 et la taile de mon tableau -> c'est la que ce fait le choix */
		Random generator = new Random();
		int choix = generator.nextInt(taille);
		
		maDirection = tab.get(choix);
	//	System.out.println("CHOIXDEPLACEMENT: "+ maDirection);
		return maDirection;
	}

}