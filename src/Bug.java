/**
 * @author ndieks
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Bug extends Rectangle {

	public Random random = new Random();
	public boolean leftSide = false;

	public Bug(int x) {
		this.y = PandaClone.CANVAS_HEIGHT;
		this.x = x;
		this.width = 20;
		this.height = 30;

	}
	public Bug(Bamboo bamboo) {
		this.width = 20;
		this.height = 30;

		this.y = PandaClone.CANVAS_HEIGHT;

		leftSide = random.nextBoolean();
		if (leftSide) {
			this.x = bamboo.x - this.width;
		} else {
			this.x = bamboo.x + bamboo.width;
		}

	}

	public Bug(Bamboo bamboo, int space) {
		this.width = 20;
		this.height = 30;

		this.y = space;
		leftSide = random.nextBoolean();
		if (leftSide) {
			this.x = bamboo.x - this.width;
		} else {
			this.x = bamboo.x + bamboo.width;
		}

	}

	public void moveUp() {
		this.y--;
	}

	public Bug newBug() {
		return null;
		// return new Bug();
	}

	public void paint(Graphics2D graphics2d) {
		graphics2d.setColor(Color.RED);
		graphics2d.fillRect(this.x, this.y, this.width, this.height);
		graphics2d.setColor(Color.BLACK);
		graphics2d.drawRect(this.x, this.y, this.width, this.height);
	}
}
