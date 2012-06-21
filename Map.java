import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;


import javax.imageio.ImageIO;
import javax.swing.*;

//import sun.security.mscapi.KeyStore.MY;



public class Map extends JPanel{

	protected int nbLignes;
	protected int nbColonnes;
	protected int nbCarrefour;

	private String repertoire_workspace ="./Ressources/";
	protected int hauteur;
	protected int largeur;
	
	/**
	 * La taille en pixel d'un element (carré) de type MapElement
	 */
	protected int sizeElement;
	protected static HashMap<BackgroundElement, Image> backgroundImage;
	protected Vector<ElementMobiles> tabElementMobile;

	/**
	 * le tableau de bloc qui compose la map
	 */
	protected MapElement tabMapElement[][];

	private static int nbDefaultLigne = 15;
	private static int nbDefaultColonne = 15;



	/**
	 * Créé une map prédéfini
	 * sizeElem : la taille en pixel des tuiles qui la compose
	 */
	public Map(int sizeElem) {

		backgroundImage = new HashMap<BackgroundElement, Image>();
		this.nbColonnes = nbDefaultColonne;
		this.nbLignes = nbDefaultLigne;
		largeur = nbDefaultLigne * sizeElem;
		hauteur = nbDefaultColonne * sizeElem;
		this.tabElementMobile = new Vector<ElementMobiles>();

		this.tabMapElement = new MapElement[nbLignes][nbColonnes];
		CityMove.map=this;
		this.sizeElement = sizeElem;
		
		
		/* On ajoute notre base de donnée d'images de Background à notre image */
		ajouterBackgroundImages();

		/* On ouvre une map préfabriquée */
		open(repertoire_workspace+"Map/map1.txt");
		System.out.println("VA FEU");
		openFeu(repertoire_workspace+"Map/map1.txt");
		System.out.println("FEU FAIT");
/*		try {
			this.wait(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		ElementMobileGenerateur generateur = new ElementMobileGenerateur();
		generateur.start();
		
		

		setFocusable(true);
		setDoubleBuffered(true);

	}











	/**
	 * @param heightInTiles
	 * @param widthInTiles
	 * @param sizeElem
	 */
	/*public Map(int nbLignes, int nbColonnes, int sizeElem) {
		backgroundImage = new HashMap<BackgroundElement, Image>();

		backgroundImage = new HashMap<BackgroundElement, Image>(); 
		this.nbColonnes = nbColonnes;
		this.nbLignes = nbLignes;
		largeur = nbDefaultLigne * sizeElem;
		hauteur = nbDefaultColonne * sizeElem;
		this.tabElementMobile = new Vector<ElementMobiles>();

		this.tabMapElement = new MapElement[nbLignes][nbColonnes];

*/
		/* On ajoute notre base de donnée d'images de Background à notre image */
	//	ajouterBackgroundImages();

		/* On remplit la map avec de l'herbe */
	//	remplirDefaultMap();
/*


		CityMove.map=this;
		this.sizeElement = sizeElem;



		setFocusable(true);
		setDoubleBuffered(true);
	}
*/

	/**
	 * Ajoute toutes les images de background à notre HashMap
	 */
	private void ajouterBackgroundImages() {
		Image img;


		try {
			/* -- Image de background */
			img = ImageIO.read(new File(repertoire_workspace+"Background/herbe.jpg"));
			backgroundImage.put(BackgroundElement.HERBE, img);

			img = ImageIO.read(new File(repertoire_workspace+"Background/route_nord.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_NORD, img);

			img = ImageIO.read(new File(repertoire_workspace+"Background/route_sud.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_SUD, img);

			img = ImageIO.read(new File(repertoire_workspace+"Background/route_est.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_EST, img);

			img = ImageIO.read(new File(repertoire_workspace+"Background/route_ouest.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_OUEST, img);

			img = ImageIO.read(new File(repertoire_workspace+"Background/route_nord_est.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_NORD_EST, img);

			img = ImageIO.read(new File(repertoire_workspace+"Background/route_nord_ouest.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_NORD_OUEST, img);

			img = ImageIO.read(new File(repertoire_workspace+"Background/route_sud_est.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_SUD_EST, img);

			img = ImageIO.read(new File(repertoire_workspace+"Background/route_sud_ouest.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_SUD_OUEST, img);

			
			
			
		} catch (IOException e) {
			System.out.println("Erreur lors du chargment des images...");
			e.printStackTrace();
		}



	}


	public void deplacementElementMobile() {

		int indice=0;	
		for(indice=0;indice<getSizeTabElementMobile();indice++){			

			getTabElementMobileAt(indice).seDeplacer();
		}

	}


	synchronized public ElementMobiles getTabElementMobileAt(int indice) {
		return getTabElementMobile().get(indice);
	}


	synchronized public int getSizeTabElementMobile() {
		return getTabElementMobile().size();
	}


	/**
	 * @return the tabElementMobile
	 */
	public synchronized Vector<ElementMobiles> getTabElementMobile() {
		return tabElementMobile;
	}











	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D)g;

		/* affichage du background et des feux */
		for(int c=0 ; c < nbColonnes ; c++ ){
			for(int l=0 ; l < nbLignes ; l++) {
				Image img;
				MapElement courant = tabMapElement[l][c];
				img = backgroundImage.get(courant.myBackgroundElement);
				g2d.drawImage( img, c*sizeElement, l*sizeElement, this);
				
				
				if(courant.getMyFeu()!=null) {
					switch(courant.getMyFeu().getEtat()) {
					
					case ROUGE:
						g2d.drawImage( Toolkit.getDefaultToolkit().getImage(repertoire_workspace+"FixeElement/feu_rouge.png"), c*sizeElement, l*sizeElement, this);
						break;
					case VERT:
						g2d.drawImage( Toolkit.getDefaultToolkit().getImage(repertoire_workspace+"FixeElement/feu_vert.png"), c*sizeElement, l*sizeElement, this);
						break;
					}
				}
			}
			
			
		}



		for(int indice=0;indice<getSizeTabElementMobile();indice++) {
				/* Determiner quelle image à afficher, et aussi dans quel sens */
				
				
				ElementMobiles courant = getTabElementMobileAt(indice);
				switch(courant.getDirection()) {
				case NORD:
					g2d.drawImage(Toolkit.getDefaultToolkit().getImage(repertoire_workspace+"MobileElement/voiture_nord.png"), courant.getPosition().getX(), courant.getPosition().getY(), this);
					break;
				case SUD:
					g2d.drawImage(Toolkit.getDefaultToolkit().getImage(repertoire_workspace+"MobileElement/voiture_sud.png"), courant.getPosition().getX(), courant.getPosition().getY(), this);
					break;
				case EST:
					g2d.drawImage(Toolkit.getDefaultToolkit().getImage(repertoire_workspace+"MobileElement/voiture_est.png"), courant.getPosition().getX(), courant.getPosition().getY(), this);
					break;
				case OUEST:
					g2d.drawImage(Toolkit.getDefaultToolkit().getImage(repertoire_workspace+"MobileElement/voiture_ouest.png"), courant.getPosition().getX(), courant.getPosition().getY(), this);
					break;
					
				default:
					System.out.println("Erreur lors de l'affichage voiture");
				}
		}
		
		
		
		
		
		
		
	}











	/**
	 * Remplit la map avec de l'herbe
	 */
	public void remplirDefaultMap () {
		/* On remplit notre nouvelle map de cases vides */
		for(int l=0; l<nbLignes; l++) {
			for(int c=0; c<nbColonnes; c++) {
				MapElement newMapElement;

				newMapElement = new MapElement(BackgroundElement.HERBE, null, TypeMobileElement.VIDE);
				tabMapElement[l][c] = newMapElement;
			}
		}
	}










	/* ACCESSEURS */
	/**
	 * @return le nombre de tuiles en hauteur de la map
	 */
	public int getHauteurInTiles() {
		return nbLignes;
	}

	/**
	 * @return le nombre de tuiles en largeur de la map
	 */
	public int getLargeurInTiles() {
		return nbColonnes;
	}


	/**
	 * @return the sizeElement
	 */
	public int getSizeElement() {
		return sizeElement;
	}



	synchronized public int addElementMobile(ElementMobiles monElementMobile) {
		synchronized(tabElementMobile) {
			tabElementMobile.add(monElementMobile);
		}
		return 0;
	}

	synchronized public int removeElementMobile(ElementMobiles monElementMobile) {
		
			getTabElementMobile().remove(monElementMobile);
		
		return 0;
	}

	/**
	 * @param x la position sur les x de l'éléments que l'on veut obtenir
	 * @param y la position sur les y de l'éléments que l'on veut obtenir
	 * @return
	 */
	synchronized public MapElement getMapElement(int x,int y) {
		return tabMapElement[x][y];
	}


	/**
	 * @param tilesCoord coordonnees en  Tuiles dont on veut recuperer le MapElement
	 * @return
	 */
	synchronized public MapElement getMapElement(Coordonnee tilesCoord) {
		return tabMapElement[tilesCoord.getY()][tilesCoord.getX()];
	}





	/**
	 * Renvoie la position en tuiles d'une position en pixels
	 * @param pixelsPosition la position en pixels
	 * @return
	 */
	public Coordonnee getPositionInTiles(Coordonnee pixelsPosition) {
		Coordonnee tilesPosition = new Coordonnee(pixelsPosition.getX()/sizeElement,pixelsPosition.getY()/sizeElement);
		
		return tilesPosition;
	}





	/**
	 * @return the hauteur
	 */
	public int getHauteur() {
		return hauteur;
	}











	/**
	 * @param hauteur the hauteur to set
	 */
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}


	/**
	 * @return the largeur
	 */
	public int getLargeur() {
		return largeur;
	}


	/**
	 * @param largeur the largeur to set
	 */
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	/**
	 * Ouvre un fichier contenant une map
	 * @param filename le fichier a ouvrir
	 * @return 0 en cas de succes, 1 sinon
	 */
	//TODO : securité si lecture foire
	public int open(String filename) {
		if (filename==null) return 1 ;

		int encore = 1 ;
		int recup_param =0;

		nbLignes=nbColonnes=0;

		FichierLecture fe = new FichierLecture (filename) ;
		int l=0;
		while (encore == 1) {
			String s = fe.lireLigne() ;
			//System.out.println("Lecture de "+s);
			StringTokenizer st = new StringTokenizer(s, new String(" ")) ;
			String sg = st.nextToken().trim() ;

			if (sg.compareToIgnoreCase("NB_LIGNES")==0){
				nbLignes = Integer.parseInt(st.nextToken().trim()) ;
				recup_param++;
			}

			if (sg.compareToIgnoreCase("NB_COLONNES")==0) {
				nbColonnes = Integer.parseInt(st.nextToken().trim()) ;
				recup_param++;
			}
			
			//System.out.println("nbl= "+nbLignes+" nbc = "+nbColonnes);
			if(recup_param==2) {
				recup_param=0;
				//	System.out.println("Allocation de tab de nbl = "+nbLignes+" nbCol = "+nbColonnes);
				tabMapElement = new MapElement[nbLignes][nbColonnes];
			}

			if ( sg.compareToIgnoreCase("LIGNE")==0) {// && nbLignes!=0 && nbColonnes!=0) {

				StringTokenizer st2 = new StringTokenizer(st.nextToken().trim(), new String(":"));
				for (int c=0;c<nbColonnes;c++) {
					int inte = new Integer(st2.nextToken()) ;
					MapElement newElem = new MapElement(inte);
					
					
				/*	if(l==10 && c==3)
					newElem.setMyFeu(new FeuTemps(EtatFeu.ROUGE));
					
					if(l==13 && c==4)
						newElem.setMyFeu(new FeuTemps(EtatFeu.ROUGE));						
					*/
					//System.out.println("l= "+l+" c = "+c);

					tabMapElement[l][c] = newElem;
				}

				++l;
				if(l==nbLignes) {
					encore = 0 ;						
				}
			}

		}
		
		
		
		largeur = nbColonnes*sizeElement;
		hauteur = nbLignes*sizeElement;

		fe.fermer();

		return 0 ;

	}
	
	/**
	 * fonction de chargement les feux, appel unique dans le constructeur de map
	 * @param filename fichier source
	 * @return non utilisé
	 */
	public int openFeu(String filename) {
		if (filename==null) return 1 ;

		int encore = 1 ;

		FichierLecture fe = new FichierLecture (filename) ;
		while (encore > 0) {
			String s = fe.lireLigne() ;
			//System.out.println("Lecture de "+s);
			StringTokenizer st = new StringTokenizer(s, new String(" ")) ;
			String sg = st.nextToken().trim() ;	
			if (sg.compareToIgnoreCase("NB_CARREFOUR")==0) {
				nbCarrefour = Integer.parseInt(st.nextToken().trim()) ;
				System.out.println("NB_CARREFOUR:" + nbCarrefour);
				encore = nbCarrefour;
			}

			if (sg.compareToIgnoreCase("CARREFOUR") == 0) {
				System.out.println("Trouve CARREFOUR");
				String ss = null, snbfeu , sinfofeu, sfeu;
				StringTokenizer stnbFeu = new StringTokenizer(st.nextToken(), new String(";"));
				snbfeu = stnbFeu.nextToken().trim();
				//System.out.println("snbfeu: "+snbfeu);
				
				StringTokenizer stInfoFeu = new StringTokenizer(st.nextToken(), new String(","));
				//System.out.println("stINFOFEU:"+stInfoFeu.nextToken());
				//System.out.println("stINFOFEU:"+stInfoFeu.nextToken());


					int nbFeu = new Integer(snbfeu);
					System.out.println("nbFeu: " + nbFeu);
					Feu tabFeu[] = new Feu[nbFeu];
					/* récupération des n feux du carrefour */
					for(int i=0; i < nbFeu; i++) {
						int intf = new Integer(stInfoFeu.nextToken());
						Coordonnee cf = new Coordonnee(new Integer(stInfoFeu.nextToken()) ,new Integer( stInfoFeu.nextToken()));
						System.out.println("Coord:" + cf);
						switch(intf) {
						case 100:
							System.out.println("100");
							tabFeu[i] = new FeuTemps(cf);
							break;
						case 101:
							System.out.println("101");
							tabFeu[i] = new FeuPieton(cf);
							break;
						case 102:
							System.out.println("102");
							tabFeu[i] = new FeuHybride(cf);
							break;
						default:
							// erreur
							System.out.println("Code feu erroné");
							System.exit(1);
						}
					}
					/* on a tout les feux d'un carrefour, on crée les liens */
					for(int j=0; j<nbFeu;j++) {
						System.out.println("Observer ajouter à  j:"+ j);
						for(int k = j+1; k<nbFeu+j ; k++) {
							tabFeu[j].addObserver(tabFeu[k%nbFeu]);
							System.out.println("liste: -" + k%nbFeu); 
						}
						/* attribution à tabMapElement */
						Coordonnee c = new Coordonnee(tabFeu[j].getPositionInTiles());
						tabMapElement[c.getY()][c.getX()].myFeu = tabFeu[j];
					}
					for(int y = 0; y< nbFeu ; y++) {
						new Thread((Runnable)tabFeu[y]).start();
					}

				encore --;
			}
		}
		fe.fermer();
		return 0;
	}
}
