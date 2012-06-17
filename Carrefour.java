import java.util.Observable;
import java.util.Observer;

public class Carrefour implements Observer {
/*----- Attributs -----*/
  public Feu tabFeu;
  public int nbFeu;

/*----- Autres methodes -----*/
  public void ordonnerChangementEtat(Feu monFeu, EtatFeu etatDemande) {
	  /* On utilise toujours Observable finalement ? */
  }

  @Override
public void update(Observable o, Object monObjet) {
  }

}