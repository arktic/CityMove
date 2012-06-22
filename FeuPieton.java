import java.util.Observable;


public class FeuPieton extends Feu implements Runnable {
	private static int red_time = 4000; //temps de traversé
	
	/**
	 * Constructeur a partir d'un etat
	 * @param etat: Etat initial voulu
	 */
	public FeuPieton(EtatFeu etat) {
		super(etat);
		etat = EtatFeu.VERT;
	}
	
	/**
	 * Constructeur par defaut
	 * etat est a rouge par defaut
	 */
	public FeuPieton() {
		super();
	}
	
	/**
	 * Constructeur a partir de coordonnee
	 * @param c: coordonnees d'initialisation voulus
	 */
	public FeuPieton(Coordonnee c) {
		super(c);
	}


	@Override
	/**
	 * Methode de gestion du feu
	 * un feu pieton est au vert la pluspart du temps
	 * il passe au rouge sous la demande d'un pieton
	 */
	/* TODO verifier la methode (signaux) */
	public void run() {
			while(true) {
				if(getEtat() == EtatFeu.ROUGE) {
						try {
							Thread.sleep(red_time);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						this.setEtat(EtatFeu.VERT);
						try {
							Thread.sleep(10000); // on attend le carrefour
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}	
			}
		}



	@Override
	/**
	 * Methode de synchronisation avec les autres feu
	 * le feu observe les autres de son carrefour
	 * les autres feux l'observe egalement
	 */
	/*TODO verifier la methode (notify etc..) */
	public void update(Observable o, Object arg) {
		MapElement myMapElement = CityMove.map.getMapElement(positionInTiles);
		BackgroundElement myBackgroundElement = myMapElement.getMyBackgroundElement();
		EtatFeu etatFeuObserve = (EtatFeu) arg;
		Feu feuObserve = (Feu) o;
		Coordonnee mapPositionObserve = feuObserve.getPositionInTiles();
		MapElement mapElementObserve = CityMove.map.getMapElement(mapPositionObserve);
		BackgroundElement backgroundElementFeuObserve = mapElementObserve.getMyBackgroundElement();
		/* reperage des feux du carrefour */
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
		
		System.out.println("NOTIF RECU dans feupieton");
	}
	
	/**
	 * Fonction de synchronisation du carrefour 
	 * determine la couleur des feux en fonctions de leurs position sur le carrefour
	 * @param be : background element du feu en cours
	 * @param backgroundAtester : background element du feu à tester
	 * @param e : etat du feu
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
		else {
			if(e == EtatFeu.VERT) {
				setEtat(EtatFeu.ROUGE);
			}
			else {
				setEtat(EtatFeu.VERT);
			}
		}
	}

}


