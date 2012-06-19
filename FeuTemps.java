

public class FeuTemps extends Feu implements Runnable {
	protected long vert_time = 3000;
	protected  long red_time = 4000;
	protected  long vert_orange_time = 4000;
	protected  long orange_red_time = 4000;
	
	
	public FeuTemps(EtatFeu etat) {
		super(etat);
		red_time = 5000;
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
		while(true) {
			if(etat == EtatFeu.VERT) {
				etat = EtatFeu.ROUGE;
				try {
					this.wait(red_time);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
					etat = EtatFeu.VERT;
					try {
						this.wait(vert_time);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}		
	}

}