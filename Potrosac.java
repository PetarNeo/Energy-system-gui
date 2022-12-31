package sistem;

import java.awt.Color;

public abstract class Potrosac extends Parcela {
	
	protected Baterija baterija;
	protected EnergetskiSistem vlasnikSistem;
	
	public Potrosac(char o, Color b,Baterija bat) {
		super(o, b);
		baterija = bat;
	}
	public abstract int potrosnja();
	

}
