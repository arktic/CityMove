public class Voiture extends Vehicule {
	@Override
	public int verifierDeplacement(Direction d) {
		Coordonnee maMapPosition = getMapPosition();
		Coordonnee verifMapPosition;
		MapElement verifMapElement;
		BackgroundElement verifBackgroundElement;
		
		
		switch (d){
			case NORD :
				verifMapPosition.set(maMapPosition.getx(), maMapPosition.gety()-1);
				break;
			case SUD :
				verifMapPosition.set(maMapPosition.getx(), maMapPosition.gety()+1);
				break;
			case EST :
				verifMapPosition.set(maMapPosition.getx()+1, maMapPosition.gety());
				break;
			case OUEST :
				verifMapPosition.set(maMapPosition.getx()-1, maMapPosition.gety());
				break;
			default :
		}
		
		verifMapElement = getElement(verifMapPosition);
		verifBackgroundElement = verifMapElement.myBackgroundElement;
		if(verifBackgroundElement == BackgroundElement.ROUTE_NORD || verifBackgroundElement == BackgroundElement.ROUTE_SUD
		   || verifBackgroundElement == BackgroundElement.ROUTE_EST || verifBackgroundElement == BackgroundElement.ROUTE_OUEST
		   || verifBackgroundElement == BackgroundElement.ROUTE_SUD_EST || verifBackgroundElement == BackgroundElement.ROUTE_SUD_OUEST
		   || verifBackgroundElement == BackgroundElement.ROUTE_NORD_EST || verifBackgroundElement == BackgroundElement.ROUTE_NORD_OUEST
		   || verifBackgroundElement == BackgroundElement.ROUTE_PIETON_NORD_SUD || verifBackgroundElement == BackgroundElement.ROUTE_PIETON_EST_OUEST){
			
			// A COMPLETER
		}
		
		return 0;
	}






}