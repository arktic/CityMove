import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;


public class FeuTemps extends Feu implements Runnable {
	protected long vert_time = 5000;
	protected  long red_time = 5000;
	
	
	public FeuTemps(EtatFeu etat) {
		super(etat);
		red_time = 5000;
		// TODO Auto-generated constructor stub
	}
	
	public FeuTemps() {
		super();
	}
	
	public FeuTemps(Coordonnee c) {
		super(c);
	}

	//EN COURS DIMPLEMENTATION
	/**
	 * Valeur courante du timer
	 */
	public int timer;

	/**
	 * Le feu change de couleur toute les intervalTemps secondes
	 */
	public int intervalTemps;

	/**
	 * @return the timer
	 */
	public int getTimer() {
		return timer;
	}

	/**
	 * @param timer, the timer to set
	 */
	public void setTimer(int timer) {
		this.timer = timer;
	}

	/**
	 * @return the intervalTemps
	 */
	public int getIntervalTemps() {
		return intervalTemps;
	}

	/**
	 * @param intervalTemps the intervalTemps to set
	 */
	public void setIntervalTemps(int intervalTemps) {
		this.intervalTemps = intervalTemps;
	}

	@Override
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
	public void update(Observable o, Object arg) {
		MapElement myMapElement = CityMove.map.getMapElement(positionInTiles);
		BackgroundElement myBackgroundElement = myMapElement.getMyBackgroundElement();
		
		EtatFeu etatFeuObserve = (EtatFeu) arg;
		System.out.println("reception d'une notif de chgt en "+etatFeuObserve);
		
		//Calendar actualTime = new GregorianCalendar();
		
		/* On viens de recevoir un changement, on marque donc la derniere notification */
		lastNotif.setTime(new Date());
		Feu feuObserve = (Feu) o;
		Coordonnee mapPositionObserve = feuObserve.getPositionInTiles();
		MapElement mapElementObserve = CityMove.map.getMapElement(mapPositionObserve);
		BackgroundElement backgroundElementFeuObserve = mapElementObserve.getMyBackgroundElement();
		
		switch (myBackgroundElement) {
			case ROUTE_NORD :
				System.out.println("TEST NORD");
				adapterEtat(backgroundElementFeuObserve, BackgroundElement.ROUTE_SUD, etatFeuObserve);
				break;

			case ROUTE_SUD :
				System.out.println("TEST SUD");
				adapterEtat(backgroundElementFeuObserve, BackgroundElement.ROUTE_NORD, etatFeuObserve);
				break;
				
			case ROUTE_EST :
				System.out.println("TEST EST");
				adapterEtat(backgroundElementFeuObserve, BackgroundElement.ROUTE_OUEST, etatFeuObserve);
				break;
				
			case ROUTE_OUEST :
				System.out.println("TEST OUEST");
				adapterEtat(backgroundElementFeuObserve, BackgroundElement.ROUTE_EST, etatFeuObserve);
				break;
			default : System.out.println("Probleme dans le switch de myBackgroundElement\n"); System.exit(1);
		}
		
		System.out.println("NOTIF RECU dans feu pieton");
	}
	
	private void adapterEtat(BackgroundElement be, BackgroundElement backgroundAtester, EtatFeu e) {
		System.out.println("Je suis dans adapterEtat\n");
		if(be == backgroundAtester) {
			if(e == EtatFeu.VERT) {
				System.out.println("Ca merde 1 !!!\n");
				setEtat(EtatFeu.VERT);
			}
			else {
				System.out.println("Ca merde 2 !!!\n");
				setEtat(EtatFeu.ROUGE);
			}
		}
		else{
			if(e == EtatFeu.VERT) {
				System.out.println("Ca merde 3 !!!\n");
				setEtat(EtatFeu.ROUGE);
			}
			else {
				System.out.println("Ca merde 4 !!!\n");
				setEtat(EtatFeu.VERT);
			}
		}
		
	}
	

}