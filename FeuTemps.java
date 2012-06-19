

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
			switch(etat) {
			case VERT:
				System.out.println("\t ETAT VERT en direction ROUGE");
				changerEtat(EtatFeu.ROUGE);
				System.out.println("\t ETAT:" + etat);
			break;
			case ROUGE:
				System.out.println("\t ETAT ROUGE en direction VERT");
				changerEtat(EtatFeu.VERT);
				System.out.println("\t ETAT:" + etat);
				
			break;
			}
		}
	}

	/**
	 *  On se place ici du point de vue des voitures (le rouge indique l'arret des voitures)
	 *  changerEtat tente d'amener l'état du feu dans l'état demandé en respectant la sécurité du feu
	 */
	@Override
	synchronized public void changerEtat(EtatFeu e) {
		switch(e) {
		case ROUGE:
			switch(etat) {
			case VERT:
				System.out.println("Ftemps,attente vert_time");
				try {
					Thread.sleep(vert_time);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					
				}
				System.out.println("Ftemps, etat transit ORANGE");
				etat = EtatFeu.ORANGE;
				System.out.println("Ftemps,attente vert_orange_time");
				try {
					Thread.sleep(vert_orange_time);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					
				}
				System.out.println("Ftemps, fin vert_orange_time");
				changerEtat(EtatFeu.ROUGE);

				break;
			case ORANGE:
				//TODO: on reset le temps, il faudrait une continuité, ceci n'est pas vraiment correct
				System.out.println("FT attente orange_red_time");
				try {
					Thread.sleep(orange_red_time);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println("Ftemps, etat ROUGE atteint");
				etat = e;
				break;
			}
		break;
		case VERT:
			switch(etat) {
			case ROUGE:
				System.out.println("Ftemps,attente red_time");
				try {
					Thread.sleep(red_time);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println("Ftemps,etat VERT atteint");
				etat = e;
			break;
			case ORANGE:
				System.out.println("Ftemps,attente orange_red_time");
				try {
					Thread.sleep(orange_red_time);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println("Ftemps, fin vert_orange");
				etat = EtatFeu.ROUGE;
				changerEtat(EtatFeu.VERT);
			}
		break;
		}		
	}
}