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
import javax.swing.text.html.HTMLDocument.Iterator;


public class Map extends JPanel{

	protected int nbLignes;
	protected int nbColonnes;


	public int X1 = 120;
	public int Y1 = 570;
	
	public int X2 = 720;
	public int Y2 = 150;
	
	
	/**
	 * La taille en pixel d'un element (carr�) de type MapElement
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
	 * Cr�� une map pr�d�fini
	 * sizeElem : la taille en pixel des tuiles qui la compose
	 */
	public Map(int sizeElem) {
		
		backgroundImage = new HashMap<BackgroundElement, Image>();
		this.nbColonnes = nbDefaultColonne;
		this.nbLignes = nbDefaultLigne;
		this.tabElementMobile = new Vector<ElementMobiles>();
		
		this.tabMapElement = new MapElement[nbLignes][nbColonnes];
		
		
		/* On ajoute notre base de donn�e d'images de Background � notre image */
		ajouterBackgroundImages();
		
		/* On remplit la map avec de l'herbe */
		//remplirDefaultMap();

		open("./CityMove/Ressources/Map/map1.txt");
		//tabMapElement = map1;
		CityMove.map=this;
		this.sizeElement = sizeElem;
		//System.out.println("ELEM = 3 : 0 : "+tabMapElement[0][3]);
		addElementMobile(new Voiture(3*sizeElem,0*sizeElem,Direction.SUD));
		
		ElementMobileGenerateur generateur = new ElementMobileGenerateur();
		
		setFocusable(true);
		setDoubleBuffered(true);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @param heightInTiles
	 * @param widthInTiles
	 * @param sizeElem
	 */
	public Map(int nbLignes, int nbColonnes, int sizeElem) {
		backgroundImage = new HashMap<BackgroundElement, Image>();

		backgroundImage = new HashMap<BackgroundElement, Image>(); 
		this.nbColonnes = nbColonnes;
		this.nbLignes = nbLignes;

		this.tabElementMobile = new Vector<ElementMobiles>();
		
		this.tabMapElement = new MapElement[nbLignes][nbColonnes];
		
		
		/* On ajoute notre base de donn�e d'images de Background � notre image */
		ajouterBackgroundImages();
		
		/* On remplit la map avec de l'herbe */
		remplirDefaultMap();
		
		
		
		CityMove.map=this;
		this.sizeElement = sizeElem;
		
		

		setFocusable(true);
		setDoubleBuffered(true);
	}

	
	/**
	 * Ajoute toutes les images de background � notre HashMap
	 */
	private void ajouterBackgroundImages() {
		Image img;
		
		
		try {
			img = ImageIO.read(new File("./CityMove/Ressources/Background/herbe.jpg"));
			backgroundImage.put(BackgroundElement.HERBE, img);
			
			img = ImageIO.read(new File("./CityMove/Ressources/Background/route_nord.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_NORD, img);
			
			img = ImageIO.read(new File("./CityMove/Ressources/Background/route_sud.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_SUD, img);
			
			img = ImageIO.read(new File("./CityMove/Ressources/Background/route_est.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_EST, img);
			
			img = ImageIO.read(new File("./CityMove/Ressources/Background/route_ouest.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_OUEST, img);
			
			img = ImageIO.read(new File("./CityMove/Ressources/Background/route_nord_est.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_NORD_EST, img);
			
			img = ImageIO.read(new File("./CityMove/Ressources/Background/route_nord_ouest.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_NORD_OUEST, img);
			
			img = ImageIO.read(new File("./CityMove/Ressources/Background/route_sud_est.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_SUD_EST, img);
			
			img = ImageIO.read(new File("./CityMove/Ressources/Background/route_sud_ouest.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_SUD_OUEST, img);
			
			
		} catch (IOException e) {
			System.out.println("Erreur lors du chargment des images...");
			e.printStackTrace();
		}
		
		
		
	}


	public void deplacementElementMobile() {
		java.util.Iterator<ElementMobiles> it = tabElementMobile.iterator();
		//System.out.println("Deplacement");
		/* On parcours tout nos elements mobiles et on les fait se d�placer */
		while (it.hasNext()){
			//System.out.println("while ");
			it.next().seDeplacer();
		}
		
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D)g;

		for(int c=0 ; c < nbColonnes ; c++ ){
			for(int l=0 ; l < nbLignes ; l++) {
				Image img;
				img = backgroundImage.get(tabMapElement[l][c].myBackgroundElement);
				
				g2d.drawImage( img, c*sizeElement, l*sizeElement, this);	
			}
		}

		g2d.drawImage( Toolkit.getDefaultToolkit().getImage("./CityMove/Ressources/Background/voiture.png"), X1, Y1, this);	
		g2d.drawImage( Toolkit.getDefaultToolkit().getImage("./CityMove/Ressources/Background/voiture.png"), X2, Y2, this);	
		g2d.drawImage( Toolkit.getDefaultToolkit().getImage("./CityMove/Ressources/Background/voiture.png"), tabElementMobile.get(0).position.x, tabElementMobile.get(0).position.y, this);	
		
		/*Toolkit.getDefaultToolkit().sync();
		g.dispose();
		*/
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
	 * @param tabMapElement the tabMapElement to set
	 */
	public void setTabMapElement(MapElement[][] tabMapElement) {
		this.tabMapElement = tabMapElement;
	}
	
	/**
	 * @return the sizeElement
	 */
	public int getSizeElement() {
		return sizeElement;
	}



	public int addElementMobile(ElementMobiles monElementMobile) {
		tabElementMobile.add(monElementMobile);
		return 0;
	}

	public int removeElementMobile(ElementMobiles monElementMobile) {
		tabElementMobile.remove(monElementMobile);
		return 0;
	}

	/**
	 * @param x la position sur les x de l'�l�ments que l'on veut obtenir
	 * @param y la position sur les y de l'�l�ments que l'on veut obtenir
	 * @return
	 */
	public MapElement getMapElement(int x,int y) {
		return tabMapElement[x][y];
	}
	
	
	/**
	 * @param tilesCoord coordonnees en  Tuiles dont on veut recuperer le MapElement
	 * @return
	 */
	public MapElement getMapElement(Coordonnee tilesCoord) {
		return tabMapElement[tilesCoord.getY()][tilesCoord.getX()];
	}

	
	
	
	
	/**
	 * Renvoie la position en tuiles d'une position en pixels
	 * @param pixelsPosition la position en pixels
	 * @return
	 */
	public Coordonnee getPositionInTiles(Coordonnee pixelsPosition) {
		
		//.out.print("getPositionInTiles de "+pixelsPosition);
		Coordonnee tilesPosition = new Coordonnee(pixelsPosition.getX()/sizeElement,pixelsPosition.getY()/sizeElement);
		//System.out.println("  vaut "+tilesPosition);
		return tilesPosition;
	}


	
	
	
	/**
	 * Ouvre un fichier contenant une map
	 * @param filename le fichier a ouvrir
	 * @return 0 en cas de succes, 1 sinon
	 */
	//TODO : securit� si lecture foire
	public int open(String filename) {
			if (filename==null) return 1 ;

			int encore = 1 ;
			int recup_ligne_colonne =0;
			
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
					recup_ligne_colonne++;
				}
				
				if (sg.compareToIgnoreCase("NB_COLONNES")==0) {
					nbColonnes = Integer.parseInt(st.nextToken().trim()) ;
					recup_ligne_colonne++;
				}
				
				//System.out.println("nbl= "+nbLignes+" nbc = "+nbColonnes);
				if(recup_ligne_colonne==2) {
					recup_ligne_colonne=0;
				//	System.out.println("Allocation de tab de nbl = "+nbLignes+" nbCol = "+nbColonnes);
					tabMapElement = new MapElement[nbLignes][nbColonnes];
				}
				
				if ( sg.compareToIgnoreCase("LIGNE")==0) {// && nbLignes!=0 && nbColonnes!=0) {
					
					StringTokenizer st2 = new StringTokenizer(st.nextToken().trim(), new String(":"));
					for (int c=0;c<nbColonnes;c++) {
						int inte = new Integer(st2.nextToken()) ;
						MapElement newElem = new MapElement(inte);
						//System.out.println("l= "+l+" c = "+c);
						tabMapElement[l][c] = newElem;
					}
					
					++l;
					if(l==nbLignes) {
						encore = 0 ;						
					}
				}
				
			}

			
			fe.fermer();

			return 0 ;

		}
	}
