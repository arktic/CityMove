
public class MapElement {

	protected BackgroundElement myBackgroundElement;

	protected Feu myFeu;

	protected TypeMobileElement myTypeMobileElement;

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

	public MapElement (BackgroundElement bg, Feu feu,TypeMobileElement tmobile) {
		myBackgroundElement=bg;
		myFeu=feu;
		myTypeMobileElement=tmobile;
	}


	public void set(BackgroundElement bg, Feu feu,TypeMobileElement tmobile) {
		myBackgroundElement=bg;
		myFeu=feu;
		myTypeMobileElement=tmobile;
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



