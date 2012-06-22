import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;


public abstract class Feu extends Observable implements Observer {



	protected EtatFeu etat;
	;
	protected Coordonnee positionInTiles;
	/* la date de la derniere notification */
	protected Calendar lastNotif;
	/* permet d'ignorer des notifications trop proches, en ms */
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
	
	/**
	 * 
	 * @return la position in tiles
	 */
	synchronized public Coordonnee getPositionInTiles() {
		return positionInTiles;
	}

	
	/* chgt d'etat sans notification*/
	synchronized public void setEtat(EtatFeu e) {
		
		etat = e;
	}

	/* de meme, mais en notifiant */
	synchronized public void setEtatAndNotify(EtatFeu e) {
		
		/* On recupere le temps actuel */
		Calendar actualTime = new GregorianCalendar();
		actualTime.setTime(new Date());
		
		/* Si il y a eu une notification il y a pas longtemps, on ne fait rien */
		if (actualTime.getTimeInMillis() - lastNotif.getTimeInMillis() >= tempsMinEntre2Notifs ) {
			etat = e;
			setChanged();
			notifyObservers(etat);
			lastNotif = actualTime;
		}
	}

	
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
	 * permet d'adapter son etat a celui d'un autre
	 * @param be notre bg
	 * @param backgroundAtester celui du feu que l'on test, e:notreEtat
	 * @param e
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
	
	
	
	
	
	@Override
	public String toString() {
		return "Feu [etat=" + getEtat() + ", positionInTiles=" + getPositionInTiles()
				+ "]";
	}

}