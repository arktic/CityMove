import java.util.ArrayList;
import java.util.Random;


public abstract class ElementMobiles extends Elements {
	/*----- Attributs -----*/
	private int vitesse;
	private Direction direction;



	public ElementMobiles(int posx, int posy,Direction dir) {
		super(posx,posy);
		vitesse=10;
		direction=dir;
	}



	/**
	 * 
	 * @return
	 */
	synchronized public int seDeplacer() { 

		Direction maDirection = getDirection();


		/* On détermine notre nouvelle direction */
		
		if(getPosition().isOnMap()) {
		maDirection = choixDeplacement();
		//System.out.println("mon choix deplacement:" + maDirection);
		}

		Coordonnee newPosition = CityMove.map.getPositionInTiles(getNextPosition(maDirection));
		//System.out.println("Next position :" + newPosition);

		if(isElementMobileOnMap(newPosition)) {
			if(verifierDeplacement(maDirection)) {
				/* Si on peut se rendre sur la case vers maDirection */
				//System.out.println("OK VERIF");
				//System.out.println("maPosition : "+mapPosition);

				MapElement oldMapElement = CityMove.map.getMapElement(CityMove.map.getPositionInTiles(getPosition()));
				MapElement newMapElement;
				TypeMobileElement myTypeMobileElement = oldMapElement.getMyTypeMobileElement();

				/* On actualise la position courante  et la nouvelle direction */
				setPosition(getNextPosition(maDirection));
				setDirection(maDirection);
				
				
				newMapElement = CityMove.map.getMapElement(newPosition);
				newMapElement.setMyTypeMobileElement(myTypeMobileElement);


				if(doitLibererCaseDerriere()) {
					oldMapElement.setMyTypeMobileElement(TypeMobileElement.VIDE);
				}
			}
			else {
				stoper();
			}
		}
		else if(isElementMobileOnDarkMap(newPosition)){
			//System.out.println("PASSAGE DARK MAP");
			/* Sortir visuellement le véhicule de la carte */
			setPosition(getNextPosition(maDirection));
			setDirection(maDirection);
			
			MapElement oldMapElement = null;
			
			switch(maDirection) {
			case NORD:
				oldMapElement = CityMove.map.getMapElement(
						CityMove.map.getPositionInTiles(new Coordonnee(getPosition().getX(),0))
						);
				break;
				
			case SUD:
				oldMapElement = CityMove.map.getMapElement(
						CityMove.map.getPositionInTiles(new Coordonnee(getPosition().getX(),CityMove.map.getHauteur()))
						);
				break;
				
			case EST:
				oldMapElement = CityMove.map.getMapElement(
						CityMove.map.getPositionInTiles(new Coordonnee(0,getPosition().getY()))
						);
				break;
			case OUEST:
				oldMapElement = CityMove.map.getMapElement(
						CityMove.map.getPositionInTiles(new Coordonnee(CityMove.map.getLargeur(),getPosition().getY()))
						);
				break;
				
			}
			
			oldMapElement.setMyTypeMobileElement(TypeMobileElement.VIDE);
			

		} else {
			System.out.println("Suppresion de un vehicule !!!!");
			CityMove.map.removeElementMobile(this);
		}
			




		return 0;
	}



	/**
	 * Renvoie vrai si la coordonnée d'un véhicule passé en param est visible sur la map dark
	 * @param pos
	 * @return
	 */
	private boolean isElementMobileOnDarkMap(Coordonnee pos) {
		
		Map map = CityMove.map;
	//	System.out.println("DARKMAP avec "+pos);
		return (  pos.getX()*map.sizeElement>= -map.sizeElement
				&& pos.getX()*map.sizeElement< map.largeur-2*map.sizeElement 
				&& pos.getY()*map.sizeElement>= -map.sizeElement
				&& pos.getY()*map.sizeElement< map.hauteur-2*map.sizeElement);
	}


	/**
	 * Renvoie vrai si la coordonnée d'un véhicule passé en param est visible sur la map 
	 * @param pos
	 * @return
	 */
	private boolean isElementMobileOnMap(Coordonnee pos) {
		
		return (  pos.getX()>=0 
				&& pos.getX()<CityMove.map.largeur-CityMove.map.sizeElement 
				&& pos.getY()>=0 
				&& pos.getY()<CityMove.map.hauteur-CityMove.map.sizeElement);
	}



	synchronized public  Direction getDirection() {
		return direction;
	}



	synchronized public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
	
	synchronized public  int getVitesse() {
		return vitesse;
	}



	synchronized public void setVitesse(int vit) {
		this.vitesse = vit;
	}



	/**
	 * renvoi vrai si nous avons atteint le bout de notre case courante, donnc qu'on vient de libérer la case d'avant
	 * Note: on suppose ici que tout nos éléments mobiles occupent une case
	 * @return
	 */
	private boolean doitLibererCaseDerriere() {

		/* On determine si on est encore sur la case derriere */

		switch (direction) {
		case NORD:
			return (getPosition().getY()% (CityMove.map.sizeElement)==0);
		case SUD:
			return (getPosition().getY()% (CityMove.map.sizeElement)==0);
		case EST:
			return (getPosition().getX()% (CityMove.map.sizeElement)==0);
		case OUEST:
			return (getPosition().getX()% (CityMove.map.sizeElement)==0);
		default:
			return false;
		}
	}





	public int stoper() {
		setVitesse(0);
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

		Coordonnee nextCoord = getNextTilesPosition(1, d);
		if(nextCoord.isOnMap()) {
			MapElement nextMapElement = CityMove.map.getMapElement(nextCoord);

			TypeMobileElement nextTme = nextMapElement.getMyTypeMobileElement();

			if(nextTme!=null && tme == nextTme)
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

		if(coordonneeVerifiee.isOnMap()) {
			MapElement monMapElementVerifie = CityMove.map.getMapElement(coordonneeVerifiee);
			Feu monFeuVerifie = monMapElementVerifie.getMyFeu();

			if(monFeuVerifie != null && monFeuVerifie.getEtat() == EtatFeu.ROUGE) {
				isRouge = true;
			}
		}

		return isRouge;
	}

	public Coordonnee getNextPosition(Direction direction) {
		/* On recupere notre position courante */
		Coordonnee maPosition = this.getPosition();

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
	 * @return La case situŽ plusieurs cases en avance de la case actuelle
	 */
	public Coordonnee getNextTilesPosition(int nbTiles, Direction direction) {
		/* On recupere notre position courante, en tuiles, sur la map */
		Coordonnee maMapPosition = CityMove.map.getPositionInTiles(getPosition());
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
		Coordonnee posInTiles = CityMove.map.getPositionInTiles(getPosition());
		MapElement monMapElement = CityMove.map.getMapElement(posInTiles);
		//System.out.println("mapElem : "+monMapElement);

		//System.out.println("ChoixDeplacemnt : getpossi avec " +monMapElement);
		/* Je recupere le tableau de possibilites de ce mapElement puis je stock la taille du tableau */
		ArrayList<Direction> tab = monMapElement.getPossibilities();
		int taille = tab.size();
		//System.out.println("tab: " + tab);
		/* Je genere un nommbre aleatoire compris entre 0 et la taile de mon tableau -> c'est la que ce fait le choix */
		Random generator = new Random();

		if(taille!=0){
			int choix = generator.nextInt(taille);
			maDirection = tab.get(choix);
		}
		else
			maDirection = Direction.AUCUNE;
		//System.out.println("choix:" + choix);

		

		return maDirection;
	}

}