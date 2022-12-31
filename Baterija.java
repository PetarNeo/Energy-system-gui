package sistem;

public class Baterija {
	
	private int trEnergija;
	private int kapacitet = 100;
	
	public Baterija(int kap) {
		kapacitet = kap;
		trEnergija = kapacitet;
	}
	public void napuni(int b) {
		trEnergija += b;
		if(trEnergija>kapacitet)trEnergija = kapacitet;
	}
	public void isprazni() {trEnergija = 0;}
	public boolean puna() {return trEnergija == kapacitet;}
	
	//
	public int dohvTrE() {return trEnergija;}
	public int dohvKap() {return kapacitet;}
}
