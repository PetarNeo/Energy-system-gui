package sistem;

import java.awt.Color;
import java.util.Random;

public abstract class Proizvodjac extends Parcela{
	
	protected int vreme;
	protected Baterija baterija;
	protected EnergetskiSistem vlasnikSistem;
	
	public Proizvodjac(char o, Color b,int vr,Baterija bat) {
		super(o, b);
		vreme = vr;
		baterija = bat;
	}
	public int ukupnoVreme() {
		return vreme+ new Random().nextInt(300);
	}
	
	public abstract boolean proizvodnjaEnergije();
	public abstract void zaustavi();
}
