import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;


public abstract class Feu extends Observable implements Observer {



	protected EtatFeu etat;
	@Override
	public String toString() {
		return "Feu [etat=" + getEtat() + ", positionInTiles=" + getPositionInTiles()
				+ "]";
	}

	//protected EtatFeu demande;
	//protected boolean busy;
	protected Coordonnee positionInTiles;
	protected Calendar lastNotif;
	protected int tempsMinEntre2Notifs = 200;

	public Feu() {
		etat = EtatFeu.ROUGE;
		lastNotif = new GregorianCalendar();
		lastNotif.setTime(new Date());
	}

	public Feu(Coordonnee coordonnee) {
		etat = EtatFeu.ROUGE;
		positionInTiles = coordonnee;
		lastNotif = new GregorianCalendar();
		lastNotif.setTime(new Date());
	}
	/**
	 * 
	 * @param etat: l'etat demande par le feu
	 */
	public Feu(EtatFeu etat) {
		super();
		this.etat = etat;
		lastNotif = new GregorianCalendar();
		lastNotif.setTime(new Date());
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
		//System.out.println("Je suis dans setEtat\n");
		etat = e;
	}

	synchronized public void setEtatAndNotify(EtatFeu e) {
		
		/* On recupere le temps actuel */
		Calendar actualTime = new GregorianCalendar();
		actualTime.setTime(new Date());
		
		/* Si il y a eu une notification il y a pas longtemps, on ne fait rien */
		if (actualTime.getTimeInMillis() - lastNotif.getTimeInMillis() >= tempsMinEntre2Notifs ) {
			/*Random generator = new Random();
			try {
				this.wait(generator.nextInt(100)+100);
			} catch (InterruptedException e1) {
			
				e1.printStackTrace();
			}*/
			//System.out.println("Notif de chgt d etat en"+e+" a "+countObservers()+" feux");
			etat = e;
			setChanged();
			notifyObservers(etat);
			lastNotif = actualTime;
		}
	}

	/*synchronized public void setBusy(boolean b) {
		busy = b;
	}*/

	//	public void changerEtat(EtatFeu e) {}

	public void update(Observable o, Object arg) {
		MapElement myMapElement = CityMove.map.getMapElement(positionInTiles);
		BackgroundElement myBackgroundElement = myMapElement.getMyBackgroundElement();

		EtatFeu etatFeuObserve = (EtatFeu) arg;
		//System.out.println("reception d'une notif de chgt en "+etatFeuObserve);
		Feu feuObserve = (Feu) o;
		Coordonnee mapPositionObserve = feuObserve.getPositionInTiles();
		MapElement mapElementObserve = CityMove.map.getMapElement(mapPositionObserve);
		BackgroundElement backgroundElementFeuObserve = mapElementObserve.getMyBackgroundElement();

		switch (myBackgroundElement) {
		case ROUTE_NORD :
			//System.out.println("TEST NORD");
			adapterEtat(backgroundElementFeuObserve, BackgroundElement.ROUTE_SUD, etatFeuObserve);
			break;

		case ROUTE_SUD :
			//System.out.println("TEST SUD");
			adapterEtat(backgroundElementFeuObserve, BackgroundElement.ROUTE_NORD, etatFeuObserve);
			break;

		case ROUTE_EST :
			//System.out.println("TEST EST");
			adapterEtat(backgroundElementFeuObserve, BackgroundElement.ROUTE_OUEST, etatFeuObserve);
			break;

		case ROUTE_OUEST :
			//System.out.println("TEST OUEST");
			adapterEtat(backgroundElementFeuObserve, BackgroundElement.ROUTE_EST, etatFeuObserve);
			break;
		default : System.out.println("Probleme dans le switch de myBackgroundElement\n"); System.exit(1);
		}

		//System.out.println("NOTIF RECU dans feu pieton");
	}

	private void adapterEtat(BackgroundElement be, BackgroundElement backgroundAtester, EtatFeu e) {
		//System.out.println("Je suis dans adapterEtat\n");
		if(be == backgroundAtester) {
			if(e == EtatFeu.VERT) {
				//System.out.println("Ca merde 1 !!!\n");
				setEtat(EtatFeu.VERT);
			}
			else {
				//System.out.println("Ca merde 2 !!!\n");
				setEtat(EtatFeu.ROUGE);
			}
		}
		else{
			if(e == EtatFeu.VERT) {
				//System.out.println("Ca merde 3 !!!\n");
				setEtat(EtatFeu.ROUGE);
			}
			else {
				//System.out.println("Ca merde 4 !!!\n");
				setEtat(EtatFeu.VERT);
			}
		}

	}

}