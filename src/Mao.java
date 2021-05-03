

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class Mao {
	//Metode abstracte per pintar els maons segons el color que volguem
	public abstract void paint(Graphics2D g);
	private static final int Y = 100;
	private static final int WITDH = 50;
	private static final int HEIGHT = 12;
	int x =0;
	int y;
	int xa = 0;
	private boolean tocatraquetaOno=false;
	private Game game;
	int ya = 1;
	int vida;
	char tipus;
	boolean tocable;
	public Mao(Game game,int x, int y,int vida, char tips,boolean toc) {
		this.game = game;
		this.x=x;
		this.y=y;
		this.vida=vida;
		this.tipus=tips;
		this.tocable=toc;
	}
	public int getVida() {
		return vida;
	}

	public boolean getTocable() {
		return tocable;
	}

	public void setTocable(boolean tocable) {
		this.tocable = tocable;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void move() {
			y = y +2;
	}



	public Rectangle getBounds() {
		return new Rectangle(x, y, WITDH, HEIGHT);
	}

	public char getTipus() {
		return tipus;
	}

	public void setTipus(char tipus) {
		this.tipus = tipus;
	}

	public int getXa() {
		return xa;
	}

	public void setXa(int xa) {
		this.xa = xa;
	}

	public int getYa() {
		return ya;
	}

	public void setYa(int ya) {
		this.ya = ya;
	}
}