public class Voiture extends Vehicule {
	
	
	@Override
	public int verifierDeplacement(Direction d) {
		/* On recupere la position en tuiles de notre Voiture */
		//TODO : map est une varialbe a ajouter dans le main afin d'y avoir acc�s de n'importe o� ? J'espere qque �a marche..
		Coordonnee maMapPosition = map.getPositionInTiles(position.getX(),position.getY());
		Coordonnee verifMapPosition;
		MapElement verifMapElement;
		
		
		
		/* On d�duit notre future position avec notre direction et notre position courante */
		
		verifMapPosition = getNextTilesPosition(d);
		
		
		//De meme
		verifMapElement = map.getMapElement(verifMapPosition);
		
		if(verifMapElement.isRoute()){
			
			
		}
		//TODO: A COMPLETER
		
		return 0;
	}






}