/**
 * @author ndieks
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Panda extends Rectangle {

	public boolean leftSide = true;
	public Bamboo bamboo;

	public Panda() {

	}

	public Panda(Bamboo bamboo) {
		this.x = 50;
		this.y = bamboo.y + bamboo.width;
		this.width = 30;
		this.height = 40;
		this.bamboo = bamboo;
		this.bamboo.hasPanda = true;
	}

	public void moveDown() {
		this.y += 2;
	}
	public void moveUp() {
		this.y -= 2;
	}
	/**
	 * moves Panda to the left side of Bamboo if it not already on the left side
	 */
	public void moveLeft() {
		if (this.leftSide && bamboo.myPosition != 1) {
			// System.out.println("Jumping from Pole at POS:"+bamboo.myPosition+" to Left ");
			this.leftSide = false;

			this.x = (PandaClone.bamboos
					.get(leftBamboo(this.bamboo.myPosition)).x + 80);// +this.width
			this.bamboo.hasPanda = false;
			this.bamboo = PandaClone.bamboos
					.get(leftBamboo(this.bamboo.myPosition));
			this.bamboo.hasPanda = true;
			// System.out.println("Jumped Poles to Pole at pos:"+bamboo.myPosition);
			return;// jump to left pole
		} else if (!leftSide) {
			this.leftSide = !this.leftSide;
			// System.out.println("Moved to Left");
			this.x -= (bamboo.width + this.width);
		} else {
			return;
		}

	}
	public void moveRight() {
		if (!this.leftSide && bamboo.myPosition != 3) {

			// System.out.println("Jumping from Poles at POS:"+bamboo.myPosition+" to Right ");
			// System.out.println("bamboos size: "+PandaClone.bamboos.size());

			this.leftSide = true;
			this.x = PandaClone.bamboos
					.get(rightBamboo(this.bamboo.myPosition)).x - this.width;
			this.bamboo.hasPanda = false;
			this.bamboo = PandaClone.bamboos
					.get(rightBamboo(this.bamboo.myPosition));
			this.bamboo.hasPanda = true;
			// System.out.println("Jumped Poles to Pole at pos:"+bamboo.myPosition);

			return; // jump to right pole
		} else if (leftSide) {

			this.leftSide = !this.leftSide;
			// System.out.println("Moved to Right");
			this.x += (bamboo.width + this.width);
		} else {
			return;
		}
	}

	public int leftBamboo(int pos) {
		switch (pos) {
			case 3 :
				return 2 - 1;
			case 2 :
				return 1 - 1;
			case 1 :
				return 0;
			default :
				return 0;
		}

	}
	public int rightBamboo(int pos) {
		switch (pos) {
			case 1 :
				return 2 - 1;

			case 2 :
				return 3 - 1;

			case 3 :
				return 0;
			default :
				return 0;
		}

	}

	public void paint(Graphics2D graphics2d) {
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillRect(this.x, this.y, this.width, this.height);
		graphics2d.setColor(Color.BLACK);
		graphics2d.drawRect(this.x, this.y, this.width, this.height);

	}

	public double getX() {
		return this.x;
	}

}
