


import java.awt.*;

public class MaoBlau extends Mao {

	private static final int WITDH = 50;
	private static final int HEIGHT = 12;

	public MaoBlau(Game game, int x, int y,int vida,char tips,boolean toc) {
		super(game,x,y,vida,tips,toc);
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.blue);
		g.fillRect(getX(), getY(), WITDH, HEIGHT);
	}
}
