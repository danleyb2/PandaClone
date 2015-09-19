/**
 * @author Ndieks
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class PandaClone extends JPanel {
	// todo:lots of static variable.. is this a good idea???
	public static final int CANVAS_WIDTH = 640;
	public static final int CANVAS_HEIGHT = 480;
	public static final String TITLE = "Panda Clone";
	public static Thread animationThread;
	public static PandaClone pandaClone;
	public static Runnable runnable;
	public static int score = 0;
	public static boolean gameOver, gamePaused;
	public static JFrame frame = new JFrame(TITLE);
	public static Panda panda;
	public static ArrayList<Bamboo> bamboos;// =new ArrayList<Bamboo>();

	public Random random;

	public PandaClone() {
		bamboos = new ArrayList<Bamboo>();
		animationThread = new Thread() {
			@Override
			public void run() {
				System.out.println("Running");
				while (true) {
					update();// update positions
					repaint();// refresh display
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
					}
				}
			}

		};

		animationThread.start();// start thread to run the animation
		setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
	}

	public void update() {
		// System.out.println("updating game");

		// do nothing if game over
		if (gameOver) {
			return;
		}

		// do nothing if game pause either.. ^i could put this two together
		if (gamePaused) {
			return;
		}

		// move up all bugs
		for (Bamboo bamboo : bamboos) {
			for (Bug bug : bamboo.bugs) {

				if (bug.y < (bug.height * -1)) {
					// remove all bugs that are not visible
					// TODO: i need a better bug creation algorithm

					// 1 /*Bug creation algorithm 1*/ //reposition bug y pos
					bug.y += bamboo.getLastBug().y + bug.height
							+ bamboo.bugSpace;
					// System.out.println("bug repositioned");

					//sets new bug on the opposite side
					// bug.leftSide=!bug.leftSide;

					// a new random side.. i'l go with this
					bug.leftSide = new Random().nextBoolean();
					bamboo.setLastBug(bug);

					// 2 /*Bug creation algorithm 2*/ //remove bugs and create
					// new ones /*INCOMPLETE*/
					// bamboo.removeBug(bug); //or set the bug to null
					// bamboo.addBug();//

					score++;

					// bamboo.addBug();

				} else {
					bug.moveUp();
				}
			}
		}

		// is the Panda dead???
		for (Bamboo bamboo : bamboos) {

			// System.out.println("Checking a bamboo");
			if (bamboo.hasPanda) {
				// System.out.println("Bamboo has Panda");
				for (Bug bug : bamboo.bugs) {

					/* GAME OVER CONDITIONS */

					if (
					// if Bug intersects Panda :) bug meets the Panda
					 bug.intersects(panda)  ||
					// panda.y<0 :) Panda hits head on the roof
					panda.y < 0

					) {
						// game over
						System.out.println("Game over SCORE :" + score);
						gameOver = true;
						// animationThread.stop();
					} else {

					}
				}
				break;// no need for checking other poles
			} else {
				continue;
			}

		}

	}
	@Override
	public void paintComponent(Graphics g) {
		if (gamePaused && !gameOver) {
			// don't distort the screen (don't paint anything).
			// even though nothing is changing
			// just saving resources :)
			return;
		}
		super.paintComponent(g);
		setBackground(Color.WHITE);
		Graphics2D graphics2d = (Graphics2D) g;
		if (gameOver) {// paint only the score then return

			setBackground(Color.BLACK);
			super.paintComponent(g);
			g.setColor(Color.YELLOW);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
			FontMetrics fm = g.getFontMetrics();

			String msg = "Game Over !!";
			String msg3 = "Score : " + score + ".";
			String msg2 = " Press Space Bar";

			int msgWidth = fm.stringWidth(msg);
			int msgAscent = fm.getAscent();
			int msgX = getWidth() / 2 - msgWidth / 2;
			int msgY = getHeight() / 2 + msgAscent / 2;

			g.drawString(msg, msgX, msgY - 60);
			g.drawString(msg3, (getWidth() / 2 - fm.stringWidth(msg3) / 2),
					msgY - 30);
			g.drawString(msg2, (getWidth() / 2 - fm.stringWidth(msg2) / 2),
					msgY);

			setBackground(Color.WHITE);// reset background color

			return;// paint score
		}

		// paint Bamboos
		// System.out.println("Painting Bamboos");
		for (Bamboo bamboo : bamboos) {
			// System.out.println(bamboo.myPosition);
			if (bamboo.myPosition == 2) {
				// continue;
			}

			bamboo.paint(graphics2d);

		}

		// paint poles
		// System.out.println("Painting Poles");
		for (Bamboo bamboo : bamboos) {
			// bamboo.paint(graphics2d);
			for (Bug bug : bamboo.bugs) {
				bug.paint(graphics2d);
			}
		}

		// paint Panda
		// System.out.println("Painting Panda");
		panda.paint(graphics2d);

	}

	public void startGame() {
		// reset game

		gameOver = false;
		gamePaused = false;
		score = 0;
		if (bamboos.size() > 0) {
			Bamboo.position = 0;
			bamboos.clear();
		}

		// add the three bamboo trees
		bamboos.add(new Bamboo(80));
		bamboos.add(new Bamboo(260));
		bamboos.add(new Bamboo(450));

		// create panda on bamboo at index 0
		panda = new Panda(bamboos.get(0));

		// animationThread.start();
		// System.out.println( Thread.currentThread().getPriority());
		// end reset game
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/* Runnable */
		runnable = new Runnable() {
			public void run() {
				pandaClone = new PandaClone();
				frame.setContentPane(pandaClone);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();

				pandaClone.startGame();

				// center app window. woo!! i didn't know this till recently
				frame.setLocationRelativeTo(null);
				frame.addKeyListener(new GameKeyAdapter());
				frame.setVisible(true);
				frame.setAlwaysOnTop(true);

			}
		};
		// runnable.run();
		SwingUtilities.invokeLater(runnable);

	}

}
