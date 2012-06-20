import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Carrefour implements Observer {
	/* le carrefour un thread ?? */
	/*----- Attributs -----*/
	public ArrayList<Feu> tabFeu;
	public int nbFeu;

	

	/*----- Constructeurs ----- */
	/* tous les carrefours a 2 feux sont vus comme des carrefours avec feu nord et feu sud */
	public Carrefour (Feu nord, Feu sud) {
		nbFeu = 2;
		
		nord.addObserver(this);
		sud.addObserver(this);
		tabFeu = new ArrayList<Feu>();
		tabFeu.add(nord);
		tabFeu.add(sud);
	}

	/* tous les carrefours a 3 feux sont vus comme des carrefour N/S/E */
	public Carrefour (Feu nord, Feu sud, Feu est) {
		nbFeu = 3;
		nord.addObserver(this);
		sud.addObserver(this);
		est.addObserver(this);
		tabFeu = new ArrayList<Feu>();
		tabFeu.add(nord);
		tabFeu.add(sud);
		tabFeu.add(est);
	}

	public Carrefour (Feu nord, Feu sud, Feu est, Feu ouest) {
		nbFeu = 4;
		nord.addObserver(this);
		sud.addObserver(this);
		est.addObserver(this);
		ouest.addObserver(this);
		tabFeu = new ArrayList<Feu>();
		tabFeu.add(nord);
		tabFeu.add(sud);
		tabFeu.add(est);
		tabFeu.add(ouest);
	}

	/*----- Autres methodes -----*/
	private void bloquerEstOuest() {
		switch (nbFeu) {
		case 3:
			tabFeu.get(0).etat = EtatFeu.ROUGE;
			break;
		case 4:
			tabFeu.get(2).etat = EtatFeu.ROUGE;
			tabFeu.get(3).etat = EtatFeu.ROUGE;
			break;
		default:
			// erreur
		}
	}

	private void bloquerNord() {
		tabFeu.get(0).etat = EtatFeu.ROUGE;
	}

	private void bloquerSud() {
		tabFeu.get(1).etat = EtatFeu.ROUGE;
	}

	private void bloquerNordSud() {
		tabFeu.get(0).etat = EtatFeu.ROUGE;
		tabFeu.get(1).etat = EtatFeu.ROUGE;
	}

	private void autoriserEstOuest() {
		tabFeu.get(2).etat = EtatFeu.VERT;
		tabFeu.get(3).etat = EtatFeu.VERT;
	}

	private void autoriserNordSud() {
		tabFeu.get(0).etat = EtatFeu.VERT;
		tabFeu.get(1).etat = EtatFeu.VERT;
	}


	//TODO
	@Override
	public void update(Observable o, Object valeurDemandee) {
		try {  
			Feu feuEnQuestion = (Feu) o;  


			/* Ici, il faut checker nombre de feu dans ce carrefour, checker quel est le feu qui a demander kke chose, et agir en conséquence */
			
			/* On récupère l'indice du feu qui nous demande un changement */
			int indiceFeu = tabFeu.indexOf(feuEnQuestion);
			/* Si le feu appartient bien à ce carrefour */
			if(indiceFeu!=-1) {
				//TODO
			}
			
		} catch (Exception e) {  
			e.printStackTrace();  
		}  



	}
}