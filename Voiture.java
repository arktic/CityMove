public class Voiture extends Vehicule {
	
	
	@Override
	public int verifierDeplacement(Direction d) {
		/* On recupere la position en tuiles de notre Voiture */
		//TODO : map est une varialbe a ajouter dans le main afin d'y avoir accès de n'importe où ? J'espere qque ça marche..
	//	Coordonnee maMapPosition = Application.map.getPositionInTiles(position);
		Coordonnee verifMapPosition;
		MapElement verifMapElement;
		
		
		
		/* On déduit notre future position avec notre direction et notre position courante */
		verifMapPosition = getNextTilesPosition(0, direction);

		
		
		//De meme
		verifMapElement = CityMove.map.getMapElement(verifMapPosition);
		
		if(verifMapElement.isRoute()){
			
			
		}
		//TODO: A COMPLETER
		
		return 0;
	}






}