

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JPanel {
	//Creem la variable que ens dira quantes vides ens queden per jugar i un metode GET per retornar les vides que tenim
	int vidas = 3;
	public int getVidas()
	{
		return vidas;
	}
	//Una variable de la posicio del mao que hens han enviat desde la classe ball i el seu seter per poder donarli valor.
	int pos;
	public void setPos(int pos1) {
		this.pos = pos1;
	}
	Ball ball = new Ball(this);
	Graphics2D g2d;
	Racquet racquet = new Racquet(this);
	//L'array list de maons
	public static ArrayList<Mao> maons;
	int speed = 2;
	//Variable per mostrar quina puntuació portem i el seu metode GET per retornar el valor
	int puntuacio=0;
	private int getPunt()
	{
		return puntuacio;
	}
	public Game() {
		this.setBackground(Color.black);
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		setFocusable(true);
		//Sound.BACK.loop();
	}
	//Metode per poder moure els maons vermells, i eliminarlos si han caigut per sota de la posició de la pala
	public void moureMaons()
	{
		//Variable per ficar el tipus de mao que li hem passat desde la classe BALL
		char aux = 0;
		//Try catch per controlar la excepcio que es produeix al pasarli desde la classe BALL una posicio no existent, aixi podra seguir
		//continuar jugant
		try{
			aux = maons.get(pos).getTipus();
		}catch (Exception e)
		{

		}
		//Si el mao que ens ha collisionat que ens passen la posició de la classe BALL es un mao vermell i esta tocat i sense vida,
		//cridem al metode de moures dels maons per que baixi.
		if(aux == 'r' && maons.get(pos).getTocable()==false)
		{

			maons.get(pos).move();
			//Si el mao esta per sota de la posició de la pala, l'eliminem del array i per tant del joc
			if(maons.get(pos).getY()>=450)
			{
				maons.remove(pos);
			}
		}
		//Si el mao vermell caigut, toca la pala, fem que s'acabi la partida
		try{
			if(maons.get(pos).getY()==racquet.getY())
			{
				if(maons.get(pos).getX()>=racquet.getX()-55 && maons.get(pos).getX()<=racquet.getX()+75)
				{
					VermellTocat();
				}
			}
			System.out.println(racquet.getX() + "-------"+maons.get(pos).getX());
		}catch (Exception e)
		{

		}


	}

	private void move() {
		ball.move();
		moureMaons();
		racquet.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);

		//Creem i pintem els maons
		pintarElsMaonets(g2d);
		g2d.setColor(Color.blue);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		//Assignem text al panell de puntuació i de quantes vides ens queden
		g2d.drawString("Puntuació:" + String.valueOf(getPunt() + " punts."), 100, 30);
		g2d.drawString("Vides:" + String.valueOf(getVidas()), 480, 30);
	}
	//Metode que fara que s'acabi el joc si ens quedem sense vida
	public void gameOver() {
		Sound.BACK.stop();
		Sound.GAMEOVER.play();
		JOptionPane.showMessageDialog(this, "La teva puntuació ha sigut: " + getPunt(), "Fi partida", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	//Metode que fara que s'acabi el joc si ens toca el mao vermell, fent que ens mati de 1 cop
	public void VermellTocat() {
		Sound.BACK.stop();
		Sound.GAMEOVER.play();
		JOptionPane.showMessageDialog(this, "El mao vermell t'ha tocat!!, Mala sort, has perdut!, Punts aconseguits: " + getPunt(), "Fi partida", JOptionPane.YES_NO_CANCEL_OPTION);
		System.exit(ABORT);
	}
	//Metode que finalitzara el joc per que hem destruit tots els maons
	public void victoria() {
		Sound.BACK.stop();
		Sound.GAMEOVER.play();
		JOptionPane.showMessageDialog(this, "Felicitats!!, has destruit tots els maons!!, Puntuació aconseguida: " + getPunt(), "Fi partida", JOptionPane.YES_NO_CANCEL_OPTION);
		System.exit(ABORT);
	}


	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Arkanoid");
		Game game = new Game();
		frame.add(game);
		frame.setBackground(Color.black);
		frame.setSize(700, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Variable int que ens servira per definir la posició de x dels maons en cada secció de colors dels maons, la qual sempre sera 5
		final int cons =5;
		//Instanciem el array list de maons
		maons = new ArrayList<Mao>();
		int x1 = cons;
		//Bucle on crearem 30 maons, els 10 primers de color blau, els següents vermells i per ultim verds
		for(int i=0; i<30;i++)
		{
			//10 maons de color blau
			if(i<10)
			{
				//Li diem que la Y es 80 per que els creei a la mateixa linea
				int y1 = 80;
				MaoBlau mao = new MaoBlau(game,x1,y1,1,'a',true);
				maons.add(mao);
				//Augmentem la X per que es vaguin movent cap a la dreta de la posició inicial
				x1=x1+70;
				//Si ja hem creat els 10 maons, per que els seguents maons es fiquin en la posicio 5, li asignem a la X el valor de la constant.
				if(i==9)
				{
					x1=cons;
				}
			}
			else{
				//10 maons de color vermell
				if(i>9 && i<20)
				{
					//Li diem que la Y es 100 per que els creei a la mateixa linea
					int y1 = 100;
					MaoVermell mao1 = new MaoVermell(game,x1,y1,2,'r',true);
					maons.add(mao1);
					//Augmentem la X per que es vaguin movent cap a la dreta de la posició inicial
					x1=x1+70;
					//Si ja hem creat els 10 maons, per que els seguents maons es fiquin en la posicio 5, li asignem a la X el valor de la constant.
					if(i==19)
					{
						x1=cons;
					}
				}
				else{
					//10 maons de color verd
					if(i>19 && i<30)
					{
						//Li diem que la Y es 120 per que els creei a la mateixa linea
						int y1 = 120;
						MaoVerd mao2 = new MaoVerd(game,x1,y1,3,'v',true);
						maons.add(mao2);
						//Augmentem la X per que es vaguin movent cap a la dreta de la posició inicial
						x1=x1+70;
					}
				}
			}
		}
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}

	//Metode que ens pintara els maons per pantalla
	public void pintarElsMaonets(Graphics2D g2d)
	{
		for(int i=0; i<maons.size();i++)
		{
			maons.get(i).paint(g2d);
		}
	}
}