

import java.awt.*;

public class Ball {
	private static final int DIAMETER = 15;

	int x = 0;
	int y = 300;
	int xa = 1;
	int ya = -2;
	private Game game;
	Racquet racquet = new Racquet(game);
	public Ball(Game game) {
		this.game = game;
	}

	//Metode que fara les accions per moure la pilota
	void move() {
		boolean changeDirection = true;
		if (x + xa < 0)
		{
			xa = 2;
		}
		else {
			if (x + xa > game.getWidth() - DIAMETER)
			{
				xa = -2;
			}
			else{
				if (y + ya < 0)
				{
					ya = 2;
				}
				else{
					if (y + ya > game.getHeight() - DIAMETER)
					{
						game.vidas--;
						x = 0;
						y = 300;
						ya = -2;
						if(game.getVidas()==0)
						{
							game.gameOver();
						}
					}
					else {
						if (collision()){
							ya = -2;
							y = game.racquet.getTopY() - DIAMETER;
						} else{
							changeDirection = false;
						}
					}
				}
			}
		}
		if (changeDirection)
			Sound.BALL.play();
		x = x + xa;
		y = y + ya;

		//Metode que si la pilota colisiona amb el mao, fa una acció establerta dins del seguent bucle for
		boolean sort =false;

		for(int i =0; i<game.maons.size() && sort!=true;i++)
		{
			int auxpos=-1;
			//Si el mao ha sigut colisionat i encara te el atribut tocable en true(significara que encara es pot colisionar amb ell),
			//es modifica la direccio de la pilota
			if (collisionMaons(i)&&game.maons.get(i).getTocable()==true){
				Sound.BALL.play();
				ya = -ya;
				//Li restem una vida al mao que ha colisionat
				game.maons.get(i).setVida(game.maons.get(i).getVida()-1);
				//Si el mao que ha colisionat no te vides, procedim a mirar quin ha sigut
				if(game.maons.get(i).getVida()==0)
				{
					//Ara el mao al no tenir vides, farem que no sigui colisionable
					game.maons.get(i).setTocable(false);
					//Mirem quin tipus de mao a tocat dels 3
					switch (game.maons.get(i).getTipus())
					{
						//Si es a de AZUL, augmentem puntuació, enviem la posició per veure si l'hem de moure a la classe principal GAME,
						//i augmentem la velocitat de la pala
						case 'a':
							game.setPos(i);
							game.speed++;
							game.puntuacio++;
							break;
						//En cas de que sigui r de ROJO, pasem la posicio ala classe principal GAME per veure si l'hem de moure,
						//fem que ja no busqui mes colisions al bucle on estem per que no interfereixi amb la caiguda del mao si es vermell ficant
						//la variable sort a true, augemnte puntuació, y li assignem a la variable auxpos la i, per mes endevant
						//quan volguem borrar un mao, mirem si es vermell o no.
						case 'r':
							game.setPos(i);
							auxpos=i;
							sort=true;
							game.puntuacio++;
							break;
						//Si es v de Verde, pasem posició ala classe principal GAME, per veure si mourel i augmentem puntuació
						case 'v':
							game.setPos(i);
							game.puntuacio++;
							break;
					}
					//Si la posició es -1, no es vermell, aixi que l'eliminem desde aquí.
					if(auxpos==-1)
					{
						game.maons.remove(i);
					}
				}
				//Tornem a pintar els maons que tenim al arraylist de maons
				game.pintarElsMaonets(game.g2d);
			}
		}
		//Si ja no queden maons, hem guanyat la partida, aixi que cridem al metode de la victoria
		if(game.maons.size()==0)
		{
			game.victoria();
		}
	}
	//Metode que detectara la colissio dels maons amb la pilota
	private boolean collisionMaons(int i) {
		return game.maons.get(i).getBounds().intersects(getBounds());
	}
	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.red);
		g.fillOval(x, y, DIAMETER, DIAMETER);


	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);

	}
}