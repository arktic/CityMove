
public class FeuPieton extends Feu implements Runnable{//implements IFeuPieton {
	private static int red_time = 4000; //temps de traversé
	
	public FeuPieton(EtatFeu etat) {
		super(etat);
		etat = EtatFeu.VERT;
		
		// TODO Auto-generated constructor stub
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
							this.wait(red_time);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						this.setDemande(EtatFeu.VERT);
						setBusy(true); // on est en attente d'une réponse du carrefour, empeche les doubles demandes
						try {
							this.wait(); // on attend le carrefour
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						setBusy(false); // on est libre en demande
				}	
			}
		}
}