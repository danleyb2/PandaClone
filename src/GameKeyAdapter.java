/**
 * @author ndieks
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class GameKeyAdapter extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		// System.err.println("Key pressed");
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT :
				if (!PandaClone.gamePaused) { // ignore key events if game is
												// paused except space bar
					PandaClone.panda.moveLeft();
				}
				break;
			case KeyEvent.VK_RIGHT :
				if (!PandaClone.gamePaused)
					PandaClone.panda.moveRight();
				break;
			case KeyEvent.VK_UP :
				if (!PandaClone.gamePaused)
					PandaClone.panda.moveUp();
				break;
			case KeyEvent.VK_DOWN :
				if (!PandaClone.gamePaused)
					PandaClone.panda.moveDown();
				break;
			case KeyEvent.VK_SPACE :
				if (PandaClone.gameOver) {
					System.out.println("Restarting game");
					// restart game
					PandaClone.pandaClone.startGame();
					// System.out.println(PandaClone.bamboos.size());
				} else {
					if (!PandaClone.gamePaused) {
						// pause game
						PandaClone.gamePaused = true;
						System.out.println("Game paused");
					} else {
						// resume game
						PandaClone.gamePaused = false;
					}
				}

				break;
		}

	}

}
