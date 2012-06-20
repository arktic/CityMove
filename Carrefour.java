import java.util.Observable;
import java.util.Observer;

public class Carrefour extends Thread implements Observer {
	/* le carrefour un thread ?? */
/*----- Attributs -----*/
  public Feu[] tabFeu;
  public int nbFeu;

/*----- Constructeurs ----- */
  /* tous les carrefours a 2 feux sont vus comme des carrefours avec feu nord et feu sud */
  public Carrefour (Feu nord, Feu sud) {
	  nbFeu = 2;
	  tabFeu = new Feu[2];
	  nord.addObserver(this);
	  sud.addObserver(this);
	  tabFeu[0] = nord;
	  tabFeu[1] = sud;
  }
  
  /* tous les carrefours a 3 feux sont vus comme des carrefour N/S/E */
  public Carrefour (Feu nord, Feu sud, Feu est) {
	  nbFeu = 3;
	  tabFeu = new Feu[3];
	  nord.addObserver(this);
	  sud.addObserver(this);
	  est.addObserver(this);
	  tabFeu[0] = nord;
	  tabFeu[1] = sud;
	  tabFeu[2] = est;
  }
  
  public Carrefour (Feu nord, Feu sud, Feu est, Feu ouest) {
	  nbFeu = 4;
	  nord.addObserver(this);
	  sud.addObserver(this);
	  est.addObserver(this);
	  ouest.addObserver(this);
	  tabFeu = new Feu[4];
	  tabFeu[0] = nord;
	  tabFeu[1] = sud;
	  tabFeu[2] = est;
	  tabFeu[3] = ouest;
  }
  
/*----- Autres methodes -----*/
private void bloquerEstOuest() {
	switch (nbFeu) {
	case 3:
		tabFeu[2].etat = EtatFeu.ROUGE;
	break;
	case 4:
		tabFeu[2].etat = EtatFeu.ROUGE;
		tabFeu[3].etat = EtatFeu.ROUGE;
	break;
	default:
		// erreur
	}
}

private void bloquerNord() {
	tabFeu[0].etat = EtatFeu.ROUGE;
}

private void bloquerSud() {
	tabFeu[1].etat = EtatFeu.ROUGE;
}

private void bloquerNordSud() {
	tabFeu[0].etat = EtatFeu.ROUGE;
	tabFeu[1].etat = EtatFeu.ROUGE;
}

private void autoriserEstOuest() {
	tabFeu[2].etat = EtatFeu.VERT;
	tabFeu[3].etat = EtatFeu.VERT;
}

private void autoriserNordSud() {
	tabFeu[0].etat = EtatFeu.VERT;
	tabFeu[1].etat = EtatFeu.VERT;
}

  @Override
public void update(Observable o, Object monObjet) {
  }

  @Override
public void run() {
	  switch(nbFeu) {
	  
	  }
  }
}