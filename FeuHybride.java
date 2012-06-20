public class FeuHybride extends FeuTemps implements IFeuPieton {

	public FeuHybride(EtatFeu etat) {
		super(etat);
		// TODO Auto-generated constructor stub
	}

	@Override 
	public void run() {
		while(true) {
			if(etat == EtatFeu.VERT) {
				etat = EtatFeu.ROUGE;
				try {
					this.wait(red_time);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
					etat = EtatFeu.VERT;
					try {
						this.wait(vert_time);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}		
	}

	@Override
	public void changerEtat(EtatFeu e) {
		// TODO Auto-generated method stub
		
	}
}