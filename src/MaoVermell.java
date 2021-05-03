


import java.awt.*;

public class MaoVermell extends Mao {


	private static final int WITDH = 50;
	private static final int HEIGHT = 12;

	public MaoVermell(Game game, int x, int y,int vida,char tips,boolean toc) {
		super(game,x,y,vida,tips,toc);
	}


	public void paint(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect(getX(), getY(), WITDH, HEIGHT);
	}
}
