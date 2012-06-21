import java.util.Observable;


public class FeuPieton extends Feu implements Runnable{//implements IFeuPieton {
	private static int red_time = 4000; //temps de traversé
	
	public FeuPieton(EtatFeu etat) {
		super(etat);
		etat = EtatFeu.VERT;
		
		// TODO Auto-generated constructor stub
	}
	
	public FeuPieton() {
		super();
	}
	
	/**
	 *  On se place ici du point de vue des voitures (le rouge indique l'arret des voitures)
	 */
	//@Override
	//public void changerEtat(EtatFeu e) {
	
	//}

	@Override
	public void run() {
			// TODO Auto-generated method stub
			while(true) {
				if(getEtat() == EtatFeu.ROUGE) {
						try {
							Thread.sleep(red_time);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						this.setEtat(EtatFeu.VERT);
						setBusy(true); // on est en attente d'une réponse du carrefour, empeche les doubles demandes
						try {
							Thread.sleep(10000); // on attend le carrefour
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						setBusy(false); // on est libre en demande
				}	
			}
		}



	@Override
	public void update(Observable o, Object arg) {
		MapElement myMapElement = CityMove.map.getMapElement(positionInTiles);
		BackgroundElement myBackgroundElement = myMapElement.getMyBackgroundElement();
		EtatFeu etatFeuObserve = (EtatFeu) arg;
		Feu feuObserve = (Feu) o;
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
		
		System.out.println("NOTIF RECU dans feupieton");
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


