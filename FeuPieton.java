
public class FeuPieton extends Feu implements IFeuPieton {
	private static int orange_time = 3000;
	private static int red_time = 4000; //temps de traversé
	public FeuPieton(EtatFeu etat) {
		super(etat);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 *  On se place ici du point de vue des voitures (le rouge indique l'arret des voitures)
	 */
	@Override
	public void changerEtat(EtatFeu e) {
	
	}

	@Override
	public void run() {
			// TODO Auto-generated method stub
			while(true) {
				if(demande == EtatFeu.ROUGE) {
					etat = EtatFeu.ROUGE;
					try {
						this.wait(red_time);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					etat = EtatFeu.VERT;
				}	
			}
		}
}