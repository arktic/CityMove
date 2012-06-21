public class FeuHybride extends FeuTemps {

	
	public FeuHybride(EtatFeu etat) {
		super(etat);
		// TODO Auto-generated constructor stub
	}

	public FeuHybride() {
		super();
	}
	
	public FeuHybride(Coordonnee c) {
		super(c);
	}
	
	@Override 
	public void run() {
		while(true) {
			if(getEtat() == EtatFeu.VERT) {
				try {
					this.wait(vert_time);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setEtat(EtatFeu.ROUGE);
			}
			else {
					try {
						this.wait(red_time);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				setEtat(EtatFeu.VERT);
			}
			//setBusy(true);
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // attente de la r�ponse du carrefour	
			//setBusy(false);
		}		
	}
}