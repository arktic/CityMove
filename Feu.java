import java.util.Observable;
import java.util.Observer;


public abstract class Feu extends Observable implements Observer {

	
	
	protected EtatFeu etat;
	//protected EtatFeu demande;
	//protected boolean busy;
	protected Coordonnee positionInTiles;
	
	
	public Feu() {
		etat = EtatFeu.ROUGE;
	}
	
	public Feu(Coordonnee coordonnee) {
		etat = EtatFeu.ROUGE;
		positionInTiles = coordonnee;
	}
	/**
	 * 
	 * @param etat: l'etat demande par le feu
	 */
	public Feu(EtatFeu etat) {
		super();
		this.etat = etat;
	}

	synchronized public EtatFeu getEtat() {
		return etat;
	}
	
	/*synchronized public boolean getBusy() {
		return busy;
	}*/
	
	synchronized public Coordonnee getPositionInTiles() {
		return positionInTiles;
	}
	
	synchronized public void setEtat(EtatFeu e) {
		etat = e;
		setChanged();
		//notifyObservers(etat);
 
	}
	
	/*synchronized public void setBusy(boolean b) {
		busy = b;
	}*/

//	public void changerEtat(EtatFeu e) {}
	
	public void update(Observable feu, EtatFeu etatFeuObserve) {
		MapElement myMapElement = CityMove.map.getMapElement(positionInTiles);
		BackgroundElement myBackgroundElement = myMapElement.getMyBackgroundElement();
		
		Feu feuObserve = (Feu) feu;
		Coordonnee mapPositionObserve = feuObserve.getPositionInTiles();
		MapElement mapElementObserve = CityMove.map.getMapElement(mapPositionObserve);
		BackgroundElement backgroundElementFeuObserve = mapElementObserve.getMyBackgroundElement();
		
		switch (myBackgroundElement) {
			case ROUTE_NORD :
				adapterEtat(backgroundElementFeuObserve, BackgroundElement.ROUTE_SUD, etatFeuObserve);
				break;

			case ROUTE_SUD :
				adapterEtat(backgroundElementFeuObserve, BackgroundElement.ROUTE_NORD, etatFeuObserve);
				break;
				
			case ROUTE_EST :
				adapterEtat(backgroundElementFeuObserve, BackgroundElement.ROUTE_OUEST, etatFeuObserve);
				break;
				
			case ROUTE_OUEST :
				adapterEtat(backgroundElementFeuObserve, BackgroundElement.ROUTE_EST, etatFeuObserve);
				break;
			default : System.out.println("Probleme dans le switch de myBackgroundElement\n"); System.exit(1);
		}
	}
	
	private void adapterEtat(BackgroundElement be, BackgroundElement backgroundAtester, EtatFeu e) {
		if(be == backgroundAtester) {
			if(e == EtatFeu.VERT) {
				setEtat(EtatFeu.VERT);
			}
			else {
				setEtat(EtatFeu.ROUGE);
			}
		}
		else {
			if(e == EtatFeu.VERT) {
				setEtat(EtatFeu.ROUGE);
			}
			else {
				setEtat(EtatFeu.VERT);
			}
		}
	}

}