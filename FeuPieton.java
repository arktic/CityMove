
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
		switch(e) {
		case ROUGE:
			switch(etat) {
			case VERT:
				System.out.println("attente rouge (orange)");
				etat = EtatFeu.ORANGE;
				try {
					Thread.sleep(orange_time);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					
				}
				etat = EtatFeu.ROUGE;
				System.out.println("attente rouge (traversé");
				try {
					Thread.sleep(red_time);
				} catch (InterruptedException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
					
				}

				break;
			case ORANGE:
				//TODO: on reset le temps, il faudrait une continuité, ceci n'est pas vraiment correct
				System.out.println("attente orange");
				try {
					Thread.sleep(orange_time);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				etat = e;
				break;
			default:
				// voila
				etat = e;
			}
		break;
		default:
			etat = e;
		}
		setDemande(EtatFeu.ETEINT); // reinit la demande (pas de boucle )
	}

	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		while(true) {

			if(demande == EtatFeu.VERT || demande == EtatFeu.ROUGE) { // en pratique la demande de feu vert est inutile
					System.out.println("demande:" + demande + " etat: " + etat);
					changerEtat(demande); // satisfaire la demande
					System.out.println("demande satisfaite, etat:" + etat);
			} 
			else { // fonctionnement normal (ou restauration d'état après une demande)
				changerEtat(EtatFeu.VERT);
				System.out.println("fonctionnement normal, etat:" + etat);
			}
		}
	}
}