package sistem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Parcela extends Label {
	
	protected char oznaka;
	protected Color boja;
	protected Plac vlasnik;
	protected int indeks;
	protected int x,y;
	protected Font font = new Font("Serif",Font.BOLD,14);
	
	public Parcela(char o, Color b) {
		oznaka = o;
		boja = b;
		
		setBackground(boja);
		setText(""+oznaka);
		setAlignment(Label.CENTER);
		setForeground(Color.WHITE);
		setFont(font);
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
					vlasnik.kliknuto(Parcela.this);
			}
		});
	}
}
