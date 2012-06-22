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

		Direction directionPrise = getDirection();

		/* On détermine notre nouvelle direction */



		/* si on est sur le bord d'une tuile, il faut qu'on évalue les nos possibilites de deplacement */
		if (getPosition().onEdgeofTile(directionPrise)) {

			/* Si on est sur la map, on determine notre nouvelle direction */
			if(CityMove.map.getPositionInTiles(getPosition()).isOnMap()) {
				directionPrise = choixDeplacement();
			}


			/* Notre nouvelle position, en pixel */
			Coordonnee newPixelPosition = getNextPosition(directionPrise);
			Coordonnee newPixelVerifPosition = getNextVerifPosition(directionPrise);
			/* Et celle en tuiles */
			//	Coordonnee newTilePosition = CityMove.map.getPositionInTiles(newPixelPosition);
			//Coordonnee newTilePosition = CityMove.map.getPositionInTiles(getPosition());

			Coordonnee newTilePosition = getNextTilesPosition(1, directionPrise);


			if (this instanceof VoitureUrgent) {
				VoitureUrgent vu = (VoitureUrgent) this;
				vu.intervenirFeu();
			}
			
			
			if(newTilePosition.isOnMap()) {
				/* Si on peut se rendre sur la tuiles suivant */
				if(verifierDeplacement(newPixelVerifPosition)) {

					/* On recupere le mapElement de notre position actuelle */
					MapElement oldMapElement = CityMove.map.getMapElement(CityMove.map.getPositionInTiles(getPosition()));

					/* On récupere le type de vehicule qui occupe notre position courante, c'est a dire notre type en fait */
					TypeMobileElement myTypeMobileElement = oldMapElement.getMyTypeMobileElement();

					/* On actualise la position courante  et la nouvelle direction */
					setPosition(newPixelPosition);
					setDirection(directionPrise);


					/* On actualise la case ou l'on va aller pour dire qu'elle est prise aux autres vehicules */
					MapElement newMapElement = CityMove.map.getMapElement(newTilePosition);
					newMapElement.setMyTypeMobileElement(myTypeMobileElement);



					oldMapElement.setMyTypeMobileElement(TypeMobileElement.VIDE);
				}
				else {
					//TODO vitesse a prendre en compte...
					stoper();
				}
			}
			else if(newTilePosition.isOnDarkMap()){

				/* Sortir visuellement le véhicule de la carte */

				MapElement oldMapElement = null;
				Coordonnee oldPosition = null;

				switch(directionPrise) {
				case NORD:
					oldPosition = new Coordonnee(getPosition().getX(),0);

					break;

				case SUD:
					oldPosition = new Coordonnee(getPosition().getX(),CityMove.map.getHauteur()-CityMove.map.sizeElement);
					break;

				case EST:
					oldPosition = new Coordonnee(0,getPosition().getY());

					break;
				case OUEST:
					oldPosition = new Coordonnee(CityMove.map.getLargeur()-CityMove.map.sizeElement,getPosition().getY());
					break;

				}

				oldMapElement = CityMove.map.getMapElement(CityMove.map.getPositionInTiles(oldPosition));

				oldMapElement.setMyTypeMobileElement(TypeMobileElement.VIDE);
				setPosition(newPixelPosition);

			} else {
				CityMove.map.removeElementMobile(this);
			}
		}
		/* si on est pas sur le bord, on continue dans notre direction actuelle */
		else {

			/* Notre nouvelle position, en pixel */


			Coordonnee newPixelPosition = getNextPosition(directionPrise);
			Coordonnee mapPositionInTile = CityMove.map.getPositionInTiles(newPixelPosition);

			MapElement nouveauMarquage=null;
			if(mapPositionInTile.isOnMap()) {
				nouveauMarquage = CityMove.map.getMapElement(mapPositionInTile);

				if (this instanceof Pieton) {
					nouveauMarquage.setMyTypeMobileElement(TypeMobileElement.PIETON);
				}
				else {
					nouveauMarquage.setMyTypeMobileElement(TypeMobileElement.VEHICULE);
				}	
			}


			//System.out.println("Pas sur le bord, on avance en : "+newPixelPosition);
			setPosition(newPixelPosition);
		}




		return 0;
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









	public int stoper() {
		setVitesse(0);
		return 0;
	}

	/**
	 * 
	 * @param check si les coordonées passées sont accessible pour un element mobile
	 * @return Oui ou Non
	 */
	public abstract boolean verifierDeplacement(Coordonnee coordonnee);

	/**
	 * 
	 * @param tme : Type d'element mobile a verifier (vehicule ou pieton)
	 * @param nextCoord : direction dans laquele on veut regarder
	 * @return si il y a un element de ce type devant
	 */
	public boolean verifierElementMobile( Coordonnee pixelPosition)
	{
		ElementMobiles aTester=null;
		boolean occupe=false;		
				
		int indice=0;
		while (!occupe && indice<CityMove.map.getSizeTabElementMobile()){
			aTester = CityMove.map.getTabElementMobileAt(indice);

			if(this!=aTester) {
				occupe = aTester.contains(pixelPosition);
				//System.out.println("indice :"+indice+" occupe ="+occupe);
			}
			indice++;
		}

		return occupe;
	}

	public boolean contains(Coordonnee pixelPosition) {
		Coordonnee tileaTester = CityMove.map.getPositionInTiles(pixelPosition);
		Coordonnee currentPositionTile = CityMove.map.getPositionInTiles(getPosition());
		Coordonnee nextPosition = CityMove.map.getPositionInTiles(getPosition()).getNextCoordonnee(getDirection());
		
		return tileaTester.equals(currentPositionTile) 
				|| tileaTester.equals(nextPosition);
	}



	/**
	 * 
	 * @param coord : Direction dans laquelle on veut verifier la presence d'un feu
	 * @return Vrai ou faux
	 */
	public boolean verifierFeu(Coordonnee coord)
	{
		boolean isRouge = false;



		if(coord.isOnMap()) {
			MapElement monMapElementVerifie = CityMove.map.getMapElement(coord);
			Feu monFeuVerifie = monMapElementVerifie.getMyFeu();

			//System.out.println("TESTFEU : "+monFeuVerifie);
			if(monFeuVerifie != null && monFeuVerifie.getEtat() == EtatFeu.ROUGE) {
				isRouge = true;
			}
		}

		return isRouge;
	}

	public Coordonnee getNextPosition(Direction direction) {
		/* On recupere notre position courante */
		Coordonnee maPosition = getPosition();

		/* On renvoie les cordonnees suivantes, qui dependent de notre position et de notre direction */
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
	
	
	public Coordonnee getNextVerifPosition(Direction direction) {
		/* On recupere notre position courante */
		Coordonnee maPosition = getPosition();

		/* On renvoie les cordonnees suivantes, qui dependent de notre position et de notre direction */
		switch (direction){
		case NORD :
			return new Coordonnee(maPosition.getX(), maPosition.getY()-1);
		case SUD :
			return new Coordonnee(maPosition.getX(), maPosition.getY()+1+CityMove.map.sizeElement);
		case EST :
			return new Coordonnee(maPosition.getX()+1+CityMove.map.sizeElement, maPosition.getY());
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
		Coordonnee tilesPosition = CityMove.map.getPositionInTiles(getPosition());
		//System.out.println("ma map position:" + maMapPosition);
		/* On renvoie la tuile suivante, qui depend de notre position et de notre diretion */
		switch (direction){
		case NORD :
			return new Coordonnee(tilesPosition.getX(), tilesPosition.getY()-nbTiles);
		case SUD :
			return new Coordonnee(tilesPosition.getX(), tilesPosition.getY()+nbTiles);
		case EST :
			return new Coordonnee(tilesPosition.getX()+nbTiles, tilesPosition.getY());
		case OUEST :
			return new Coordonnee(tilesPosition.getX()-nbTiles, tilesPosition.getY());
		default : // Si ce n'est aucun de ceux la, on renvoie la position courante (immobile)
			return new Coordonnee(tilesPosition.getX(), tilesPosition.getY());
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


		/* Je recupere le tableau de possibilites de ce mapElement puis je stock la taille du tableau */
		ArrayList<Direction> tab = monMapElement.getPossibilities();
		int taille = tab.size();

		/* Je genere un nommbre aleatoire compris entre 0 et la taile de mon tableau -> c'est la que ce fait le choix */
		Random generator = new Random();

		if(taille!=0){
			int choix = generator.nextInt(taille);
			maDirection = tab.get(choix);
		}
		else
			maDirection = Direction.AUCUNE;



		return maDirection;
	}

}