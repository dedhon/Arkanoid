

import java.awt.*;
import java.awt.event.KeyEvent;

public class Racquet {
	private final int Y = 430;
	private static final int WITH = 80;
	private static final int HEIGHT = 10;
	int x = 0;
	int xa = 0;
	private Game game;

	public Racquet(Game game) {
		this.game = game;
	}

	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - WITH)
			x = x + xa;
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.yellow);
		g.fillRect(x, Y, WITH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -game.speed;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = game.speed;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, Y, WITH, HEIGHT);
	}

	public int getTopY() {
		return Y - HEIGHT;
	}

	public int getY() {
		return Y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
}