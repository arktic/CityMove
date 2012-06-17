import java.util.ArrayList;


public class MapElement {

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
	public BackgroundElement getMyBackgroundElement() {
		return myBackgroundElement;
	}

	/**
	 * @param myBackgroundElement the myBackgroundElement to set
	 */
	public void setMyBackgroundElement(BackgroundElement myBackgroundElement) {
		this.myBackgroundElement = myBackgroundElement;
	}

	/**
	 * @return the myFeu
	 */
	public Feu getMyFeu() {
		return myFeu;
	}

	/**
	 * @param myFeu the myFeu to set
	 */
	public void setMyFeu(Feu myFeu) {
		this.myFeu = myFeu;
	}

	/**
	 * @return the myTypeMobileElement
	 */
	public TypeMobileElement getMyTypeMobileElement() {
		return myTypeMobileElement;
	}

	/**
	 * @param myTypeMobileElement the myTypeMobileElement to set
	 */
	public void setMyTypeMobileElement(TypeMobileElement myTypeMobileElement) {
		this.myTypeMobileElement = myTypeMobileElement;
	}

	/**
	 * 
	 * @param bg the BackGround to set
	 * @param feu the feu to set
	 * @param tmobile the TypeMobileElement to set
	 */
	public void set(BackgroundElement bg, Feu feu,TypeMobileElement tmobile) {
		myBackgroundElement = bg;
		myFeu = feu;
		myTypeMobileElement=tmobile;
	}
	
/*----- Autres methodes -----*/
	/**
	 * @return Tableau comprenant les directions possibles
	 */
	public ArrayList<Direction> getPossibilities(){
		ArrayList<Direction> tab = new ArrayList<Direction>();
		
		switch (this.myBackgroundElement){
			
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
			case TROTOIR_NORD_OUEST :
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
	public Boolean isRoute() {
		BackgroundElement bg = getMyBackgroundElement();
		return (bg == BackgroundElement.ROUTE_NORD || bg == BackgroundElement.ROUTE_SUD
				   || bg == BackgroundElement.ROUTE_EST || bg == BackgroundElement.ROUTE_OUEST
				   || bg == BackgroundElement.ROUTE_SUD_EST || bg == BackgroundElement.ROUTE_SUD_OUEST
				   || bg == BackgroundElement.ROUTE_NORD_EST || bg == BackgroundElement.ROUTE_NORD_OUEST
				   || bg == BackgroundElement.ROUTE_PIETON_NORD_SUD || bg == BackgroundElement.ROUTE_PIETON_EST_OUEST);
	}
	
	
}



