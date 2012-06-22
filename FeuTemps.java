import java.util.Date;
import java.util.Observable;


public class FeuTemps extends Feu implements Runnable {
	protected long vert_time = 5000;
	protected  long red_time = 4000;
	
	/**
	 * Constructeur avec intialisation a etat
	 * @param etat: etat voulu a l'initialisation
	 */
	public FeuTemps(EtatFeu etat) {
		super(etat);
		red_time = 5000;
	}
	
	/**
	 * constructeur par defaut
	 * au rouge par defaut
	 */
	public FeuTemps() {
		super();
	}
	
	/**
	 * constructeur avec position initial
	 */
	public FeuTemps(Coordonnee c) {
		super(c);
	}

	@Override
	/**
	 * Methode de gestion du feu
	 * similaire a la pluspart des autres feux
	 * un feu temps peut change de couleur en fonction de temps predefinis
	 * il peut faire l'objet d'une demande par  un vehicule urgent
	 */
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			if(getEtat() == EtatFeu.VERT) {
				try {
					Thread.sleep(vert_time);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				this.setEtatAndNotify(EtatFeu.ROUGE);
			}
			else {
						try {
							Thread.sleep(red_time);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				this.setEtatAndNotify(EtatFeu.VERT); 
			}
		}		
	}

	@Override
	/**
	 * methide de synchronisation des feux dans un carrefour similaire aux autres
	 * tous les feux se surveillent
	 */
	public void update(Observable o, Object arg) {
		MapElement myMapElement = CityMove.map.getMapElement(positionInTiles);
		BackgroundElement myBackgroundElement = myMapElement.getMyBackgroundElement();
		
		EtatFeu etatFeuObserve = (EtatFeu) arg;
		/* On viens de recevoir un changement, on marque donc la derniere notification */
		lastNotif.setTime(new Date());
		Feu feuObserve = (Feu) o;
		Coordonnee mapPositionObserve = feuObserve.getPositionInTiles();
		MapElement mapElementObserve = CityMove.map.getMapElement(mapPositionObserve);
		BackgroundElement backgroundElementFeuObserve = mapElementObserve.getMyBackgroundElement();
		/* determination des feux (avec position), pour configurer les passages (la synchronisation */	
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
	
	/**
	 * fonction de synchronisation des feux
	 * @param be: BackgroundElement du feu
	 * @param backgroundAtester: BackgroundElement du feu a tester
	 * @param e: etat du feu
	 */
	private void adapterEtat(BackgroundElement be, BackgroundElement backgroundAtester, EtatFeu e) {
		if(be == backgroundAtester) {
			if(e == EtatFeu.VERT) {
				setEtat(EtatFeu.VERT);
			}
			else {
				setEtat(EtatFeu.ROUGE);
			}
		}
		else{
			if(e == EtatFeu.VERT) {
				setEtat(EtatFeu.ROUGE);
			}
			else {
				setEtat(EtatFeu.VERT);
			}
		}
		
	}
	

}