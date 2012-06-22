public class FeuHybride extends FeuTemps {
/* TODO verifer la methode run */
	/**
	 * Constructeur avec argument 
	 * @param etat etat à initialiser
	 */
	public FeuHybride(EtatFeu etat) {
		super(etat);
	}

	/**
	 * Constructeur par defaut, Etat à ROUGE
	 */
	public FeuHybride() {
		super();
	}
	
	/**
	 * Constructeur avec Coordonnee, permet de définir une position initial
	 * Etat est à la valeur par defaut (rouge)
	 * @param c coordonnee voulus
	 */
	public FeuHybride(Coordonnee c) {
		super(c);
	}
	
	@Override 
	/**
	 * Methide run, gere le fonctionnement du feu
	 */
	public void run() {
		while(true) { // on boucle jusqu'a tant que faire ce peu
			if(getEtat() == EtatFeu.VERT) {
				try {
					this.wait(vert_time); //timeout du feu
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				setEtat(EtatFeu.ROUGE);
			}
			else {
					try {
						this.wait(red_time); //timeout du rouge
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				setEtat(EtatFeu.VERT);
			}
			try {
				this.wait(); // attente de réponse du "carrrefour"
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}