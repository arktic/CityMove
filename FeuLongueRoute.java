public class FeuLongueRoute extends Feu implements Runnable {
	public FeuLongueRoute(EtatFeu etat) {
		super(etat);
		// TODO Auto-generated constructor stub
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

	}

}