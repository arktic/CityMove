import java.util.ArrayList;


public class MapElement {

/*----- Attributs -----*/
	protected BackgroundElement myBackgroundElement;
	protected Feu myFeu;
	protected TypeMobileElement myTypeMobileElement;

/*----- Constructeurs -----*/
	/**
	 * 
	 * @param bg the BackgroundElement
	 * @param feu the Feu
	 * @param tmobile the TypeMobileElement
	 */
	public MapElement (BackgroundElement bg, Feu feu,TypeMobileElement tmobile) {
		myBackgroundElement = bg;
		myFeu = feu;
		myTypeMobileElement = tmobile;
	}

/*----- Accesseurs -----*/
	/**
	 * @return the myBackgroundElement
	 */
	synchronized public BackgroundElement getMyBackgroundElement() {
		return myBackgroundElement;
	}

	/**
	 * @param myBackgroundElement the myBackgroundElement to set
	 */
	synchronized public void setMyBackgroundElement(BackgroundElement myBackgroundElement) {
		this.myBackgroundElement = myBackgroundElement;
	}

	/**
	 * @return the myFeu
	 */
	synchronized public Feu getMyFeu() {
		return myFeu;
	}

	/**
	 * @param myFeu the myFeu to set
	 */
	synchronized public void setMyFeu(Feu myFeu) {
		this.myFeu = myFeu;
	}

	/**
	 * @return the myTypeMobileElement
	 */
	synchronized public TypeMobileElement getMyTypeMobileElement() {
		return myTypeMobileElement;
	}

	/**
	 * @param myTypeMobileElement the myTypeMobileElement to set
	 */
	synchronized public void setMyTypeMobileElement(TypeMobileElement myTypeMobileElement) {
		this.myTypeMobileElement = myTypeMobileElement;
	}

	/**
	 * 
	 * @param bg the BackGround to set
	 * @param feu the feu to set
	 * @param tmobile the TypeMobileElement to set
	 */
	synchronized public void set(BackgroundElement bg, Feu feu,TypeMobileElement tmobile) {
		myBackgroundElement = bg;
		myFeu = feu;
		myTypeMobileElement = tmobile;
	}
	
/*----- Autres methodes -----*/
	/**
	 * @return Tableau comprenant les directions possibles
	 */
	public ArrayList<Direction> getPossibilities() {
		ArrayList<Direction> tab = new ArrayList<Direction>();
		
		switch (this.myBackgroundElement) {
			
			case ROUTE_NORD :
				tab.add(Direction.NORD);
				break;
			case ROUTE_SUD :
				tab.add(Direction.SUD);
				break;
			case ROUTE_EST :
				tab.add(Direction.EST);
				break;
			case ROUTE_OUEST :
				tab.add(Direction.OUEST);
				break;
			case ROUTE_SUD_EST :
				tab.add(Direction.SUD);
				tab.add(Direction.EST);
				break;
			case ROUTE_SUD_OUEST :
				tab.add(Direction.SUD);
				tab.add(Direction.OUEST);
				break;
			case ROUTE_NORD_EST :
				tab.add(Direction.NORD);
				tab.add(Direction.EST);
				break;
			case ROUTE_NORD_OUEST :
				tab.add(Direction.NORD);
				tab.add(Direction.OUEST);
				break;
			case TROTTOIR_NORD_SUD :
				tab.add(Direction.NORD);
				tab.add(Direction.SUD);
				break;
			case TROTTOIR_EST_OUEST :
				tab.add(Direction.EST);
				tab.add(Direction.OUEST);
				break;
			case TROTTOIR_SUD_EST :
				tab.add(Direction.SUD);
				tab.add(Direction.EST);
				break;
			case TROTTOIR_SUD_OUEST :
				tab.add(Direction.SUD);
				tab.add(Direction.OUEST);
				break;
			case TROTTOIR_NORD_EST :
				tab.add(Direction.NORD);
				tab.add(Direction.EST);
				break;
			case TROTTOIR_NORD_OUEST :
				tab.add(Direction.NORD);
				tab.add(Direction.OUEST);
				break;
			default : System.out.println("Probleme dans le switch");
		}

		return tab;
	}
	
	/**
	 * 
	 * @return Renvoie vrai si le mapElement appelant est une route
	 */
	public boolean isRoute() {
		BackgroundElement bg = getMyBackgroundElement();
		
		return (bg == BackgroundElement.ROUTE_NORD || bg == BackgroundElement.ROUTE_SUD
				   || bg == BackgroundElement.ROUTE_EST || bg == BackgroundElement.ROUTE_OUEST
				   || bg == BackgroundElement.ROUTE_SUD_EST || bg == BackgroundElement.ROUTE_SUD_OUEST
				   || bg == BackgroundElement.ROUTE_NORD_EST || bg == BackgroundElement.ROUTE_NORD_OUEST
				   || bg == BackgroundElement.ROUTE_PIETON_NORD_SUD || bg == BackgroundElement.ROUTE_PIETON_EST_OUEST);
	}
	
	/**
	 * retourne vrai si l'element est une route pieton
	 * @return
	 */
	public boolean isRoutePieton() {
		BackgroundElement bg = getMyBackgroundElement();
		
		return (bg == BackgroundElement.ROUTE_PIETON_NORD_SUD || bg == BackgroundElement.ROUTE_PIETON_EST_OUEST);
	}
	
	
	
	/**
	 * mappage des elements pour pouvoir parser les fichiers maps
	 * @param i: code de l'element (represente dans le fichier map)
	 */
	public MapElement(Integer i) {
		//int s_int = Integer.parseInt(s);
		
		myFeu=null;
		myTypeMobileElement = TypeMobileElement.VIDE;
		
		switch (i) {
		case 0:
			myBackgroundElement = BackgroundElement.HERBE;
			break;
		case 1:
			myBackgroundElement = BackgroundElement.ROUTE_NORD;
			break;
		case 2:
			myBackgroundElement = BackgroundElement.ROUTE_SUD;
			break;
		case 3:
			myBackgroundElement = BackgroundElement.ROUTE_EST;
			break;
		case 4:
			myBackgroundElement = BackgroundElement.ROUTE_OUEST;
			break;
		case 5:
			myBackgroundElement = BackgroundElement.ROUTE_SUD_EST;
			break;
		case 6:
			myBackgroundElement = BackgroundElement.ROUTE_SUD_OUEST;
			break;
		case 7:
			myBackgroundElement = BackgroundElement.ROUTE_NORD_EST;
			break;
		case 8:
			myBackgroundElement = BackgroundElement.ROUTE_NORD_OUEST;
			break;
		case 9:
			myBackgroundElement = BackgroundElement.ROUTE_PIETON_EST_OUEST;
			break;
		case 10:
			myBackgroundElement = BackgroundElement.ROUTE_PIETON_NORD_SUD;
			break;
		case 11:
			myBackgroundElement = BackgroundElement.TROTTOIR_NORD_SUD;
			break;
		case 12:
			myBackgroundElement = BackgroundElement.TROTTOIR_EST_OUEST;
			break;
		case 13:
			myBackgroundElement = BackgroundElement.TROTTOIR_SUD_EST;
			break;	
		case 14:
			myBackgroundElement = BackgroundElement.TROTTOIR_SUD_OUEST;
			break;
		case 15:
			myBackgroundElement = BackgroundElement.TROTTOIR_EST_OUEST;
			break;
		case 16:
			myBackgroundElement = BackgroundElement.TROTTOIR_NORD_EST;
			break;
		case 17:
			myBackgroundElement = BackgroundElement.TROTTOIR_NORD_OUEST;
			break;
		case 100:
			myFeu = new FeuTemps(EtatFeu.ROUGE);
		break;
		case 101:
			myFeu = new FeuPieton(EtatFeu.ROUGE);
		break;
		case 102:
			myFeu = new FeuHybride(EtatFeu.ROUGE);
		break;
		default:
			System.out.println("Erreur lors dela transcription de int en BackgroundElement.");
			System.exit(1);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MapElement [myBackgroundElement=" + myBackgroundElement
				+ ", myFeu=" + myFeu + ", myTypeMobileElement="
				+ myTypeMobileElement + "]";
	}
	
	
	
}



